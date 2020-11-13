import spock.lang.Specification

class StudentServiceTest extends Specification {


    def "first Test ever"() {
        given:
        def snask = "snask"

        when:
        1+1
        then:
        snask == snask
    }
}
