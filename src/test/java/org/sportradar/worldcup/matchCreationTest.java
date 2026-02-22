package org.sportradar.worldcup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests verifying match creation rules for the Scoreboard.
 *
 * Focus:
 *  - Valid team name handling
 *  - Proper exception handling for invalid input
 */
class MatchCreationTest {

    /** System Under Test (SUT). */
    private Scoreboard scoreboard;

    /**
     * Creates a fresh scoreboard instance before each test
     * to guarantee test isolation and avoid shared state.
     */
    @BeforeEach
    void setUp() {
        scoreboard = new Scoreboard();
    }

    /**
     * Happy-path test:
     * Verifies that a match is successfully created when both
     * team names are valid.
     *
     * Expected behavior:
     *  - No exception is thrown
     *  - Match can be updated normally after creation
     */
    @Test
    void shouldCreateMatch_whenTeamNamesAreValid() {
        // Arrange
        String homeTeam = "Mexico";
        String awayTeam = "Brazil";

        // Act + Assert — creation should succeed
        assertDoesNotThrow(() ->
                scoreboard.startMatch(homeTeam, awayTeam));

        // Additional sanity check: returned match works correctly
        WorldCupMatch match = scoreboard.startMatch("Finland", "Sweden");
        match.setScores(9, 9);

        WorldCupMatch match2 = scoreboard.startMatch("Finland2", "Sweden2");
        match2.setScores(999, 999);

        // If no exceptions were thrown, the test passes
    }

    /**
     * Negative test:
     * Verifies that an empty home team name is rejected.
     *
     * Expected behavior:
     *  - IllegalArgumentException is thrown
     */
    @Test
    void shouldThrowException_whenHomeTeamNameIsEmpty() {
        // Arrange
        String homeTeam = "";
        String awayTeam = "Brazil";

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> scoreboard.startMatch(homeTeam, awayTeam));
    }

    /**
     * Negative test:
     * Verifies that an empty visitor (away) team name is rejected.
     *
     * Expected behavior:
     *  - IllegalArgumentException is thrown
     */
    @Test
    void shouldThrowException_whenVisitorTeamNameIsEmpty() {
        // Arrange
        String homeTeam = "Italy";
        String awayTeam = "";

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> scoreboard.startMatch(homeTeam, awayTeam));
    }

    /**
     * Negative test:
     * Verifies that null home team name is rejected.
     *
     * Expected behavior:
     *  - IllegalArgumentException is thrown
     */
    @Test
    void shouldThrowException_whenHomeTeamNameIsNull() {
        // Arrange
        String homeTeam = null;
        String awayTeam = "Brazil";

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> scoreboard.startMatch(homeTeam, awayTeam));
    }

    /**
     * Negative test:
     * Verifies that null visitor (away) team name is rejected.
     *
     * Expected behavior:
     *  - IllegalArgumentException is thrown
     */
    @Test
    void shouldThrowException_whenVisitorTeamNameIsNull() {
        // Arrange
        String homeTeam = "Mexico";
        String awayTeam = null;

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> scoreboard.startMatch(homeTeam, awayTeam));
    }
}
