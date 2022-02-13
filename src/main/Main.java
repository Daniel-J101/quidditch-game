package main;

import main.models.Game;
import main.models.Team;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    private static Game game;
    private static final String TEAMS_FILE = "src/main/teams.txt";
    private static final String PLAYS_FILES = "src/main/plays.txt";
    private static String[][] data;

    public static void main(String[] args) {

        try {
            data = getData();
            game = new Game(
                    new Team(data[0][0], data[0][1], data[0][2], new String[] {data[0][3], data[0][4], data[0][5]}),
                    new Team(data[1][0], data[1][1], data[1][2], new String[] {data[1][3], data[1][4], data[1][5]})
            );
            startGame();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            printResult();
        }

    }


    /**
     * Function name: getData
     * @return (String[][])
     * @throws FileNotFoundException
     *
     * Inside the function:
     *   1. Returns data from TEAMS_FILE as a String[][] array
     */
    public static String[][] getData() throws IOException {
        FileInputStream fis = new FileInputStream(TEAMS_FILE);
        Scanner scanner = new Scanner(fis);
        String[][] data = new String[2][6];

        int row = 0;
        while (scanner.hasNextLine()) {
            int column = 0;
            for (String word : scanner.nextLine().split(",")) {
                data[row][column] = word;
                column++;
            }
            row++;
        }
        scanner.close();
        fis.close();
        return data;

    }


    /** Function name: startGame
     *
     * Inside the function:
     *    1. Grabs each play from plays.txt and calls game.simulate(play);
     *    2. Prints the return from game.simulate(play)
     *        - println("\n" + <return> + "\n");
     */
    public static void startGame() throws IOException, InterruptedException {
        FileInputStream fis = new FileInputStream(PLAYS_FILES);
        Scanner scanner = new Scanner(fis);

        while(scanner.hasNextLine()) {
            System.out.println("\n" + game.simulate(scanner.nextLine()) + "\n");
            wait(3);
        }

        scanner.close();
        fis.close();
    }


    /** Function name: printResult()
     *
     * Inside the function:
     *    1. Prints the final score: println("\nGRYFFINDOR: " + <gryffindor score> + " SLYTHERIN: " + <slytherin score>);
     *    2. Prints the winner: println("\n" + <winner team name> + " WINS!");
     */
     public static void printResult() {
         System.out.println("\nGryffindor: " + game.getScore(game.getTeam("Gryffindor")) + " Syltherin: " + game.getScore(game.getTeam("Slytherin")));
         String winner = (game.getScore(game.getTeam("Gryffindor")) > game.getScore(game.getTeam("Slytherin")) ? "Gryffindor" : "Slytherin");
         System.out.println("\n" + winner + " WINS!");
     }

    /**
     * Function name: wait
     * @param sec
     *
     * Inside the function:
     *  1. Make the code sleep for X seconds.
     */
    public static void wait(int sec) throws InterruptedException {
        TimeUnit.SECONDS.sleep(sec);
    }


}