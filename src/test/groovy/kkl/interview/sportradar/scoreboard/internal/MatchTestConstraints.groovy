package kkl.interview.sportradar.scoreboard.internal

import kkl.interview.sportradar.scoreboard.internal.domain.FootballMatch

import java.time.LocalDateTime

import static kkl.interview.sportradar.scoreboard.internal.AppConstants.ZERO

class MatchTestConstraints {

    public static final String CORRECT_HOME_TEAM_NAME = "correct home name"
    public static final String CORRECT_AWAY_TEAM_NAME = "correct away name"

    static final LocalDateTime DATE_TIME_2001_01_01 = LocalDateTime.of(2001, 1, 1, 1, 1, 1)
    static final LocalDateTime DATE_TIME_2001_01_02 = LocalDateTime.of(2001, 1, 2, 1, 1, 1)
    static final LocalDateTime DATE_TIME_2001_01_03 = LocalDateTime.of(2001, 1, 3, 1, 1, 1)


    static final String SECOND_TEAM_NAME = "team 2"
    static final String THIRD_TEAM_NAME = "team 3"
    static final String FORTH_TEAM_NAME = "team 4"

    public static final short THEN =  10
    public static final short SEVEN =  7

    static final MATCH_2001_01_01_BETWEEN_CH_AND_CA_WITH_ZERO_ZERO = new FootballMatch(CORRECT_HOME_TEAM_NAME,
            CORRECT_AWAY_TEAM_NAME,
            ZERO,
            ZERO,
            DATE_TIME_2001_01_01
    )

    static final MATCH_2001_01_02_BETWEEN_ST_AND_TT_WITH_ZERO_ZERO = new FootballMatch(SECOND_TEAM_NAME,
            THIRD_TEAM_NAME,
            ZERO,
            ZERO,
            DATE_TIME_2001_01_02
    )

    static final MATCH_2001_01_02_BETWEEN_TT_AND_CA_WITH_NINE_NINE = new FootballMatch(THIRD_TEAM_NAME,
            CORRECT_AWAY_TEAM_NAME,
            (short) 9,
            (short) 9,
            DATE_TIME_2001_01_02
    )

    static final MATCH_2001_01_03_BETWEEN_FT_AND_CA_WITH_THEN_SEVEN = new FootballMatch(FORTH_TEAM_NAME,
            CORRECT_AWAY_TEAM_NAME,
            THEN,
            SEVEN,
            DATE_TIME_2001_01_03
    )

    static final MATCH_2001_01_01_BETWEEN_FT_AND_ST_WITH_THEN_SEVEN = new FootballMatch(FORTH_TEAM_NAME,
            SECOND_TEAM_NAME,
            THEN,
            SEVEN,
            DATE_TIME_2001_01_01
    )
}
