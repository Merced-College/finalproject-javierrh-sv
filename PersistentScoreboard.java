//Javier Ramirez
//12/4/2025

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

// Keeps track of all scores in a file so we can see past games
public class PersistentScoreboard {
    private String filename;
    
    public PersistentScoreboard(String filename) {
        this.filename = filename;
    }
    
    
    // Save score to file after each game
    public void saveScore(String playerName, int wins, int losses, int ties) {
        try {
            String scoreEntry = playerName + " | Wins: " + wins + " | Losses: " + losses + " | Ties: " + ties;
            Files.write(
                Paths.get(filename),
                (scoreEntry + "\n").getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            );
            System.out.println("Score saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving score: " + e.getMessage());
        }
    }
    
    
    // Show all the past game scores
    public void displayAllScores() {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                System.out.println("No previous games recorded yet.");
                return;
            }
            
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(filename)));
            if (lines.isEmpty()) {
                System.out.println("No previous games recorded yet.");
                return;
            }
            
            System.out.println("Game History:");
            for (int i = 0; i < lines.size(); i++) {
                System.out.println((i + 1) + ". " + lines.get(i));
            }
        } catch (IOException e) {
            System.err.println("Error reading scores: " + e.getMessage());
        }
    }
    
    
    // Clear all the scores if needed
    public void clearScores() {
        try {
            Files.deleteIfExists(Paths.get(filename));
            System.out.println("Score history cleared.");
        } catch (IOException e) {
            System.err.println("Error clearing scores: " + e.getMessage());
        }
    }
}
