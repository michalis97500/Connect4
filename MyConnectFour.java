import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyConnectFour {

  private BufferedReader input;

  Board newBoard = new Board(6, 7);

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
    while (!win) {
      // player 1
      String userInput = getUserInput();
      int move = Integer.parseInt(userInput);
      newBoard.placeCounter('r', move);
      newBoard.printBoard(); // here
      boolean hasWon = false;
      // check horizontal
      newBoard.printBoard();
      if (hasWon) {
        win = true;
      } else {
        // player 2
        userInput = getUserInput();
        move = Integer.parseInt(userInput);
        newBoard.placeCounter('y', move);
        newBoard.printBoard(); // here
        hasWon = false;
        newBoard.printBoard();
        if (hasWon) {
          win = true;
        }
      }
    }
    System.out.println("You Have Won!!!");
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