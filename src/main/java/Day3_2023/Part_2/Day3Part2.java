package Day3_2023.Part_2;

import java.io.File;
import java.util.Scanner;

public class Day3Part2 {
    private static final File FILE = new File("src/main/resources/engine.txt");
    private static final Grid GRID = new Grid();

    public static void main(String[] args) {
        importData();
        FullNumbersInGrid numbersInGrid = new FullNumbersInGrid(GRID);
        System.out.println("Output: " + numbersInGrid.getFactorTotal());
    }

    private static void importData() {
        try (Scanner scanner = new Scanner(FILE)) {
            while (scanner.hasNextLine()) {
                GRID.addRow(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
