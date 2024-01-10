package game;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import boardgame.Saveable;
import tictactoe.TicTacToeGame;
import numericaltictactoe.NumericalTicTacToeGame;

/**
The GameStorage class handles the saving of games and users to a text file
This class has static methods that wrap the java file saving and loading mechanisms
@author Nicholas Vertentes
*/

public class GameStorage{

    /**
    Saves a game or user profile that implements the interface Saveable to a text file
    @param toSave The Saveable instance that is to be saved
    @throws IOException IOException thrown if file access error occurs
    */

    public static void saveGame(Saveable toSave, String filePath) throws IOException{
        
        FileWriter writer = new FileWriter(filePath);
        writer.write(toSave.getStringToSave());
        writer.close();
    }

    /**
    Loads a game or user profile that implements the interface Saveable from a text file
    @param toSave The Saveable instance that is to be loaded
    @throws IOException IOException thrown if file access error occurs
    */

    public static void loadGame(Saveable toLoad, String filePath) throws IOException{
        String finalStr = "";
        String tempStr = "";

        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        while((tempStr = reader.readLine()) != null){
            finalStr = finalStr + tempStr + "\n";
        }

        toLoad.loadSavedString(finalStr);

        reader.close();
    }
}