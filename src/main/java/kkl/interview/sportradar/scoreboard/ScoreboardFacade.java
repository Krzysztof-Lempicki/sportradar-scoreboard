package kkl.interview.sportradar.scoreboard;

import kkl.interview.sportradar.scoreboard.internal.mapper.DefaultFootballMatchMapper;
import kkl.interview.sportradar.scoreboard.internal.mapper.FootballMatchMapper;
import kkl.interview.sportradar.scoreboard.internal.repository.ConcurrentHashMapFootballMatchRepository;
import kkl.interview.sportradar.scoreboard.internal.repository.FootballMatchRepository;
import kkl.interview.sportradar.scoreboard.internal.repository.FootballMatchSummaryRepository;
import kkl.interview.sportradar.scoreboard.internal.service.DefaultScoreboardMatchService;
import kkl.interview.sportradar.scoreboard.internal.service.DefaultScoreboardMatchSummaryService;
import kkl.interview.sportradar.scoreboard.internal.service.DefaultTimeService;
import kkl.interview.sportradar.scoreboard.internal.service.TimeService;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.NONE;

@NoArgsConstructor(access = NONE)
public final class ScoreboardFacade {

    private static final  ConcurrentHashMapFootballMatchRepository repository =
            new ConcurrentHashMapFootballMatchRepository();
    private static final FootballMatchRepository matchRepository = repository;
    private static final FootballMatchSummaryRepository matchSummaryRepository = repository;
    private static final FootballMatchMapper footballMatchMapper = new DefaultFootballMatchMapper();

    private static final TimeService timeService = new DefaultTimeService();

    @Getter
    private final static ScoreboardMatchSummaryService scoreboardMatchSummaryService =
            new DefaultScoreboardMatchSummaryService(matchSummaryRepository,
                                                     footballMatchMapper);

    @Getter
    private final static ScoreboardMatchService scoreboardMatchService =
            new DefaultScoreboardMatchService(matchRepository,
                                              footballMatchMapper,
                                              timeService);
}
