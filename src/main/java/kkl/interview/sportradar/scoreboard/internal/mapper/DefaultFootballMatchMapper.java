package kkl.interview.sportradar.scoreboard.internal.mapper;

import kkl.interview.sportradar.scoreboard.external.dto.FootballMatchDto;
import kkl.interview.sportradar.scoreboard.internal.domain.FootballMatch;

import static java.util.Objects.requireNonNull;

public class DefaultFootballMatchMapper implements FootballMatchMapper {
    @Override
    public FootballMatchDto map(FootballMatch match) {
        requireNonNull(match);
        return new FootballMatchDto(match.getId(),
                match.getHomeTeamName(),
                match.getAwayTeamName(),
                match.getHomeTeamScore(),
                match.getAwayTeamScore());
    }
}
