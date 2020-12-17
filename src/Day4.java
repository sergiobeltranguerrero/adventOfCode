import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day4 {
    private final List<String> expectedFields = new ArrayList<>(List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"));
    private final List<String> passports = new ArrayList<>();


    public Day4(String filePath) throws IOException {
        File input = new File(filePath);
        readFile(input);
    }

    private void readFile(File input) throws IOException {
        BufferedReader fileInput = new BufferedReader(new FileReader(input));
        StringBuilder  passport  = new StringBuilder();
        while (fileInput.ready()) {
            String line = fileInput.readLine();
            if (line.isEmpty()) {
                this.passports.add(passport.toString());
                passport = new StringBuilder();
            }
            line += (" ");
            passport.append(line);
        }
        this.passports.add(passport.toString());
    }

    public int part0() {
        int validPassports = 0;
        for (String passport : passports) {
            String[]     requiredFields = passport.split(" ");
            List<String> keys           = new ArrayList<>();
            for (String requiredField : requiredFields) {
                String key = requiredField.split(":")[0];
                keys.add(key);
            }
            if (isValid(keys)) {
                validPassports += 1;
            }
        }
        return validPassports;
    }

    private boolean isValid(List<String> keys) {
        for (String expectedField : expectedFields) {
            if (!keys.contains(expectedField)) {
                return false;
            }
        }
        return true;
    }

    public int part1() {
        int validPassports = 0;
        for (String passport : passports) {
            String[]     requiredFields = passport.split(" ");
            List<String> keys           = new ArrayList<>();
            boolean      isValid        = true;
            int i = 0;
            while (isValid && i < requiredFields.length) {
                String key = requiredFields[i].split(":")[0];
                keys.add(key);
                String value = requiredFields[i].split(":")[1];
                isValid = validate(key, value);
                i += 1;
            }
            isValid = isValid && isValid(keys);
            if (isValid) {
                validPassports += 1;
            }
        }
        return validPassports;
    }

    private boolean validate(String key, String value) {
        switch (key) {
            case "byr":
                int birthYear = Integer.parseInt(value);
                return 1920 <= birthYear && birthYear <= 2002;
            case "iyr":
                int issueYear = Integer.parseInt(value);
                return 2010 <= issueYear && issueYear <= 2020;
            case "eyr":
                int expirationYear = Integer.parseInt(value);
                return 2020 <= expirationYear && expirationYear <= 2030;
            case "hgt":
                if (value.contains("cm")) {
                    int number = Integer.parseInt(value.split("cm")[0]);
                    return 150 <= number && number <= 193;
                }
                if (value.contains("in")) {
                    int number = Integer.parseInt(value.split("in")[0]);
                    return 59 <= number && number <= 76;
                }
                return false;
            case "hcl":
                if (value.length() == 7 && value.charAt(0) == '#') {
                    if (!value.contains(invalidCharactersInHcl)) {
                        return true;
                    }
                }
                break;
            case "ecl":

                break;
            case "pid":

                break;
        }
        return false;
    }
}
