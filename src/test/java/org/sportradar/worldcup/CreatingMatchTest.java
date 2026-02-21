package org.sportradar.worldcup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests validating match creation rules
 * for the World Cup scoreboard system.
 *
 * <p>Test focus:</p>
 * <ul>
 *   <li>Team name validation</li>
 *   <li>Proper exception handling for invalid input</li>
 * </ul>
 *
 * <p>Design notes:</p>
 * <ul>
 *   <li>Each test uses a fresh {@link Scoreboard} instance.</li>
 *   <li>Tests follow the Arrange–Act–Assert pattern.</li>
 *   <li>Method names describe the expected behavior.</li>
 * </ul>
 */
class CreatingMatchTest {

    /** System under test (SUT). */
    private Scoreboard scoreboard;

    /**
     * Creates a new scoreboard before every test to
     * guarantee test isolation and avoid shared state.
     */
    @BeforeEach
    void setUp() {
        scoreboard = new Scoreboard();
    }

    /**
     * Happy-path guard:
     * verifies that INVALID home team input (empty string)
     * is rejected.
     *
     * <p>Expected behavior:</p>
     * <ul>
     *   <li>{@link IllegalArgumentException} is thrown</li>
     * </ul>
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
     * Verifies that creating a match fails when
     * the visitor (away) team name is empty.
     *
     * <p>Expected behavior:</p>
     * <ul>
     *   <li>{@link IllegalArgumentException} is thrown</li>
     * </ul>
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
     * Verifies that passing {@code null} as the home team name
     * is rejected.
     *
     * <p>Expected behavior:</p>
     * <ul>
     *   <li>{@link IllegalArgumentException} is thrown</li>
     * </ul>
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
     * Verifies that passing {@code null} as the visitor team name
     * is rejected.
     *
     * <p>Expected behavior:</p>
     * <ul>
     *   <li>{@link IllegalArgumentException} is thrown</li>
     * </ul>
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

    /**
     * Positive control test:
     * verifies that valid team names DO NOT throw.
     *
     * <p>This helps ensure validation is not overly strict.</p>
     */
    @Test
    void shouldCreateMatch_whenTeamNamesAreValid() {
        // Arrange
        String homeTeam = "Mexico";
        String awayTeam = "Brazil";

        // Act + Assert
        assertDoesNotThrow(() ->
                scoreboard.startMatch(homeTeam, awayTeam));
    }
}