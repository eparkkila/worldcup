package org.sportradar.worldcup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests verifying score modification behavior in the Scoreboard.
 */
class ModifyingTheScoreTest {

    private final Scoreboard scoreboard = new Scoreboard();

    /**
     * Happy path:
     * When a match exists, updating the score should correctly
     * update both home and visitor scores.
     */
    @Test
    void updateScoreTest() {
        WorldCupMatch match = scoreboard.startMatch("Germany", "Finland");

        scoreboard.updateMatchScore("Germany", "Finland", 1, 3);

        assertEquals(1, match.getHomeTeamScore());
        assertEquals(3, match.getVisitorTeamScore());
    }

    /**
     * Updating score for a match that is not currently ongoing
     * should throw IllegalArgumentException.
     */
    @Test
    void updateScoreWhenMatchIsNotOngoingTest() {
        scoreboard.startMatch("Germany", "Finland");

        assertThrows(
                IllegalArgumentException.class,
                () -> scoreboard.updateMatchScore("Germany", "Finland2", 1, 3)
        );
    }

    /**
     * Visitor team score cannot be negative.
     */
    @Test
    void trySettingScoreToNegativeNumberForVisitorTeamTest() {
        scoreboard.startMatch("Germany", "Finland");

        assertThrows(
                IllegalArgumentException.class,
                () -> scoreboard.updateMatchScore("Germany", "Finland", 1, -1)
        );
    }

    /**
     * Home team score cannot be negative.
     */
    @Test
    void trySettingScoreToNegativeNumberForHomeTeamTest() {
        scoreboard.startMatch("Germany", "Finland");

        assertThrows(
                IllegalArgumentException.class,
                () -> scoreboard.updateMatchScore("Germany", "Finland", -1, 1)
        );
    }

    /**
     * Visitor score exceeding allowed range should throw exception.
     * (Assumes implementation validates score bounds.)
     */
    @Test
    void trySettingScoreToHigherNumberThanPossibleForVisitorTeamTest() {
        scoreboard.startMatch("Germany", "Finland");

        assertThrows(
                IllegalArgumentException.class,
                () -> scoreboard.updateMatchScore("Germany", "Finland", 1, Long.MAX_VALUE)
        );
    }

    /**
     * Home score exceeding allowed range should throw exception.
     */
    @Test
    void trySettingScoreToHigherNumberThanPossibleForHomeTeamTest() {
        scoreboard.startMatch("Germany", "Finland");

        assertThrows(
                IllegalArgumentException.class,
                () -> scoreboard.updateMatchScore("Germany", "Finland", Long.MAX_VALUE, 1)
        );
    }
}