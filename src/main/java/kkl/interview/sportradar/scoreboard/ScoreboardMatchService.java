package kkl.interview.sportradar.scoreboard;

import kkl.interview.sportradar.scoreboard.dto.FootballMatchDto;
import kkl.interview.sportradar.scoreboard.dto.StartNewMatchDto;

public interface ScoreboardMatchService {

    FootballMatchDto startNewMatch(StartNewMatchDto dto);
}
