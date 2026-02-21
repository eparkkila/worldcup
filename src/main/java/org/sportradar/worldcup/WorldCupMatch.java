package org.sportradar.worldcup;

/**
 * Represents a single World Cup match.
 * Matches are ordered by:
 *  1. Total score (descending)
 *  2. Start time (ascending)
 */
public class WorldCupMatch implements Comparable<WorldCupMatch> {

    private final long startTime;
    private final String homeTeam;
    private final String visitorTeam;

    private int homeTeamScore;
    private int visitorTeamScore;

    /**
     * Creates a new match with initial score 0–0.
     *
     * @param homeTeam    home team name
     * @param visitorTeam away team name
     */
    public WorldCupMatch(String homeTeam, String visitorTeam) {
        if (homeTeam == null || homeTeam.isBlank()) {
            throw new IllegalArgumentException("Home team name must not be empty");
        }
        if (visitorTeam == null || visitorTeam.isBlank()) {
            throw new IllegalArgumentException("Visitor team name must not be empty");
        }
        if (homeTeam.equals(visitorTeam)) {
            throw new IllegalArgumentException("Teams must be different");
        }

        // For simplicity we capture the creation time as match start time.
        this.startTime = System.currentTimeMillis();
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
        this.homeTeamScore = 0;
        this.visitorTeamScore = 0;
    }

    public long getStartTime() {
        return startTime;
    }

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

    protected void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    protected void setVisitorTeamScore(int visitorTeamScore) {
        this.visitorTeamScore = visitorTeamScore; // ✅ fixed bug
    }

    /**
     * Sort order:
     *  - Higher total score first
     *  - If equal, earlier start time first
     */
    @Override
    public int compareTo(WorldCupMatch other) {
        if (other == null) {
            return -1;
        }

        int thisTotal = this.homeTeamScore + this.visitorTeamScore;
        int otherTotal = other.homeTeamScore + other.visitorTeamScore;

        // Higher total score first
        if (thisTotal != otherTotal) {
            return Integer.compare(otherTotal, thisTotal);
        }

        // Earlier start time first
        return Long.compare(this.startTime, other.startTime);
    }
}