package game;

import java.util.ArrayList;

/**
The Player class manages and stores the player's profile
@author Nicholas Vertentes
*/

public class Player implements boardgame.Saveable{
    
    private String thePlayer;
    private int wins;
    private int loses;
    private int games;

    /**
    Creates a new player instance
    @param player The name of the player
    @param won How many wins the player has attained
    @param lost How many loses the player has attained
    @param played How many games the player has played
    */

    public Player(String player, int won, int lost, int played){
        thePlayer = player;
        wins = won;
        loses = lost;
        games = played;
    }

    /**
    Creates a new player instance with no wins, loses, or games played
    @param player The name of the player
    */

    public Player(String player){
        this(player, 0, 0, 0);
    }

    /**
    Increments the player's games won
    */

    public void incrementWin(){
        ++wins;
    }

    /**
    Increments the player's games lost
    */

    public void incrementLoss(){
        ++loses;
    }

    /**
    Increments the player's games played
    */

    public void incrementPlayed(){
        ++games;
    }

    /**
    Creates a formatted string of the player's profile to be saved in a text file
    @return The formatted string of the player's profile to be saved in a text file
    */

    @Override
    public String getStringToSave(){
        String toPrint = "";
        
        toPrint = toPrint + thePlayer  + "\n";
        toPrint = toPrint + String.valueOf(wins) + "\n";
        toPrint = toPrint + String.valueOf(loses) + "\n";
        toPrint = toPrint + String.valueOf(games) + "\n";

        return toPrint;
    }

    /**
    Loads the player profile from a formatted string in a text file
    @param saved The formatted string in a text file representing the player profile
    */

    @Override
    public void loadSavedString(String saved){
        ArrayList<String> playerParts = new ArrayList<>();

        String[] parts = saved.split("\n");

        for(String str: parts){
            playerParts.add(str);
        }

        thePlayer = playerParts.get(0);
        wins = Integer.valueOf(playerParts.get(1)).intValue();
        loses = Integer.valueOf(playerParts.get(2)).intValue();
        games = Integer.valueOf(playerParts.get(3)).intValue();
    }

    /**
    Creates a formatted string of the player's profile to be displayed
    @return The formatted string of the player's profile to be displayed
    */

    @Override
    public String toString(){
        String toPrint = "";
        
        toPrint = toPrint + thePlayer  + "\n";
        toPrint = toPrint + "Wins: " + String.valueOf(wins) + "\n";
        toPrint = toPrint + "Loses: " + String.valueOf(loses) + "\n";
        toPrint = toPrint + "Played: " + String.valueOf(games) + "\n";

        return toPrint;
    }

}