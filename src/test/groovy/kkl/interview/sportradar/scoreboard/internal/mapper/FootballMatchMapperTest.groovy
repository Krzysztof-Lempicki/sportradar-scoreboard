package kkl.interview.sportradar.scoreboard.internal.mapper


import kkl.interview.sportradar.scoreboard.internal.domain.FootballMatch
import spock.lang.Specification

import static kkl.interview.sportradar.scoreboard.internal.AppConstants.ZERO
import static kkl.interview.sportradar.scoreboard.internal.MatchTestConstraints.CORRECT_AWAY_TEAM_NAME
import static kkl.interview.sportradar.scoreboard.internal.MatchTestConstraints.CORRECT_HOME_TEAM_NAME

class FootballMatchMapperTest extends Specification {

    private FootballMatchMapper mapper = new DefaultFootballMatchMapper();

    def 'should correctly map data'() {
        given:
        def match = new FootballMatch(CORRECT_HOME_TEAM_NAME,
                CORRECT_AWAY_TEAM_NAME,
                ZERO,
                ZERO
        )

        when:
        def result = mapper.map(match)

        then:
        result.id() == match.getId()
        result.homeTeamName() == match.getHomeTeamName()
        result.awayTeamName() == match.getAwayTeamName()
        result.homeTeamScore() == match.getAwayTeamScore()
        result.awayTeamScore() == match.getAwayTeamScore()
    }

    def 'should not accept undefined data'() {
        when:
        mapper.map(null)

        then:
        thrown(NullPointerException)
    }
}
