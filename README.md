[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=21917287)

Rock Paper Scissors Game

Javier Ramirez  
12/9/2025

The rock paper scissors game lets you play against the computer. It keeps track of your wins, losses, and ties as you play multiple rounds.

To run the program, compile all the files with javac .java and then run java rockPaperScissors. Just pick a number between 1 and 4, and the game will tell you if you won, lost, or tied against the computer. Your score gets saved so you can see your history next time you play.

The main part of the code checks if rock beats scissors, paper beats rock, and scissors beats paper to determine the winner. I used recursion to keep asking if you want to play again until you give a real answer like yes or no. The computer just generates random numbers 0, 1, or 2 to pick its move.

I used an ArrayList to store the results of each round because I didn't know how many rounds the player would want to go. I used numbers instead of strings to make the comparisons faster. I saved the scores to a file so you can see your game history when you come back to play again.

The program had errors at first if you typed letters instead of numbers because the scanner would crash. I fixed it by checking if the input is actually a number before trying to read it, so now it just asks you to try again if you enter something wrong.

In the next version I could add difficulty levels, stats showing your win percentage, two player mode.

The files in this project are rockPaperScissors.java, Game.java, Player.java, Scoreboard.java, and PersistentScoreboard.java.


