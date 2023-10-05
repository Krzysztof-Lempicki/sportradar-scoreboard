package kkl.interview.sportradar.scoreboard;

import kkl.interview.sportradar.scoreboard.internal.mapper.DefaultFootballMatchMapper;
import kkl.interview.sportradar.scoreboard.internal.mapper.FootballMatchMapper;
import kkl.interview.sportradar.scoreboard.internal.repository.ConcurrentHashMapFootballMatchRepository;
import kkl.interview.sportradar.scoreboard.internal.repository.FootballMatchRepository;
import kkl.interview.sportradar.scoreboard.internal.service.DefaultScoreboardMatchService;
import kkl.interview.sportradar.scoreboard.internal.service.DefaultScoreboardMatchSummaryService;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.NONE;

@NoArgsConstructor(access = NONE)
public final class ScoreboardFacade {

    private static final FootballMatchRepository repository = new ConcurrentHashMapFootballMatchRepository();
    private static final FootballMatchMapper footballMatchMapper = new DefaultFootballMatchMapper();

    @Getter
    private final static ScoreboardMatchSummaryService scoreboardMatchSummaryService =
            new DefaultScoreboardMatchSummaryService();

    @Getter
    private final static ScoreboardMatchService scoreboardMatchService =
            new DefaultScoreboardMatchService(repository, footballMatchMapper);
}
