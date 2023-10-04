package kkl.interview.sportradar.scoreboard;

import kkl.interview.sportradar.scoreboard.internal.domain.FootballMatch;
import kkl.interview.sportradar.scoreboard.internal.mapper.DefaultFootballMatchMapper;
import kkl.interview.sportradar.scoreboard.internal.mapper.FootballMatchMapper;
import kkl.interview.sportradar.scoreboard.internal.repository.FootballMatchRepository;
import kkl.interview.sportradar.scoreboard.internal.service.DefaultScoreboardMatchService;
import kkl.interview.sportradar.scoreboard.internal.service.DefaultScoreboardMatchSummaryService;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static kkl.interview.sportradar.scoreboard.internal.AppConstants.ZERO;
import static lombok.AccessLevel.NONE;

@NoArgsConstructor(access = NONE)
public final class ScoreboardFacade {
    @Deprecated // will be implemented in next commit
    private static final FootballMatchRepository repository = (match) -> {
        System.out.println("WARN - mock NewFootballMatchPolicy used");
        return new FootballMatch("mocked team name", "mocked team name",ZERO, ZERO );
    };
    private static final FootballMatchMapper footballMatchMapper = new DefaultFootballMatchMapper();

    @Getter
    private final static ScoreboardMatchSummaryService scoreboardMatchSummaryService =
            new DefaultScoreboardMatchSummaryService();

    @Getter
    private final static ScoreboardMatchService scoreboardMatchService =
            new DefaultScoreboardMatchService(repository, footballMatchMapper);
}
