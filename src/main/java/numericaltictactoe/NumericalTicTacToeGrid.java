package numericaltictactoe;

import java.util.Iterator;

/**
The NumericalTicTacToeGrid class represents a Numerical TicTacToe grid.
The methods within this class operate on the contents of the grid.
@author Nicholas Vertentes
*/

public class NumericalTicTacToeGrid extends boardgame.Grid{

    /**
    Creats a new Numerical TicTacToe grid
    @param wide The width of the grid
    @param tall The height of the grid
    */

    public NumericalTicTacToeGrid(int wide, int tall){
        super(wide, tall);
    }

    /**
    Creates a string representation of the Numerical TicTacToe grid
    @return The string representation of the Numerical TicTacToe grid
    */

    @Override
    public String getStringGrid(){
        Iterator<String> iter = iterator();
        String toPrint ="";
        int wide = 1;
        int high = 1;
        int i;
        String cell;

        
        while (iter.hasNext()){

            cell = iter.next();

            if(wide < getWidth()){
                toPrint = toPrint + cell + "|";
                ++wide;
            }else if(wide == getWidth()){
                toPrint = toPrint + cell + "\n";
                wide = 1;

                if (high < getHeight()){
                    toPrint = toPrint + "-+-+-" + "\n";
                    ++high;
                }
            }
        }
        
        return toPrint;
    }

    /**
    Parses the contents of a formatted string into the Numerical TicTacToe grid
    @param toParse The String that conatains the contents of the Numerical TicTacToe grid
    */

    protected void parseStringIntoBoard(String toParse){
       int strLength = toParse.length();
       char[] charArray = toParse.toCharArray();
       int i;
       Character cell = Character.valueOf(' ');
       int wide = 1;
       int high = 1;

        for (i = 2; i < strLength; i++){
            
            if(charArray[i] == ',' || charArray[i] == '\n'){
                
                setValue(wide, high, cell.toString());
                ++wide;
                cell = Character.valueOf(' ');

            }else{
                cell = Character.valueOf(charArray[i]);
            }

            if (wide > getWidth()){
                ++high;
                wide = 1;
            }
        }
    }
}