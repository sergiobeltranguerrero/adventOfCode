import java.util.ArrayList;
import java.util.List;

public class Day7 {
    static class Rule {
        private final String name;
        private final int quantity;
        private final List<Rule> rules = new ArrayList<>();

        Rule(String name) {
            this.name = name;
            this.quantity = 1;
        }

        Rule(String name, int quantity) {
            this.name = name.replace("bags", "").replace("bag", "").trim();
            this.quantity = quantity;
        }

        void addRule(Rule rule) {
            rules.add(rule);
        }


    }
}
