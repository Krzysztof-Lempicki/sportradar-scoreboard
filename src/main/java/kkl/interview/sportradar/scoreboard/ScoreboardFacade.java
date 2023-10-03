package kkl.interview.sportradar.scoreboard;

import kkl.interview.sportradar.scoreboard.internal.service.DefaultScoreboardMatchService;
import kkl.interview.sportradar.scoreboard.internal.service.DefaultScoreboardMatchSummaryService;
import lombok.Getter;

public class ScoreboardFacade {

    @Getter
    private final static ScoreboardMatchSummaryService scoreboardMatchSummaryService =
            new DefaultScoreboardMatchSummaryService();

    @Getter
    private final static ScoreboardMatchService scoreboardMatchService =
            new DefaultScoreboardMatchService();
}
