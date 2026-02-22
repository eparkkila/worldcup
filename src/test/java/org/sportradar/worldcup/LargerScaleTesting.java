package org.sportradar.worldcup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LargerScaleTesting {

    private Scoreboard scoreboard;
    /**
     * Creates a new scoreboard before every test to
     * guarantee test isolation and avoid shared state.
     */
    @BeforeEach
    void setUp () {
        scoreboard = new Scoreboard();
    }

    @Test
    void useDataSetGivenInTheDescriptionTest() throws InterruptedException{
        WorldCupMatch match = scoreboard.startMatch("Mexico","Canada");
        scoreboard.updateScore(match, 0,5);
        Thread.sleep(10);
        match = scoreboard.startMatch("Spain","Brazil");
        scoreboard.updateScore(match, 10,2);
        Thread.sleep(10);
        match = scoreboard.startMatch("Germany","France");
        scoreboard.updateScore(match,2,2);
        Thread.sleep(10);
        match = scoreboard.startMatch("Uruguay","Italy");
        scoreboard.updateScore(match, 6,6);
        Thread.sleep(10);
        match = scoreboard.startMatch("Argentina","Australia");
        scoreboard.updateScore(match, 3, 1);
        Thread.sleep(10);
        List<String> list = scoreboard.getOngoingMatches();
        for (String str:list) {
            System.out.println(str);
        }
    }
}
