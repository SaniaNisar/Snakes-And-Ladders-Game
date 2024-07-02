import java.util.*;

// Player Class
class Player {
    private String name;
    private int currentPosition;

    public Player(String name) {
        this.name = name;
        this.currentPosition = 0; // Start at position 0
    }

    public String getName() {
        return name;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int position) {
        this.currentPosition = position;
    }
}

// Board Class
class Board {
    private int[] board;
    private int size;

    public Board(int size) {
        this.size = size;
        board = new int[size];
        initializeBoard();
    }

    private void initializeBoard() {
        // Initialize board with normal positions (0), snakes (-1), and ladders (+1)
        Arrays.fill(board, 0);
        // Example snakes and ladders positions
        board[14] = -10; // Snake at position 14, moves down to position 4
        board[8] = 10;   // Ladder at position 8, moves up to position 18
    }

    public int getPosition(int index) {
        return board[index];
    }

    public int getSize() {
        return size;
    }
}

// Game Class
class Game {
    private Player[] players;
    private Board board;
    private int currentPlayerIndex;
    private boolean gameOver;

    public Game(String[] playerNames, int boardSize) {
        players = new Player[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            players[i] = new Player(playerNames[i]);
        }
        board = new Board(boardSize);
        currentPlayerIndex = 0; // Start with the first player
        gameOver = false;
    }

    public void playTurn() {
        if (!gameOver) {
            Player currentPlayer = players[currentPlayerIndex];
            int diceRoll = rollDice();
            int newPosition = currentPlayer.getCurrentPosition() + diceRoll;
            int finalPosition = calculateFinalPosition(newPosition);
            currentPlayer.setCurrentPosition(finalPosition);
            printPlayerMove(currentPlayer, diceRoll, finalPosition);
            checkWinCondition(currentPlayer);
            if (!gameOver) {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
            }
        }
    }

    private int rollDice() {
        return (int) (Math.random() * 6) + 1; // Simulates a dice roll between 1 and 6
    }

private int calculateFinalPosition(int newPosition) {
    if (newPosition >= board.getSize()) {
        return board.getSize() - 1; // Ensure player doesn't move beyond board size
    }
    int positionModifier = board.getPosition(newPosition);
    int finalPosition = newPosition + positionModifier;

    // Check if final position is within board bounds
    if (finalPosition < 0) {
        finalPosition = 0; // Prevent moving below position 0 (beginning of board)
    } else if (finalPosition >= board.getSize()) {
        finalPosition = board.getSize() - 1; // Ensure player doesn't move beyond board size
    }

    return finalPosition;
}


    private void printPlayerMove(Player player, int diceRoll, int finalPosition) {
        System.out.println(player.getName() + " rolled a " + diceRoll +
                " and moved from " + player.getCurrentPosition() + " to " + finalPosition);
    }

    private void checkWinCondition(Player player) {
        if (player.getCurrentPosition() >= board.getSize() - 1) {
            System.out.println(player.getName() + " wins the game!");
            gameOver = true;
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getCurrentPlayerName() {
        return players[currentPlayerIndex].getName();
    }
}

// Main Class
public class SnakesAndLaddersGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Snakes and Ladders!");

        // Initialize game with two players and a board of size 100
        String[] playerNames = {"Player 1", "Player 2"};
        Game game = new Game(playerNames, 100);

        // Game loop
        while (!game.isGameOver()) {
            System.out.println("\nIt's " + game.getCurrentPlayerName() + "'s turn. Press enter to roll the dice.");
            scanner.nextLine(); // Wait for user to press enter
            game.playTurn();
        }

        scanner.close();
    }
}
