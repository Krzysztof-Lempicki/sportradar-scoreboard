package kkl.interview.sportradar.scoreboard.internal.service;

import kkl.interview.sportradar.scoreboard.external.ScoreboardMatchService;
import kkl.interview.sportradar.scoreboard.external.dto.FinishMatchInProgressDto;
import kkl.interview.sportradar.scoreboard.external.dto.FootballMatchDto;
import kkl.interview.sportradar.scoreboard.external.dto.StartNewMatchDto;
import kkl.interview.sportradar.scoreboard.external.dto.UpdateMatchScoreDto;
import kkl.interview.sportradar.scoreboard.internal.domain.FootballMatch;
import kkl.interview.sportradar.scoreboard.internal.mapper.FootballMatchMapper;
import kkl.interview.sportradar.scoreboard.internal.repository.FootballMatchRepository;
import lombok.RequiredArgsConstructor;

import static java.util.Objects.requireNonNull;
import static kkl.interview.sportradar.scoreboard.internal.AppConstants.ZERO;

@RequiredArgsConstructor
public class DefaultScoreboardMatchService implements ScoreboardMatchService {

    private final FootballMatchRepository repository;
    private final FootballMatchMapper footballMatchMapper;
    private final TimeService timeService;

    @Override
    public FootballMatchDto startNewFootballMatch(StartNewMatchDto dto) {
        requireNonNull(dto);

        FootballMatch newFootballMatch = new FootballMatch(dto.homeTeamName(),
                                                           dto.awayTeamName(),
                                                           ZERO,
                                                           ZERO,
                                                           timeService.getCurrentTime());

        return footballMatchMapper.map(
                repository.save(newFootballMatch));
    }

    @Override
    public FootballMatchDto updateFootballMatchScore(UpdateMatchScoreDto dto) {
        requireNonNull(dto);

        var match = repository.findWithId(dto.matchId());
        match.changeMatchScore(dto.homeTeamScore(), dto.awayTeamScore());

        return footballMatchMapper.map(match);
    }

    @Override
    public void finishFootballMatch(FinishMatchInProgressDto dto) {
        requireNonNull(dto);
        repository.delete(dto.matchId());
    }
}
