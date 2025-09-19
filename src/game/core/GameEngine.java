package game.core;

import game.io.ConsoleInput;

import java.io.PrintStream;
import java.util.Optional;

public class GameEngine<
        B extends Board<M>,
        M extends Move
        > {

    private final RuleSet<B> rules;
    private final Renderer<B> renderer;
    private final ConsoleInput input;
    private final PrintStream out;

    public interface MoveParser<M> {
        Optional<M> parse(String line);
    }

    public GameEngine(RuleSet<B> rules, Renderer<B> renderer, ConsoleInput input, PrintStream out) {
        this.rules = rules;
        this.renderer = renderer;
        this.input = input;
        this.out = out;
    }

    public void run(B initial, MovePrompt<M> prompt) {
        rules.validateInitial(initial);
        B board = initial;

        while (!rules.isTerminal(board)) {
            renderer.render(board, out);
            Optional<M> maybeMove = prompt.requestMove(board);
            if (!maybeMove.isPresent()) {
                out.println("Goodbye!");
                return;
            }
            M move = maybeMove.get();
            try {
                board = (B) board.apply(move);
            } catch (IllegalArgumentException iae) {
                out.println("Illegal move: " + iae.getMessage());
            }
        }
        renderer.render(board, out);
        out.println("Puzzle solved!");
    }

    @FunctionalInterface
    public interface MovePrompt<M> {
        Optional<M> requestMove(Board<M> currentBoard);
    }
}
