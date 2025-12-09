//Javier Ramirez
//12/4/2025

import java.util.Scanner;
import java.util.Random;

public class Game {
    private Player player;
    private Player computer;
    private Scoreboard scoreboard;
    private PersistentScoreboard persistentScoreboard;
    private Scanner scanner;
    private Random random;
    private static final String[] CHOICES = {"Rock", "Paper", "Scissors"};
    private static final int ROCK = 0;
    private static final int PAPER = 1;
    private static final int SCISSORS = 2;
    private static final String SCOREBOARD_FILE = "scoreboard.txt";
    
    public Game() {
        this.player = new Player("Player");
        this.computer = new Player("Computer");
        this.scoreboard = new Scoreboard();
        this.persistentScoreboard = new PersistentScoreboard(SCOREBOARD_FILE);
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }
    
    public void start() {
        System.out.println("Welcome to Rock Paper Scissors!");
        displayPreviousStats();
        playGameLoop();
        displayFinalResults();
        persistentScoreboard.saveScore(player.getName(), player.getWins(), computer.getWins(), player.getTies());
        scanner.close();
    }
    
    private void displayPreviousStats() {
        System.out.println("\n========== Previous Game Stats ==========");
        persistentScoreboard.displayAllScores();
        System.out.println("=========================================\n");
    }
    
    private void playGameLoop() {
        boolean continueGame = true;
        
        while (continueGame) {
            displayMenu();
            int playerChoice = getPlayerInput();
            
            if (playerChoice == -1) {
                System.out.println("Invalid input. Please try again.");
                continue;
            }
            
            int computerChoice = getComputerChoice();
            playRound(playerChoice, computerChoice);
            
            continueGame = askPlayAgain();
        }
    }
    
    private void displayMenu() {
        System.out.println("\n========== Rock Paper Scissors ==========");
        System.out.println("1. Rock");
        System.out.println("2. Paper");
        System.out.println("3. Scissors");
        System.out.println("4. Quit Game");
        System.out.println("=========================================");
    }
    
    private int getPlayerInput() {
        System.out.print("Enter your choice (1-4): ");
        
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            return -1;
        }
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice == 4) {
            System.out.println("Thanks for playing! Exiting game...");
            return -1;
        }
        
        if (choice < 1 || choice > 3) {
            return -1;
        }
        
        return choice - 1;
    }
    
    private int getComputerChoice() {
        return random.nextInt(3);
    }
    
    private void playRound(int playerChoice, int computerChoice) {
        System.out.println("\n--- Round " + (scoreboard.getTotalRounds() + 1) + " ---");
        System.out.println("Player chose: " + CHOICES[playerChoice]);
        System.out.println("Computer chose: " + CHOICES[computerChoice]);
        
        String result = determineWinner(playerChoice, computerChoice);
        
        // Store round outcome in array
        scoreboard.addRoundOutcome(result);
        
        // Update player stats
        if (result.equals("WIN")) {
            System.out.println("You win this round!");
            player.addWin();
        } else if (result.equals("LOSS")) {
            System.out.println("Computer wins this round!");
            computer.addWin();
        } else {
            System.out.println("It's a tie!");
            player.addTie();
        }
        
        displayCurrentScore();
    }
    
    private String determineWinner(int player, int computer) {
        if (player == computer) {
            return "TIE";
        }
        
        // Rock beats Scissors, Paper beats Rock, Scissors beats Paper
        if ((player == ROCK && computer == SCISSORS) ||
            (player == PAPER && computer == ROCK) ||
            (player == SCISSORS && computer == PAPER)) {
            return "WIN";
        }
        
        return "LOSS";
    }
    
    private void displayCurrentScore() {
        System.out.println("\n--- Current Score ---");
        System.out.println("Player Wins: " + player.getWins());
        System.out.println("Computer Wins: " + computer.getWins());
        System.out.println("Ties: " + player.getTies());
    }
    
    // Recursive method to ask if player wants to play again
    private boolean askPlayAgain() {
        System.out.print("\nPlay another round? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        
        if (response.equals("yes") || response.equals("y")) {
            return true;
        } else if (response.equals("no") || response.equals("n")) {
            return false;
        } else {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            return askPlayAgain(); // Recursion
        }
    }
    
    private void displayFinalResults() {
        System.out.println("\n========== Final Results ==========");
        System.out.println("Total Rounds Played: " + scoreboard.getTotalRounds());
        System.out.println("\nPlayer Stats:");
        System.out.println("  Wins: " + player.getWins());
        System.out.println("  Losses: " + computer.getWins());
        System.out.println("  Ties: " + player.getTies());
        System.out.println("\nRound Outcomes: " + scoreboard.getOutcomes());
        
        if (player.getWins() > computer.getWins()) {
            System.out.println("\nPlayer won the game!");
        } else if (computer.getWins() > player.getWins()) {
            System.out.println("\nComputer won the game!");
        } else {
            System.out.println("\nThe game ended in a tie!");
        }
        System.out.println("===================================");
        System.out.println("Scores have been saved to " + SCOREBOARD_FILE);
    }
}
