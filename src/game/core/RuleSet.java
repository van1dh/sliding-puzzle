package game.core;

public interface RuleSet<B extends Board<? extends Move>> {

    void validateInitial(B board);

    default boolean isTerminal(B board) { return board.isSolved(); }
}