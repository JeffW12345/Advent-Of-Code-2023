package Day4.Part_2;

import java.util.HashMap;
import java.util.Map;

public class Cards {
    private final Map<Integer, Card> cardNumberToCard = new HashMap<>();
    int cardNumber = 0;

    public void add(Card card){
        cardNumberToCard.put(++cardNumber, card);
    }

    public int calculateNumberOfCardsWon() {
        int totalCardsWon = 0;
        for (Map.Entry<Integer, Card> entry : cardNumberToCard.entrySet()) {
            int cardNumber = entry.getKey();
            Card card = entry.getValue();

            int numberOfWinningNumbersThisCard = card.calculateNumberOfWinningNumbers();
            if (numberOfWinningNumbersThisCard == 0) continue;

            int numberOfInstances = card.getNumberOfInstances();
            int updateCardsUpToAndIncluding = cardNumber + numberOfWinningNumbersThisCard;
            incrementCards(cardNumber, updateCardsUpToAndIncluding, numberOfInstances);
            totalCardsWon += card.totalCardsWon();
        }
        return totalCardsWon + cardNumberToCard.size();
    }

    private void incrementCards(int fromExcluding, int toIncluding, int numberOfInstances) {
        for (int cardNumber = fromExcluding + 1; cardNumber < toIncluding + 1; cardNumber++) {
            Card cardToUpdate = cardNumberToCard.get(cardNumber);
            cardToUpdate.incrementNumberOfInstances(numberOfInstances);
        }
    }
}