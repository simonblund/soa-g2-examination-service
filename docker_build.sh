#!/bin/bash
# Gradle build in Docker with persistent Gradle cache.
#
# author: Weleoka <weleoka@mailfence.com>
# edited: 2020-11-09
#
# Creates a persistent gradle cache for the build container here in the project /build dir.
# Because Gradle is "installed" for a normal user in the official Gradle docker images we have to work around that.
# On the development machine the cache will be saved in project_dir/build/gradle_build_cache.
#
# This script is here because Gradle does not allow a neat and effective way to fetch dependencies and host them locally.
#
# Mistakes made when in the process of arriving at this script:
# - tried to use volumes in a multistage Dockerfile for early targets
# - tried to mount the modules-2 folder as a volume but that made the parent dir owned by root,
#     so sister files to modules-2 could not be created
#
set -euo pipefail
script="`realpath "$0"`"
project_dir="`dirname "$script"`"
# Gradle Build Image name
gbi_name="my-gbi"
# Gradle dependencies cache:
gd_cache_dir="${project_dir}/build/my_gradle_cache"
# Artifacts saved to host:
artifacts_dir="${project_dir}/build/my_artifacts"
# This is the problem... the Gradle process builds the Jar to an explicit dir depending on java module:
container_artifacts_dir="/home/gradle/service/build/libs"

echo
echo "== Building Gradle project: ${project_dir} in a Docker container."

echo "> Create persistent build cache directory on host."
mkdir -p "$gd_cache_dir"

echo "> Create artifacts storage directory on host."
mkdir -p "$artifacts_dir"

# Note added this as removing classes in source code was not reflected
# in the extracted archive contents after build. Due to class autoloading
# old classes appear again and again.
echo "> (disabled) Removing build/application and build/classes folder to ensure clean build."
# This is disabled as we do not use multilayer Jars yet."
#rm -r "${project_dir}/build/application"
#rm -r "${project_dir}/build/classes"

echo "> Prepare the build image..."
#docker build --build-arg UID=$(id -u) --build-arg GID=$(id -g) -f Dockerfile_build -t my-gbi .
# similar to:
docker build \
--build-arg UID=$(id -u) \
--build-arg GID=$(id -g) \
-f Dockerfile_build \
-t "$gbi_name" .

echo "> Build artifacts in build container..."

#docker run -it --name gradle_builder --rm -v $(pwd)/build/my_gradle_cache:/home/gradle/caches -v $(pwd)/build/my_artifacts:/home/gradle/service/build/libs my-gbi:latest bash
#docker run -u root -it --name gradle_builder --rm -v $(pwd)/build/my_gradle_cache:/home/gradle/caches -v $(pwd)/build/my_artifacts:/home/gradle/service/build/libs my-gbi:latest bash
# similar to:
docker run --rm \
--name gradle_builder \
-v "$gd_cache_dir":/home/gradle/caches \
-v "$artifacts_dir":"$container_artifacts_dir" \
"$gbi_name"
# This is now moved to the entrypoint.sh file.
#/bin/bash -c "gradle -g /home/gradle --quiet --no-daemon build"
# -v "$project_dir":/home/gradle \
#/bin/bash -c "gradle --quiet --no-daemon bootJar"
#/bin/bash

# Todo: multilayer jars, so run-container can have optimised docker caching.
#/bin/bash -c "gradle --quiet --no-daemon bootJar && cd build && java -Djarmode=layertools -jar ./libs/*.jar extract"
#/bin/bash -c "gradle --quiet --no-daemon bootJar && cd service/build && java -Djarmode=layertools -jar ./libs/*.jar extract"
# todo: the extracting of multilayer jar is not very neat as we have to cd to ./build to extract to build.
# todo: Q: what's the --build-cache flag for gradle?
# todo: Q: the --no-daemon flag for gradle? Might be important for parallel running,
#   as per: https://dev.to/markomannux/gradle-daemon-with-multi-module-spring-project-3nog

#echo "> Remove *.lock files in persistent build cache."
#rm -r "$project_dir"/build/docker_build_cache/modules-2/*.lock 2> /dev/null


echo "> Done!"
echo
