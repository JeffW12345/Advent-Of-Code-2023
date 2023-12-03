package Day3_2023.Part_2;

import java.util.Set;

public class DigitInGrid {
    private final String numberAsString;
    private final Set<Asterisk> neighbouringAsterisks;

    public DigitInGrid(Character digit, Set<Asterisk> neighbouringAsterisks) {
        this.numberAsString = String.valueOf(digit);
        this.neighbouringAsterisks = neighbouringAsterisks;
    }

    public boolean areThereNeighbouringAsterisks() {
        return neighbouringAsterisks.size() > 0;
    }

    public String getNumberAsString(){
        return numberAsString;
    }

    public Set<Asterisk> getNeighbouringAsterisks() {return neighbouringAsterisks;}
}
