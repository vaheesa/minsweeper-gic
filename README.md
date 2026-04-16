# Sudoku CLI

A command-line Sudoku game built with clean architecture and SOLID principles.

## Quick Start
## Dependencies

- **Java 17+** (uses records and switch expressions)
- No external dependencies - pure Java implementation

### Build

**Windows (CMD & PowerShell):**
```cmd prmt & PowerShell
cmd /c "dir /s /b src\*.java > sources.txt && javac -d out @sources.txt && del sources.txt"

```

**Linux/macOS:**
```bash
javac -d out $(find src/main/java src/test/java -name "*.java")
```

### Run the Game
```bash CMD & PowerShell
java -cp out com.gic.App
```

### Run Tests
```bash CMD & PowerShell
java -cp out com.gic.TestRunner
```

## How to Play

1. The game displays a 9x9 Sudoku grid with 30 pre-filled numbers
2. Empty cells are shown as `_`
3. Enter commands to play:

| Command | Example | Description |
|---------|---------|-------------|
| Set cell | `A3 4` | Place number 4 in row A, column 3 |
| Clear cell | `C5 clear` | Clear the cell at row C, column 5 |
| Hint | `hint` | Reveal one correct number |
| Check | `check` | Validate current grid for rule violations |
| Quit | `quit` | Exit the game |

## Project Structure

```
src/main/java/com/gic/sudoku/
├── SudokuConstants.java      # Centralized configuration & messages
├── api/                      # Interfaces (contracts)
│   ├── GridValidator.java
│   ├── PuzzleSolver.java
│   └── PuzzleGenerator.java
├── impl/                     # Implementations
│   ├── StandardGridValidator.java
│   ├── BacktrackingSolver.java
│   └── RandomPuzzleGenerator.java
├── model/                    # Data classes
│   └── SudokuPuzzle.java
└── cli/                      # User interface
    ├── SudokuCli.java
    ├── CommandParser.java
    └── GridRenderer.java
```

## Architecture & Design Principles
## Class Responsibilities
Each class has one clear purpose:
- `StandardGridValidator` - validates grid rules only
- `BacktrackingSolver` - solves puzzles using backtracking algorithm
- `RandomPuzzleGenerator` - generates puzzles with specified clues
- `CommandParser` - parses user input into commands
- `GridRenderer` - renders the grid to string format

#### How to Extend New Features or Algorithms
- Add new solving algorithms by implementing `PuzzleSolver`
- Add new validation rules by implementing `GridValidator`
- Add new puzzle generation strategies by implementing `PuzzleGenerator`

**Example: Adding a new solver**

public class ConstraintPropagationSolver implements PuzzleSolver {
    // Implement a different solving algorithm
}

Interfaces are small and focused:
- `GridValidator` - validation only
- `PuzzleSolver` - solving only
- `PuzzleGenerator` - generation only


### Centralized Configuration

All constants and messages are in `SudokuConstants.java`:
- **Grid configuration**: `GRID_SIZE`, `BOX_SIZE`, `MIN_VALUE`, `MAX_VALUE`
- **UI messages**: `MSG_WELCOME`, `MSG_MOVE_ACCEPTED`, etc.

To change any configuration or message, modify only this single file.

## Extending the Application

### Add a New Solving Algorithm
1. Create a new class implementing `PuzzleSolver`
2. Inject it into `SudokuCli` via the constructor

### Add New Validation Rules
1. Create a new class implementing `GridValidator`
2. Extend existing validation or create composite validators

### Add New UI (GUI, Web)
1. Create a new UI class that uses the existing `api` interfaces
2. Inject the same `impl` classes for game logic

### Add Difficulty Levels
1. Modify `RandomPuzzleGenerator` or create `DifficultPuzzleGenerator`
2. Adjust the number of clues based on difficulty

## Testing

Tests are organized to mirror the main source structure:
```
src/test/java/com/gic/sudoku/
├── impl/
│   ├── StandardGridValidatorTest.java
│   ├── BacktrackingSolverTest.java
│   └── RandomPuzzleGeneratorTest.java
├── model/
│   └── SudokuPuzzleTest.java
└── cli/
    └── SudokuCliTest.java
```

Run all tests:
```bash
java -cp out com.gic.TestRunner
```


