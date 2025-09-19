package puzzle.sliding;

import game.core.Renderer;

import java.io.PrintStream;

public final class ConsoleGridRenderer implements Renderer<SlidingBoard> {

    @Override
    public void render(SlidingBoard board, PrintStream out) {
        int w = board.getWidth(), h = board.getHeight();
        int[] a = board.raw();

        int maxVal = w * h - 1;
        int cellWidth = Integer.toString(maxVal).length();
        String border = repeat("+", w).replace("+", "+") + "\n";

        StringBuilder line = new StringBuilder();
        for (int c = 0; c < w; c++) {
            line.append("+").append(repeat("-", cellWidth + 2));
        }
        line.append("+");

        out.println(line.toString());
        for (int r = 0; r < h; r++) {
            StringBuilder row = new StringBuilder();
            for (int c = 0; c < w; c++) {
                int v = a[r * w + c];
                String s = (v == 0) ? repeat(" ", cellWidth) : padLeft(Integer.toString(v), cellWidth);
                row.append("| ").append(s).append(" ");
            }
            row.append("|");
            out.println(row.toString());
            out.println(line.toString());
        }
    }

    private static String repeat(String s, int n) {
        StringBuilder sb = new StringBuilder(s.length() * n);
        for (int i = 0; i < n; i++) sb.append(s);
        return sb.toString();
    }

    private static String padLeft(String s, int width) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < width; i++) sb.append(' ');
        sb.append(s);
        return sb.toString();
    }
}
