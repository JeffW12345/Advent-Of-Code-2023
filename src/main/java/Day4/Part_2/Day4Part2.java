package Day4.Part_2;

import java.io.File;
import java.util.Scanner;

public class Day4Part2 {

    private static final File FILE = new File("src/main/resources/cards.txt");
    private static final Cards CARDS = new Cards();

    public static void main(String[] args) {
        importData();
        System.out.println(CARDS.calculateNumberOfCardsWon());
    }

    private static void importData() {
        try (Scanner scanner = new Scanner(FILE)) {
            while (scanner.hasNextLine()) {
                CARDS.add(Card.createCard(scanner.nextLine()));
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
