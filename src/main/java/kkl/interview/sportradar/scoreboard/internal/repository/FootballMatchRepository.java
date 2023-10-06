package kkl.interview.sportradar.scoreboard.internal.repository;

import kkl.interview.sportradar.scoreboard.external.exception.MatchNotFoundException;
import kkl.interview.sportradar.scoreboard.internal.domain.FootballMatch;

import java.util.UUID;

public interface FootballMatchRepository {

    FootballMatch save(FootballMatch footballMatch);

    FootballMatch findWithId(UUID id) throws MatchNotFoundException;

    void delete(UUID matchInProgressId);

}
