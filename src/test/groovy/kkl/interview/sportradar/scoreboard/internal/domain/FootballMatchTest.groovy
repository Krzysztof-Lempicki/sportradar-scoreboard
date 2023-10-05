package kkl.interview.sportradar.scoreboard.internal.domain

import kkl.interview.sportradar.scoreboard.exception.IncorrectNewMatchException
import spock.lang.Specification

import static kkl.interview.sportradar.scoreboard.exception.Violation.*
import static kkl.interview.sportradar.scoreboard.internal.AppConstants.EMPTY_TEXT
import static kkl.interview.sportradar.scoreboard.internal.AppConstants.ZERO
import static kkl.interview.sportradar.scoreboard.internal.domain.FootballMatch.MAX_SCORE
import static kkl.interview.sportradar.scoreboard.internal.domain.FootballMatch.MAX_TEAM_NAME_LENGTH
import static kkl.interview.sportradar.scoreboard.internal.MatchTestConstraints.CORRECT_AWAY_TEAM_NAME
import static kkl.interview.sportradar.scoreboard.internal.MatchTestConstraints.CORRECT_HOME_TEAM_NAME

class FootballMatchTest extends Specification {

    public static final String TOO_LONG_TEAM_TEST_NAME = "a".repeat(MAX_TEAM_NAME_LENGTH + 1)
    public static final Short UNDEFINED_SCORE = null
    public static final Short TOO_BIG_TEAM_SCORE = MAX_SCORE + 1
    public static final Short TOO_SMALL_TEAM_SCORE = -1

    def 'should not accept incorrect team name'() {
        when:
        new FootballMatch(home_team_name, away_team_name, ZERO, ZERO)

        then:
        def exception = thrown(IncorrectNewMatchException)

        and:
        exception.getViolations().size() == violations.size()

        and:
        exception.getViolations().containsAll(violations)

        where:

        home_team_name           | away_team_name           | violations
        null                     | null                     | Set.of(UNDEFINED_TEAM_NAME)
        CORRECT_HOME_TEAM_NAME   | null                     | Set.of(UNDEFINED_TEAM_NAME)
        null                     | CORRECT_AWAY_TEAM_NAME   | Set.of(UNDEFINED_TEAM_NAME)
        EMPTY_TEXT               | null                     | Set.of(UNDEFINED_TEAM_NAME)
        null                     | EMPTY_TEXT               | Set.of(UNDEFINED_TEAM_NAME)
        CORRECT_HOME_TEAM_NAME   | EMPTY_TEXT               | Set.of(UNDEFINED_TEAM_NAME)
        EMPTY_TEXT               | CORRECT_AWAY_TEAM_NAME   | Set.of(UNDEFINED_TEAM_NAME)
        EMPTY_TEXT               | EMPTY_TEXT               | Set.of(UNDEFINED_TEAM_NAME)
        TOO_LONG_TEAM_TEST_NAME | CORRECT_AWAY_TEAM_NAME  | Set.of(TOO_LONG_TEAM_NAME)
        CORRECT_HOME_TEAM_NAME  | TOO_LONG_TEAM_TEST_NAME | Set.of(TOO_LONG_TEAM_NAME)
        CORRECT_HOME_TEAM_NAME   | CORRECT_HOME_TEAM_NAME   | Set.of(SAME_NAME_FOR_TWO_TEAMS)
    }

    def 'should not accept incorrect score'() {
        when:
        new FootballMatch(CORRECT_HOME_TEAM_NAME,
                CORRECT_AWAY_TEAM_NAME,
                home_team_score,
                away_team_score)

        then:
        def exception = thrown(IncorrectNewMatchException)

        and:
        exception.getViolations().size() == violations.size()

        and:
        exception.getViolations().containsAll(violations)

        where:

        home_team_score    | away_team_score    | violations
        UNDEFINED_SCORE    | UNDEFINED_SCORE    | Set.of(UNDEFINED_TEAM_SCORE)
        ZERO               | UNDEFINED_SCORE    | Set.of(UNDEFINED_TEAM_SCORE)
        UNDEFINED_SCORE    | ZERO               | Set.of(UNDEFINED_TEAM_SCORE)
        TOO_SMALL_TEAM_SCORE        | ZERO               | Set.of(NEGATIVE_SCORE)
        ZERO               | TOO_SMALL_TEAM_SCORE        | Set.of(NEGATIVE_SCORE)
        TOO_SMALL_TEAM_SCORE       | TOO_SMALL_TEAM_SCORE       | Set.of(NEGATIVE_SCORE)
        TOO_BIG_TEAM_SCORE | ZERO               | Set.of(TOO_BIG_SCORE)
        ZERO               | TOO_BIG_TEAM_SCORE | Set.of(TOO_BIG_SCORE)
        TOO_BIG_TEAM_SCORE | TOO_BIG_TEAM_SCORE | Set.of(TOO_BIG_SCORE)
    }

    def 'should trim whitespaces from teams names'() {
        when:
        def result = new FootballMatch(home_team_name, away_team_name, ZERO, ZERO)

        then:
        result.getHomeTeamName() == home_team_name.trim()

        and:
        result.getAwayTeamName() == away_team_name.trim()

        where:
        home_team_name         | away_team_name
        "  abc"                | CORRECT_AWAY_TEAM_NAME
        CORRECT_HOME_TEAM_NAME | "  abc  "
        "  abc"                | "  bcd  "
    }

    def 'should expose all business rules violations'() {
        when:
        new FootballMatch(TOO_LONG_TEAM_TEST_NAME,
                TOO_LONG_TEAM_TEST_NAME,
                ZERO,
                ZERO)

        then:
        def exception = thrown(IncorrectNewMatchException)

        and:
        exception.violations.containsAll(List.of(SAME_NAME_FOR_TWO_TEAMS, TOO_LONG_TEAM_NAME))
    }

    def 'should create match for correct data without rising exceptions'() {
        when:
        new FootballMatch(CORRECT_HOME_TEAM_NAME,
                CORRECT_AWAY_TEAM_NAME,
                ZERO,
                ZERO)
        then:
        notThrown(IncorrectNewMatchException)
    }
}
