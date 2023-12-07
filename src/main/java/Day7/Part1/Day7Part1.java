package Day7.Part1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day7Part1 {

    private static final File FILE = new File("src/main/resources/hands.txt");
    private static final Hands hands = new Hands();

    public static void main(String[] args) {
        importData();
        hands.getRankings();
        int totalWinnings = hands.getTotalWinnings();
        System.out.println("Total Winnings: " + totalWinnings);
    }

    private static void importData() {
        try (Scanner scanner = new Scanner(FILE)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                String handString = parts[0];
                int handBid = Integer.parseInt(parts[1]);
                Hand hand = new Hand(handString, handBid);
                hands.addHand(hand);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

