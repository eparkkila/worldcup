package org.sportradar.worldcup;

import java.util.Comparator;
import java.util.Objects;

/**
 * Comparator for WorldCupMatch.
 *
 * Ordering rules:
 *  1. Total score (descending)
 *  2. Start time (descending) – more recent match first
 */
public class CustomComparator implements Comparator<WorldCupMatch> {

    @Override
    public int compare(WorldCupMatch m1, WorldCupMatch m2) {
        Objects.requireNonNull(m1, "First match must not be null");
        Objects.requireNonNull(m2, "Second match must not be null");

        int score1 = totalScore(m1);
        int score2 = totalScore(m2);

        // 1. Compare by total score (descending)
        int scoreComparison = Integer.compare(score2, score1);
        if (scoreComparison != 0) {
            return scoreComparison;
        }

        // 2. Compare by start time (descending – later match first)
        return Long.compare(m2.getStartTime(), m1.getStartTime());
    }

    /**
     * Calculates total score for a match.
     */
    private int totalScore(WorldCupMatch match) {
        return match.getHomeTeamScore() + match.getVisitorTeamScore();
    }
}