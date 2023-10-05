package kkl.interview.sportradar.scoreboard.internal.repository;

import kkl.interview.sportradar.scoreboard.internal.domain.FootballMatch;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

public class ConcurrentHashMapFootballMatchRepository implements FootballMatchRepository {

    private final ConcurrentHashMap<UUID, FootballMatch> storage = new ConcurrentHashMap<>();

    @Override
    public FootballMatch save(FootballMatch footballMatch) {
        requireNonNull(footballMatch);

        storage.put(footballMatch.getId(), footballMatch);

        return footballMatch;
    }
}
