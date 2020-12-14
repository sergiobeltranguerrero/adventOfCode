import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {
    List<Integer> input;

    public Day1(String path) throws FileNotFoundException {
        File file = new File(path);
        readFile(file);
    }

    private void readFile(File file) throws FileNotFoundException {
        List<Integer> numbers  = new ArrayList<>();
        Scanner       myReader = new Scanner(file);
        while (myReader.hasNext()) {
            int num = myReader.nextInt();
            numbers.add(num);
        }
        this.input = numbers;
    }

    public Integer part0() throws IllegalAccessException {
        for (int i = 0; i < input.size(); i += 1) {
            Integer number1        = input.get(i);
            Integer numberToSearch = 2020 - number1;
            if (numberToSearch > 0 && input.contains(numberToSearch)) {
                return number1 * numberToSearch;
            }
        }
        throw new IllegalAccessException("No existe ningún número");
    }

    public Integer part1() throws IllegalAccessException {
        for (int i = 0; i < input.size(); i += 1) {
            Integer number1 = input.get(i);
            for (int j = i; j < input.size(); j += 1) {
                Integer number2 = input.get(j);
                Integer numberToSerch = 2020 - number1 - number2;
                if (numberToSerch > 0 && input.contains(numberToSerch)) {
                    return number1 * number2 * numberToSerch;
                }
            }
        }
        throw new IllegalAccessException("No exixte ningún trio de numeros");
    }
}