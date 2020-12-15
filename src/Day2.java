import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
    }

    public int part0() {
        Iterator<String> iterator = input.iterator();
        int              output   = 0;
        while (iterator.hasNext()) {
            String[] password        = iterator.next().split(":");
            String   currentPassword = password[1].strip();
            String[] policy          = password[0].split(" ");
            String   character       = policy[1];
            String[] minAndMMax      = policy[0].split("-");
            int      minCharacters   = Integer.parseInt(minAndMMax[0]);
            int      maxCharacters   = Integer.parseInt(minAndMMax[1]);
            int      counter         = 0;
            for (int i = 0; i < currentPassword.length(); i += 1) {
                if (currentPassword.charAt(i) == character.charAt(0)) {
                    counter += 1;
                }
            }
            if (minCharacters <= counter && counter <= maxCharacters) {
                output += 1;
            }
        }
        return output;
    }

    public int part1() {
        Iterator<String> iterator = input.iterator();
        int              output   = 0;
        while (iterator.hasNext()) {
            String[] password        = iterator.next().split(":");
            String   currentPassword = password[1].strip();
            String[] policy          = password[0].split(" ");
            String   character       = policy[1];
            String[] minAndMMax      = policy[0].split("-");
            int      minCharacters   = Integer.parseInt(minAndMMax[0]);
            int      maxCharacters   = Integer.parseInt(minAndMMax[1]);
            if (isCorrect(currentPassword, character, minCharacters, maxCharacters)) {
                output += 1;
            }
        }
        return output;
    }

    private boolean isCorrect(String currentPassword, String character, int minCharacters, int maxCharacters) {
        return currentPassword.charAt(minCharacters - 1) == character.charAt(0) && currentPassword.charAt(maxCharacters - 1) != character.charAt(0) || currentPassword.charAt(minCharacters - 1) != character.charAt(0) && currentPassword.charAt(maxCharacters - 1) == character.charAt(0);
    }
}
