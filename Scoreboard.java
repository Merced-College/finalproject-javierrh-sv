import java.util.ArrayList;
//Javier Ramirez
//12/4/2025

 // Scoreboard class - Tracks round outcomes using arrays
 //Demonstrates array usage for storing win/loss/tie outcomes
 
public class Scoreboard {
    private ArrayList<String> roundOutcomes;
    
    public Scoreboard() {
        this.roundOutcomes = new ArrayList<>();
    }
    
    public void addRoundOutcome(String outcome) {
        roundOutcomes.add(outcome);
    }
    
    public int getTotalRounds() {
        return roundOutcomes.size();
    }
    
    public String getOutcomes() {
        return roundOutcomes.toString();
    }
    
    public String[] getOutcomesArray() {
        return roundOutcomes.toArray(new String[0]);
    }
}
