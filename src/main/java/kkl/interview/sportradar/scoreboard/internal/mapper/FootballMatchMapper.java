package kkl.interview.sportradar.scoreboard.internal.mapper;

import kkl.interview.sportradar.scoreboard.external.dto.FootballMatchDto;
import kkl.interview.sportradar.scoreboard.internal.domain.FootballMatch;

public interface FootballMatchMapper {

    FootballMatchDto map(FootballMatch match);
}
