import java.util.ArrayList;

interface PlayerInterface {
  public char getChar();
  public String getName();
  public boolean amIHuman();
  public Board whereIsPlaying();
  public Boolean haveIWon();
  public boolean myMove(int positionToDrop);
  public int makeMove(InputHandler input);
  public void setEnemyCharacter(ArrayList<Character> enemyCharacter);
}
