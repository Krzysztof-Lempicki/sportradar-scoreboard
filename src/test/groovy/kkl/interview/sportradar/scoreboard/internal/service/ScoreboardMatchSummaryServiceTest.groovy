package kkl.interview.sportradar.scoreboard.internal.service

import kkl.interview.sportradar.scoreboard.external.ScoreboardMatchSummaryService
import kkl.interview.sportradar.scoreboard.internal.mapper.DefaultFootballMatchMapper
import kkl.interview.sportradar.scoreboard.internal.repository.ConcurrentHashMapFootballMatchRepository
import spock.lang.Specification

import static kkl.interview.sportradar.scoreboard.internal.MatchTestConstraints.*

class ScoreboardMatchSummaryServiceTest extends Specification {

    private ConcurrentHashMapFootballMatchRepository repository = new ConcurrentHashMapFootballMatchRepository()

    private ScoreboardMatchSummaryService matchSummaryService = new DefaultScoreboardMatchSummaryService(
            repository,
            new DefaultFootballMatchMapper()
    )

    def 'should return empty list when there is no matches'() {
        when:
        def result = matchSummaryService.getMatchInProgressSummary()

        then:
        result.isEmpty()
    }

    def 'should return matches in progress ordered by total score and recently started time'() {
        given: 'test matches'
        repository.storage.put(MATCH_2001_01_01_BETWEEN_CH_AND_CA_WITH_ZERO_ZERO.getId(), MATCH_2001_01_01_BETWEEN_CH_AND_CA_WITH_ZERO_ZERO)
        repository.storage.put(MATCH_2001_01_02_BETWEEN_ST_AND_TT_WITH_ZERO_ZERO.getId(), MATCH_2001_01_02_BETWEEN_ST_AND_TT_WITH_ZERO_ZERO)
        repository.storage.put(MATCH_2001_01_02_BETWEEN_TT_AND_CA_WITH_NINE_NINE.getId(), MATCH_2001_01_02_BETWEEN_TT_AND_CA_WITH_NINE_NINE)
        repository.storage.put(MATCH_2001_01_03_BETWEEN_FT_AND_CA_WITH_THEN_SEVEN.getId(), MATCH_2001_01_03_BETWEEN_FT_AND_CA_WITH_THEN_SEVEN)
        repository.storage.put(MATCH_2001_01_01_BETWEEN_FT_AND_ST_WITH_THEN_SEVEN.getId(), MATCH_2001_01_01_BETWEEN_FT_AND_ST_WITH_THEN_SEVEN)

        and: 'expected order'
        def expectedOrderedIds = List.of(
                MATCH_2001_01_02_BETWEEN_TT_AND_CA_WITH_NINE_NINE.getId(),
                MATCH_2001_01_03_BETWEEN_FT_AND_CA_WITH_THEN_SEVEN.getId(),
                MATCH_2001_01_01_BETWEEN_FT_AND_ST_WITH_THEN_SEVEN.getId(),
                MATCH_2001_01_02_BETWEEN_ST_AND_TT_WITH_ZERO_ZERO.getId(),
                MATCH_2001_01_01_BETWEEN_CH_AND_CA_WITH_ZERO_ZERO.getId()
        )

        when:
        def result = matchSummaryService.getMatchInProgressSummary()

        then:
        expectedOrderedIds.size() == result.size()

        and:
        for (int i = 0 ; i< expectedOrderedIds.size() ; i++) {
            assert result.get(i).id() == (expectedOrderedIds.get(i))
        }
    }
}
