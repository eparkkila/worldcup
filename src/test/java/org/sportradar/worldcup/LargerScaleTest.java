package org.sportradar.worldcup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Integration-style test that simulates a larger set of matches
 * running on the scoreboard.
 *
 * <p>This test reproduces the dataset described in the requirements
 * and verifies that the scoreboard handles multiple matches correctly.
 *
 * <p>Note: This test currently prints results for manual inspection.
 * In production-quality tests, assertions should be preferred.
 */
class LargerScaleTest {

    /** System under test. */
    private Scoreboard scoreboard;

    /**
     * Creates a fresh {@link Scoreboard} instance before each test.
     *
     * <p>This guarantees:
     * <ul>
     *   <li>Test isolation</li>
     *   <li>No shared mutable state between tests</li>
     *   <li>Predictable and reproducible results</li>
     * </ul>
     */
    @BeforeEach
    void setUp() {
        scoreboard = new Scoreboard();
    }

    /**
     * Simulates multiple World Cup matches using the dataset
     * provided in the original description.
     *
     * <p>The small delays ({@code Thread.sleep}) ensure different start times
     * so match ordering can be validated if the implementation depends
     * on timestamps.
     *
     * <p>Test flow:
     * <ol>
     *   <li>Start several matches</li>
     *   <li>Update their scores</li>
     *   <li>Retrieve ongoing matches</li>
     *   <li>Print results for manual verification</li>
     * </ol>
     */
    @Test
    void shouldHandleDatasetFromDescription() throws InterruptedException {

        // Arrange + Act: create matches with scores
        startMatchWithScore("Mexico", "Canada", 0, 5);
        startMatchWithScore("Spain", "Brazil", 10, 2);
        startMatchWithScore("Germany", "France", 2, 2);
        startMatchWithScore("Uruguay", "Italy", 6, 6);
        startMatchWithScore("Argentina", "Australia", 3, 1);

        // Act: retrieve ongoing matches
        List<String> ongoingMatches = scoreboard.getOngoingMatches();

        // Debug output (consider replacing with assertions)
        ongoingMatches.forEach(System.out::println);
    }

    /**
     * Helper method to reduce duplication when:
     * <ul>
     *   <li>Starting a match</li>
     *   <li>Updating its score</li>
     *   <li>Adding a slight delay to differentiate timestamps</li>
     * </ul>
     *
     * @param homeTeam  home team name
     * @param awayTeam  away team name
     * @param homeScore score for home team
     * @param awayScore score for away team
     */
    private void startMatchWithScore(String homeTeam,
                                     String awayTeam,
                                     int homeScore,
                                     int awayScore) throws InterruptedException {

        // Start match
        WorldCupMatch match = scoreboard.startMatch(homeTeam, awayTeam);

        // Update score
        scoreboard.updateScore(match, homeScore, awayScore);

        // Ensure unique timestamps for ordering validation
        Thread.sleep(10);
    }
}