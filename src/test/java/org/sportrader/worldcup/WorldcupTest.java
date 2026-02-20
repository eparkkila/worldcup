package org.sportrader.worldcup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for the World Cup scoreboard system
class WorldcupTest {

     @Test
     void testCreatingMatchWithInvalidHomeTeamName() {
          Scoreboard scoreboard = new Scoreboard();

          assertThrows(IllegalArgumentException.class, () ->
                  scoreboard.startMatch("", "Brazil")
          );
     }

     @Test
     void testCreatingMatchWithInvalidVisitorTeamName() {
          Scoreboard scoreboard = new Scoreboard();

          assertThrows(IllegalArgumentException.class, () ->
                  scoreboard.startMatch("Mexico", null)
          );
     }
}