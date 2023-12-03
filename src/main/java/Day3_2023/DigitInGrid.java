package Day3_2023;

public class DigitInGrid {
    private final String numberAsString;
    private final boolean anyNeighbouringSymbols;


    public DigitInGrid(String numberAsString, boolean anyNeighbouringSymbols) {
        this.numberAsString = numberAsString;
        this.anyNeighbouringSymbols = anyNeighbouringSymbols;
    }

    public boolean isAnyNeighbouringSymbols() {
        return anyNeighbouringSymbols;
    }

    public String getNumberAsString(){
        return numberAsString;
    }
}
