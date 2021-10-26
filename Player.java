import java.util.ArrayList;

public class Player implements PlayerInterface{
  char playerCharacter;
  String playerName;
  Board board;
  boolean human;
  ArrayList<Character> enemyCharacterList = new ArrayList<Character>();

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
    if(board.searchForPairs(playerCharacter,board.getBoardChars(),board.getconnectN()) >= 1){
      return true;
    }
    return false;
  }

  public boolean myMove(int positionToDrop){
    try{
        if (board.placeCounter(playerCharacter, positionToDrop,board.getBoardChars())){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Last move by " + getName() + " at position " + positionToDrop);
        board.printBoard();
        return true;
      } else {
        System.out.println("That move is illegal");
      }
      return false;
    } catch(Exception e){
      System.out.println("Error when trying to make move : " + e);
      return false;
    }
  }

  public int makeMove(InputHandler input){
    int move = 999999; //wait for legal move from player
    boolean validInput = false;
    while (!validInput) {
      move = input.ReadLine();
      if (move != 999999) {// valid move
        validInput = true;
      }
    }
    this.myMove(move);
    return 0;
  }
  public void setEnemyCharacter(ArrayList<Character> enemyCharacters){
    //do nothing
  }

}
