package kkl.interview.sportradar.scoreboard.internal.service;

import kkl.interview.sportradar.scoreboard.ScoreboardMatchService;
import kkl.interview.sportradar.scoreboard.dto.FootballMatchDto;
import kkl.interview.sportradar.scoreboard.dto.StartNewMatchDto;
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
    
    @Override
    public FootballMatchDto startNewMatch(StartNewMatchDto dto) {
        requireNonNull(dto);

        FootballMatch newFootballMatch = new FootballMatch(dto.homeTeamName(),
                                                           dto.awayTeamName(),
                                                           ZERO,
                                                           ZERO);

        return footballMatchMapper.map(
                repository.save(newFootballMatch));
    }
}
