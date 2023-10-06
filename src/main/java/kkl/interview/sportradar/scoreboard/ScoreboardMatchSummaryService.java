package kkl.interview.sportradar.scoreboard;

import kkl.interview.sportradar.scoreboard.dto.FootballMatchDto;

import java.util.List;

public interface ScoreboardMatchSummaryService {

    List<FootballMatchDto> getMatchInProgressSummary();
}
