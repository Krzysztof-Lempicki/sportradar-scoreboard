package kkl.interview.sportradar.scoreboard.internal.repository

import spock.lang.Specification

class FootballMatchRepositoryTest extends Specification{

    private FootballMatchRepository repository = new ConcurrentHashMapFootballMatchRepository()

    def 'should not accept undefined data for save operation' () {
        when:
        repository.save(null)

        then:
        thrown(NullPointerException)
    }
}
