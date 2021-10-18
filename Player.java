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

  private Boolean haveIWon() {
    
    return true;
  }

}
