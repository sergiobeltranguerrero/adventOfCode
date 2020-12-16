import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3 {
    List<String> input;
    public Day3(String path) throws IOException {
        File file = new File(path);
        readFile(file);
    }

    private void readFile(File file) throws IOException {
        BufferedReader reader    = new BufferedReader(new FileReader(file));
        List<String>   map = new ArrayList<>();
        while (reader.ready()) {
            String line = reader.readLine();
            map.add(line);
        }
        this.input = map;
    }

    private int part0(int numRows, int numCols) {
        int counter = 0;
        int row = numRows;
        int col = numCols;
        while (row < input.size()) {
            char currentChar = input.get(row).charAt(col);
            if (currentChar == '#') {
                counter += 1;
            }
            col = (col + numCols) % input.get(row).length();
            row = row + numRows;
        }
        return counter;
    }

    public int part0() {
        return part0(1, 3);
    }
    public int part1() {
        return part0(1, 1) * part0(1, 3) * part0(1, 5) * part0(1, 7) * part0(2, 1);
    }
}
