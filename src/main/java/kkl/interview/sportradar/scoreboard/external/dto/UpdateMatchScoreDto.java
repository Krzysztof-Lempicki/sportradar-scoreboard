package kkl.interview.sportradar.scoreboard.external.dto;

import java.util.UUID;

public record UpdateMatchScoreDto(
        UUID matchId,
        Short homeTeamScore,
        Short awayTeamScore
) {
}
