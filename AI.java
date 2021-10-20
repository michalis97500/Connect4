import java.util.ArrayList;
import java.util.Collections;
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

  public ArrayList<Integer> moveValue(char friendlyCharacter, char enemyCharacter, Board boardToCheck){
    ArrayList<Integer> listOfMoves = new ArrayList<Integer>();
    for(int positionToPlay=1;positionToPlay<1+boardToCheck.getBoardX();positionToPlay++){ //for all possible plays that I can make
      Board temporaryHelper = new Board(this.board.getBoardX(),this.board.getBoardY()); //create temp board
      temporaryHelper.setBoardChars(boardToCheck.getBoardChars());
      if(this.board.placeCounter(friendlyCharacter,positionToPlay,temporaryHelper.getBoardChars())){ //I have made my play.
        int thisMove = 0;
        for(int i=0;i<temporaryHelper.getBoardX();i++){ //loop through all positions
          for(int j=0;j<temporaryHelper.getBoardY();j++){
            int diag2 = temporaryHelper.searchForDiagonals(friendlyCharacter, temporaryHelper.getBoardChars(), 2);
            int diag3 = temporaryHelper.searchForDiagonals(friendlyCharacter, temporaryHelper.getBoardChars(), 3);
            int pairs2 = temporaryHelper.searchForPairs(friendlyCharacter, temporaryHelper.getBoardChars(), 2);
            int pairs3 = temporaryHelper.searchForPairs(friendlyCharacter, temporaryHelper.getBoardChars(), 3);
            thisMove =+ diag2+ diag3*100 +pairs2 + pairs3*100;
          }
        }
        listOfMoves.add(thisMove);
      }else {
        listOfMoves.add(0);
      }
    }
    System.out.println(listOfMoves);
    return listOfMoves;
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
    ArrayList<Integer> moves = new ArrayList<Integer>();
    while(!validMove){
      moves = moveValue(this.playerCharacter,this.enemyCharacter,this.board);
      //since more than 1 spot can be "best-move", lets choose randomly one
      ArrayList<Integer> bestmoves = new ArrayList<Integer>();
      for(int spot : moves){
        if(spot == Collections.max(moves)){
          bestmoves.add(moves.indexOf(spot));
        }
      }
      moves.clear(); 
      int moveToPlay = bestmoves.get(ThreadLocalRandom.current().nextInt(0, bestmoves.size()));
      validMove = myMove(moveToPlay+1);
    }
    return 0; // i did nothing
  }
}
