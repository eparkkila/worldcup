package org.sportradar.worldcup;

/**
 * Represents a single World Cup match.
 *
 * <p><strong>Ordering rule (conceptual):</strong>
 * <ol>
 *   <li>Total score (descending) — higher scoring matches first</li>
 *   <li>Start time (ascending) — earlier matches first when tied</li>
 * </ol>
 *
 * <p><strong>Immutability:</strong>
 * <ul>
 *   <li>Teams and start time are immutable</li>
 *   <li>Scores are mutable during match lifetime</li>
 * </ul>
 */
public class WorldCupMatch {

    /** Timestamp when the match was created (acts as start time). */
    private final long startTime;

    /** Home team name (never null/blank). */
    private final String homeTeam;

    /** Visitor (away) team name (never null/blank). */
    private final String visitorTeam;

    /** Current score of the home team. */
    private int homeTeamScore;

    /** Current score of the visitor team. */
    private int visitorTeamScore;

    /**
     * Creates a new match with an initial score of 0–0.
     *
     * @param homeTeam    home team name (must not be null or blank)
     * @param visitorTeam away team name (must not be null or blank and must differ from home team)
     * @throws IllegalArgumentException if validation fails
     */
    public WorldCupMatch(String homeTeam, String visitorTeam) {
        validateTeamName(homeTeam, "Home");
        validateTeamName(visitorTeam, "Visitor");

        if (homeTeam.equals(visitorTeam)) {
            throw new IllegalArgumentException("Teams must be different");
        }

        this.startTime = System.currentTimeMillis();
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
        this.homeTeamScore = 0;
        this.visitorTeamScore = 0;
    }

    // ---------------------------------------------------------------------
    // Validation helpers
    // ---------------------------------------------------------------------

    /**
     * Validates that a team name is neither null nor blank.
     *
     * @param teamName team name to validate
     * @param label    label used in error messages
     * @throws IllegalArgumentException if invalid
     */
    private static void validateTeamName(String teamName, String label) {
        if (teamName == null || teamName.isBlank()) {
            throw new IllegalArgumentException(
                    label + " team name must not be null or blank"
            );
        }
    }

    // ---------------------------------------------------------------------
    // Getters
    // ---------------------------------------------------------------------

    /** @return match start timestamp (epoch millis) */
    public long getStartTime() {
        return startTime;
    }

    /** @return home team name */
    public String getHomeTeam() {
        return homeTeam;
    }

    /** @return visitor team name */
    public String getVisitorTeam() {
        return visitorTeam;
    }

    /** @return current home team score */
    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    /** @return current visitor team score */
    public int getVisitorTeamScore() {
        return visitorTeamScore;
    }

    /**
     * Returns the total number of goals scored in the match.
     *
     * @return combined score of both teams
     */
    public int getTotalScore() {
        return homeTeamScore + visitorTeamScore;
    }

    // ---------------------------------------------------------------------
    // Score update
    // ---------------------------------------------------------------------

    /**
     * Updates the match score.
     *
     * <p>Uses {@code long} parameters to safely detect overflow before casting
     * to {@code int}.
     *
     * @param homeTeamScore    new home score (must be >= 0)
     * @param visitorTeamScore new visitor score (must be >= 0)
     * @throws IllegalArgumentException if scores are negative or exceed int range
     */
    public void setScores(long homeTeamScore, long visitorTeamScore) {
        if (homeTeamScore < 0 || visitorTeamScore < 0) {
            throw new IllegalArgumentException("Scores must be non-negative");
        }

        if (homeTeamScore > Integer.MAX_VALUE || visitorTeamScore > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Score value is too large");
        }

        this.homeTeamScore = (int) homeTeamScore;
        this.visitorTeamScore = (int) visitorTeamScore;
    }

    // ---------------------------------------------------------------------
    // Object methods
    // ---------------------------------------------------------------------

    /**
     * Returns a human-readable representation of the match.
     */
    @Override
    public String toString() {
        return String.format(
                "%s %d - %d %s (started: %d)",
                homeTeam,
                homeTeamScore,
                visitorTeamScore,
                visitorTeam,
                startTime
        );
    }
}