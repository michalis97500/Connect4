import java.util.*;
import java.lang.Math;

public class Board {
  private char[][] board;
  private String myPlaces;
  private int minBoard = 2;
  private int boardX;
  private int boardY;
  private int connectN;

  public Board(int x, int y, int n) { // guard
    if (x < minBoard) {
      System.out.println("X-dimension must be " + minBoard + " or more");
      x = minBoard;
    }
    if (y < minBoard) {
      System.out.println("Y-dimension must be " + minBoard + " or more");
      y = minBoard;
    }
    if(n > Math.sqrt(Math.pow(y,2)+Math.pow(x,2))){
      Integer nold = n;
      n = (int)Math.sqrt(Math.pow(y,2)+Math.pow(x,2)) - 1;
      System.out.println("With connect N set to " + nold + " all match results in draw/instant win. Resetting to " + n);
    }
    this.connectN = n;
    this.boardX = x;
    this.boardY = y;
    this.board = new char[x][y];
    this.myPlaces = GetMyPlaces(x);
  }

  private String GetMyPlaces(int x) { // Last line to show options for player to place mark
    try {
      StringBuilder stringToBuild = new StringBuilder();
      stringToBuild.append("  1");
      for (int i = 1; i < x; i++) {
        int place = 1 + i;
        stringToBuild.append("   " + place);
      }
      return stringToBuild.toString();
    } catch (Exception e) {
      System.out.println("Error in building board : *1* " + e);
      return "ERROR ON BOARD";
    }
  }

  public int getconnectN() { // set N
    return this.connectN;
  }

  public int getBoardX() { // get board columns
    return boardX;
  }

  public int getBoardY() {// get board rows
    return boardY;
  }

  public char getCharAtPosition(int x, int y) {// return mark at given positon
    try {
      return this.board[x][y];
    } catch (Exception e) {
      System.out.println("Error in getting character @" + x + "," + y + " :" + e);
      return '\0';
    }

  }

  public void printBoard() { // print to console
    for (int i = 0; i < boardY; i++) {
      for (int j = 0; j < boardX; j++) {
        if (board[j][i] != '\0') {
          System.out.print("| " + board[j][i] + " ");
        } else {
          System.out.print("|   ");
        }
      }
      System.out.println("|");
    }
    System.out.println(myPlaces);
  }

  public char[][] getBoardChars() {
    return board;
  }

  public void setBoardChars(char[][] setMe) {
    if(setMe.length == boardX && setMe[0].length == boardY){
      for (int i = 0; i < setMe.length; i++) {
        for (int y = 0; y < setMe[0].length; y++) {
          this.board[i][y] = setMe[i][y];
        }
      }
    }else{
      System.out.println("Dimension mismatch, cannot set board");
    }
  }

  public boolean placeCounter(char characterToPlace, int positionToDrop, char[][] _board) { // simulate the dropping of
                                                                                            // a mark, if illegal
                                                                                            // returns false
    boolean placed = false;
    for (int i = _board[0].length - 1; i >= 0; i--) {
      if (!placed) {
        if (_board[positionToDrop - 1][i] == '\0') {// position null
          _board[positionToDrop - 1][i] = characterToPlace;
          placed = true;
        }
      }
    }
    return placed;
  }

  public boolean didMatchDraw(){
    for (char[] charcterArray : board){
      for (char character : charcterArray){
        if (character == '\0'){
          return false;
        }
      }
    }
    return true;
  }

  public int searchForPairs(char charToSearchFor, char[][] _board, int repetitions) {
    // check horizontal
    int pairs = 0;
    int count = 0;
    for (int i = 0; i < boardX; i++) {
      for (int j = 0; j < boardY; j++) {
        if (_board[i][j] == charToSearchFor) {
          count = count + 1;
          if (count == repetitions) {
            pairs++;
          }
        } else {
          count = 0;
        }
      }
      count = 0;
    }
    // check vertical
    count = 0;
    for (int i = 0; i < boardY; i++) {
      for (int j = 0; j < boardX; j++) {
        if (_board[j][i] == charToSearchFor) {
          count = count + 1;
          if (count == repetitions) {
            pairs++;
          }
        } else {
          count = 0;
        }
      }
      count = 0;
    }
    for (int i = repetitions - 1; i < boardX; i++) { // check negative dia
      for (int j = 0; j < boardY - (repetitions - 1); j++) {
        ArrayList<Character> storeChar = new ArrayList<Character>();
        // store n characters in an arraylist
        for (int winInt = 0; winInt < repetitions; winInt++) {
          storeChar.add(_board[i - winInt][j + winInt]);
        }
        // This trick was inspired by a question in Stackoverflow,
        // https://stackoverflow.com/questions/562894/java-detect-duplicates-in-arraylist
        // Code was modified for this implementation
        Set<Character> set = new HashSet<Character>(storeChar); // convert to set, duplicates will be removed
        Object[] testArray = set.toArray(); // make it an array again
        if (testArray.length == 1 && testArray[0] == (Object) charToSearchFor) { // only 1 char exists AND that char is
                                                                                 // the one we want
          pairs++;
        }
        storeChar.clear();
      }
    }
    for (int i = 0; i < boardX - (repetitions - 1); i++) { // check positive dia
      for (int j = 0; j < boardY - (repetitions - 1); j++) {
        ArrayList<Character> storeChar = new ArrayList<Character>();
        // store n characters in an arraylist
        for (int winInt = 0; winInt < repetitions; winInt++) {
          storeChar.add(_board[i + winInt][j + winInt]);
        }
        // This trick was inspired by a question in Stackoverflow,
        // https://stackoverflow.com/questions/562894/java-detect-duplicates-in-arraylist
        // Code was modified for this implementation
        Set<Character> set = new HashSet<Character>(storeChar);
        Object[] testArray = set.toArray();
        if (testArray.length == 1 && testArray[0] == (Object) charToSearchFor) {
          pairs++;
        }
        storeChar.clear();
      }
    }
    return pairs;
  }
}
