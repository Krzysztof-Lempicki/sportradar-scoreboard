package kkl.interview.sportradar.scoreboard.internal.repository

import kkl.interview.sportradar.scoreboard.external.exception.MatchNotFoundException
import kkl.interview.sportradar.scoreboard.internal.domain.FootballMatch
import spock.lang.Specification

import static kkl.interview.sportradar.scoreboard.internal.AppConstants.ZERO
import static kkl.interview.sportradar.scoreboard.internal.MatchTestConstraints.CORRECT_AWAY_TEAM_NAME
import static kkl.interview.sportradar.scoreboard.internal.MatchTestConstraints.CORRECT_HOME_TEAM_NAME
import static kkl.interview.sportradar.scoreboard.internal.MatchTestConstraints.DATE_TIME_2001_01_01

class FootballMatchRepositoryTest extends Specification{

    public static final UUID NOT_EXISTING_ID = UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455")
    private FootballMatchRepository repository = new ConcurrentHashMapFootballMatchRepository()

    def 'should not accept undefined data for save operation' () {
        when:
        repository.save(null)

        then:
        thrown(NullPointerException)
    }

    def 'should rise exception if match with given id does not exist' () {
        when:
        repository.findWithId(NOT_EXISTING_ID)

        then:
        def exception = thrown(MatchNotFoundException)

        and:
        exception.getId() == NOT_EXISTING_ID
    }

    def 'should not accept undefined data for as searched match id' () {
        when:
        repository.findWithId(null)

        then:
        thrown(NullPointerException)
    }

    def 'should return match if it was persisted in repository' () {
        given:
        def match = new FootballMatch(CORRECT_HOME_TEAM_NAME,
                CORRECT_AWAY_TEAM_NAME,
                ZERO,
                ZERO,
                DATE_TIME_2001_01_01)

        and:
        repository.save(match)

        when:
        def result = repository.findWithId(match.getId())

        then:
        result == match
    }

    def 'should not accept undefined id for deletion of match' () {
        when:
        repository.delete(null)

        then:
        thrown(NullPointerException)
    }

    def 'should delete match from repository' () {
        given:
        def match = new FootballMatch(CORRECT_HOME_TEAM_NAME,
                CORRECT_AWAY_TEAM_NAME,
                ZERO,
                ZERO,
                DATE_TIME_2001_01_01)

        and:
        def savedMatch = repository.save(match)

        when:
        repository.delete(savedMatch.getId())

        then:
        repository.storage.isEmpty()
    }

    def 'should not rise exception even if match for deletion does not exist' () {

        when:
        repository.delete(NOT_EXISTING_ID)

        then:
        notThrown(Exception)
    }
}
