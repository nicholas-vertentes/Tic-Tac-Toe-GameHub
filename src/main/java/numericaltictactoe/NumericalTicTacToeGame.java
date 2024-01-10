package numericaltictactoe;

import java.util.Iterator;
import game.InvalidInputExceptionTTT;
import game.InvalidPositionExceptionTTT;
import java.util.ArrayList;

/**
The NumericalTicTacToeGame class represents a Numerical TicTacToe game.
The methods within this class handle the state of the game.
@author Nicholas Vertentes
*/

public class NumericalTicTacToeGame extends boardgame.BoardGame implements boardgame.Saveable{

    private String thePlayer = "";
    private ArrayList<Integer> evenList;
    private ArrayList<Integer> oddList;

    /**
    Creats a new Numerical TicTacToe game
    @param wide The width of the gameboard
    @param high The height of the gameboard
    */

    public NumericalTicTacToeGame(int wide, int high){
        super(wide, high);
        setGrid(new NumericalTicTacToeGrid(wide, high));
        createEvenList();
        createOddList();
    }

    /**
    Resets the Numerical TicTacToe game to its starting state
    */

    @Override
    public void newGame(){
        super.newGame();
        createEvenList();
        createOddList();
    }

    private void createEvenList(){
        evenList = new ArrayList<>();

        for (int i = 0; i <= 8; i += 2){
            evenList.add(Integer.valueOf(i));
        }
    }

    private void createOddList(){
        oddList = new ArrayList<>();

        for (int i = 1; i <= 9; i += 2){
            oddList.add(Integer.valueOf(i));
        }
    }

    /**
    Places a piece at the specified position on the Numerical TicTacToe gameboard
    @param across The horizontal position of desired placement
    @param down The vertical position of desired placement
    @param input The piece to be placed on the gameboard (string representation)
    @return True if the piece was successfully placed, false otherwise
    */

    @Override
    public boolean takeTurn(int across, int down, String input){

        try{
            validatePosition(across, down);
            validateInput(input);
        }catch(InvalidInputExceptionTTT | InvalidPositionExceptionTTT e){
            return false;
        }

        setValue(across, down, input);
        return true;
    }

    /**
    Places a piece at the specified position on the Numerical TicTacToe gameboard
    @param across The horizontal position of desired placement
    @param down The vertical position of desired placement
    @param input The piece to be placed on the gameboard (int representation)
    @return True if the piece was successfully placed, false otherwise
    */

    @Override
    public boolean takeTurn(int across, int down, int input){

       try{
            validatePosition(across, down);
            validateInput(String.valueOf(input));
        }catch(InvalidInputExceptionTTT | InvalidPositionExceptionTTT e){
            return false;
        }

        setValue(across, down, input);
        return true;
    }

    private int validateRange(String input) throws InvalidInputExceptionTTT{

        for (int i = 0; i <= 9; i++){
            if (input.contentEquals(String.valueOf(i))){
                return i;
            }
        }
        
        throw new InvalidInputExceptionTTT();
    }

    private void validateEvenInput(Integer numInput) throws InvalidInputExceptionTTT{
        boolean invalidInput = true;
        int index = -1;

        if ((numInput.intValue() % 2) == 0){
            for(int i = 0; i < evenList.size(); i++){
                if (numInput.intValue() == evenList.get(i).intValue()){
                    invalidInput = false;
                    index = i;
                }
            }
            if(index != -1){
                evenList.remove(index);
            }
        }

        if (invalidInput){
            throw new InvalidInputExceptionTTT();
        }
    }

    private void validateOddInput(Integer numInput) throws InvalidInputExceptionTTT{
        boolean invalidInput = true;
        int index = -1;

        if ((numInput.intValue() % 2) == 1){
            for(int i = 0; i < oddList.size(); i++){
                if (numInput.intValue() == oddList.get(i).intValue()){
                    invalidInput = false;
                    index = i;
                }
            }
            if(index != -1){
                oddList.remove(index);
            }
        }

        if (invalidInput){
            throw new InvalidInputExceptionTTT();
        }
    }


    private void validateInput(String input) throws InvalidInputExceptionTTT{
        Integer numInput = Integer.valueOf(validateRange(input));

        if (getPlayerTurn().contentEquals("E")){
            validateEvenInput(numInput);
        }else if (getPlayerTurn().contentEquals("O")){
            validateOddInput(numInput);
        }else{
            throw new InvalidInputExceptionTTT();
        }
    }

    private void validatePosition(int across, int down) throws InvalidPositionExceptionTTT{
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
        return(checkWinner() || checkTie());
    }

    private boolean checkWinner(){

        return (checkColumnWinner() || checkRowWinner() || checkDiagnolWinner());

    }

    private boolean checkColumnWinner(){

        for (int i = 1; i <= getWidth(); i++){

            if(!getCell(i, 1).contentEquals(" ")
            && !getCell(i, 2).contentEquals(" ")
            && !getCell(i, 3).contentEquals(" ")){

                if (Integer.valueOf(getCell(i, 1)).intValue() 
                + Integer.valueOf(getCell(i, 2)).intValue()
                + Integer.valueOf(getCell(i, 3)).intValue()
                == 15){
                    return true;
                }
            }   
        }

        return false;
    }

    private boolean checkRowWinner(){

        for (int i = 1; i <= getHeight(); i++){
            if(!getCell(1, i).contentEquals(" ")
            && !getCell(2, i).contentEquals(" ")
            && !getCell(3, i).contentEquals(" ")){
            
                if (Integer.valueOf(getCell(1, i)).intValue() 
                + Integer.valueOf(getCell(2, i)).intValue()
                + Integer.valueOf(getCell(3, i)).intValue()
                == 15){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkDiagnolWinner(){

        if(!getCell(1, 1).contentEquals(" ")
        && !getCell(2, 2).contentEquals(" ")
        && !getCell(3, 3).contentEquals(" ")){

            if (Integer.valueOf(getCell(1, 1)).intValue() 
            + Integer.valueOf(getCell(2, 2)).intValue()
            + Integer.valueOf(getCell(3, 3)).intValue()
            == 15){
                return true;
            }
        }

        if(!getCell(3, 1).contentEquals(" ")
        && !getCell(2, 2).contentEquals(" ")
        && !getCell(1, 3).contentEquals(" ")){

            if (Integer.valueOf(getCell(3, 1)).intValue() 
            + Integer.valueOf(getCell(2, 2)).intValue()
            + Integer.valueOf(getCell(1, 3)).intValue()
            == 15){
                return true;
            }
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
        swapPlayerTurn();
        
        if (checkWinner() && getPlayerTurn().contentEquals("O")){
            swapPlayerTurn();
            return 1;
        }else if (checkWinner() && getPlayerTurn().contentEquals("E")){
            swapPlayerTurn();
            return 2;
        }else if(checkTie()){
            swapPlayerTurn();
            return 0;
        }
        swapPlayerTurn();
        return -1;
    }

    /**
    Generates a message based on the state of the game
    @return The string representation of the game state message
    */

    @Override
    public String getGameStateMessage(){
        String player = "";

        if(isDone()){
            if (getWinner() == 0){
                return "Tie Game!";
            }else if (getWinner() == 1){
                return "Player Odd Wins!";
            }else if (getWinner() == 2){
                return "Player Even Wins!";
            }
        }else if(getPlayerTurn().contentEquals("O")){
            player = "Odd";
        }else if(getPlayerTurn().contentEquals("E")){
            player = "Even";
        }

        return "Player " + player + "'s Turn";
    }

    /**
    Swaps the turn to that of the opposing player
    */

    public void swapPlayerTurn(){
        if(getPlayerTurn().contentEquals("O")){
            setPlayerTurn("E");
        }else{
            setPlayerTurn("O");
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
    Creates a formatted string of the Numerical TicTacToe game to be saved in a text file
    @return The formatted string of the Numerical TicTacToe game to be saved in a text file
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
    Loads the Numerical TicTacToe game from a formatted string in a text file
    @param saved The formatted string in a text file that represents a Numerical TicTacToe game
    */

    @Override
    public void loadSavedString(String saved){
        char[] charArray = saved.toCharArray();
        setPlayerTurn(String.valueOf(charArray[0]));
        swapPlayerTurn();

        NumericalTicTacToeGrid myGrid = (NumericalTicTacToeGrid)getGrid();  
        myGrid.parseStringIntoBoard(saved);

        createEvenList();
        createOddList();
        int strLength = saved.length();
        int i;
        Character cell;

        for (i = 2; i < strLength; i++){
            
            if(charArray[i] != ',' && charArray[i] != '\n'){
                cell = Character.valueOf(charArray[i]);
                try{
                    Integer numInput = Integer.valueOf(validateRange(cell.toString()));
                    if ((numInput.intValue() % 2) == 0){
                        validateEvenInput(numInput);
                    }else if((numInput.intValue() % 2) == 1){
                        validateOddInput(numInput);
                    }
                }catch(InvalidInputExceptionTTT e){
                }
            }
        }
    }
}