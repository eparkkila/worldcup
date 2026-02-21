package org.sportradar.worldcup;

import java.util.HashMap;
import java.util.Map;

public class Scoreboard {

    // For now using a hashmap to keep track of the matches
    private final Map<String, WorldCupMatch> map = new HashMap<>();

    public WorldCupMatch startMatch(String homeTeam, String visitorTeam) {
        if (homeTeam == null || visitorTeam == null || homeTeam.isEmpty() || visitorTeam.isEmpty()) {
            throw new IllegalArgumentException();
        }

        WorldCupMatch match = new WorldCupMatch(homeTeam, visitorTeam);
        map.put(getKeyForTheMatch(match), match);

        return match;
    }

    public void updateMatchScore(String homeTeam, String visitorTeam, long homeTeamScore, long visitorTeamScore) {
        if (!map.containsKey(getKeyForTheTeams(homeTeam, visitorTeam))) {
            throw new IllegalArgumentException("Could not find the the match between " + homeTeam + " and " + visitorTeam);
        }

        if (homeTeamScore < 0 || visitorTeamScore < 0) {
            throw new IllegalArgumentException("Home team score or visitor team score cannot be a negative number");
        }

        if (homeTeamScore > Integer.MAX_VALUE || visitorTeamScore > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("The score of any team cannot be bigger than " + Integer.MAX_VALUE);
        }

        WorldCupMatch match = map.get(getKeyForTheTeams(homeTeam, visitorTeam));
        match.setHomeTeamScore((int)homeTeamScore);
        match.setVisitorTeamScore((int)visitorTeamScore);
    }

    private static String getKeyForTheMatch(WorldCupMatch match) {
        return getKeyForTheTeams(match.getHomeTeam(), match.getVisitorTeam());
    }

    private static String getKeyForTheTeams(String homeTeam, String visitorTeam) {
        return homeTeam + "<-->" + visitorTeam;
    }
}
