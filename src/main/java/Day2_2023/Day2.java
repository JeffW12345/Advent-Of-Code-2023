package Day2_2023;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2 {
    private static final File FILE = new File("src/main/resources/games.txt");
    private static final List<String> GAME_LIST = new ArrayList<>();
    public static void main(String[] args) {
        importData();
        System.out.println("The Part 1 answer is: " + calculatePart1Sum());
        System.out.println("The Part 2 answer is: " + calculatePart2Answer());
    }

    private static int calculatePart2Answer() {
        int total = 0;
        for (String game : GAME_LIST) {
            String withoutGameNumber = game.split(":")[1];
            String[] reveals = withoutGameNumber.split(";");

            int greens = 0;
            int reds = 0;
            int blues = 0;
            for (String reveal : reveals) {
                String[] ballsWithinReveal = reveal.split(",");
                for (String ballsOfParticularColour : ballsWithinReveal) {
                    ballsOfParticularColour = ballsOfParticularColour.trim();
                    int numberOfBalls = Integer.parseInt(ballsOfParticularColour.split(" ")[0]);
                    String colour = ballsOfParticularColour.split(" ")[1];
                    switch (colour) {
                        case "green" -> {
                            if (numberOfBalls > greens) greens = numberOfBalls;
                        }
                        case "blue" -> {
                            if (numberOfBalls > reds) reds = numberOfBalls;
                        }
                        case "red" -> {
                            if (numberOfBalls > blues) blues = numberOfBalls;
                        }
                    }
                }
            }
            total += getPower(greens, reds, blues);
        }
        return total;
    }

    private static int getPower(int greens, int reds, int blues) {
        if(greens == 0) greens = 1;
        if(reds == 0) reds = 1;
        if(blues == 0) blues = 1;
        return greens * reds * blues;
    }


    private static int calculatePart1Sum() {
        int gameNumber = 1;
        int sumOfIds = 0;
        for(String game : GAME_LIST){
            String withoutGameNumber = game.split(":")[1];
            String [] reveals = withoutGameNumber.split(";");
            boolean gamePossible = true;

            for(String reveal : reveals){
                if(!gamePossible) break;
                String [] ballsWithinReveal = reveal.split(",");
                gamePossible = isRevealPossible(ballsWithinReveal);

            }
            if(gamePossible) {
                sumOfIds += gameNumber;
            }
            gameNumber++;
            }
        return sumOfIds;
    }

    private static boolean isRevealPossible(String[] ballsWithinReveal) {
        int greens = 0;
        int blues = 0;
        int reds = 0;
        for(String ballsOfParticularColour : ballsWithinReveal){
            ballsOfParticularColour = ballsOfParticularColour.trim();
            int numberOfBalls = Integer.parseInt(ballsOfParticularColour.split(" ")[0]);
            String colour = ballsOfParticularColour.split(" ")[1];
            switch (colour) {
                case "green" -> greens += numberOfBalls;
                case "blue" -> blues += numberOfBalls;
                case "red" -> reds += numberOfBalls;
            }
        }
        return reds <= 12 && greens <= 13 && blues <= 14;
    }

    private static void importData() {
        try (Scanner scanner = new Scanner(FILE)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                GAME_LIST.add(line);
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
