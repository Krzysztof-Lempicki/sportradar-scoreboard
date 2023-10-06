package kkl.interview.sportradar.scoreboard.internal.repository;

import kkl.interview.sportradar.scoreboard.exception.MatchNotFoundException;
import kkl.interview.sportradar.scoreboard.internal.domain.FootballMatch;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

public class ConcurrentHashMapFootballMatchRepository implements FootballMatchRepository, FootballMatchSummaryRepository {

    private final ConcurrentHashMap<UUID, FootballMatch> storage = new ConcurrentHashMap<>();

    @Override
    public FootballMatch save(FootballMatch footballMatch) {
        requireNonNull(footballMatch);

        storage.put(footballMatch.getId(), footballMatch);

        return footballMatch;
    }

    @Override
    public FootballMatch findWithId(UUID id) throws MatchNotFoundException {
        requireNonNull(id);

        var result = storage.get(id);
        if(isNull(result)) throw new MatchNotFoundException(id);

        return result;
    }

    @Override
    public void delete(UUID matchInProgressId) {
        requireNonNull(matchInProgressId);
        storage.remove(matchInProgressId);
    }

    @Override
    public Collection<FootballMatch> getAllMatches() {
        return storage.values();
    }
}
