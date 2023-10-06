package kkl.interview.sportradar.scoreboard.external;

import kkl.interview.sportradar.scoreboard.external.dto.FootballMatchDto;

import java.util.List;

public interface ScoreboardMatchSummaryService {

    List<FootballMatchDto> getMatchInProgressSummary();
}
