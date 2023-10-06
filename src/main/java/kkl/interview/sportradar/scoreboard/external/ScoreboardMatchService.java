package kkl.interview.sportradar.scoreboard.external;

import kkl.interview.sportradar.scoreboard.external.dto.FinishMatchInProgressDto;
import kkl.interview.sportradar.scoreboard.external.dto.FootballMatchDto;
import kkl.interview.sportradar.scoreboard.external.dto.StartNewMatchDto;
import kkl.interview.sportradar.scoreboard.external.dto.UpdateMatchScoreDto;

public interface ScoreboardMatchService {

    FootballMatchDto startNewFootballMatch(StartNewMatchDto dto);
    FootballMatchDto updateFootballMatchScore(UpdateMatchScoreDto dto);

    void finishFootballMatch(FinishMatchInProgressDto dto);
}
