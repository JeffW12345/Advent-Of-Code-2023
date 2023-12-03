package Day3_2023;

import java.util.ArrayList;
import java.util.List;

public class NumbersInGrid {
    private final Grid grid;

    public NumbersInGrid(Grid grid) {
        this.grid = grid;
    }

    private List<List<DigitInGrid>> getListOfListOfAdjoiningDigits() {
        List<List<DigitInGrid>> listOfListOfAdjoiningDigits = new ArrayList<>();
        for (int rowNumber = 0; rowNumber < grid.getNumberOfRows(); rowNumber++) {

            List<DigitInGrid> adjoiningDigits = new ArrayList<>();
            for (int columnNumber = 0; columnNumber < grid.getNumberOfColumns(); columnNumber++) {
                if (grid.isDigit(rowNumber, columnNumber)) {
                    if(grid.isRightHorizontalNeighbourADigit(rowNumber, columnNumber)){
                        adjoiningDigits.add(new DigitInGrid(
                                grid.getCharacterAt(rowNumber, columnNumber),
                                grid.anyNeighbouringSymbols(rowNumber, columnNumber)
                        ));
                    } else{
                        adjoiningDigits.add(new DigitInGrid(
                                grid.getCharacterAt(rowNumber, columnNumber),
                                grid.anyNeighbouringSymbols(rowNumber, columnNumber)
                        ));
                        listOfListOfAdjoiningDigits.add(adjoiningDigits);
                        adjoiningDigits = new ArrayList<>();
                    }
                }
            }
        }
        return listOfListOfAdjoiningDigits;
    }

    public int getTotal(){
        int total = 0;
        List<List<DigitInGrid>> listOfListOfAdjoiningDigits = getListOfListOfAdjoiningDigits();
        for(List<DigitInGrid> list : listOfListOfAdjoiningDigits){
            boolean anyAdjoiningSymbols = false;
            StringBuilder numberAsString = new StringBuilder();
            for(DigitInGrid digitInGrid : list){
                numberAsString.append(digitInGrid.getNumberAsString());
                if(digitInGrid.areThereNeighbouringSymbols()) {anyAdjoiningSymbols = true;}
            }
            if(anyAdjoiningSymbols){
                total += Integer.parseInt(numberAsString.toString());
            }
        }
        return total;
    }
}
