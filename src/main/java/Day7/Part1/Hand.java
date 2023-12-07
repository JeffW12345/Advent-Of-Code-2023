package Day7.Part1;

import java.util.HashMap;
import java.util.Map;

class Hand {
    private final String handString;
    private final int handBid;
    private final long handValue;
    private int handRanking;
    private final Map<Character, Integer> CHARACTER_TO_VALUE;
    private final Map<Character, Integer> CHARACTER_TO_QUANTITY;
    private final char[] cards = {'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'};
    private final int DISTINCT_CARD_COUNT;

    public Hand(String handString, int handBid) {
        this.handString = handString;
        this.handBid = handBid;
        this.CHARACTER_TO_VALUE = createCardValueMap();
        this.CHARACTER_TO_QUANTITY = createCharacterToQuantityMap();
        this.DISTINCT_CARD_COUNT = (int) CHARACTER_TO_QUANTITY.keySet().stream()
                .filter(card -> CHARACTER_TO_QUANTITY.get(card) == 1)
                .count();
        this.handValue = calculateHandValue();
    }

    private Map<Character, Integer> createCharacterToQuantityMap() {
        Map<Character, Integer> characterToQuantity = new HashMap<>();

        for (char cardEntry : handString.toCharArray()) {
            characterToQuantity.put(cardEntry, characterToQuantity.getOrDefault(cardEntry, 0) + 1);
        }

        return characterToQuantity;
    }

    private Map<Character, Integer> createCardValueMap() {
        Map<Character, Integer> cardValueMap = new HashMap<>();

        int value = 13;
        for (char card : cards) {
            cardValueMap.put(card, value);
            value--;
        }
        return cardValueMap;
    }

    private boolean isCardRepeatedGivenNumberOfTimes(int numberOfRepetitions) {
        return CHARACTER_TO_QUANTITY.values().stream().anyMatch(count -> count == numberOfRepetitions);
    }

    private long calculateHandValue() {
        boolean fiveOfAKind = isCardRepeatedGivenNumberOfTimes(5);
        if (fiveOfAKind) {
            return getCardValue(8);
        }

        boolean fourOfAKind = isCardRepeatedGivenNumberOfTimes(4);
        if (fourOfAKind) {
            return getCardValue(7);

        }

        if (isFullHouse()) {
            return getCardValue(6);
        }

        if (isThreeOfAKind()) {
            return getCardValue(5);
        }

        if (isCardRepeatedGivenNumberOfTimes(3)) {
            return getCardValue(4);
        }

        if (isTwoPair()) {
            return getCardValue(3);
        }

        if (isOnePair()) {
            return getCardValue(2);
        }

        if (isHighCard()){
            return getCardValue(1);
        }
        return 0;
    }

    private boolean isThreeOfAKind() {
        return isCardRepeatedGivenNumberOfTimes(3) && DISTINCT_CARD_COUNT == 2;
    }

    private boolean isHighCard() {
        return DISTINCT_CARD_COUNT == 5;
    }

    private boolean isFullHouse() {
        return isCardRepeatedGivenNumberOfTimes(3) && isCardRepeatedGivenNumberOfTimes(2);
    }

    private boolean isTwoPair() {
        return isCardRepeatedGivenNumberOfTimes(2)
                && DISTINCT_CARD_COUNT == 1;
    }

    private boolean isOnePair() {
        if (isCardRepeatedGivenNumberOfTimes(2)) {
            return DISTINCT_CARD_COUNT == 3;
        }
        return false;
    }

    private long getCardValue(int categoryImportance) {
        int total = 0;
        long toMultiplyBy = (long) Math.pow(13, 4);
        for (char character : handString.toCharArray()) {
            total += (CHARACTER_TO_VALUE.get(character) * toMultiplyBy);
            toMultiplyBy /= 13;
        }
        return (long) (total + (categoryImportance * Math.pow(13, 5)));
    }

    public int getHandRanking() {
        return handRanking;
    }

    public void setHandRanking(int handRanking) {
        this.handRanking = handRanking;
    }

    public int getHandBid() {
        return handBid;
    }

    public long getHandValue() {
        return handValue;
    }

    public String getHandString(){
        return handString;
    }
}
