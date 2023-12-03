package Day3_2023.Part_2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FullNumbersInGrid {
    private final Grid grid;
    private final Set<Asterisk> allAsterisks = new HashSet<>();
    public FullNumbersInGrid(Grid grid) {
        this.grid = grid;
    }

    private List<List<DigitInGrid>> getListOfListOfAdjoiningDigits() {
        List<List<DigitInGrid>> listOfListOfAdjoiningDigits = new ArrayList<>();
        for (int rowNumber = 0; rowNumber < grid.getNumberOfRows(); rowNumber++) {
            List<DigitInGrid> adjoiningDigits = new ArrayList<>();
            for (int columnNumber = 0; columnNumber < grid.getNumberOfColumns(); columnNumber++) {
                if (grid.isDigit(rowNumber, columnNumber)) {
                    if(grid.isRightHorizontalNeighbourADigit(rowNumber, columnNumber)){
                        addNewDigitInGridObjectToAdjoiningDigitsList(rowNumber, adjoiningDigits, columnNumber);
                    } else{
                        addNewDigitInGridObjectToAdjoiningDigitsList(rowNumber, adjoiningDigits, columnNumber);
                        listOfListOfAdjoiningDigits.add(adjoiningDigits);
                        adjoiningDigits = new ArrayList<>();
                    }
                }
            }
        }
        return listOfListOfAdjoiningDigits;
    }

    private void addNewDigitInGridObjectToAdjoiningDigitsList(int rowNumber, List<DigitInGrid> adjoiningDigits, int columnNumber) {
        adjoiningDigits.add(new DigitInGrid(
                grid.getCharacterAt(rowNumber, columnNumber),
                grid.getSetOfNeighbouringAsterisks(rowNumber, columnNumber)
        ));
    }

    public void giveAsteriskObjectsTheirAdjoiningNumbers(){
        List<List<DigitInGrid>> listOfListOfAdjoiningDigits = getListOfListOfAdjoiningDigits();
        for(List<DigitInGrid> listOfDigits : listOfListOfAdjoiningDigits){

            boolean anyAdjoiningAsterisks = false;
            StringBuilder numberAsString = new StringBuilder();
            Set<Asterisk> asteriskSet = new HashSet<>();
            for(DigitInGrid digitInGrid : listOfDigits){
                numberAsString.append(digitInGrid.getNumberAsString());
                if(digitInGrid.areThereNeighbouringAsterisks()) {
                    anyAdjoiningAsterisks = true;
                    asteriskSet.addAll(digitInGrid.getNeighbouringAsterisks());
                }
            }

            if(anyAdjoiningAsterisks){
                int fullNumber = Integer.parseInt(numberAsString.toString());
                FullNumber fullNumberObject = new FullNumber(fullNumber);
                for(Asterisk asterisk : asteriskSet){
                    asterisk.add(fullNumberObject);
                }
                allAsterisks.addAll(asteriskSet);
            }
        }
    }

    public int getFactorTotal(){
        giveAsteriskObjectsTheirAdjoiningNumbers();
        int total = 0;
        for(Asterisk asterisk : allAsterisks){
            if(asterisk.twoAdjoiningNumbers()){
                total += asterisk.factor();
            }
        }
        return total;
    }
}
