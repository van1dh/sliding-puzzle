package puzzle.sliding;

import game.core.RuleSet;
import game.core.Board;

import java.util.Random;

public final class SlidingRules implements RuleSet<SlidingBoard> {

    @Override
    public void validateInitial(SlidingBoard board) {
        if (!isSolvable(board)) {
            throw new IllegalArgumentException("Initial board is not solvable for " +
                    board.getWidth() + "x" + board.getHeight());
        }
    }

    public boolean isSolvable(SlidingBoard b) {
        int w = b.getWidth();
        int h = b.getHeight();
        int[] arr = b.raw();
        int N = w * h;

        int inv = 0;
        for (int i = 0; i < N; i++) if (arr[i] != 0)
            for (int j = i + 1; j < N; j++) if (arr[j] != 0 && arr[i] > arr[j]) inv++;

        int blankIdx = -1;
        for (int i = 0; i < N; i++) if (arr[i] == 0) { blankIdx = i; break; }
        int blankRow = blankIdx / w;
        int blankFromBottom = h - blankRow;

        if (w % 2 == 1) {

            return inv % 2 == 0;
        } else {
            boolean blankEven = (blankFromBottom % 2 == 0);
            boolean invOdd = (inv % 2 == 1);
            return (blankEven && invOdd) || (!blankEven && !invOdd);
        }
    }

    public SlidingBoard randomSolvable(int w, int h, long seed) {
        int N = w * h;
        int[] arr = new int[N];
        for (int i = 0; i < N - 1; i++) arr[i] = i + 1;
        arr[N - 1] = 0;

        Random rnd = new Random(seed);
        SlidingBoard candidate;
        do {
            for (int i = N - 1; i > 0; i--) {
                int j = rnd.nextInt(i + 1);
                int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
            }
            candidate = new SlidingBoard(w, h, arr);
        } while (!isSolvable(candidate) || candidate.isSolved());
        return candidate;
    }

    public SlidingBoard randomSolvable(int w, int h) {
        return randomSolvable(w, h, System.nanoTime());
    }
}
