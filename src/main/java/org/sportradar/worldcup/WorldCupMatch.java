package org.sportradar.worldcup;

/**
 * Represents a single World Cup match.
 *
 * Matches are ordered by:
 *  1. Total score (descending) — more exciting matches first
 *  2. Start time (ascending)   — earlier matches first when scores tie
 */
public class WorldCupMatch implements Comparable<WorldCupMatch> {

    /** Timestamp when the match was created (used as start time) */
    private final long startTime;

    /** Home team name */
    private final String homeTeam;

    /** Visitor (away) team name */
    private final String visitorTeam;

    /** Current score of the home team */
    private int homeTeamScore;

    /** Current score of the visitor team */
    private int visitorTeamScore;

    /**
     * Creates a new match with initial score 0–0.
     *
     * @param homeTeam    home team name (must not be null/blank)
     * @param visitorTeam away team name (must not be null/blank and must differ from home team)
     * @throws IllegalArgumentException if validation fails
     */
    public WorldCupMatch(String homeTeam, String visitorTeam) {
        // Validate home team
        if (homeTeam == null || homeTeam.isBlank()) {
            throw new IllegalArgumentException("Home team name must not be empty");
        }

        // Validate visitor team
        if (visitorTeam == null || visitorTeam.isBlank()) {
            throw new IllegalArgumentException("Visitor team name must not be empty");
        }

        // Teams must be different
        if (homeTeam.equals(visitorTeam)) {
            throw new IllegalArgumentException("Teams must be different");
        }

        // Capture creation time as match start time
        this.startTime = System.currentTimeMillis();
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
        this.homeTeamScore = 0;
        this.visitorTeamScore = 0;
    }

    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getVisitorTeam() {
        return visitorTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getVisitorTeamScore() {
        return visitorTeamScore;
    }

    public long getStartTime() {
        return startTime;
    }

    // -------------------------------------------------------------------------
    // Score updates (package/protected visibility by design)
    // -------------------------------------------------------------------------

    /**
     * Updates home team score.
     *
     * @param homeTeamScore new score (must be >= 0)
     */
    protected void setScores(long homeTeamScore, long visitorTeamScore) {
        if (homeTeamScore < 0 || visitorTeamScore < 0 || homeTeamScore > Integer.MAX_VALUE || visitorTeamScore > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Score must be non-negative and below max int value");
        }
        this.homeTeamScore = (int)homeTeamScore;
        this.visitorTeamScore = (int)visitorTeamScore;

    }

    /**
     * Updates visitor team score.
     *
     * @param visitorTeamScore new score (must be >= 0)
     */
    protected void setVisitorTeamScore(int visitorTeamScore) {
        if (visitorTeamScore < 0) {
            throw new IllegalArgumentException("Score must be non-negative");
        }
        this.visitorTeamScore = visitorTeamScore;
    }

    // -------------------------------------------------------------------------
    // Sorting logic
    // -------------------------------------------------------------------------

    /**
     * Defines match ordering.
     *
     * Order rules:
     *  1. Higher total score first
     *  2. If equal, earlier start time first
     */
    @Override
    public int compareTo(WorldCupMatch other) {
        if (other == null) {
            return -1; // non-null comes before null
        }

        int thisTotal = this.homeTeamScore + this.visitorTeamScore;
        int otherTotal = other.homeTeamScore + other.visitorTeamScore;

        // Higher total score first (descending)
        int scoreCompare = Integer.compare(otherTotal, thisTotal);
        if (scoreCompare != 0) {
            return scoreCompare;
        }

        // Earlier start time first (ascending)
        return Long.compare(this.startTime, other.startTime);
    }

    @Override
    public String toString() {
        return "WorldCupMatch{" +
                "startTime=" + startTime +
                ", homeTeam='" + homeTeam + '\'' +
                ", visitorTeam='" + visitorTeam + '\'' +
                ", homeTeamScore=" + homeTeamScore +
                ", visitorTeamScore=" + visitorTeamScore +
                '}';
    }
}