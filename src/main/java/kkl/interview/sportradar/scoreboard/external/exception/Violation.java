package kkl.interview.sportradar.scoreboard.external.exception;

public enum Violation {

    UNDEFINED_TEAM_NAME,
    TOO_LONG_TEAM_NAME,
    SAME_NAME_FOR_TWO_TEAMS,

    UNDEFINED_TEAM_SCORE,
    NEGATIVE_SCORE,
    TOO_BIG_SCORE,

    UNDEFINED_START_TIME
}
