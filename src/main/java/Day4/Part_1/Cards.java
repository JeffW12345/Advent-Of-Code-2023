package Day4.Part_1;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Card> CARDS = new ArrayList<>();

    public void add(Card card){
        CARDS.add(card);
    }
    public int totalAllCards() {
        return CARDS.stream()
                .mapToInt(Card::getCardTotal)
                .sum();
    }

}
