package Day3_2023;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grid {
    public final List<List<Character>> gridMatrix = new ArrayList<>();
    public int numberOfRows;
    public int numberOfColumns;

    public void addRow(String row){
        numberOfColumns = row.length();
        List<Character> symbolsThisRow = new ArrayList<>();
        for(int columnNumber = 0; columnNumber < numberOfColumns; columnNumber++){
            symbolsThisRow.add(row.charAt(columnNumber));
        }
        gridMatrix.add(symbolsThisRow);
        numberOfRows++;
    }

    public Character getCharacterAt(int rowNumber, int columnNumber){
        List<Character> rowOfCharacters = gridMatrix.get(rowNumber);
        return rowOfCharacters.get(columnNumber);
    }

    public boolean isRightHorizontalNeighbourADigit(int rowNumber, int columnNumber){
        return (columnNumber + 1) <= (numberOfColumns - 1) &&
                Character.isDigit(getCharacterAt(rowNumber, columnNumber + 1));
    }

    public boolean isLeftHorizontalNeighbourASymbol(int rowNumber, int columnNumber) {
        return (columnNumber - 1) >= 0 &&
                isCharacterNotAPeriodOrDigit(rowNumber, columnNumber - 1);
    }

    public boolean isRightHorizontalNeighbourASymbol(int rowNumber, int columnNumber) {
        return (columnNumber + 1) <= (numberOfColumns - 1) &&
                isCharacterNotAPeriodOrDigit(rowNumber, columnNumber + 1);
    }

    public boolean isDirectlyAboveASymbol(int rowNumber, int columnNumber) {
        return rowAboveExists(rowNumber) &&
                isCharacterNotAPeriodOrDigit(rowNumber - 1, columnNumber);
    }

    public boolean isDirectlyBelowASymbol(int rowNumber, int columnNumber) {
        return rowBelowExists(rowNumber) &&
                isCharacterNotAPeriodOrDigit(rowNumber + 1, columnNumber);
    }

    public boolean isLeftUpDiagnoalNeighbourASymbol(int rowNumber, int columnNumber) {
        return rowAboveExists(rowNumber) && columnToLeftExists(columnNumber) &&
                isCharacterNotAPeriodOrDigit(rowNumber - 1, columnNumber - 1);
    }

    public boolean isLeftDownDiagonalNeighbourASymbol(int rowNumber, int columnNumber) {
        return rowBelowExists(rowNumber) && columnToLeftExists(columnNumber) &&
                isCharacterNotAPeriodOrDigit(rowNumber + 1, columnNumber - 1);
    }

    public boolean isRightUpDiagnoalNeighbourASymbol(int rowNumber, int columnNumber) {
        return rowAboveExists(rowNumber) &&
                columnToRightExists(columnNumber) &&
                isCharacterNotAPeriodOrDigit(rowNumber - 1, columnNumber + 1);
    }

    public boolean isRightDownDiagonalNeighbourASymbol(int rowNumber, int columnNumber) {
        return rowBelowExists(rowNumber) &&
                columnToRightExists(columnNumber) &&
                isCharacterNotAPeriodOrDigit(rowNumber + 1, columnNumber + 1);
    }


    private boolean columnToRightExists(int columnNumber){
        return (columnNumber + 1) <= (numberOfColumns - 1);
    }

    private boolean columnToLeftExists(int columnNumber){
        return (columnNumber - 1) >= 0;
    }

    private boolean rowBelowExists(int rowNumber){
        return (rowNumber + 1) <= (numberOfRows - 1);
    }

    private boolean rowAboveExists(int rowNumber){
        return (rowNumber - 1) >= 0;
    }

    public boolean anyNeighbouringSymbols(int rowNumber, int columnNumber){
        return isLeftHorizontalNeighbourASymbol(rowNumber, columnNumber) ||
                isRightHorizontalNeighbourASymbol(rowNumber, columnNumber) ||
                isDirectlyAboveASymbol(rowNumber, columnNumber) ||
                isDirectlyBelowASymbol(rowNumber, columnNumber) ||
                isLeftUpDiagnoalNeighbourASymbol(rowNumber, columnNumber) ||
                isLeftDownDiagonalNeighbourASymbol(rowNumber, columnNumber) ||
                isRightUpDiagnoalNeighbourASymbol(rowNumber, columnNumber) ||
                isRightDownDiagonalNeighbourASymbol(rowNumber, columnNumber);
    }

    public boolean isDigit(int rowNumber, int columnNumber){
        return Character.isDigit(getCharacterAt(rowNumber, columnNumber));
    }

    public boolean isCharacterNotAPeriodOrDigit(int rowNumber, int columnNumber) {
        char toCheck = getCharacterAt(rowNumber, columnNumber);
        Pattern pattern = Pattern.compile("[^\\d.]");
        String toCheckAsString = String.valueOf(toCheck);
        Matcher match = pattern.matcher(toCheckAsString);
        return match.find();
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }
}
