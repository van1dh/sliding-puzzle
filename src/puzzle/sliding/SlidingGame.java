package puzzle.sliding;

import game.core.GameEngine;
import game.io.ConsoleInput;

import java.io.PrintStream;
import java.util.Optional;

public final class SlidingGame {

    public static void main(String[] args) {
        PrintStream out = System.out;
        ConsoleInput in = new ConsoleInput();

        out.println("Welcome to Sliding Puzzle!");
        out.println("This program demonstrates an OO design that scales to other turn-based games.");
        out.println();

        out.print("Please enter your player name: ");
        String name = in.readLine();
        if (name == null || name.trim().isEmpty()) name = "Player";

        int w = promptInt(in, out, "Enter board width (columns, min 2, max 10): ", 2, 10);
        int h = promptInt(in, out, "Enter board height (rows,   min 2, max 10): ", 2, 10);

        SlidingRules rules = new SlidingRules();
        ConsoleGridRenderer renderer = new ConsoleGridRenderer();

        boolean playAgain;
        do {
            SlidingBoard start = rules.randomSolvable(w, h);

            GameEngine<SlidingBoard, SlideMove> engine =
                    new GameEngine<>(rules, renderer, in, out);

            out.println();
            out.println("OK " + name + ", try to solve the " + w + "x" + h + " puzzle.");
            out.println("Type a TILE NUMBER that is adjacent to the blank to slide it.");
            out.println("Type 'q' or 'quit' to exit this game.");
            out.println();

            engine.run(start, board -> {
                out.print("Which tile do you want to slide to the empty space? ");
                String line = in.readLine();
                if (line == null) return Optional.empty();
                line = line.trim().toLowerCase();
                if (line.equals("q") || line.equals("quit")) return Optional.empty();

                try {
                    int tile = Integer.parseInt(line);
                    int max = board.getWidth() * board.getHeight() - 1;
                    if (tile < 1 || tile > max) {
                        out.println("Invalid tile number. Must be in [1, " + max + "].");
                        return requestAgain();
                    }
                    return Optional.of(new SlideMove(tile));
                } catch (NumberFormatException nfe) {
                    out.println("Please enter a tile number, or 'q' to quit.");
                    return requestAgain();
                }
            });

            out.print("Play again? (y/n): ");
            String again = in.readLine();
            playAgain = (again != null && again.trim().toLowerCase().startsWith("y"));
        } while (playAgain);

        out.println("Thanks for playing!");
    }

    private static Optional<SlideMove> requestAgain() {
        return Optional.of(new SlideMove(-1))
                .filter(m -> false);
    }

    private static int promptInt(ConsoleInput in, PrintStream out, String prompt, int min, int max) {
        while (true) {
            out.print(prompt);
            String line = in.readLine();
            if (line == null) System.exit(0);
            try {
                int v = Integer.parseInt(line.trim());
                if (v < min || v > max) {
                    out.println("Please enter a number in [" + min + ", " + max + "].");
                } else {
                    return v;
                }
            } catch (NumberFormatException e) {
                out.println("Please enter a valid integer.");
            }
        }
    }
}
