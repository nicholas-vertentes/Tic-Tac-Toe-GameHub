package game;

import tictactoe.TicTacToeGame;
import java.util.Scanner;

/**
The TextUI class represents the user interface and is responsible for 
interacting with the user through collecting user input and displaying output.
This class allows for a CLI TicTacToe game to be run.
@author Nicholas Vertentes
*/

public class TextUI{
    
    private Scanner input = new Scanner(System.in);
    private String userInput = "";
    private int across;
    private int down;
    private String piece;
    private TicTacToeGame game = new TicTacToeGame(3,3);
    

    /**
    Creates a string identifying the last user input
    @return A string identifying the last user input
    */

    @Override
    public String toString(){
        String toPrint = "";

        toPrint = toPrint.concat("Last User Input: " + getUserIn());

        return toPrint;
    }

    /**
    Gets input from the user and stores it for later use
    */

    public void setUserIn(){
        userInput = input.next();
    }

    /**
    Gets the most recent input entered by the user
    @return The most recent input entered by the user
    */

    public String getUserIn(){
        return userInput;
    }

    /**
    Prints a string to the CLI
    @param theString The string to be printed to the CLI
    */

    public void print(String theString){
        System.out.println(theString);
    }

    private void indexRow(){
        print("Enter row number (1-3): ");
        setUserIn();
        while (!checkValidIndex(getUserIn())){
            print("\nInvalid Row\n");
            indexRow();   
        }

        setDown(getIntPosition(getUserIn()));
    }

    private void indexColumn(){
        print("Enter column number (1-3): ");
        setUserIn();
        while (!checkValidIndex(getUserIn())){
            print("\nInvalid Column\n");
            indexColumn();   
        }

        setAcross(getIntPosition(getUserIn()));
    }
    
    private boolean checkValidIndex(String indexInput){
        if (indexInput.contentEquals("1")
        || indexInput.contentEquals("2")
        || indexInput.contentEquals("3")){
            return true;
        }

        return false;
    }

    private void inputPosition(){
        indexRow();
        indexColumn();

        try{
            game.validatePosition(getAcross(), getDown());
        }catch(InvalidPositionExceptionTTT e){
            print("\nPosition Occupied\n");
            inputPosition();
        }
    }

    private void inputPiece(){
        print("Enter piece to place: ");
        setUserIn();

        try{
            game.validateInput(getUserIn());
        }catch(InvalidInputExceptionTTT e){
            print("\nInvalid Piece\n");
            inputPiece();
        }
        
        setPiece(getUserIn());
    }

    private void setPiece(String thePiece){
        piece = thePiece;
    }

    private String getPiece(){
        return piece;
    }

    private void setAcross(int posAcross){
        across = posAcross;
    }

    private int getAcross(){
        return across;
    }

    private void setDown(int posDown){
        down = posDown;
    }

    private int getDown(){
        return down;
    }

    private int getIntPosition(String strPos){
        int position = -1;

        switch(strPos){
            case "1":
                position = 1;
                break;
            case "2":
                position = 2;
                break;
            case "3":
                position = 3;
                break;
            default:
                break;
        }

        return position;
    }

    private String determineWinner(){
        String toPrint = "";

        if (game.getWinner() == 0){
            toPrint = "Tie Game!";
        }else if (game.getWinner() == 1){
            toPrint = "Player X Wins!";
        }else if (game.getWinner() == 2){
            toPrint = "Player O Wins!";
        }

        return toPrint;
    }

    /**
    Creates a new TicTacToe game on the CLI that can be played through
    */

    public void playGame(){

        while (!game.isDone()){
            print(game.toString());
            game.swapPlayerTurn();
            print("Player " + game.getPlayerTurn() + "'s Turn");


            do{
                inputPosition();
                inputPiece();
            } while (!game.takeTurn(getAcross(), getDown(), getPiece()));
        }

        print(game.toString());
        print(determineWinner());
    }

    /**
    Creates a new user iterface and starts a new TicTacToe game on the CLI
    @param args Arguments given to the program via the CLI
    */

    public static void main(String[] args) {
        TextUI ui = new TextUI();

        ui.playGame();
    }
}