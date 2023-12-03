package Day3_2023.Part_2;

import java.util.*;

public class Grid {
    public final List<List<Character>> gridMatrix = new ArrayList<>();
    private int numberOfRows = 0;
    private int numberOfColumns = 0;
    private final Map<String, Asterisk> rowColumnAsStringToAsterisk = new HashMap<>();

    public void addRow(String row){
        numberOfColumns = row.length();
        List<Character> symbolsThisRow = new ArrayList<>();
        for(int columnNumber = 0; columnNumber < numberOfColumns; columnNumber++){
            symbolsThisRow.add(row.charAt(columnNumber));
            if(row.charAt(columnNumber) == '*'){
                String gridReference = numberOfRows + "," + columnNumber;
                rowColumnAsStringToAsterisk.put(gridReference, new Asterisk());
            }
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


    public boolean isDigit(int rowNumber, int columnNumber){
        return Character.isDigit(getCharacterAt(rowNumber, columnNumber));
    }
    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public Set<Asterisk> getSetOfNeighbouringAsterisks(int rowNumber, int columnNumber) {
        Set<Asterisk> neighbouringAsterisks = new HashSet<>();

        boolean leftSameRow = rowColumnAsStringToAsterisk.containsKey((rowNumber) + "," + (columnNumber - 1));
        if (leftSameRow) neighbouringAsterisks.add(rowColumnAsStringToAsterisk.get((rowNumber) + "," + (columnNumber - 1)));

        boolean rightSameRow = rowColumnAsStringToAsterisk.containsKey((rowNumber) + "," + (columnNumber + 1));
        if (rightSameRow) neighbouringAsterisks.add(rowColumnAsStringToAsterisk.get((rowNumber) + "," + (columnNumber + 1)));

        boolean leftUp = rowColumnAsStringToAsterisk.containsKey((rowNumber - 1) + "," + (columnNumber - 1));
        if (leftUp) neighbouringAsterisks.add(rowColumnAsStringToAsterisk.get((rowNumber - 1) + "," + (columnNumber - 1)));

        boolean rightUp = rowColumnAsStringToAsterisk.containsKey((rowNumber - 1) + "," + (columnNumber + 1));
        if (rightUp) neighbouringAsterisks.add(rowColumnAsStringToAsterisk.get((rowNumber - 1) + "," + (columnNumber + 1)));

        boolean leftDown= rowColumnAsStringToAsterisk.containsKey((rowNumber + 1) + "," + (columnNumber - 1));
        if (leftDown) neighbouringAsterisks.add(rowColumnAsStringToAsterisk.get((rowNumber + 1) + "," + (columnNumber - 1)));

        boolean rightDown = rowColumnAsStringToAsterisk.containsKey((rowNumber + 1) + "," + (columnNumber + 1));
        if (rightDown) neighbouringAsterisks.add(rowColumnAsStringToAsterisk.get((rowNumber + 1) + "," + (columnNumber + 1)));

        boolean directlyAbove = rowColumnAsStringToAsterisk.containsKey((rowNumber - 1) + "," + (columnNumber));
        if (directlyAbove) neighbouringAsterisks.add(rowColumnAsStringToAsterisk.get((rowNumber - 1) + "," + (columnNumber)));

        boolean directlyBelow = rowColumnAsStringToAsterisk.containsKey((rowNumber + 1) + "," + (columnNumber));
        if (directlyBelow) neighbouringAsterisks.add(rowColumnAsStringToAsterisk.get((rowNumber + 1) + "," + (columnNumber)));

        return neighbouringAsterisks;
    }
}
