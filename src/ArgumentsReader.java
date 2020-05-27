import java.util.HashMap;
import java.util.Map;

class WrongArgumentException extends Exception {
    WrongArgumentException(String message) {
        super(message);
    }
}

public class ArgumentsReader {
    Map<String, String> read(String[] args) throws WrongArgumentException {
        Map<String, String> params = new HashMap<>();
        params.put("i", "data/targets.csv");
        params.put("o", "output/targets_groups.txt");

        for (int i = 0; i < args.length; i += 2) {
            if (args[i].charAt(0) != '-') {
                throw new WrongArgumentException("Option must start with '-', got: " + args[i]);
            }
            params.put(args[i].substring(1), args[i + 1]);
        }

        return params;
    }
}
