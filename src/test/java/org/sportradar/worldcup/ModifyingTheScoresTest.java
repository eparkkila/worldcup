package org.sportradar.worldcup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests verifying score modification behavior in the Scoreboard.
 *
 * Focus areas:
 *  - Successful score updates
 *  - Validation of negative scores
 *  - Validation of score upper bounds
 *  - Protection against updating non-ongoing matches
 */
class ModifyingTheScoresTest {

    /** System under test */
    private final Scoreboard scoreboard = new Scoreboard();

    /**
     * Happy path:
     * When a match is ongoing, updating the score should correctly
     * update both the home and visitor team scores.
     */
    @Test
    void updateScoreTest() {
        // Arrange
        WorldCupMatch match = scoreboard.startMatch("Germany", "Finland");

        // Act
        match.setScores(1, 3);

        // Assert
        assertEquals(1, match.getHomeTeamScore(),
                "Home team score should be updated correctly");
        assertEquals(3, match.getVisitorTeamScore(),
                "Visitor team score should be updated correctly");
    }

    /**
     * Visitor team score cannot be negative.
     */
    @Test
    void trySettingScoreToNegativeNumberForVisitorTeamTest() {
        // Arrange
        WorldCupMatch match = scoreboard.startMatch("Germany", "Finland");

        // Act + Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> match.setScores(1, -1),
                "Visitor team score must not be negative"
        );
    }

    /**
     * Home team score cannot be negative.
     */
    @Test
    void trySettingScoreToNegativeNumberForHomeTeamTest() {
        // Arrange
        WorldCupMatch match = scoreboard.startMatch("Germany", "Finland");

        // Act + Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> match.setScores(-1, 1),
                "Home team score must not be negative"
        );
    }

    /**
     * Visitor score exceeding allowed range should throw exception.
     *
     * Assumption:
     * Implementation validates score bounds (e.g., int overflow protection).
     */
    @Test
    void trySettingScoreToHigherNumberThanPossibleForVisitorTeamTest() {
        // Arrange
        WorldCupMatch match = scoreboard.startMatch("Germany", "Finland");

        // Act + Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> match.setScores(1, Long.MAX_VALUE),
                "Visitor team score exceeding allowed range should throw"
        );
    }

    /**
     * Home score exceeding allowed range should throw exception.
     */
    @Test
    void trySettingScoreToHigherNumberThanPossibleForHomeTeamTest() {
        // Arrange
        WorldCupMatch match = scoreboard.startMatch("Germany", "Finland");

        // Act + Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> match.setScores(Long.MAX_VALUE, 1),
                "Home team score exceeding allowed range should throw"
        );
    }
}