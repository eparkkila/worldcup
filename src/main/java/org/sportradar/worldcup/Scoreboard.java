package org.sportradar.worldcup;

import java.util.*;

/**
 * Scoreboard maintains all World Cup matches currently known by the system.
 *
 * Responsibilities:
 *  - Start new matches
 *  - Update scores of ongoing matches
 *  - End matches
 *  - Provide ordered summary of ongoing matches
 *
 * Matches are ordered using CustomComparator.
 */
public class Scoreboard {

    /** All matches (ordered when needed) */
    private final List<WorldCupMatch> matches = new ArrayList<>();

    /** Fast lookup for ongoing matches */
    private final Set<WorldCupMatch> ongoingMatches = new HashSet<>();

    /** Comparator defining match ordering rules */
    private final CustomComparator comparator = new CustomComparator();

    /**
     * Starts a new match between two teams.
     *
     * @param homeTeam     home team name (must not be null/empty)
     * @param visitorTeam  visitor team name (must not be null/empty)
     * @return created WorldCupMatch instance
     * @throws IllegalArgumentException if team names are invalid
     */
    public synchronized WorldCupMatch startMatch(String homeTeam, String visitorTeam) {
        if (homeTeam == null || visitorTeam == null
                || homeTeam.isBlank() || visitorTeam.isBlank()) {
            throw new IllegalArgumentException("Team names must be provided");
        }

        WorldCupMatch match = new WorldCupMatch(homeTeam, visitorTeam);
        matches.add(match);
        ongoingMatches.add(match);

        return match;
    }

    /**
     * Checks whether the given match is currently ongoing.
     *
     * @param match match to check
     * @return true if match is ongoing
     */
    public synchronized boolean isMatchOngoing(WorldCupMatch match) {
        return ongoingMatches.contains(match);
    }

    /**
     * Updates the score of an ongoing match.
     *
     * NOTE:
     * We remove and reinsert the match to ensure correct ordering.
     *
     * @param match             match to update
     * @param homeTeamScore     new home score (>= 0)
     * @param visitorTeamScore  new visitor score (>= 0)
     *
     * @throws IllegalArgumentException if:
     *         - scores are negative or too large
     *         - match is not ongoing
     */
    void updateScore(WorldCupMatch match, long homeTeamScore, long visitorTeamScore) {
        if (homeTeamScore < 0 || visitorTeamScore < 0
                || homeTeamScore > Integer.MAX_VALUE
                || visitorTeamScore > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Please provide proper score values");
        }

        if (!ongoingMatches.contains(match)) {
            throw new IllegalArgumentException("Match is not ongoing");
        }

        // Remove before updating to maintain correct ordering
        matches.remove(match);

        match.setScores(homeTeamScore, visitorTeamScore);

        matches.add(match);
        matches.sort(comparator);
    }

    /**
     * Ends a match and removes it from ongoing tracking.
     *
     * @param match match to end
     * @throws IllegalArgumentException if match is null or not ongoing
     */
    public synchronized void endMatch(WorldCupMatch match) {
        if (match == null) {
            throw new IllegalArgumentException("Match cannot be null");
        }

        if (!ongoingMatches.contains(match)) {
            throw new IllegalArgumentException("The match is not being played.");
        }

        matches.remove(match);
        ongoingMatches.remove(match);
        matches.sort(comparator);
    }

    /**
     * Returns formatted list of ongoing matches ordered by comparator.
     *
     * Format:
     *   1 HomeTeam X - VisitorTeam Y
     *
     * @return ordered list of match summaries
     */
    public synchronized List<String> getOngoingMatches() {
        matches.sort(comparator);

        List<String> summary = new ArrayList<>();
        int position = 1;

        for (WorldCupMatch match : matches) {
            summary.add(position + " "
                    + match.getHomeTeam() + " " + match.getHomeTeamScore()
                    + " - "
                    + match.getVisitorTeam() + " " + match.getVisitorTeamScore());
            position++;
        }

        return summary;
    }
}
