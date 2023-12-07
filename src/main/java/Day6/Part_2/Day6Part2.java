package Day6.Part_2;

import java.io.File;
import java.util.Scanner;

public class Day6Part2 {

    private static final File FILE = new File("src/main/resources/boats.txt");
    private static TimeAndDistance timeAndDistance;

    public static void main(String[] args) {
        importData();
        System.out.println("The Part 2 answer is: " + calculateNumberOfWaysToWin());
    }
    private static long calculateNumberOfWaysToWin() {
        return timeAndDistance.getNumberOfWillingHoldTimes();
    }
    private static void importData() {
        try (Scanner scanner = new Scanner(FILE)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.contains("Time")){
                    String times = line.split(":")[1];
                    long raceDuration = Long.parseLong(times.replaceAll(" ", ""));
                    timeAndDistance = new TimeAndDistance(raceDuration);
                }
                else{
                    long distanceToBeat = Long.parseLong(line.split(":")[1].replaceAll(" ", ""));
                    timeAndDistance.setRaceDistanceToBeat(distanceToBeat);
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
