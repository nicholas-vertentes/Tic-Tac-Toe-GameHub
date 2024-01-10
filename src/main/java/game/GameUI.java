package game;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Container;
import java.awt.Component;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.io.File;

import tictactoe.TicTacToeView;
import numericaltictactoe.NumericalTicTacToeView;

/**
The GameUI class is the driving force of the TicTacToe Gamehub GUI
@author Nicholas Vertentes
*/

public class GameUI extends JFrame{

    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;
    private JPanel gameContainer;
    private Container contentPane;
    private JMenuBar menuBar;
    private Player player1;
    private Player player2;
    private String theGame;
    private TicTacToeView tttGame;
    private NumericalTicTacToeView ntttGame;
    private JTextArea player1Text;
    private JTextArea player2Text;

    /**
    Creates a new TicTacToe Gamehub GUI
    @param title The title of the newly created GUI
    */

    public GameUI(String title){

        super();
        setSize(WIDTH, HEIGHT);
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameContainer = new JPanel();
        gameContainer.setLayout(new BorderLayout());
        start();
        setMainContainer();
        makeMenu();
        setJMenuBar(menuBar);
        pack();
        loadPlayers();
    }

    private void setMainContainer() {
        contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(gameContainer, BorderLayout.CENTER);
        contentPane.add(makeButtonPanel(), BorderLayout.EAST);
        contentPane.add(makePlayerPanel(), BorderLayout.WEST);
    }

    private void makeMenu(){
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem item = new JMenuItem("Save Game");
        JMenuItem item2 = new JMenuItem("Load Game");
        JMenuItem item3 = new JMenuItem("Save Profiles");
        JMenuItem item4 = new JMenuItem("Load Profiles");
        menu.add(item);
        menu.add(item2);
        menu.add(item3);
        menu.add(item4);
        menuBar.add(menu);
        item.addActionListener(e->saveGame());
        item2.addActionListener(e->loadGame());
        item3.addActionListener(e->savePlayers());
        item4.addActionListener(e->loadPlayers());
    }

    private void savePlayers(){
        savePlayer1();
        savePlayer2();
    }

    private void savePlayer1(){
        int selection = 0;
        JOptionPane playerSave = new JOptionPane();
        selection = playerSave.showConfirmDialog(null, "Save Player 1?", null, JOptionPane.YES_NO_OPTION);

        if(selection == JOptionPane.YES_OPTION){
            JFileChooser fileChooser = new JFileChooser();
            File file;
            String filePath;
            try{
                int i = fileChooser.showSaveDialog(this);
                if (i == JFileChooser.APPROVE_OPTION){
                    file = fileChooser.getSelectedFile();
                    filePath = file.getPath();
                    GameStorage.saveGame(player1, filePath);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error Occured");
            }
        }
    }

    private void savePlayer2(){
        int selection = 0;
        JOptionPane playerSave = new JOptionPane();
        selection = playerSave.showConfirmDialog(null, "Save Player 2?", null, JOptionPane.YES_NO_OPTION);

        if(selection == JOptionPane.YES_OPTION){
            JFileChooser fileChooser = new JFileChooser();
            File file;
            String filePath;
            try{
                int i = fileChooser.showSaveDialog(this);
                if (i == JFileChooser.APPROVE_OPTION){
                    file = fileChooser.getSelectedFile();
                    filePath = file.getPath();
                    GameStorage.saveGame(player2, filePath);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error Occured");
            }
        }
    }

    private void loadPlayers(){
        loadPlayer1();
        loadPlayer2();
        setPlayerProfile();
    }

    private void loadPlayer1(){
        int selection = 0;
        JOptionPane playerImport = new JOptionPane();
        selection = playerImport.showConfirmDialog(null, "Import Player 1?", null, JOptionPane.YES_NO_OPTION);
        if(selection == JOptionPane.YES_OPTION){
            JFileChooser fileChooser = new JFileChooser();
            File file;
            String filePath;
            try{
                int i = fileChooser.showOpenDialog(this);
                if (i == JFileChooser.APPROVE_OPTION){
                    file = fileChooser.getSelectedFile();
                    filePath = file.getPath();
                    GameStorage.loadGame(player1, filePath);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error Occured");
            }
        }
    }

    private void loadPlayer2(){
        int selection = 0;
        JOptionPane playerImport = new JOptionPane();
        selection = playerImport.showConfirmDialog(null, "Import Player 2?", null, JOptionPane.YES_NO_OPTION);
        if(selection == JOptionPane.YES_OPTION){
            JFileChooser fileChooser = new JFileChooser();
            File file;
            String filePath;
            try{
                int i = fileChooser.showOpenDialog(this);
                if (i == JFileChooser.APPROVE_OPTION){
                    file = fileChooser.getSelectedFile();
                    filePath = file.getPath();
                    GameStorage.loadGame(player2, filePath);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error Occured");
            }
        }
    }

    private void setPlayerProfile(){
        player1Text.setText(player1.toString());
        player2Text.setText(player2.toString());
    }

    private void saveGame(){
  
        if(theGame.contentEquals("TTT")){
            tttGame.saveGame();
        }else if(theGame.contentEquals("NTTT")){
            ntttGame.saveGame();
        }else{
            JOptionPane.showMessageDialog(null, "No Game in Progress");
        }
    }

    private void loadGame(){
  
        if(theGame.contentEquals("TTT")){
            tttGame.loadGame();

        }else if(theGame.contentEquals("NTTT")){
            ntttGame.loadGame();
        }else{
            JOptionPane.showMessageDialog(null, "No Game Selected");
        }
    }

    private JPanel makePlayerPanel(){
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.add(makePlayer1());
        playerPanel.add(makePlayer2());
        return playerPanel;
    }

    private JTextArea makePlayer1(){
        player1Text = new JTextArea();
        player1 = new Player("Player 1");
        player1Text.setEditable(false);
        player1Text.setText(player1.toString());
        return player1Text;
    }

    private JTextArea makePlayer2(){
        player2Text = new JTextArea();
        player2 = new Player("Player 2");
        player2Text.setEditable(false);
        player2Text.setText(player2.toString());
        return player2Text;
    }

    /**
    Increments player statistics appropriately when Player 1 wins
    */

    public void player1Win(){
        player1.incrementWin();
        player2.incrementLoss();
        gamePlayed();
    }

    /**
    Increments player statistics appropriately when Player 2 wins
    */

    public void player2Win(){
        player2.incrementWin();
        player1.incrementLoss();
        gamePlayed();
    }

    /**
    Increments the games played statistic of each player upon completion of a game.
    Each player's statistics are then visually updated on the GUI.
    */

    public void gamePlayed(){
        player1.incrementPlayed();
        player2.incrementPlayed();
        setPlayerProfile();
    }

    
    private JPanel makeButtonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(makeTicTacToeButton());
        buttonPanel.add(makeNumericalTicTacToeButton());
        return buttonPanel;
    }

    private JButton makeTicTacToeButton(){
        JButton button = new JButton("Play TicTacToe");
        button.addActionListener(e->ticTacToe());
        return button;
    }

    private JButton makeNumericalTicTacToeButton(){
        JButton button = new JButton("Play Numerical TicTacToe");
        button.addActionListener(e->numericalTicTacToe());
        return button;
    }


    private void ticTacToe(){
        theGame = "TTT";
        tttGame = new TicTacToeView(3,3,this);
        gameContainer.removeAll();
        gameContainer.add(tttGame, BorderLayout.CENTER);
        gameContainer.repaint();
        gameContainer.revalidate();
        pack();
    }

    private void numericalTicTacToe(){
        theGame = "NTTT";
        ntttGame = new NumericalTicTacToeView(3,3,this);
        gameContainer.removeAll();
        gameContainer.add(ntttGame, BorderLayout.CENTER);
        gameContainer.repaint();
        gameContainer.revalidate();
        pack();
    }
    
    /**
    Displays the main menu of the TicTacToe Gamehub GUI
    */

    public void start() {
        theGame = "";
        gameContainer.removeAll();
        gameContainer.add(startupMessage(), BorderLayout.CENTER);
        gameContainer.repaint();
        gameContainer.revalidate();
        pack();
    }

    private JPanel startupMessage(){
        JPanel temp = new JPanel();
        temp.add(new JLabel("Welcome to TicTacToe Games"));
        return temp;
    }

    /**
    Creates a new instance of the TicTacToe Gamehub GUI
    @param args Arguments given to the program via the CLI
    */

    public static void main(String[] args){
        GameUI ticTacToeGUI = new GameUI("TicTacToe Games");
        ticTacToeGUI.setVisible(true);
    }
}