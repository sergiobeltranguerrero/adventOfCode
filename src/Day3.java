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
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String>   map    = new ArrayList<>();
        while (reader.ready()) {
            String line = reader.readLine().replace(" ", "");
            map.add(line);
        }
        this.input = map;
    }

    private long numTrees(int x, int y) {
        long numTrees = 0;

        for (int i = 0; i < input.size(); i += y) {
            int currentX = i * x;
            int adjustedX = currentX % input.get(i).length();

            if (input.get(i).charAt(adjustedX) == '#') {
                numTrees += 1;
            }
        }
        System.out.println(numTrees);
        return numTrees;
    }

    public long part0() {
        return numTrees(3, 1);
    }

    public long part1() {
        long numTrees = 1;
        numTrees *= numTrees(1, 1);
        numTrees *= numTrees(3, 1);
        numTrees *= numTrees(5, 1);
        numTrees *= numTrees(7, 1);
        numTrees *= numTrees(1, 2);
        return numTrees;

    }
}
