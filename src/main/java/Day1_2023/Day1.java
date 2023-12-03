package Day1_2023;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {
    private static final File FILE = new File("src/main/resources/words.txt");
    private static final List<String> INPUT_LINES = new ArrayList<>();
    private static final Map<String, Integer> SPELLED_OUT_DIGITS_TO_DIGITS_MAP = Map.of(
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9
    );

    public static void main(String[] args) {
        importData();
        System.out.println("The sum is: " + calculateSum());
    }

    public static int calculateSum() {
        int total = 0;
        for (String line : INPUT_LINES) {
            total += getValueForLine(line);
        }
        return total;
    }

    private static int getValueForLine(final String line) {
        int firstIndexWithMatch = Integer.MAX_VALUE;
        int lastIndexWithMatch = -1;

        int firstDigit = 0;
        int lastDigit = 0;

        for (Map.Entry<String, Integer> entry : SPELLED_OUT_DIGITS_TO_DIGITS_MAP.entrySet()) {
            String spelledOutNumber = entry.getKey();
            int correspondingDigit = entry.getValue();

            if (line.contains(spelledOutNumber)) {

                int firstOccurrenceIndexThisSpelledOutNumber = line.indexOf(spelledOutNumber);
                if (firstOccurrenceIndexThisSpelledOutNumber < firstIndexWithMatch) {
                    firstIndexWithMatch = firstOccurrenceIndexThisSpelledOutNumber;
                    firstDigit = correspondingDigit;
                }

                int lastOccurrenceIndexThisSpelledOutNumber = line.lastIndexOf(spelledOutNumber);
                if (lastOccurrenceIndexThisSpelledOutNumber > lastIndexWithMatch) {
                    lastIndexWithMatch = lastOccurrenceIndexThisSpelledOutNumber;
                    lastDigit = correspondingDigit;
                }
            }

            if (line.contains(String.valueOf(correspondingDigit))) {

                int firstOccurrenceThisDigit = line.indexOf(String.valueOf(correspondingDigit));
                if (firstOccurrenceThisDigit < firstIndexWithMatch) {
                    firstIndexWithMatch = firstOccurrenceThisDigit;
                    firstDigit = correspondingDigit;
                }

                int lastOccurrenceThisDigit = line.lastIndexOf(String.valueOf(correspondingDigit));
                if (lastOccurrenceThisDigit > lastIndexWithMatch) {
                    lastIndexWithMatch = lastOccurrenceThisDigit;
                    lastDigit = correspondingDigit;
                }
            }
        }

        return lastDigit == 0 ? (firstDigit * 10) + firstDigit : (firstDigit * 10) + lastDigit;
    }

    private static void importData() {
        try (Scanner scanner = new Scanner(FILE)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (isValidLine(line)) {
                    INPUT_LINES.add(line);
                } else {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static boolean isValidLine(String line) {
        String alphaNumericAndWhitespaceOnlyRegex = "^[\\p{Alnum}\\s]+$";
        return line.matches(alphaNumericAndWhitespaceOnlyRegex);
    }
}
