package kkl.interview.sportradar.scoreboard.internal.repository;

import kkl.interview.sportradar.scoreboard.internal.domain.FootballMatch;

import java.util.Collection;

public interface FootballMatchSummaryRepository {

    Collection<FootballMatch> getAllMatches();

}
