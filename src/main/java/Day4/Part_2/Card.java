package Day4.Part_2;

import java.util.ArrayList;
import java.util.List;

public class Card {

    private List<Integer> winningNumbers = new ArrayList<>();
    private List<Integer> actualNumbers = new ArrayList<>();
    private int numberOfInstances = 1;
    private Card() {
    }

    public static Card createCard(String inputLine) {
        Card card = new Card();

        String inputWithoutBitBeforeColon = inputLine.split(":")[1];
        String winningNumbersAsStrings = inputWithoutBitBeforeColon.split("\\|")[0];
        card.winningNumbers = extractListOfNumbers(winningNumbersAsStrings);

        String actualNumbersAsStrings = inputWithoutBitBeforeColon.split("\\|")[1];
        card.actualNumbers = extractListOfNumbers(actualNumbersAsStrings);

        return card;
    }

    private static List<Integer> extractListOfNumbers(String listOfNumbers) {
        List<Integer> temporaryList = new ArrayList<>();
        String [] numbers = listOfNumbers.split(" ");
        for(String number : numbers){
            if(number.equals("")) continue;
            temporaryList.add(Integer.parseInt(number));
        }
        return temporaryList;
    }
    public int getNumberOfInstances(){
        return numberOfInstances;
    }

    public void incrementNumberOfInstances(int numberOfIncrements){
        numberOfInstances += numberOfIncrements;
    }

    public int calculateNumberOfWinningNumbers(){
        return (int) actualNumbers.stream().filter(winningNumbers::contains).count();
    }

    public int totalCardsWon(){
        return calculateNumberOfWinningNumbers() * numberOfInstances;
    }
}
