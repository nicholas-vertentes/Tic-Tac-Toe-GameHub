# Project Title

Java GUI TicTacToe Gamehub

## Description

This program uses an event-driven object-oriented programming paradigm to create a GUI TicTacToe Gamehub written in Java.

The GUI allows the players to choose between playing TicTacToe and Numerical TicTacToe. 

TicTacToe Rules:
* This game is played on a 3x3 board
* There are two players
    * Player X and Player O
* Player X always moves first
* The player must place their respective piece on the gameboard by clicking on their desired position
* Players may only place the piece they are assigned (eg. player X may only place 'X')
* Players may not place a piece in an already occupied position
* Upon placement of a valid piece, the turn is set to the opposing player 
* The first player to place three consecutive pieces in either the horizontal, vertical, or diagnol directions wins the game
* If the gameboard becomes full and there is no winner, the game is declared a tie

Numerical TicTacToe Rules
* This game is played on a 3x3 board
* Integers 0-9 are used as the play pieces
* There are two players
    * Player Odd and Player Even
* Player Odd always moves first
* Player Odd may only play odd integers from the specified range
    * 1, 3, 5, 7, 9
* Player Even may only play even integers from the specified range
    * 0, 2, 4, 6, 8
* The player must place their choosen integer on the gameboard by clicking on their desired position
* Players may not place an integer in an already occupied position
* Each valid integer may only be used once
* Upon placement of a valid integer, the turn is set to the opposing player
* The first player to produce three consecutive integers that sum to 15 in either the horizontal, vertical, or diagnol directions wins the game
    * Both odd and even integers can be used in the sum, regardless of player
* If the gameboard becomes full and there is no winner, the game is declared a tie


The desired game may be selected by the user on the right-hand side of the GUI:
* Play TicTacToe
* Play Numerical TicTacToe


The GUI keeps tally of the total wins, losses, and games played by each player, and can be seen on the left-hand side of the GUI
* Both players are initalized to "Player 1" and "Player 2", with their wins, losses and played games initialized to 0
* Upon game startup users are given the option to import their previous statistics and player name from a file
* Player 1 is assigned to the piece who moves first in each game
* Wins, losses, and ties appropriately increment the statistics of each player


The top-left of the GUI contains a menu that consists of the following options:
* Save Game
    * Allows the user to save the current state of the current game to a file
* Load Game
    * Allows the user to load, from a representative file, a previous game state to the current game
* Save Profiles
    * Allows the user to save a specified player profile to a file
* Load Profiles
    * Allows the user to load a player profile for a specified player


Player Profile Save File:
* "player Name"           (String)
* number of games won     (int)
* number of games lost    (int)
* number of games played  (int)

    * Example (see assets folder):
        * Nicholas V
        * 100
        * 0
        * 105

TicTacToe and Numerical TicTacToe Save Files:
* Refer to assignment description for file format details


The user is also able to play TicTacToe using the CLI, following the same rules as the GUI implementation of the TicTacToe game
          

### Dependencies

* Java Virtual Machine - used to compile program (Ubuntu 20.04: sudo apt install openjdk-11-jdk)
* Command Line Interface - used to play TicTacToe without a GUI and allows for execution of instructions
* gradle - aids in program compilation and javadoc formatting (Ubuntu 20.04: sudo apt install gradle)

### Executing program

In the Command Line Interface:
* Make the program folder the current working directory 
* Compile the program using the build command:
```
gradle build
```

* Run the build folder:
```
gradle run
```

* The follwing should be output:
```
> Task :run
To run the program in GUI:
java -jar build/libs/A3-main.jar

To run TicTacToe in CLI:
java -cp build/classes/java/main game.TextUI
```

* Clear the build folder:
```
gradle clean
```

* Run Javadocs:
```
gradle javadoc
```
Docs will be located in build folder

To run the GUI TicTacToe Gamehub:
* Navigate to the program directory via the CLI on a native computer that supports GUI
* Copy the first command to the CLI and run it
* Play the game and enjoy!

To run the CLI TicTacToe game:
* Copy the second command to the CLI and run it
* Play the game and enjoy!

## Limitations

* The program does not check if loaded files follow the correct format
    * Checking for valid file format and rejecting invalid files would greatly improve the program

* In the GUI Numerical TicTacToe game
    * The program does not display the remaining integers that the player may use
    * The program can be improved by informing the player of the remaing pieces available for them to place

* The player who moves first is always Player 1, giving them an unfair advantage
    * The program can be improved by swapping the player who goes first after each game

