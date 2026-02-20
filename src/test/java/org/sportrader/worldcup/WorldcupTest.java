package org.sportrader.worldcup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sprortradar.worldcup.Scoreboard;

import static org.junit.jupiter.api.Assertions.*;

// Tests for the World Cup scoreboard system
class WorldcupTest {

    private Scoreboard scoreboard;

    @BeforeEach
    void setUp() {
        scoreboard = new Scoreboard();
    }

    @Test
    void testCreatingMatchWithValidTeamNames() {
          assertThrows(IllegalArgumentException.class, () ->
                  scoreboard.startMatch("", "Brazil")
          );
     }

     @Test
     void testCreatingMatchWithInvalidHomeTeamName() {
          assertThrows(IllegalArgumentException.class, () ->
                  scoreboard.startMatch("", "Brazil")
          );
     }

     @Test
     void testCreatingMatchWithInvalidVisitorTeamName() {
          assertThrows(IllegalArgumentException.class, () ->
                  scoreboard.startMatch("", "Brazil")
          );
     }


     @Test
     void testCreatingMatchWithNullHomeTeamName() {
          assertThrows(IllegalArgumentException.class, () ->
                  scoreboard.startMatch(null, "Brazil")
          );
     }

     @Test
     void testCreatingMatchWithNullVisitorTeamName() {
          assertThrows(IllegalArgumentException.class, () ->
                  scoreboard.startMatch("Mexico", null)
          );
     }
}