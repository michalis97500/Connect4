public class Player {
  char playerCharacter;
  String playerName;
  boolean isPlayerHuman;
  Board board;

  public Player(String name, char playerChar, boolean amIHuman, Board boardIplayOn) {
    this.playerCharacter = playerChar;
    this.playerName = name;
    this.isPlayerHuman = amIHuman;
    this.board = boardIplayOn;
  }
  public char getChar(){
    return this.playerCharacter;
  }
  public String getName(){
    return this.playerName;
  }
  public boolean isHuman(){
    return this.isPlayerHuman;
  }
  public Board whereIsPlaying(){
    return this.board;
  }
  private Boolean haveIWon() {
    
    return true;
  }

}
