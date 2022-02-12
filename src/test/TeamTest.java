package test;

import main.models.Team;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TeamTest {
    Team team;

    @Test
    public void hasNullTest() {
        String[] chasers = new String[] {null, "Ginny", "Katie"};
        Assertions.assertTrue(Team.hasNull(chasers));

    }

    @Test
    public void hasBlankTest() {
        String[] chasers = {"    ", "Ginny", "Katie"};
        Assertions.assertTrue(Team.hasBlank(chasers));
    }
}
