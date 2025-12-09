//Javier Ramirez
//12/4/2025
// Player class - Stores player information and statistics
 
public class Player {
    private String name;
    private int wins;
    private int ties;
    
    public Player(String name) {
        this.name = name;
        this.wins = 0;
        this.ties = 0;
    }
    
    public void addWin() {
        this.wins++;
    }
    
    public void addTie() {
        this.ties++;
    }
    
    public int getWins() {
        return wins;
    }
    
    public int getTies() {
        return ties;
    }
    
    public String getName() {
        return name;
    }
}
