import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day8 {
    private class Command {
        private final String opertation;
        private final int argument;

        Command(String opertation, int argument) {
            this.opertation = opertation;
            this.argument   = argument;
        }

        public String getOpertation() {
            return opertation;
        }

        public int getArgument() {
            return argument;
        }
    }

    private List<Command> comands = new ArrayList<>();

    Day8(String filePath) throws IOException {
        File file = new File(filePath);
        readFile(file);
    }

    private void readFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.ready()) {
            String[] stringCommand = reader.readLine().split(" ");
            String   operation     = stringCommand[0];
            int      argument      = Integer.parseInt(stringCommand[1]);
            Command  command       = new Command(operation, argument);
            this.comands.add(command);
        }
    }

    public int part0() {
        int           accumulator    = 0;
        List<Command> visited        = new ArrayList<>();
        int           pos            = 0;
        boolean       isInfiniteLoop = false;
        while (pos < comands.size() && !isInfiniteLoop) {
            if (visited.contains(comands.get(pos))) {
                isInfiniteLoop = true;
            } else {
                visited.add(comands.get(pos));
                String operation = comands.get(pos).getOpertation();
                if (operation.equals("acc")) {
                    accumulator += comands.get(pos).getArgument();
                    pos += 1;
                } else if (operation.equals("jmp")){
                    pos = pos + comands.get(pos).getArgument();
                } else {
                    pos += 1;
                }
            }
        }
        return accumulator;
    }
}
