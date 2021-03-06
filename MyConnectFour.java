import java.io.*;
import java.util.*;

public class MyConnectFour {
  BufferedReader settings = new BufferedReader(new InputStreamReader(System.in));
  InputHandler input = new InputHandler();
  ArrayList<Player> players = new ArrayList<Player>();
  Board boardToPlayOn;

  public MyConnectFour() {
    try {
      cleanConsole();
      System.out.println(" Welcome to connect-N game. Play against other players  or the computer!\n\n");
      while (true) {
        System.out.println("1-> Start a new game with default settings (7x6 board, connect-4)");
        System.out.println("2-> New Custom game");
        System.out.println("3-> Exit the game");
        switch(input.ReadLine()){
          case 3:
            System.exit(1);
          case 2:
            input.setMax(99); // allow user to write any int
            boardToPlayOn = setupBoard();
            addPlayers();
            playGame();
            break;
          case 1:
            boardToPlayOn = new Board(7, 6, 4);
            addPlayers();
            playGame();
            break;
          default:
            System.out.println("Please choose 1,2 or 3");
            break;
        }
      }
    } catch (Exception e) {
      System.out.println("Error in loading game : " + e);
    }
  }

  public void cleanConsole(){
    System.out.print("\033[H\033[2J"); //clean screen - this works well in VSCode + repl.it
    System.out.flush();
  }

  public Board setupBoard() {
    input.setMax(999);
    System.out.println("Warning : Boards with either dimension > 10 will likely look weird");
    System.out.println("Enter x dimension of board");
    int x = input.ReadLine();
    System.out.println("Enter y dimension of board");
    int y = input.ReadLine();
    System.out.println("Connect how many?");
    int n = input.ReadLine();
    Board newBoard = new Board(x, y, n);
    input.setMax(10);
    return newBoard;
  }

  private void playGame() {
    try {
      input.setMax(boardToPlayOn.getBoardX());
      exchangePlayerCharacters();
      cleanConsole();
      System.out.println(players.get(0).getName() + " goes first.");
      boardToPlayOn.printBoard();
      boolean win = false , draw = false;
      String winner = "";
      while (!win && !draw) {
        for (Player currentPlayer : players) {
          if (boardToPlayOn.didMatchDraw() == true){ draw = true; break;}
          currentPlayer.makeMove(input);
          win = currentPlayer.haveIWon();
          if (win == true) { winner = currentPlayer.getName(); break;}
        }
      }
      if(win==true){
        System.out.println(winner + " has won!!! \n");
      }
      if(draw==true){
        System.out.println("Match was a draw\n");
      }
      players.clear();
    } catch (Exception e) {
      System.out.println("Error in running game : " + e);
    }
  }

  private void exchangePlayerCharacters() { //get everyones characters and give them to possible bots
    try{
      ArrayList<Character> allPlayerCharacters = new ArrayList<Character>();
      for(Player thisPlayer : players){
        allPlayerCharacters.add(thisPlayer.getChar());
      }
      for(Player thisPlayer : players){
        thisPlayer.setEnemyCharacter(allPlayerCharacters);
      }
    }catch (Exception e){
      System.out.println("Error when trying to set enemy characters: " + e);
    }
  }

  private void addPlayers() {
    /*method to add AI or Human players. Asks user for input of a name, whether player is human or bot, and a character
    Will check if the character is unique AND if it is valid i.e. not '\0' (nothing) or a whitespace ' '
    */
    input.setMax(10);
    System.out.println("Enter number of players (2-10).");
    int numberOfPlayers = input.ReadLine();
    try {
      while (numberOfPlayers <= 1 || numberOfPlayers > 10) {
        System.out.println("Enter number of players (2-10)");
        numberOfPlayers = input.ReadLine();
      }
      for (int i = 0; i < numberOfPlayers; i++) {
        Player newPlayer;
        int z = 1 + i;
        System.out.println("Enter name of player " + z);
        String newPlayerName = settings.readLine();
        Boolean humanHelper = false, human = true, charHelper = false;
        System.out.println("Is this player human? (Y or N)");
        String playerHuman = settings.readLine();
        while (!humanHelper) {
          switch (playerHuman) {
          case "y":
            human = true;
            humanHelper = true;
            break;
          case "Y":
            human = true;
            humanHelper = true;
            break;
          case "N":
            human = false;
            humanHelper = true;
          case "n":
            human = false;
            humanHelper = true;
            break;
          default:
            System.out.println("Enter Y or N only");
            playerHuman = settings.readLine();
            break;
          }
        }
        boolean validCharacter = false;
        System.out.println("Enter a character for this player");
        char[] characters = settings.readLine().toCharArray();
        char newPlayerCharacter = '\0';
        while (!charHelper) { //is char unique?
          while(!validCharacter){ // is char valid?
            switch(characters.length){
              case 0:
                System.out.println("Enter a character for this player");
                characters = settings.readLine().toCharArray();
                break;
              default:
                if(characters[0] != ' '){//not empty or a space
                  validCharacter = true;
                  newPlayerCharacter = characters[0];
                  break;
                }
                  System.out.println("Enter a character for this player");
                  characters = settings.readLine().toCharArray();
                  break;
            }
          }
          Boolean isCharUnique = true;
          for (Player checkPlayer : players) {
            if (checkPlayer.getChar() == newPlayerCharacter) {
              isCharUnique = false;
              validCharacter = false;
            }
          }
          if (isCharUnique != true) {
            System.out.println("Character \"" + characters[0] + "\" already used, please choose another one");
            characters = settings.readLine().toCharArray();
          } else if (isCharUnique == true) {
              System.out.println("Character \"" + characters[0] + "\" chosen successfuly");
              charHelper = true;
          }
        }
        if (human == true) {
          newPlayer = new Player(newPlayerName, newPlayerCharacter, boardToPlayOn, human);
        } else {
          newPlayer = new AI(newPlayerName, newPlayerCharacter, boardToPlayOn);
        }
        players.add(newPlayer);
      }
      cleanConsole();
    } catch (Exception e) {
      System.out.println("Error when trying to add players : " + e);
    }
  }
}