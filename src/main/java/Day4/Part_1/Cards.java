package Day4.Part_1;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Card> cards = new ArrayList<>();

    public void add(Card card){
        cards.add(card);
    }
    public int totalAllCards() {
        return cards.stream()
                .mapToInt(Card::getCardTotal)
                .sum();
    }

}
