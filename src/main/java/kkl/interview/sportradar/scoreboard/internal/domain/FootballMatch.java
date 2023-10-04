package kkl.interview.sportradar.scoreboard.internal.domain;

import kkl.interview.sportradar.scoreboard.exception.IncorrectNewMatchException;
import kkl.interview.sportradar.scoreboard.exception.Violation;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.UUID.randomUUID;
import static kkl.interview.sportradar.scoreboard.exception.Violation.*;
import static kkl.interview.sportradar.scoreboard.internal.AppConstants.ZERO;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class FootballMatch {

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

    public FootballMatch(String homeTeamName, String awayTeamName, Short homeTeamScore, Short awayTeamScore) {
        this.homeTeamName = isNull(homeTeamName) ? homeTeamName : homeTeamName.trim();
        this.awayTeamName = isNull(awayTeamName) ? awayTeamName : awayTeamName.trim();
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;

        assertStateCorrectness();
    }

    private void assertStateCorrectness() {
        assertThatDataIsDefined();

        Set<Violation> violations = new HashSet<>();

        if (homeTeamName.length() > MAX_TEAM_NAME_LENGTH || awayTeamName.length() > MAX_TEAM_NAME_LENGTH) {
            violations.add(TOO_LONG_TEAM_NAME);
        }

        if (homeTeamName.equals(awayTeamName)) {
            violations.add(SAME_NAME_FOR_TWO_TEAMS);
        }

        if (homeTeamScore < ZERO || awayTeamScore < ZERO) {
            violations.add(NEGATIVE_SCORE);
        }

        if (homeTeamScore > MAX_SCORE || awayTeamScore > MAX_SCORE) {
            violations.add(TOO_BIG_SCORE);
        }

        if (homeTeamScore > WORLD_HIGHEST_FOOTBALL_MATCH_SCORE || awayTeamScore > WORLD_HIGHEST_FOOTBALL_MATCH_SCORE) {
            System.out.println("WARN: Amount of goals of one team is higher then world Guinness record. Match id:" + id);
        }

        if(! violations.isEmpty()) {
            throw new IncorrectNewMatchException(violations);
        }
    }

    private void assertThatDataIsDefined() {
        Set<Violation> violations = new HashSet<>();

        if (isBlank(homeTeamName) || isBlank(awayTeamName)) {
            violations.add(UNDEFINED_TEAM_NAME);
        }

        if (isNull(homeTeamScore) || isNull(awayTeamScore)) {
            violations.add(UNDEFINED_TEAM_SCORE);
        }

        if(! violations.isEmpty()) {
            throw new IncorrectNewMatchException(violations);
        }
    }
}
