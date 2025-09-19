package game.core;

import java.io.PrintStream;

public interface Renderer<B extends Board<? extends Move>> {
    void render(B board, PrintStream out);
}