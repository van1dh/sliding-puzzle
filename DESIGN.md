# Sliding Puzzle – Design Documentation

## Project Goal

The objective is to design and implement a clean, **object-oriented sliding puzzle game** in Java.

Key requirements:
- **Correctness**: Only solvable puzzles, valid moves, correct solved-state detection
- **Scalability**: Support arbitrary board sizes (m × n)
- **Extensibility**: Allow reuse of the engine and abstractions for other games (e.g., Lights Out, Sokoban, Sudoku)

---

## Class Structure (Architecture)

### Core Framework

| Class / Interface | Responsibility |
|-------------------|----------------|
| `Board<M>`        | Represents the board state. Provides legal moves, apply(move), and solved check. |
| `Move`            | Marker interface representing a game action. |
| `RuleSet<B>`      | Validates initial boards (e.g., solvability) and determines terminal states. |
| `Renderer<B>`     | Renders the board to an output medium (console or GUI). |
| `GameEngine<B,M>` | Turn-based game loop engine. Runs the game until solved or exited. |

### Sliding Puzzle Implementation

| Class             | Responsibility |
|-------------------|----------------|
| `SlidingBoard`    | Stores the puzzle grid, implements moves, solved-state detection. |
| `SlideMove`       | Represents a move: "slide tile X into the blank". |
| `SlidingRules`    | Checks solvability and generates random solvable puzzles. |
| `ConsoleGridRenderer` | Prints the board as ASCII art in the terminal. |
| `Position`        | Simple row/column coordinate pair. |

### Input Handling

| Class             | Responsibility |
|-------------------|----------------|
| `ConsoleInput`    | Wrapper for `Scanner`, handles user text input. |
| `Main`            | Entry point. Sets up board size, initializes game, runs engine. |

---

## 🧩 UML Diagram

```text
                ┌──────────────────────────┐
                │        GameEngine        │
                │  + run(initial, prompt)  │
                └───────────┬──────────────┘
                            │ uses
                            │
            ┌───────────────┼────────────────┬───────────────────┐
            │               │                │                   │
     ┌────────────┐   ┌──────────────┐  ┌─────────────┐   ┌────────────────┐
     │  RuleSet   │   │   Renderer   │  │   Board     │   │     Move       │
     └─────┬──────┘   └──────┬───────┘  └────┬────────┘   └────────────────┘
           │                 │               │
           │implements       │implements     │implements
           │                 │               │
     ┌───────────────┐  ┌────────────────┐  ┌─────────────────┐
     │ SlidingRules  │  │ConsoleGridRend.│  │  SlidingBoard   │
     └───────────────┘  └────────────────┘  └─────────┬───────┘
                                                      uses
                                                ┌──────────────┐
                                                │  SlideMove   │
                                                └──────────────┘
```