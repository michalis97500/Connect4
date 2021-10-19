import java.util.concurrent.ThreadLocalRandom;
public class AI extends Player {

  private int depth;
  private char enemyCharacter;
  public AI(String name,char playerChar,Board boardIplayOn,int depth){
    super(name, playerChar, boardIplayOn,false);
    this.depth = depth;
  }

  public int canMoveWin(char characterToPlace,Board boardIplayOn){
    for(int i=1; i<=this.board.getBoardX(); i++){
      Board temporaryHelper = new Board(this.board.getBoardX(),this.board.getBoardY());
      temporaryHelper.setBoardChars(this.board.getBoardChars());
      this.board.placeCounter(characterToPlace, i,temporaryHelper.getBoardChars());
      if(this.board.searchForWin(characterToPlace, temporaryHelper.getBoardChars()) == true){
        return i;
      } 
    }
    return 0;
  }
  @Override
  public void setEnemyCharacter(char enemyCharacter){
    this.enemyCharacter = enemyCharacter;
  }
  @Override
  public int makeMove(){
    //look for win
    if(canMoveWin(this.playerCharacter, this.board) != 0){
      this.myMove(canMoveWin(this.playerCharacter, this.board));;
      return 1; //i won
    }
    else if(canMoveWin(this.enemyCharacter, this.board) != 0){
      this.myMove(canMoveWin(this.enemyCharacter, this.board));;
      return 1; //i blocked enemy
    }
    boolean validMove = false;
    while(!validMove){
      int randomNum = ThreadLocalRandom.current().nextInt(1, this.board.getBoardX() + 1);
      validMove = myMove(randomNum);
    }
    return 0; // i did nothing
  }
}
