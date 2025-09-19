package game.io;

import java.util.Scanner;
import java.util.function.Function;

public class ConsoleInput {

    private final Scanner scanner;

    public ConsoleInput() {
        this.scanner = new Scanner(System.in);
    }

    public <T> T readLineMap(Function<String, T> mapper) {
        if (!scanner.hasNextLine()) return null;
        String line = scanner.nextLine();
        return mapper.apply(line);
    }

    public String readLine() {
        if (!scanner.hasNextLine()) return null;
        return scanner.nextLine();
    }
}
