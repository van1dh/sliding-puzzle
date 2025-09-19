package game.core;

import java.util.List;

public interface Board<M extends Move> {

    int getWidth();

    int getHeight();

    boolean isSolved();

    Board<M> copy();

    List<M> legalMoves();

    Board<M> apply(M move);
}