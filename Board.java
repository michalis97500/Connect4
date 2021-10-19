public class Board {
  private char[][] board;
  String myPlaces;
  int minBoard = 4;
  int boardX;
  int boardY;

  public Board(int x, int y) { //guard
    if(x<minBoard){
      System.out.println("X-dimension must be " + minBoard + " or more");
      x = minBoard;
    }
    if(y<minBoard){
      System.out.println("Y-dimension must be " + minBoard + " or more");
      y = minBoard;
    }
    boardX = x;
    boardY = y;
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
  public int getBoardX(){
    return boardX;
  }
  public int getBoardY(){
    return boardY;
  }
  public void printBoard() {
    for (int i = 0; i < boardY; i++) {
      for (int j = 0; j < boardX; j++) { 
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

  public boolean placeCounter(char characterToPlace, int positionToDrop) {
    boolean placed = false;
    for (int i = boardY - 1; i >= 0; i--) {
      if (!placed) {
        if (board[positionToDrop - 1][i] == '\0') {// position null
          board[positionToDrop - 1][i] = characterToPlace;
          placed = true;
        }
      }
    }
    return placed;
  }

    public boolean searchForWin(char didCharWin){
    int count = 0;
    for(int i=0; i<boardX; i++){
      for(int j=0; j<boardY; j++){
        if(board[i][j] == didCharWin){
          count = count + 1;
          if(count == 4){
            return true;
          }
        }
        else{
          count = 0;
        }
      }
    }
    // check vertical 
    count = 0;
    for(int i=0; i<boardY; i++){
      for(int j=0; j<boardX; j++){
        if(board[j][i] == didCharWin){
          count = count + 1;
          if(count == 4){
            return true;
          }
        }
        else{
          count = 0;
        }
      }
    }
    return false;
  }

}
