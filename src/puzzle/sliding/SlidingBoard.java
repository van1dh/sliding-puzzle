package puzzle.sliding;

import game.core.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class SlidingBoard implements Board<SlideMove> {

    private final int width, height;
    private final int[] tiles;

    public SlidingBoard(int width, int height, int[] tiles) {
        if (width <= 1 || height <= 1) throw new IllegalArgumentException("Minimum size is 2x2");
        if (tiles == null || tiles.length != width * height)
            throw new IllegalArgumentException("Bad tiles length");
        this.width = width;
        this.height = height;
        this.tiles = Arrays.copyOf(tiles, tiles.length);
    }

    public static SlidingBoard solved(int w, int h) {
        int n = w * h;
        int[] t = new int[n];
        for (int i = 0; i < n - 1; i++) t[i] = i + 1;
        t[n - 1] = 0;
        return new SlidingBoard(w, h, t);
    }

    public int indexOf(int value) {
        for (int i = 0; i < tiles.length; i++) if (tiles[i] == value) return i;
        return -1;
    }

    public Position posOf(int value) {
        int idx = indexOf(value);
        if (idx < 0) return null;
        return new Position(idx / width, idx % width);
    }

    public int valueAt(int r, int c) { return tiles[r * width + c]; }

    @Override public int getWidth() { return width; }
    @Override public int getHeight() { return height; }

    @Override
    public boolean isSolved() {
        int n = width * height;
        for (int i = 0; i < n - 1; i++) if (tiles[i] != i + 1) return false;
        return tiles[n - 1] == 0;
    }

    @Override
    public Board<SlideMove> copy() {
        return new SlidingBoard(width, height, tiles);
    }

    @Override
    public List<SlideMove> legalMoves() {
        int blankIdx = indexOf(0);
        int br = blankIdx / width, bc = blankIdx % width;
        List<SlideMove> moves = new ArrayList<>(4);
        if (br > 0)   moves.add(new SlideMove(valueAt(br - 1, bc)));
        if (br + 1 < height) moves.add(new SlideMove(valueAt(br + 1, bc)));
        if (bc > 0)   moves.add(new SlideMove(valueAt(br, bc - 1)));
        if (bc + 1 < width)  moves.add(new SlideMove(valueAt(br, bc + 1)));
        return moves;
    }

    @Override
    public Board<SlideMove> apply(SlideMove move) {
        int tIdx = indexOf(move.tileValue);
        if (tIdx < 0) throw new IllegalArgumentException("No such tile: " + move.tileValue);

        int bIdx = indexOf(0);
        int tr = tIdx / width, tc = tIdx % width;
        int br = bIdx / width, bc = bIdx % width;
        int manhattan = Math.abs(tr - br) + Math.abs(tc - bc);
        if (manhattan != 1) throw new IllegalArgumentException("Tile not adjacent to blank.");

        int[] next = Arrays.copyOf(tiles, tiles.length);
        next[bIdx] = move.tileValue;
        next[tIdx] = 0;
        return new SlidingBoard(width, height, next);
    }

    public int[] raw() { return Arrays.copyOf(tiles, tiles.length); }
}
