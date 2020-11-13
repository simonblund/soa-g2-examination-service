#FROM gradle:6.7.0-jdk11 as builder
#WORKDIR /home/gradle
## Copy in dependency and build instructions
#COPY ./api/build.gradle ./api/build.gradle
#COPY ./service/build.gradle ./service/build.gradle
#
## Copy main project and settings.gradle
#COPY ./build.gradle ./build.gradle
#COPY ./settings.gradle ./settings.gradle
#RUN gradle --info build
#
## Copy in your source files
#COPY ./api/src /home/gradle/api/src
#COPY ./service/src /home/gradle/service/src
#
## Make the wrapper for use in the execution environment.
#RUN gradle --no-daemon wrapper
## Build the project
#RUN gradle --no-daemon build
##RUN gradle --no-daemon --info build # extra debug



# === The execution ===
FROM adoptopenjdk/openjdk11@sha256:18a90fe4c1b4140ce960294edb05c9ab5113fd4868a2c06b885c33db3bf99ab3
#FROM adoptopenjdk/openjdk11:jre-11.0.9_11.1-alpine
LABEL maintainer="weleoka@gitlab.com"
WORKDIR /home/spring

# Added for documentation purposes
ENV SS_PORT 8080
EXPOSE 8080

# If using multi-stage dockerfile, then need the bootJar and dependencies.
# Using the bash script or commandline for building and to enable local persistent Gradle cache then
# this is not needed.
#COPY --from=builder /home/gradle ./


# Create the group and user
RUN addgroup spring && adduser --system spring
# Own all by spring user.
RUN chown -R spring:spring /home/spring


# === BEGIN Gradle Wrapper ===
# It may be a good idea to run the project with gradle which is what this section
# allows you to do. The alternative is to operate with java directly on a jar... which is often absolutely fine!


# Copy in the gradle wrapper stuff
#COPY --chown=spring:spring ./gradlew ./gradlew
#COPY --chown=spring:spring ./settings.gradle ./settings.gradle
#COPY --chown=spring:spring ./gradle ./gradle


# The problem of the Gradle binary file.
# Warning: this needs to be run as spring user to actually work.
#USER spring:spring
# Load in https://services.gradle.org/distributions/gradle-6.6.1-bin.zip to /home/spring/.gradle/wrapper/dists/
# ENV GRADLE_USER_HOME=~/.gradle # this is the default.
# echo "$GRADLE_USER_HOME/wrapper/dists" # this is where the gradle binary is saved.
#RUN ./gradlew --no-daemon init
#USER root:root


# Copy in the build.gradle files if we want to use the wrapper
#COPY --chown=spring:spring ./build.gradle ./build.gradle
#COPY --chown=spring:spring ./service/build.gradle ./service/build.gradle
#COPY --chown=spring:spring ./api/build.gradle ./api/build.gradle
# === END Gradle Wrapper

# Copy in the compiled Jars.
#COPY ./build/libs ./build/libs
#COPY --chown=spring:spring ./service/build/libs ./service/build/libs
## Note: This is OK with Spring as we always get one singe mega-jar... if the product of build was
## multiple jars this may pose a problem.
COPY --chown=spring:spring ./build/my_artifacts/*.jar ./app.jar
#COPY --chown=spring:spring ./api/build/libs ./api/build/libs


# Own all by spring user. (Just to make sure)
RUN chown -R spring:spring /home/spring
# Set the active user.
USER spring:spring



# Copy in the Jars and artifacts from extracted multilayer Jar.
# Todo: implement this to enable effective Docker caching and fast reloads of project.
#COPY ./build/dependencies/ ./
#COPY ./build/snapshot-dependencies/ ./
#COPY ./build/spring-boot-loader/ ./
#COPY ./build/resources/ ./ # This doesn't exist from Gradle build of the layered fat jar.
#COPY ./build/application ./


# Run the process. This can be overriden if using DC to start the container.
#ENTRYPOINT ["./gradlew", ":service:bootRun"]
#ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
#ENTRYPOINT ["java", "-jar", "./service/build/libs/service-1.0.null.jar"]
ENTRYPOINT ["java", "-jar", "app.jar"]
