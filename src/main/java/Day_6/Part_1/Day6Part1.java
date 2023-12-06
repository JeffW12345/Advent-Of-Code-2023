package Day_6.Part_1;

import java.io.File;
import java.util.*;

public class Day6Part1 {

    private static final File FILE = new File("src/main/resources/boats.txt");
    private static final HashMap<Integer, TimeAndDistance> indexToTimeAndDistance = new HashMap<>();
    public static void main(String[] args) {
        importData();
        System.out.println("The Part 1 answer is: " + calculateNumberOfWaysToWin());
    }

    private static int calculateNumberOfWaysToWin() {
        List<Integer> waysToWin = indexToTimeAndDistance.values()
                .stream()
                .map(TimeAndDistance::getNumberOfWillingHoldTimes)
                .toList();

        return waysToWin.stream().reduce(1, (a, b) -> a * b);
    }


    private static void importData() {
        try (Scanner scanner = new Scanner(FILE)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.contains("Time")){
                    String times = line.split(":")[1];
                    String [] numbers = times.split(" ");
                    int index = 0;
                    for(String number: numbers){
                        if(number.equals("")) continue;
                        int numberAsInt = Integer.parseInt(number.replaceAll(" ", ""));
                        indexToTimeAndDistance.put(index++, new TimeAndDistance(numberAsInt));
                    }
                }
                else{
                    String distances = line.split(":")[1];
                    String [] numbers = distances.split(" ");
                    int index = 0;
                    for(String number: numbers){
                        if(number.equals("")) continue;
                        int numberAsInt = Integer.parseInt(number.replaceAll(" ", ""));
                        TimeAndDistance timeAndDistance = indexToTimeAndDistance.get(index++);
                        timeAndDistance.setRaceDistanceToBeat(numberAsInt);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
