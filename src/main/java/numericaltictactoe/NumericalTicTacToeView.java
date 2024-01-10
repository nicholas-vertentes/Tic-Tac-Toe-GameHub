package numericaltictactoe;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;


import boardgame.ui.PositionAwareButton;
import game.GameUI;
import game.GameStorage;

/**
The NumericalTicTacToeView class displays a Numerical TicTacToe game on a GUI.
@author Nicholas Vertentes
*/

public class NumericalTicTacToeView extends JPanel{
    
    private GameUI root;
    private NumericalTicTacToeGame game;
    private PositionAwareButton[][] buttons;
    private JLabel gameText;

    /**
    Creates a new graphical Numerical TicTacToe game display
    @param wide The width of the gameboard
    @param tall The height of the gameboard
    @param gameFrame The GUI where the game is to be displayed
    */

    public NumericalTicTacToeView(int wide, int tall, GameUI gameFrame){
        super();
        setLayout(new BorderLayout());
        root = gameFrame;
        setGameController(new NumericalTicTacToeGame(wide, tall));
        add(makeNewGameButton(), BorderLayout.EAST);
        add(makeButtonGrid(tall, wide), BorderLayout.CENTER);
        add(makeGameTextLabel(), BorderLayout.NORTH);
        game.setPlayerTurn("O");
        gameText.setText(game.getGameStateMessage());
    }


    private void setGameController(NumericalTicTacToeGame controller){
        this.game = controller;
    }

    private JPanel makeGameTextLabel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Welcome to Numerical TicTacToe"));
        panel.add(new JLabel(" "));
        gameText = new JLabel();
        panel.add(gameText);
        return panel;
        
    }

    private JButton makeNewGameButton(){
        JButton button = new JButton("New Numerical TicTacToe Game");
        button.addActionListener(e->newGame());
        return button;
    }

    private JPanel makeButtonGrid(int tall, int wide){
        JPanel panel = new JPanel();
        buttons = new PositionAwareButton[tall][wide];
        panel.setLayout(new GridLayout(wide, tall));
        for (int y = 0; y < wide; y++){
            for (int x = 0; x < tall; x++){ 
                buttons[y][x] = new PositionAwareButton();
                buttons[y][x].setAcross(x+1); 
                buttons[y][x].setDown(y+1);
                buttons[y][x].addActionListener(e->{
                                        enterNumber(e);
                                        checkGameState();
                                        });
                panel.add(buttons[y][x]);
            }
        }
        return panel;
    }

    private void newGame(){
        game.newGame();
        updateView();
        game.setPlayerTurn("O");
        gameText.setText(game.getGameStateMessage());
    }

    private void updateView(){

        for (int y = 0; y < game.getHeight(); y++){
            for (int x = 0; x < game.getWidth(); x++){  
                buttons[y][x].setText(game.getCell(buttons[y][x].getAcross(),buttons[y][x].getDown())); 
            }
        }
    }

    private void checkGameState(){
        int selection = 0;
        JOptionPane gameOver = new JOptionPane();
        if(game.isDone()){
            JOptionPane.showMessageDialog(null, game.getGameStateMessage()); 
            if(game.getWinner() == 1){
                root.player1Win();
            }else if (game.getWinner() == 2){
                root.player2Win();
            }else if (game.getWinner() == 0){
                root.gamePlayed();
            }
            selection = gameOver.showConfirmDialog(null, "Play Again?", null, JOptionPane.YES_NO_OPTION);
            if(selection == JOptionPane.NO_OPTION){
                root.start();
            } else{
                newGame();
            }
        }
    
    }   

    private void enterNumber(ActionEvent e){

        try{
            String piece = JOptionPane.showInputDialog(game.getGameStateMessage() + " (0-9)"); 
        
            PositionAwareButton clicked = ((PositionAwareButton)(e.getSource()));
            if(game.takeTurn(clicked.getAcross(), clicked.getDown(), piece)){
                clicked.setText(game.getCell(clicked.getAcross(), clicked.getDown()));

                game.swapPlayerTurn();
                gameText.setText(game.getGameStateMessage());
            }else{
                JOptionPane.showMessageDialog(null, "Invalid Position or Number"); 
            }
        }catch (Exception ex){
            
        }
    }

    /**
    Saves the current instance of a Numerical TicTacToe game to a file via a formatted string
    */

    public void saveGame(){
        JFileChooser fileChooser = new JFileChooser();
        File file;
        String filePath;
            try{
                int i = fileChooser.showSaveDialog(this);
                if (i == JFileChooser.APPROVE_OPTION){
                    file = fileChooser.getSelectedFile();
                    filePath = file.getPath();
                    GameStorage.saveGame(game, filePath);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error Occured");
            }
    }

    /**
    Loads a Numerical TicTacToe game from a formatted stirng in a file to the current instance of the game
    */

    public void loadGame(){
        JFileChooser fileChooser = new JFileChooser();
        File file;
        String filePath;
            try{
                int i = fileChooser.showOpenDialog(this);
                if (i == JFileChooser.APPROVE_OPTION){
                    file = fileChooser.getSelectedFile();
                    filePath = file.getPath();
                    GameStorage.loadGame(game, filePath);
                    updateView();
                    gameText.setText(game.getGameStateMessage());
                    checkGameState();
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error Occured");
            }
    }
}