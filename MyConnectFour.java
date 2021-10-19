import java.io.*;
import java.util.*;

public class MyConnectFour {
  Board newBoard = new Board(6, 8);
  BufferedReader settings = new BufferedReader(new InputStreamReader(System.in));
  InputHandler input = new InputHandler();
  Map<Integer, String> _myMap = new HashMap<Integer, String>();
  Player p1;
  int yo = 1;
  _myMap.put(10, "Geeks");


  public MyConnectFour() {
    try{
      System.out.println("Enter number of players (2-10)");
      int numberOfPlayers = input.ReadLine();
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
        while (playerHuman != "y" || playerHuman != "Y" || playerHuman != "n" || playerHuman != "N"){
          System.out.println("Enter Y or N only");
          playerHuman = settings.readLine();
        }
        boolean human;
        if (playerHuman == "N" || playerHuman == "n" ){
          human = false;
        }
        if (playerHuman == "Y" || playerHuman == "y" ){
          human = true;
        }
        System.out.println("Enter a character for this player");
        char[] characters = settings.readLine().toCharArray();
        char newPlayerCharacter = characters[0];
        
  
       // myMap.get(i) = new Player(newPlayerName,newPlayerCharacter, human, newBoard);
      }
      
      
      playGame();
    }catch(Exception e ){
    }
  }

  private void playGame() {
    input.setMax(newBoard.boardX);
    try{
      newBoard.printBoard();
      boolean win = false;
      Player p1 = new Player("michalis",'r',true,newBoard);
      Player p2 = new Player("someonelse",'y',true,newBoard);
      ArrayList<Player> players = new ArrayList<Player>();
      players.add(p1);
      players.add(p2);
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

}