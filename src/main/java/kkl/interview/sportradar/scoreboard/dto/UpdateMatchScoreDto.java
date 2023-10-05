package kkl.interview.sportradar.scoreboard.dto;

import java.util.UUID;

public record UpdateMatchScoreDto(
        UUID matchId,
        Short homeTeamScore,
        Short awayTeamScore
) {
}
