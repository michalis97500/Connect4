public class Player {
  char playerCharacter;
  String playerName;
  Board board;
  boolean human;

  public Player(String name, char playerChar, Board boardIplayOn, boolean human) {
    this.playerCharacter = playerChar;
    this.playerName = name;
    this.board = boardIplayOn;
    this.human = human;
  }
  public char getChar(){
    return this.playerCharacter;
  }
  public String getName(){
    return this.playerName;
  }
  public boolean amIHuman(){
    return this.human;
  }
  public Board whereIsPlaying(){
    return this.board;
  }
  public Boolean haveIWon(){
    return board.searchForWin(playerCharacter,board.getBoardChars());
  }
  public boolean myMove(int positionToDrop){
    if (board.placeCounter(playerCharacter, positionToDrop,board.getBoardChars())){
      board.printBoard();
      return true;
    }
    return false;
  }
  public int makeMove(){
    return 0; //do nothing
  }
  public void setEnemyCharacter(char enemyCharacter){
    //donoth
  }

}
