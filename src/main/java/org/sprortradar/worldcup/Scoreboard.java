package org.sprortradar.worldcup;

public class Scoreboard {
    public void startMatch(String homeTeam, String visitorTeam) {
        if (homeTeam == null || visitorTeam == null || homeTeam.isEmpty() || visitorTeam.isEmpty()) {
            throw new IllegalArgumentException();
        }

        // TODO: Add match data object with proper fields and store it in a data structure that can keep things in order
    }
}
