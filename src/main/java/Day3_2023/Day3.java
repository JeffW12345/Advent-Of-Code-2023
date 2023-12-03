package Day3_2023;

import java.io.File;
import java.util.Scanner;

public class Day3 {
    private static final File FILE = new File("src/main/resources/engine.txt");
    private static final Grid grid = new Grid();

    public static void main(String[] args) {
        importData();
        NumbersInGrid numbersInGrid = new NumbersInGrid(grid);
        System.out.println("Output: " + numbersInGrid.getTotal());
    }

    private static void importData() {
        try (Scanner scanner = new Scanner(FILE)) {
            while (scanner.hasNextLine()) {
                grid.addRow(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
