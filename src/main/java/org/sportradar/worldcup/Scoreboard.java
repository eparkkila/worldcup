package org.sportradar.worldcup;

import java.util.*;

public class Scoreboard {

    private final TreeSet<WorldCupMatch> treeSet = new TreeSet<>();

    public WorldCupMatch startMatch(String homeTeam, String visitorTeam) {
        if (homeTeam == null || visitorTeam == null || homeTeam.isEmpty() || visitorTeam.isEmpty()) {
            throw new IllegalArgumentException();
        }

        WorldCupMatch match = new WorldCupMatch(homeTeam, visitorTeam);
        treeSet.add(match);

        return match;
    }

    public void endMatch(WorldCupMatch match) {
        if (match == null) {
            throw new IllegalArgumentException();
        }

        if (!treeSet.contains(match)) {
            throw new IllegalArgumentException("The match is not being played.");
        }

        treeSet.remove(match);
    }

    public List<String> getOngoingMatches() {
        List<String> list = new ArrayList<>();
        for (WorldCupMatch match: treeSet) {
            System.out.println(match.toString());
            list.add(match.toString());
        }

        return list;
    }

    private static String getKeyForTheMatch(WorldCupMatch match) {
        return getKeyForTheTeams(match.getHomeTeam(), match.getVisitorTeam());
    }

    private static String getKeyForTheTeams(String homeTeam, String visitorTeam) {
        return homeTeam + "<-->" + visitorTeam;
    }
}
