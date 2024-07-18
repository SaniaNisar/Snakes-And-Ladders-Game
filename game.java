import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SnakesAndLaddersGame extends JFrame {

    private JPanel boardPanel;
    private JButton rollDiceButton;
    private JLabel diceResultLabel;
    private JLabel turnLabel;
    private JLabel[] playerLabels;

    private static final int BOARD_SIZE = 100;
    private static final Map<Integer, Integer> snakes = new HashMap<>();
    private static final Map<Integer, Integer> ladders = new HashMap<>();
    private int currentPlayerIndex = 0;
    private int[] playerPositions;
    private String[] playerNames;
    private Random random = new Random();

    public SnakesAndLaddersGame(String[] names) {
        this.playerNames = names;
        setTitle("Snakes and Ladders Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        setResizable(false);
        setLocationRelativeTo(null);

        initializeComponents();
        setupBoard();
        setupSnakesAndLadders();

        setVisible(true);
    }

    private void initializeComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(10, 10));

        rollDiceButton = new JButton("Roll Dice");
        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int diceRoll = rollDice();
                diceResultLabel.setText("Dice Rolled: " + diceRoll);
                movePlayer(diceRoll);
            }
        });

        diceResultLabel = new JLabel("Dice Rolled: ");
        diceResultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.add(rollDiceButton);
        controlPanel.add(diceResultLabel);

        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void setupBoard() {
        boardPanel.removeAll();
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        int position = 100;
        playerPositions = new int[playerNames.length];
        playerLabels = new JLabel[playerNames.length];

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                JLabel label = new JLabel(String.valueOf(position), JLabel.CENTER);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                boardPanel.add(label);
                position--;

                if (position == 0)
                    break;
            }
        }

        for (int i = 0; i < playerNames.length; i++) {
            playerLabels[i] = new JLabel(playerNames[i]);
            boardPanel.add(playerLabels[i]);
        }

        pack();
    }

    private void setupSnakesAndLadders() {
        // Define the positions of snakes and ladders
        snakes.put(16, 6);
        snakes.put(47, 26);
        snakes.put(49, 11);
        snakes.put(56, 53);
        snakes.put(62, 19);
        snakes.put(64, 60);
        snakes.put(87, 24);
        snakes.put(93, 73);
        snakes.put(95, 75);
        snakes.put(98, 78);

        ladders.put(1, 38);
        ladders.put(4, 14);
        ladders.put(9, 31);
        ladders.put(21, 42);
        ladders.put(28, 84);
        ladders.put(36, 44);
        ladders.put(51, 67);
        ladders.put(71, 91);
        ladders.put(80, 100);
    }

    private int rollDice() {
        return random.nextInt(6) + 1;
    }

    private void movePlayer(int steps) {
        int currentPosition = playerPositions[currentPlayerIndex];
        int newPosition = currentPosition + steps;

        if (newPosition <= BOARD_SIZE) {
            playerPositions[currentPlayerIndex] = newPosition;

            // Check for snakes
            if (snakes.containsKey(newPosition)) {
                newPosition = snakes.get(newPosition);
                JOptionPane.showMessageDialog(this, "Oops! " + playerNames[currentPlayerIndex] + " encountered a snake at position " + newPosition + ". Moving down to position " + newPosition + ".");
                playerPositions[currentPlayerIndex] = newPosition;
            }

            // Check for ladders
            if (ladders.containsKey(newPosition)) {
                newPosition = ladders.get(newPosition);
                JOptionPane.showMessageDialog(this, "Yay! " + playerNames[currentPlayerIndex] + " found a ladder at position " + newPosition + ". Moving up to position " + newPosition + ".");
                playerPositions[currentPlayerIndex] = newPosition;
            }

            updatePlayerLabels();
            currentPlayerIndex = (currentPlayerIndex + 1) % playerNames.length;

            turnLabel.setText(playerNames[currentPlayerIndex] + "'s turn");
        } else {
            JOptionPane.showMessageDialog(this, "You need " + (BOARD_SIZE - currentPosition) + " to win. Keep trying!");
        }
    }

    private void updatePlayerLabels() {
        for (int i = 0; i < playerLabels.length; i++) {
            int position = playerPositions[i];
            if (position <= BOARD_SIZE) {
                playerLabels[i].setText(playerNames[i] + " at position " + position);
            } else {
                playerLabels[i].setText(playerNames[i] + " won!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                String[] playerNames = new String[2]; // Adjust for more players if needed
                for (int i = 0; i < playerNames.length; i++) {
                    playerNames[i] = JOptionPane.showInputDialog("Enter Player " + (i + 1) + "'s Name:");
                }
                new SnakesAndLaddersGame(playerNames);
            }
        });
    }
}
