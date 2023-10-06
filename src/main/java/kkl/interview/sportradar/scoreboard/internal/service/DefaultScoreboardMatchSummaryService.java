package kkl.interview.sportradar.scoreboard.internal.service;

import kkl.interview.sportradar.scoreboard.ScoreboardMatchSummaryService;
import kkl.interview.sportradar.scoreboard.dto.FootballMatchDto;
import kkl.interview.sportradar.scoreboard.internal.mapper.FootballMatchMapper;
import kkl.interview.sportradar.scoreboard.internal.repository.FootballMatchSummaryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static java.util.Comparator.reverseOrder;

@RequiredArgsConstructor
public class DefaultScoreboardMatchSummaryService implements ScoreboardMatchSummaryService {

    private final FootballMatchSummaryRepository repository;
    private final FootballMatchMapper mapper;

    @Override
    public List<FootballMatchDto> getMatchInProgressSummary() {
        return repository.getAllMatches()
                .stream()
                .sorted(reverseOrder())
                .map(mapper::map)
                .toList();
    }
}
