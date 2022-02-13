package main.models;

import java.util.HashMap;

public class Game {
    private HashMap<Team, Integer> scoreboard;
    public static int gameCount = 0;
    public final int QUAFFLE_POINTS = 10;
    public final int SNITCH_POINTS = 150;

    public Game(Team home, Team away) {
        scoreboard = new HashMap<>();
        scoreboard.put(new Team(home), 0);
        scoreboard.put(new Team(away), 0);
        gameCount++;
    }

    public int getScore(Team team) {
        return scoreboard.get(team);
    }

    public void setScore(Team team, Integer score) {
        if(team == null)
            throw new IllegalArgumentException("Team is null!");
        scoreboard.put(team, score);
    }

    public String getPlaceholder(String text) {
        return text.substring(text.indexOf('<') + 1, text.indexOf('>'));
    }

    public String setPlaceholder(String text, String placeholder, String value) {
        return text.replace("<" + placeholder + ">", value);
    }

    public void quaffleScore(Team team) {
        scoreboard.put(team, getScore(team) + QUAFFLE_POINTS);
    }

    public void catchSnitch(Team team) {
        scoreboard.put(team, getScore(team) + SNITCH_POINTS);
    }

    public Team getTeam(String name) {
        return scoreboard.entrySet().stream().filter((entry) -> {
            return entry.getKey().getHouse().equalsIgnoreCase(name);
        }).findFirst().orElse(null).getKey();
    }

    public Team getRandomTeam() {
        Object[] array = scoreboard.keySet().toArray();
        int random =  (int)(Math.random() * array.length);
        return (Team) array[random];
    }

    public String simulate(String play) {
        String placeholder = getPlaceholder(play);
        Team random = getRandomTeam();
        if(placeholder.equals(Team.POSITION_CHASER)) {
            quaffleScore(random);
            return setPlaceholder(play, "chaser", random.getChasers()[(int) (Math.random() * random.getChasers().length)]);
        } else if(placeholder.equals(Team.POSITION_SEEKER)) {
            catchSnitch(random);
            return setPlaceholder(play, "seeker", random.getSeeker());
        } else {
            return setPlaceholder(play, "keeper", random.getKeeper());
        }
    }


}
