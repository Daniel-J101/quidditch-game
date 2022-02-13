package test;

import main.models.Game;
import main.models.Team;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class GameTest {
    Game game;


    @Before
    public void setup() {
        Team home = new Team("GRYFFINDOR", "Oliver", "Harry",
                new String[] {"Angelina", "Ginny", "Katie"});

        Team away = new Team("SLYTHERIN", "Vincent",  "Draco",
                new String[] {"Bridget", "Harper", "Malcolm"});

        game = new Game(home, away);
    }

    @Test
    public void getPlaceholderTest() {
        Assertions.assertEquals("Angelina", game.getPlaceholder("<Angelina> gets the next pass"));
    }

    @Test
    public void replacePlaceholderTest() {
        Assertions.assertEquals("Katie gets the next pass", game.setPlaceholder("<chaser> gets the next pass", "chaser", "Katie"));
    }

    @Test
    public void quaffleScoreTest() {
        Team temp = game.getTeam("gryffindor");
        game.quaffleScore(temp);
        game.quaffleScore(temp);
        Assertions.assertEquals(20, game.getScore(temp));
    }

    @Test
    public void catchSnitchTest() {
        Team temp = game.getTeam("slytherin");
        game.catchSnitch(temp);
        Assertions.assertEquals(150, game.getScore(temp));
    }
}
