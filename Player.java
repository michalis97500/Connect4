public class Player {
  char playerCharacter;
  String playerName;
  Board board;

  public Player(String name, char playerChar, Board boardIplayOn) {
    this.playerCharacter = playerChar;
    this.playerName = name;
    this.board = boardIplayOn;
  }
  public char getChar(){
    return this.playerCharacter;
  }
  public String getName(){
    return this.playerName;
  }
  public Board whereIsPlaying(){
    return this.board;
  }
  public Boolean haveIWon(){
    return board.searchForWin(playerCharacter);
  }
  public void myMove(int positionToDrop){
    board.placeCounter(playerCharacter, positionToDrop);
    board.printBoard();
  }

}
