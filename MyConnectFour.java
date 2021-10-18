import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MyConnectFour {

  private BufferedReader input;

  Board newBoard = new Board(6, 8);

  public MyConnectFour() {
    input = new BufferedReader(new InputStreamReader(System.in));
    playGame();
  }

  private void playGame() {
    System.out.println("Welcome to Connect 4");
    System.out.println("There are 2 players red and yellow");
    System.out.println("Player 1 is Red, Player 2 is Yellow");
    System.out.println("To play the game type in the number of the column you want to drop you counter in");
    System.out.println("A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally");
    System.out.println("");
    newBoard.printBoard();
    boolean win = false;
    Player p1 = new Player("michalis",'r',true,newBoard);
    Player p2 = new Player("someonelse",'y',true,newBoard);
    ArrayList<Player> players = new ArrayList<Player>();
    players.add(p1);
    players.add(p2);
    String winner = "";
    while (!win){
      for(Player currentPlayer : players){
        String userInput = getUserInput();
        int move = Integer.parseInt(userInput);
        currentPlayer.myMove(move);
        win = currentPlayer.haveIWon();
        if(win==true){
          winner = currentPlayer.getName();
          break;
        }
      }
    }
    System.out.println(winner + " has won!!!");
  }

  private String getUserInput() {
    String toReturn = null;
    try {
      toReturn = input.readLine();
    } catch (Exception e) {
    }
    return toReturn;
  }

}