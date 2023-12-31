package kkl.interview.sportradar.scoreboard.external.dto;

import java.util.UUID;

public record FootballMatchDto(
        UUID id,
        String homeTeamName,
        String awayTeamName,
        Short homeTeamScore,
        Short awayTeamScore
) {}
