# 🐍🐍 Snakes and Ladders Game 🐍🐍

## Overview
This is a simple command-line implementation of the classic board game Snakes and Ladders in Java. The game allows two players to take turns rolling a die, moving their pieces according to the die rolls, and encountering snakes and ladders that can either help or hinder their progress. The first player to reach or exceed the final position on the board wins the game.

## Features
- Two-player mode
- Random dice rolls
- Snakes and ladders that affect player positions
- Win condition check

## How to Play
1. Clone the repository to your local machine.
2. Compile and run the `SnakesAndLaddersGame` class.
3. Follow the prompts to play the game. Players take turns pressing enter to roll the dice.
4. The game will display the result of each dice roll and the updated positions of the players.
5. The first player to reach or exceed the final position on the board wins the game.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- A Java IDE or text editor

## Code Structure
- Player.java: Represents a player in the game with attributes for name and current position.
- Board.java: Represents the game board with positions of snakes and ladders.
- Game.java: Manages the game flow, including player turns, dice rolls, and win conditions.
- SnakesAndLaddersGame.java: The main class that sets up the game and starts the game loop.
