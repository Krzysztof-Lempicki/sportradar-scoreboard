package kkl.interview.sportradar.scoreboard.internal.domain;

import kkl.interview.sportradar.scoreboard.external.exception.IncorrectNewMatchException;
import kkl.interview.sportradar.scoreboard.external.exception.Violation;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static java.util.UUID.randomUUID;
import static kkl.interview.sportradar.scoreboard.external.exception.Violation.*;
import static kkl.interview.sportradar.scoreboard.internal.AppConstants.ZERO;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class FootballMatch implements Comparable<FootballMatch> {

    private static final short WORLD_HIGHEST_FOOTBALL_MATCH_SCORE = 149;
    private static final short MAX_TEAM_NAME_LENGTH = 1000;
    private static final short MAX_SCORE = 1000;

    @Getter
    private final UUID id = randomUUID();
    @Getter
    private final String homeTeamName;
    @Getter
    private final String awayTeamName;
    @Getter
    private Short homeTeamScore;
    @Getter
    private Short awayTeamScore;
    private final LocalDateTime matchStartTime;

    public FootballMatch(String homeTeamName,
                         String awayTeamName,
                         Short homeTeamScore,
                         Short awayTeamScore,
                         LocalDateTime matchStartTime) {
        this.homeTeamName = isNull(homeTeamName) ? homeTeamName : homeTeamName.trim();
        this.awayTeamName = isNull(awayTeamName) ? awayTeamName : awayTeamName.trim();
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.matchStartTime = matchStartTime;

        assertStateCorrectness();
    }

    public void changeMatchScore(Short newHomeTeamScore, Short newAwayTeamScore) {
        assertScoreCorrectness(newHomeTeamScore);
        assertScoreCorrectness(newAwayTeamScore);

        homeTeamScore = newHomeTeamScore;
        awayTeamScore = newAwayTeamScore;
    }

    private void assertStateCorrectness() {
        assertScoreCorrectness(homeTeamScore);
        assertScoreCorrectness(awayTeamScore);
        assertNameCorrectness();
        assertUpdateTimeCorrectness();
    }

    private void assertUpdateTimeCorrectness() {
        if (isNull(matchStartTime)) {
            throw new IncorrectNewMatchException(Set.of(UNDEFINED_START_TIME));
        }
    }

    private void assertScoreCorrectness(Short score) {
        Set<Violation> violations = new HashSet<>();

        if (isNull(score)) {
            violations.add(UNDEFINED_TEAM_SCORE);
            throw new IncorrectNewMatchException(violations);
        }

        if (score < ZERO) {
            violations.add(NEGATIVE_SCORE);
        }

        if (score > MAX_SCORE) {
            violations.add(TOO_BIG_SCORE);
        }

        if (score > WORLD_HIGHEST_FOOTBALL_MATCH_SCORE) {
            System.out.println("WARN: Amount of goals of one team is higher then world Guinness record. Match id:" + id);
        }

        if (!violations.isEmpty()) {
            throw new IncorrectNewMatchException(violations);
        }
    }

    private void assertNameCorrectness() {
        Set<Violation> violations = new HashSet<>();

        if (isBlank(homeTeamName) || isBlank(awayTeamName)) {
            violations.add(UNDEFINED_TEAM_NAME);
            throw new IncorrectNewMatchException(violations);
        }

        if (homeTeamName.length() > MAX_TEAM_NAME_LENGTH || awayTeamName.length() > MAX_TEAM_NAME_LENGTH) {
            violations.add(TOO_LONG_TEAM_NAME);
        }

        if (homeTeamName.equals(awayTeamName)) {
            violations.add(SAME_NAME_FOR_TWO_TEAMS);
        }

        if (!violations.isEmpty()) {
            throw new IncorrectNewMatchException(violations);
        }
    }

    @Override
    public int compareTo(FootballMatch anotherMatch) {
        requireNonNull(anotherMatch);

        final int equal =0;

        if(this.equals(anotherMatch)) {
            return equal;
        }

        var scoreComparisonResult = Integer.compare(homeTeamScore + awayTeamScore,
                anotherMatch.homeTeamScore + anotherMatch.awayTeamScore);

        if (scoreComparisonResult != equal) {
            return scoreComparisonResult;
        } else {
            return matchStartTime.compareTo(anotherMatch.matchStartTime);
        }
    }
}
