package kkl.interview.sportradar.scoreboard.internal.service

import kkl.interview.sportradar.scoreboard.ScoreboardMatchService
import kkl.interview.sportradar.scoreboard.dto.StartNewMatchDto
import kkl.interview.sportradar.scoreboard.dto.UpdateMatchScoreDto
import kkl.interview.sportradar.scoreboard.internal.domain.FootballMatch
import kkl.interview.sportradar.scoreboard.internal.mapper.DefaultFootballMatchMapper
import kkl.interview.sportradar.scoreboard.internal.repository.ConcurrentHashMapFootballMatchRepository
import kkl.interview.sportradar.scoreboard.internal.repository.FootballMatchRepository
import spock.lang.Specification

import static kkl.interview.sportradar.scoreboard.internal.AppConstants.ZERO
import static kkl.interview.sportradar.scoreboard.internal.MatchTestConstraints.CORRECT_AWAY_TEAM_NAME
import static kkl.interview.sportradar.scoreboard.internal.MatchTestConstraints.CORRECT_HOME_TEAM_NAME
import static kkl.interview.sportradar.scoreboard.internal.TestConstraints.ONE

class ScoreboardMatchServiceTest extends Specification {

    private static short TWO_GOALS = 2

    private FootballMatchRepository repository = new ConcurrentHashMapFootballMatchRepository()
    private ScoreboardMatchService scoreboardMatchService = new DefaultScoreboardMatchService(repository,
            new DefaultFootballMatchMapper())

    def 'should create and save new match with default zero-zero score'() {
        given:
        def dto = new StartNewMatchDto(CORRECT_HOME_TEAM_NAME, CORRECT_AWAY_TEAM_NAME)

        when:
        def result = scoreboardMatchService.startNewFootballMatch(dto)

        then:
        repository.storage.size() == ONE

        and:
        result.homeTeamName() == CORRECT_HOME_TEAM_NAME
        result.awayTeamName() == CORRECT_AWAY_TEAM_NAME

        and:
        result.awayTeamScore() == ZERO
        result.homeTeamScore() == ZERO
    }

    def 'should not accept undefined data for creating new match'() {
        when:
        scoreboardMatchService.startNewFootballMatch(null)

        then:
        thrown(NullPointerException)
    }

    def 'should not accept undefined data for updating match score' () {
        when:
        scoreboardMatchService.updateFootballMatchScore(null)

        then:
        thrown(NullPointerException)
    }

    def 'should update score of match if data is correct' () {
        given:
        def match = new FootballMatch(CORRECT_HOME_TEAM_NAME,
                                    CORRECT_AWAY_TEAM_NAME,
                                    ZERO,
                                    ZERO)

        and:
        repository.save(match)

        and:
        def dtoWithUpdatedScore = new UpdateMatchScoreDto(match.getId(),
                                                          ZERO,
                                                          TWO_GOALS)

        when:
        scoreboardMatchService.updateFootballMatchScore(dtoWithUpdatedScore)

        then:
        match.getAwayTeamScore() == TWO_GOALS
    }
}
