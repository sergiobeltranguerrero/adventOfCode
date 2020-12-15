import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Day2 {
    List<String> input;

    public Day2(String path) throws IOException {
        File file = new File(path);
        readFile(file);
    }

    private void readFile(File file) throws IOException {
        BufferedReader reader    = new BufferedReader(new FileReader(file));
        List<String>   passwords = new ArrayList<>();
        while (reader.ready()) {
            String password = reader.readLine();
            passwords.add(password);
        }
        this.input = passwords;
        System.out.println(passwords);
    }
}
