public class Board {
  private char[][] board;
  String myPlaces;
  int minBoard = 4;

  public Board(int x, int y) { //guard
    if(x<minBoard){
      System.out.println("X-dimension must be " + minBoard + " or more");
      x = minBoard;
    }
    if(y<minBoard){
      System.out.println("Y-dimension must be " + minBoard + " or more");
      y = minBoard;
    }
    board = new char[x][y];
    myPlaces = GetMyPlaces(x);
  }

  private String GetMyPlaces(int x){
    try{
      StringBuilder stringToBuild = new StringBuilder();
      stringToBuild.append("  1" );
      for(int i=1;i<x;i++){
        int place = 1+i;
        stringToBuild.append("   " + place);
      }
      return stringToBuild.toString();
    } catch (Exception e){
      System.out.println("Error in building board : *1* " + e);
      return "ERROR ON BOARD";
    }
  }

  void printBoard() {
    for (int i = 0; i < board.length; i++) {
      System.out.println(board[i].length);
      for (int j = 0; j < board[i].length - 1; j++) {
        if (board[j][i] != '\0') {
          System.out.print("| " + board[j][i] + " ");
        }else{
          System.out.print("|   " );
        }
      }
      System.out.println("|");
    }
    System.out.println(myPlaces);
  }

  boolean placeCounter(char characterToPlace, int positionToDrop) {
    boolean placed = false;
    for (int i = board.length - 1; i >= 0; i--) {
      if (!placed) {
        if (board[positionToDrop - 1][i] == '\0') {// position null
          board[positionToDrop - 1][i] = characterToPlace;
          System.out.println(board[positionToDrop - 1][i]);
          placed = true;
        }
      }
    }
    return placed;
  }

}
