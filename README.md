# Sliding Puzzle

A clean, object-oriented infrastructure for turn-based board games, demonstrated with the classic sliding puzzle.

## Student Info
- Name: Yongyi Xie
- ID: 85683460
- Course: CS 611
- Assignment: Sliding Puzzle

## Files
- `src/game/core/*`: Reusable game engine interfaces (`Board`, `Move`, `RuleSet`, `Renderer`) and `GameEngine`.
- `src/game/io/*`: Console input wrapper.
- `src/puzzle/sliding/*`: Sliding puzzle implementation and `main`.

## How to Build / Run
```bash
javac -source 1.8 -target 1.8 -d out $(find src -name "*.java")
java -cp out puzzle.sliding.SlidingGame
```

## I/0 Example
```bash
Welcome to Sliding Puzzle!
This program demonstrates an OO design that scales to other turn-based games.

Please enter your player name: xyy0208
Enter board width (columns, min 2, max 10): 3
Enter board height (rows,   min 2, max 10): 3

OK xyy0208, try to solve the 3x3 puzzle.
Type a TILE NUMBER that is adjacent to the blank to slide it.
Type 'q' or 'quit' to exit this game.
+---+---+---+
|   | 7 | 2 |
+---+---+---+
| 6 | 1 | 4 |
+---+---+---+
| 3 | 5 | 8 |
+---+---+---+
Which tile do you want to slide to the empty space? 2
Illegal move: Tile not adjacent to blank.
+---+---+---+
|   | 7 | 2 |
+---+---+---+
| 6 | 1 | 4 |
+---+---+---+
| 3 | 5 | 8 |
+---+---+---+
Which tile do you want to slide to the empty space? 10
Invalid tile number. Must be in [1, 8].
Goodbye!
Play again? (y/n): y

OK xyy0208, try to solve the 3x3 puzzle.
Type a TILE NUMBER that is adjacent to the blank to slide it.
Type 'q' or 'quit' to exit this game.

+---+---+---+
|   | 2 | 4 |
+---+---+---+
| 3 | 7 | 8 |
+---+---+---+
| 5 | 6 | 1 |
+---+---+---+
Which tile do you want to slide to the empty space? 2

(...)

Which tile do you want to slide to the empty space? 5
+---+---+---+
| 1 | 2 | 3 |
+---+---+---+
| 4 | 5 | 6 |
+---+---+---+
| 7 |   | 8 |
+---+---+---+
Which tile do you want to slide to the empty space? 8
+---+---+---+
| 1 | 2 | 3 |
+---+---+---+
| 4 | 5 | 6 |
+---+---+---+
| 7 | 8 |   |
+---+---+---+
Puzzle solved!
Play again? (y/n): n
Thanks for playing!
```
