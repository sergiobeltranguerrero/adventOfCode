import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Day8 {
    private static class Command {
        private String operation;
        private final int argument;

        Command(String operation, int argument) {
            this.operation = operation;
            this.argument  = argument;
        }

        public String getOperation() {
            return operation;
        }

        public int getArgument() {
            return argument;
        }

        public void setOperation(String newOperation) {
            this.operation = newOperation;
        }
    }

    private final List<Command> commands = new ArrayList<>();
    private boolean isFinished = true;

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
            this.commands.add(command);
        }
    }

    public int part0() {
        return getAccumulator(this.commands);
    }

    private int getAccumulator(List<Command> commands) {
        List<Command> visited        = new ArrayList<>();
        int           pos            = 0;
        boolean       isInfiniteLoop = false;
        int           accumulator    = 0;
        while (pos < commands.size() && !isInfiniteLoop) {
            if (visited.contains(commands.get(pos))) {
                isInfiniteLoop = true;
            } else {
                visited.add(commands.get(pos));
                String operation = commands.get(pos).getOperation();
                if (operation.equals("acc")) {
                    accumulator += commands.get(pos).getArgument();
                    pos += 1;
                } else if (operation.equals("jmp")) {
                    pos = pos + commands.get(pos).getArgument();
                } else {
                    pos += 1;
                }
            }
        }
        this.isFinished = !isInfiniteLoop;
        return accumulator;
    }

    private List<Command> copy(List<Command> src) {
        List<Command> dest = new ArrayList<>();
        for (Command command : src) {
            String operation = command.getOperation();
            int    argument  = command.getArgument();
            dest.add(new Command(operation, argument));
        }
        return dest;
    }

    public int part1() {
        int bestAccumulator = 0;
        for (int i = 0; i < commands.size(); i += 1) {
            List<Command> newCommands        = copy(this.commands);
            int           currentAccumulator = 0;
            if (commands.get(i).getOperation().equals("jmp")) {
                newCommands.get(i).setOperation("nop");
                currentAccumulator = getAccumulator(newCommands);
            } else if (commands.get(i).getOperation().equals("nop")) {
                newCommands.get(i).setOperation("jmp");
                currentAccumulator = getAccumulator(newCommands);
            }
            if (isFinished && bestAccumulator < currentAccumulator) {
                bestAccumulator = currentAccumulator;
            }
        }
        return bestAccumulator;
    }
}
