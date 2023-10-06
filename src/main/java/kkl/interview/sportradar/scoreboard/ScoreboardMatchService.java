package kkl.interview.sportradar.scoreboard;

import kkl.interview.sportradar.scoreboard.dto.FinishMatchInProgressDto;
import kkl.interview.sportradar.scoreboard.dto.FootballMatchDto;
import kkl.interview.sportradar.scoreboard.dto.StartNewMatchDto;
import kkl.interview.sportradar.scoreboard.dto.UpdateMatchScoreDto;

public interface ScoreboardMatchService {

    FootballMatchDto startNewFootballMatch(StartNewMatchDto dto);
    FootballMatchDto updateFootballMatchScore(UpdateMatchScoreDto dto);

    void finishFootballMatch(FinishMatchInProgressDto dto);
}
