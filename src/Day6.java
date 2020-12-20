import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day6 {
    List<String> allAnswers = new ArrayList<>();
    File inputFile;

    public Day6(String filePath) throws IOException {
        File file = new File(filePath);
        inputFile = file;
        readFile(file);
    }

    private void readFile(File file) throws IOException {
        BufferedReader fileInput = new BufferedReader(new FileReader(file));
        StringBuilder  answers   = new StringBuilder();
        while (fileInput.ready()) {
            String line = fileInput.readLine();
            if (line.isEmpty()) {
                allAnswers.add(answers.toString());
                answers = new StringBuilder();
            } else {
                answers.append(line);
            }
        }
        allAnswers.add(answers.toString());
    }

    public int part0() {
        int answersSum = 0;
        for (String answers : allAnswers) {
            List<Character> answered = new ArrayList<>();
            for (int i = 0; i < answers.length(); i += 1) {
                Character answer = answers.charAt(i);
                if (!answered.contains(answer)) {
                    answersSum += 1;
                    answered.add(answer);
                }
            }
        }
        return answersSum;
    }

    public int part1() throws IOException {
        int            answersSum = 0;
        BufferedReader fileInput  = new BufferedReader(new FileReader(inputFile));
        List<String> groupAnswers = new ArrayList<>();
        while (fileInput.ready()) {
            String       line         = fileInput.readLine();
            if (line.isEmpty()) {
                answersSum += calculateAnswers(groupAnswers);
                groupAnswers = new ArrayList<>();
            } else {
                groupAnswers.add(line);
            }
        }
        answersSum += calculateAnswers(groupAnswers);
        return answersSum;
    }

    private int calculateAnswers(List<String> groupAnswers) {
        int sum = 0;
        if (groupAnswers.size() == 1) {
            return groupAnswers.get(0).length();
        }
        String required = groupAnswers.get(0);
        for (int i = 0; i < required.length(); i += 1) {
            boolean isValid   = true;
            char    character = required.charAt(i);
            int     j         = 0;
            while (isValid && j < groupAnswers.size()) {
                if (groupAnswers.get(j).indexOf(character) == -1) {
                    isValid = false;
                }
                j += 1;
            }
            if (isValid) {
                sum += 1;
            }
        }
        return sum;
    }
}
