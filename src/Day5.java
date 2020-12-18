import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day5 {
    List<String> boardingPasses = new ArrayList<>();

    public Day5(String filePath) throws IOException {
        File file = new File(filePath);
        readFile(file);
    }

    private void readFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.ready()) {
            String line = reader.readLine();
            boardingPasses.add(line);
        }
    }

    public int part0() throws IllegalAccessException {
        int maxSeatID = 0;
        for (String boardingPass : boardingPasses) {
            String  rows   = boardingPass.substring(0, 7);
            String  cols   = boardingPass.substring(7, 10);
            Integer numRow = getRow(rows, 0, 127);
            Integer numCol = getCol(cols, 0, 7);
            int     seatID = (numRow * 8) + numCol;
            if (seatID > maxSeatID) {
                maxSeatID = seatID;
            }
        }
        return maxSeatID;
    }

    private Integer getRow(String rowsCharacters, int minValue, int maxValue) throws IllegalAccessException {
        if (rowsCharacters.length() == 1) {
            switch (rowsCharacters) {
                case "F":
                    return minValue;
                case "B":
                    return maxValue;
            }
        }
        int newValue = (maxValue - minValue) / 2;
        switch (rowsCharacters.charAt(0)) {
            case 'F':
                return getRow(rowsCharacters.substring(1), minValue, minValue + newValue);
            case 'B':
                return getRow(rowsCharacters.substring(1), minValue + newValue + 1, maxValue);
        }
        throw new IllegalAccessException("There is a problem :(");
    }

    private Integer getCol(String colsCharacter, int minValue, int maxValue) throws IllegalAccessException {
        if (colsCharacter.length() == 1) {
            switch (colsCharacter) {
                case "L":
                    return minValue;
                case "R":
                    return maxValue;
            }
        }
        int newValue = (maxValue - minValue) / 2;
        switch (colsCharacter.charAt(0)) {
            case 'L':
                return getCol(colsCharacter.substring(1), minValue, minValue + newValue);
            case 'R':
                return getCol(colsCharacter.substring(1), minValue + newValue + 1, maxValue);
        }
        throw new IllegalAccessException("There is a problem :(");
    }
}