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
    }catch(Exception e ){
    }
  }

  private void playGame() {
    input.setMax(newBoard.boardX);
    try{
      newBoard.printBoard();
      boolean win = false;
      String winner = "";
      while (!win){
        int move = 999999;
        for(Player currentPlayer : players){
          boolean validMove = false;
          while(!validMove){
            move = input.ReadLine();
            if(move != 999999){//valid move
              validMove = true;
            }
          }
          currentPlayer.myMove(move);
          win = currentPlayer.haveIWon();
          if(win==true){
            winner = currentPlayer.getName();
            break;
          }
        }
      }
      System.out.println(winner + " has won!!!");
    }catch (Exception e){

    }
  }

  private void addPlayers(){
    System.out.println("Enter number of players (2-10)");
    int numberOfPlayers = input.ReadLine();
    try{
    while(numberOfPlayers<=1 || numberOfPlayers >10){
      System.out.println("Enter number of players (2-10)");
      numberOfPlayers = input.ReadLine();
    }
    for(int i=0;i<numberOfPlayers;i++){
      int z = 1+i;
      System.out.println("Enter name of player " + z);
      String newPlayerName = settings.readLine();
      System.out.println("Is this player human? (Y or N)");
      String playerHuman = settings.readLine();
      Boolean humanHelper = false, human = true, charHelper = false;
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
            human=false;
            humanHelper=true;
            break;
          case "n":
            human=false;
            humanHelper=true;
            break;
          default:
            System.out.println("Enter Y or N only");
            playerHuman = settings.readLine();
            break;
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
      Player newPlayer = new Player(newPlayerName,newPlayerCharacter, newBoard);
      players.add(newPlayer);
    }
    }catch(Exception e){
      System.out.println("Error when trying to add players : " + e);
    }
  }
}