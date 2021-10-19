import java.io.*;
import java.util.*;

public class MyConnectFour {
  Board newBoard = new Board(6, 8);
  BufferedReader settings = new BufferedReader(new InputStreamReader(System.in));
  InputHandler input = new InputHandler();
  ArrayList<Player> players = new ArrayList<Player>();

  public MyConnectFour() {
    try{
      addPlayers();
      playGame();
    }catch(Exception e){
    }
  }

  private void playGame() {
    input.setMax(newBoard.getBoardX());
    checkAIandStart();
    try{
      newBoard.printBoard();
      boolean win = false;
      String winner = "";
      while (!win){
        int move = 999999;
        for(Player currentPlayer : players){
          if(currentPlayer.amIHuman() == true){
            boolean validInput = false;
            while(!validInput){
              move = input.ReadLine();
              if(move != 999999){//valid move
                validInput = true;
             }
            }
            currentPlayer.myMove(move);
            win = currentPlayer.haveIWon();
            if(win==true){
              winner = currentPlayer.getName();
              break;
            }
          }else if(currentPlayer.amIHuman() != true){
            currentPlayer.makeMove();
            win = currentPlayer.haveIWon();
            if(win==true){
              winner = currentPlayer.getName();
              break;
            }
          }
        }
      }
      System.out.println(winner + " has won!!!");
    }catch (Exception e){

    }
  }

  private void checkAIandStart(){
    players.get(1).setEnemyCharacter(players.get(0).getChar());
    players.get(0).setEnemyCharacter(players.get(1).getChar());
  }

  private void addPlayers(){
    System.out.println("Enter number of players (2-10). If AI is to be used only 2 players allowed");
    int numberOfPlayers = input.ReadLine();
    try{
    while(numberOfPlayers<=1 || numberOfPlayers >10){
      System.out.println("Enter number of players (2-10)");
      numberOfPlayers = input.ReadLine();
    }
    for(int i=0;i<numberOfPlayers;i++){
      Player newPlayer;
      int z = 1+i;
      System.out.println("Enter name of player " + z);
      String newPlayerName = settings.readLine();
      Boolean humanHelper = false, human = true, charHelper = false;
      if(numberOfPlayers==2){
        System.out.println("Is this player human? (Y or N)");
        String playerHuman = settings.readLine();
        while (!humanHelper){
          switch(playerHuman){
            case "y":
              human=true;
              humanHelper=true;
              break;
            case "Y":
              human=true;
              humanHelper=true;
              break;
            case "N":
              if(numberOfPlayers==2){
                human=false;
                humanHelper=true;
                break;
              }
              human=true;
              humanHelper=true;
              break;
            case "n":
              if(numberOfPlayers==2){
                human=false;
                humanHelper=true;
                break;
              }
              human=true;
              humanHelper=true;
              break;
            default:
              System.out.println("Enter Y or N only");
              playerHuman = settings.readLine();
              break;
          }
        }
      }
      System.out.println("Enter a character for this player");
      char[] characters = settings.readLine().toCharArray();
      char newPlayerCharacter = characters[0];
      while(!charHelper){
        Boolean charNotFound = true;
        for(Player checkPlayer : players){
          if(checkPlayer.getChar() == newPlayerCharacter){
            charNotFound = false;
          }
        }
        if(charNotFound != true){
          System.out.println("Character already used, please choose another one");
          characters = settings.readLine().toCharArray();
          newPlayerCharacter = characters[0];
        } else if(charNotFound==true){
          charHelper = true;
        }
      }
      if(human == true){
        newPlayer = new Player(newPlayerName,newPlayerCharacter, newBoard,human);
      }else{
        newPlayer = new AI(newPlayerName,newPlayerCharacter, newBoard,1);
      }
      players.add(newPlayer);
    }
    }catch(Exception e){
      System.out.println("Error when trying to add players : " + e);
    }
  }
}