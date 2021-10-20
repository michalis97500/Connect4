import java.util.*;

public class AI extends Player {

  private char enemyCharacter;
  public AI(String name,char playerChar,Board boardIplayOn){
    super(name, playerChar, boardIplayOn,false);
  }
  public int canMoveWin(char characterToPlace,Board boardIplayOn){
    //creates virtual board, loops through all moves, if there is win return 1 else return 0
    for(int i=1; i<=this.board.getBoardX(); i++){
      Board temporaryHelper = new Board(this.board.getBoardX(),this.board.getBoardY(),this.board.connectN());
      temporaryHelper.setBoardChars(this.board.getBoardChars());
      this.board.placeCounter(characterToPlace, i,temporaryHelper.getBoardChars());
      if(this.board.searchForPairs(characterToPlace, temporaryHelper.getBoardChars(),temporaryHelper.connectN()) >= 1){
        return i;
      } 
    }
    return 0;
  }
  public ArrayList<Integer> moveValue(char friendlyCharacter, char enemyCharacter, Board boardToCheck){
    ArrayList<Integer> listOfMoves = new ArrayList<Integer>();
    for(int positionToPlay=1;positionToPlay<1+boardToCheck.getBoardX();positionToPlay++){ //for all possible plays that I can make
      Board temporaryHelper = new Board(this.board.getBoardX(),this.board.getBoardY(),this.board.connectN()); //create temp board
      temporaryHelper.setBoardChars(boardToCheck.getBoardChars());
      if(this.board.placeCounter(friendlyCharacter,positionToPlay,temporaryHelper.getBoardChars())){ //I have made my play.
        int thisMove = 0;
        for(int i=0;i<temporaryHelper.getBoardX();i++){ //loop through all positions
          for(int j=0;j<temporaryHelper.getBoardY();j++){
            int pairs2 = temporaryHelper.searchForPairs(friendlyCharacter, temporaryHelper.getBoardChars(), 2);
            int pairs3 = temporaryHelper.searchForPairs(friendlyCharacter, temporaryHelper.getBoardChars(), 3);
            thisMove =+ pairs2 + pairs3*100;
          }
        }
        listOfMoves.add(thisMove);
      }else {
        listOfMoves.add(0);
      }
    }
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
    ArrayList<Integer> moves = moveValue(this.playerCharacter,this.enemyCharacter,this.board);
    //since more than 1 spot can be "best-move", lets choose randomly one
    ArrayList<Integer> bestmoves = new ArrayList<Integer>();
    int maxVal = Collections.max(moves);
    for(int spot : moves){
      if(spot == maxVal){
        bestmoves.add(moves.indexOf(spot));
        moves.set(moves.indexOf(spot), spot-1);
      }
    }
    int moveToPlay = bestmoves.get(new Random().nextInt(bestmoves.size()));
    moves.clear();
    myMove(moveToPlay+1);
    return 0; //i moved
  }
}
