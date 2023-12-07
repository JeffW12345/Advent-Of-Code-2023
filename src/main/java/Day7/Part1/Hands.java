package Day7.Part1;

import java.util.ArrayList;
import java.util.List;

class Hands {
    private final List<Hand> handList;

    public Hands() {
        this.handList = new ArrayList<>();
    }

    public void addHand(Hand hand) {
        handList.add(hand);
    }

    public void getRankings() {
        handList.sort((h1, h2) -> {
            if (h1.getHandValue() != h2.getHandValue()) {
                return Long.compare(h1.getHandValue(), h2.getHandValue());
            } else {
                return h1.getHandAsString().compareTo(h2.getHandAsString());
            }
        });

        int currentRank = 1;
        int numberOfEqualsValues = 0;

        for (int i = 0; i < handList.size(); i++) {
            Hand currentHand = handList.get(i);
            Hand previousHand = (i - 1) >= 0 ? handList.get(i - 1) : null;

            if(previousHand == null){
                currentHand.setHandRanking(currentRank);
                System.out.println("Hand: " + currentHand.getHandAsString() + " Ranking: "
                        + currentHand.getHandRanking() + " Value: " + currentHand.getHandValue());
                continue;
            }
            if (currentHand.getHandValue() == previousHand.getHandValue()) {
                currentHand.setHandRanking(currentRank);
                int equalsValuesIncrement = numberOfEqualsValues == 0 ? 2 : 1;
                numberOfEqualsValues += equalsValuesIncrement;
                System.out.println("Hand: " + currentHand.getHandAsString() + " Ranking: "
                        + currentHand.getHandRanking() + " Value: " + currentHand.getHandValue() +
                        " Equal value: " + numberOfEqualsValues);
                continue;
            }

            if (currentHand.getHandValue() > previousHand.getHandValue()) {
                int increment = numberOfEqualsValues == 0 ? 1 : numberOfEqualsValues;
                currentRank += increment;
                numberOfEqualsValues = 0;
                currentHand.setHandRanking(currentRank);
                System.out.println("Hand: " + currentHand.getHandAsString() + " Ranking: "
                        + currentHand.getHandRanking() + " Value: " + currentHand.getHandValue());
            }
        }
    }
    public int getTotalWinnings() {
        int totalWinnings = 0;
        for (Hand hand : handList) {
            int bid = hand.getHandBid();
            int ranking = hand.getHandRanking();
            totalWinnings += (bid * ranking);
        }
        return totalWinnings;
    }
}
