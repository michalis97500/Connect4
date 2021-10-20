public class Board {
  private char[][] board;
  private String myPlaces;
  private int minBoard = 4;
  private int boardX;
  private int boardY;
  
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
  public boolean placeCounter(char characterToPlace, int positionToDrop,char[][] _board) {
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
  public boolean searchForWin(char didCharWin, char[][] _board){
    //check horizontal
    int count = 0;
    for(int i=0; i<boardX; i++){
      for(int j=0; j<boardY; j++){
        if(_board[i][j] == didCharWin){
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
        if(_board[j][i] == didCharWin){
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
    for(int i = 3; i < boardX; i++){ //check negative dia
			for(int j = 0; j < boardY - 3; j++){
				if (_board[i][j] == didCharWin && _board[i-1][j+1] == didCharWin && _board[i-2][j+2] == didCharWin && _board[i-3][j+3] == didCharWin){
					return true;
				}
			}
		}
		for(int i = 0; i < boardX - 3; i++){ //check positive dia
			for(int j = 0; j < boardY - 3; j++){
				if (_board[i][j] == didCharWin && _board[i+1][j+1] == didCharWin &&   _board[i+2][j+2] == didCharWin && _board[i+3][j+3] == didCharWin){
					return true;
				}
			}
		}
    return false;
  }
  public char[][] getBoardChars(){
    return board;
  }
  public void setBoardChars(char[][] setMe){
    for(int i=0;i<setMe.length;i++){
      for(int y=0;y<setMe[0].length;y++){
        this.board[i][y] = setMe[i][y];
      }
    }
  }
  public int canMoveWin(char characterToPlace){
    for(int i=1; i<=boardX; i++){
      Board temporaryHelper = new Board(this.boardX,this.boardY);
      temporaryHelper.setBoardChars(this.board);
      placeCounter(characterToPlace, i,temporaryHelper.getBoardChars());
      if(searchForWin(characterToPlace, temporaryHelper.getBoardChars()) == true){
        return i;
      } 
    }
    return 0;
  }
  public char getCharAtPosition(int x, int y){
    return this.board[x][y];
  }
  public int searchForPairs(char charToSearchFor, char[][] _board,int repetitions){
    //check horizontal
    int pairs = 0;
    int count = 0;
    for(int i=0; i<boardX; i++){
      for(int j=0; j<boardY; j++){
        if(_board[i][j] == charToSearchFor){
          count = count + 1;
          if(count == repetitions){
            pairs++;
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
        if(_board[j][i] == charToSearchFor){
          count = count + 1;
          if(count == repetitions){
            pairs++;
          }
        }
        else{
          count = 0;
        }
      }
    }
    return pairs;   
  }
  public int searchForDiagonals(char charToSearchFor, char[][] _board,int repetitions){
    int pairs =0;
    switch(repetitions){
      case 3:
        for(int i = 3; i < boardX; i++){ //check negative dia
          for(int j = 0; j < boardY - 3; j++){
            if (_board[i][j] == charToSearchFor && _board[i-1][j+1] == charToSearchFor && _board[i-2][j+2] == charToSearchFor){
              pairs++;
            }
          }
        }
        for(int i = 0; i < boardX - 3; i++){ //check positive dia
          for(int j = 0; j < boardY - 3; j++){
            if (_board[i][j] == charToSearchFor && _board[i+1][j+1] == charToSearchFor &&   _board[i+2][j+2] == charToSearchFor){
              pairs++;
            }
          }
        }
        break;
      case 2:
        for(int i = 3; i < boardX; i++){ //check negative dia
          for(int j = 0; j < boardY - 3; j++){
            if (_board[i][j] == charToSearchFor && _board[i-1][j+1] == charToSearchFor ){
              pairs++;
            }
          }
        }
        for(int i = 0; i < boardX - 3; i++){ //check positive dia
          for(int j = 0; j < boardY - 3; j++){
            if (_board[i][j] == charToSearchFor && _board[i+1][j+1] == charToSearchFor){
              pairs++;
            }
          }
        }
        break;
    }
    return pairs;
  }
}
