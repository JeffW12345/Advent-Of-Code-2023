package Day3_2023.Part_1;

public class DigitInGrid {
    private final String numberAsString;
    private final boolean areThereNeighbouringSymbols;

    public DigitInGrid(Character digit, boolean anyNeighbouringSymbols) {
        this.numberAsString = String.valueOf(digit);
        this.areThereNeighbouringSymbols = anyNeighbouringSymbols;
    }

    public boolean areThereNeighbouringSymbols() {
        return areThereNeighbouringSymbols;
    }

    public String getNumberAsString(){
        return numberAsString;
    }
}
