package tictactoe;

import java.util.Iterator;
import game.InvalidInputExceptionTTT;
import game.InvalidPositionExceptionTTT;

/**
The TicTacToeGame class represents a TicTacToe game.
The methods within this class handle the state of the game.
@author Nicholas Vertentes
*/

public class TicTacToeGame extends boardgame.BoardGame implements boardgame.Saveable{

    private String thePlayer = "";

    /**
    Creates a new TicTacToe game
    @param wide The width of the gameboard
    @param high The height of the gameboard
    */

    public TicTacToeGame(int wide, int high){
        super(wide, high);
        setGrid(new TicTacToeGrid(wide, high));
    }

    /**
    Places a piece at the specified position on the TicTacToe gameboard
    @param across The horizontal position of desired placement
    @param down The vertical position of desired placement
    @param input The piece to be placed on the gameboard (string representation)
    @return True if the piece was successfully placed, false otherwise
    */

    @Override
    public boolean takeTurn(int across, int down, String input){

        try{
            validateInput(input);
            validatePosition(across, down);
        }catch(InvalidInputExceptionTTT | InvalidPositionExceptionTTT e){
            return false;
        }

        setValue(across, down, input);
        return true;
    }

    /**
    Places a piece at the specified position on the TicTacToe gameboard
    @param across The horizontal position of desired placement
    @param down The vertical position of desired placement
    @param input The piece to be placed on the gameboard (int representation)
    @return True if the piece was successfully placed, false otherwise
    */

    @Override
    public boolean takeTurn(int across, int down, int input){

        return false;
    }

    /**
    Checks if user has input the piece they were assigned
    @param input The input that the user entered
    @throws InvalidInputExceptionTTT Thrown if invalid input is entered
    */

    public void validateInput(String input) throws InvalidInputExceptionTTT{
        if (input.contentEquals(getPlayerTurn())){
            return;
        }

        throw new InvalidInputExceptionTTT();
    }

    /**
    Checks if user has entered input into an empty position
    @param across The horizontal position of desired placement
    @param down The vertical position of desired placement
    @throws InvalidPositionExceptionTTT Thrown if desired position is not empty
    */

    public void validatePosition(int across, int down) throws InvalidPositionExceptionTTT{
        if (getCell(across, down).contentEquals(" ")){
            return;
        }

        throw new InvalidPositionExceptionTTT();
    }

    /**
    Checks win and tie conditions to determine if the game is over
    @return True if game is done, false otherwise
    */

    @Override
    public boolean isDone(){
        return(checkWinner("X") || checkWinner("O") || checkTie());
    }

    private boolean checkWinner(String player){
        
        return (checkColumnWinner(player) || checkRowWinner(player) || checkDiagnolWinner(player));
    }

    private boolean checkColumnWinner(String player){

        for (int i = 1; i <= getWidth(); i++){
            if (getCell(i, 1).contentEquals(player)
            && getCell(i, 2).contentEquals(player)
            && getCell(i, 3).contentEquals(player)){
                return true;
            }
        }

        return false;
    }

    private boolean checkRowWinner(String player){
        
        for (int i = 1; i <= getHeight(); i++){
            if (getCell(1, i).contentEquals(player)
            && getCell(2, i).contentEquals(player)
            && getCell(3, i).contentEquals(player)){
                return true;
            }
        }

        return false;
    }

    private boolean checkDiagnolWinner(String player){

        if (getCell(1, 1).contentEquals(player)
        && getCell(2, 2).contentEquals(player)
        && getCell(3, 3).contentEquals(player)){
            return true;
        }

        if (getCell(3, 1).contentEquals(player)
        && getCell(2, 2).contentEquals(player)
        && getCell(1, 3).contentEquals(player)){
            return true;
        }
        
        return false;
    }

    private boolean checkTie(){
        for (int i = 1; i <= getHeight(); i++){
            for (int j = 1; j <= getWidth(); j++){
                if (getCell(j, i).contentEquals(" ")){
                    return false;
                }
            }
        }

        return true;
    }

    /**
    Gets the winner of the game if one exists
    @return The integer representation of the player who won the game, 0 for tie, -1 otherwise
    */

    @Override
    public int getWinner(){
        if(checkWinner("X") && checkWinner("O")){
            return 0;
        }else if (checkWinner("X")){
            return 1;
        }else if (checkWinner("O")){
            return 2;
        }else if(checkTie()){
            return 0;
        }
        
        return -1;
    }

    /**
    Generates a message based on the state of the game
    @return The string representation of the game state message
    */

    @Override
    public String getGameStateMessage(){
        if(isDone()){
            if (getWinner() == 0){
                return "Tie Game!";
            }else if (getWinner() == 1){
                return "Player X Wins!";
            }else if (getWinner() == 2){
                return "Player O Wins!";
            }
        }   
        return "Player " + getPlayerTurn() + "'s Turn";
    }

    /**
    Swaps the turn to that of the opposing player
    */

    public void swapPlayerTurn(){
        if(getPlayerTurn().contentEquals("X")){
            setPlayerTurn("O");
        }else{
            setPlayerTurn("X");
        }
    }

    /**
    Sets the turn to a specified player
    @param player The player to which the turn is set to
    */

    public void setPlayerTurn(String player){
        thePlayer = player;
    }

    /**
    Gets the player whose turn it is
    @return The player whose turn it is
    */

    public String getPlayerTurn(){
        return thePlayer;
    }

    /**
    Creates a formatted string of the TicTacToe game to be saved in a text file
    @return The formatted string of the TicTacToe game to be saved in a text file
    */

    @Override
    public String getStringToSave(){
        String toSave = "";
        String cell;
        int wide = 1;

        setGrid(getGrid());

        swapPlayerTurn();

        toSave = toSave + getPlayerTurn() + "\n";

        swapPlayerTurn();

        while ((cell = getNextValue()) != null){
           
            if (!cell.contentEquals(" ")){
                toSave = toSave + cell;
            }

            if (wide < getWidth()){
                toSave = toSave + ",";
                ++wide;
            }else if (wide == getWidth()){
                toSave = toSave + "\n";
                wide = 1;
            }
        }

        return toSave;
    }

    /**
    Loads the TicTacToe game from a formatted string in a text file
    @param saved The formatted string in a text file that represents a TicTacToe game
    */

    @Override
    public void loadSavedString(String saved){
        char[] charArray = saved.toCharArray();
        setPlayerTurn(String.valueOf(charArray[0]));
        swapPlayerTurn();

        TicTacToeGrid myGrid = (TicTacToeGrid)getGrid();  
        myGrid.parseStringIntoBoard(saved);
    }
}