package puzzle.sliding;

import game.core.Move;

public final class SlideMove implements Move {
    public final int tileValue;

    public SlideMove(int tileValue) {
        this.tileValue = tileValue;
    }

    @Override
    public String toString() { return "Slide(" + tileValue + ")"; }
}
