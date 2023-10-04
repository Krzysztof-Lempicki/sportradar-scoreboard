package kkl.interview.sportradar.scoreboard.internal.service

import kkl.interview.sportradar.scoreboard.ScoreboardMatchService
import kkl.interview.sportradar.scoreboard.dto.StartNewMatchDto
import kkl.interview.sportradar.scoreboard.internal.domain.FootballMatch
import kkl.interview.sportradar.scoreboard.internal.mapper.DefaultFootballMatchMapper
import kkl.interview.sportradar.scoreboard.internal.repository.FootballMatchRepository
import spock.lang.Specification

import static kkl.interview.sportradar.scoreboard.internal.AppConstants.ZERO
import static kkl.interview.sportradar.scoreboard.internal.MatchTestConstraints.CORRECT_AWAY_TEAM_NAME
import static kkl.interview.sportradar.scoreboard.internal.MatchTestConstraints.CORRECT_HOME_TEAM_NAME
import static kkl.interview.sportradar.scoreboard.internal.TestConstraints.WAS_CALLED_ONCE

class ScoreboardMatchServiceTest extends Specification {

    private final FootballMatchRepository mockRepository = Mock();
    private ScoreboardMatchService scoreboardMatchService = new DefaultScoreboardMatchService(mockRepository,
            new DefaultFootballMatchMapper());

    def 'should create and save new match with default zero-zero score'() {
        given:
        def dto = new StartNewMatchDto(CORRECT_HOME_TEAM_NAME, CORRECT_AWAY_TEAM_NAME)

        when:
        def result = scoreboardMatchService.startNewMatch(dto)

        then:
        WAS_CALLED_ONCE * mockRepository.save(_ as FootballMatch) >> { it[0] }

        and:
        result.homeTeamName() == CORRECT_HOME_TEAM_NAME
        result.awayTeamName() == CORRECT_AWAY_TEAM_NAME

        and:
        result.awayTeamScore() == ZERO
        result.homeTeamScore() == ZERO
    }

    def 'should not accept undefined data'() {
        when:
        scoreboardMatchService.startNewMatch(null)

        then:
        thrown(NullPointerException)
    }
}
