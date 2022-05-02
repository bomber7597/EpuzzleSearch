import java.util.*;

public class EpuzzleState extends SearchState {

  private int[][] puzzleGrid = new int[3][3];


  public EpuzzleState(int[][] pg) {

    puzzleGrid = pg;
    estRemCost = manhattanDistance();
    localCost = 1;
  }

  public int[][] getPuzzleGrid(){

    return puzzleGrid;

  }

  public boolean goalPredicate(Search searcher){

    EpuzzleSearch eSearcher = (EpuzzleSearch) searcher;
    int[][] tar = eSearcher.getTarget();
    return Arrays.deepEquals(tar, puzzleGrid);

  }

  public ArrayList<SearchState> getSuccessors(Search searcher) {

    EpuzzleSearch eSearcher = (EpuzzleSearch) searcher;

    int[][] target = eSearcher.getTarget();

    int x = 0;
    int y = 0;

    int[][] puzzleTop = new int[3][3];
    int[][] puzzleBottom = new int[3][3];
    int[][] puzzleLeft = new int[3][3];
    int[][] puzzleRight = new int[3][3];

    ArrayList<EpuzzleState> eslis = new ArrayList<EpuzzleState>();
    ArrayList<SearchState> slis = new ArrayList<SearchState>();

    for (int i = 0; i < 3; i++){
      for (int j = 0; j < 3; j++){
        if (puzzleGrid[i][j] == 0){
            y = i;
            x = j;
        }
      }
    }

    //finding the values of the numbers in the places around the 0 value
    int valueTop = 0;
    int valueBottom = 0;
    int valueLeft = 0;
    int valueRight = 0;
    if (x < 2) {
      valueRight = puzzleGrid[y][x+1];
    }
    if (x > 0) {
      valueLeft = puzzleGrid[y][x-1];
    }
    if (y < 2) {
      valueBottom = puzzleGrid[y+1][x];
    }
    if (y > 0){
      valueTop = puzzleGrid[y-1][x];
    }

    //initialise each puzzle to have the same information as the current puzzleGrid that is in use
    for (int i = 0; i < 3; i++){
      for (int j = 0; j < 3; j++){
        puzzleTop[i][j] = puzzleGrid[i][j];
      }
    }

    for (int i = 0; i < 3; i++){
      for (int j = 0; j < 3; j++){
        puzzleBottom[i][j] = puzzleGrid[i][j];
      }
    }

    for (int i = 0; i < 3; i++){
      for (int j = 0; j < 3; j++){
        puzzleLeft[i][j] = puzzleGrid[i][j];
      }
    }

    for (int i = 0; i < 3; i++){
      for (int j = 0; j < 3; j++){
        puzzleRight[i][j] = puzzleGrid[i][j];
      }
    }
    //creating 4 new puzzle layouts witht he zero swapped with the surrounding tiles
    if (x > 0){
      puzzleTop[y][x] = valueLeft;
      puzzleTop[y][x-1] = 0;
      eslis.add(new EpuzzleState(puzzleTop));
    }

    if (x < 2) {
      //if y is 0, empty tile is in bottom of puzzle so can't move downwards
      puzzleBottom[y][x] = valueRight;
      puzzleBottom[y][x+1] = 0;
      eslis.add(new EpuzzleState(puzzleBottom));
    }

    if (y > 0){
      puzzleLeft[y][x] = valueTop;
      puzzleLeft[y-1][x] = 0;
      eslis.add(new EpuzzleState(puzzleLeft));
    }

    if (y < 2){
      puzzleRight[y][x] = valueBottom;
      puzzleRight[y+1][x] = 0;
      eslis.add(new EpuzzleState(puzzleRight));
    }

    for (EpuzzleState es : eslis)
      slis.add((SearchState) es);

    return slis;

  }

  public boolean sameState(SearchState s2) {

    EpuzzleState es2 = (EpuzzleState) s2;

    return (Arrays.deepEquals(puzzleGrid, es2.getPuzzleGrid()));

  }

  public int hammingDistance() {

    int counter = 0;
    int[][] target = {{1,2,3},{4,5,6},{7,8,0}};

    for (int i = 0; i < 3; i++){
      for (int j = 0; j < 3; j++){
        if (puzzleGrid[i][j] != target[i][j]) {
          counter++;
        }
      }
    }

    return counter;

  }

  public int manhattanDistance() {

    int counter = 0;

    for (int n = 1; n < 9; n++) {
      for (int i = 0; i < 3; i++){
        for (int j = 0; j < 3; j++){
          if (puzzleGrid[i][j] == n){
            counter = counter + rowDistance(i,n) + columnDistance(j,n);
          }
        }
      }
    }

    return counter;

  }

  public int rowDistance(int currentRow, int num){

    int[][] target = {{1,2,3},{4,5,6},{7,8,0}};
    int correctRow = 0;
    int difference = 0;

    for (int i = 0; i < 3; i++){
      for (int j = 0; j < 3; j++){
        if (target[i][j] == num){
          correctRow = i;
        }
      }
    }

    difference = Math.abs(correctRow - currentRow);

    return difference;

  }

  public int columnDistance(int currentColumn, int num){

    int[][] target = {{1,2,3},{4,5,6},{7,8,0}};
    int correctColumn = 0;
    int difference = 0;

    for (int i = 0; i < 3; i++){
      for (int j = 0; j < 3; j++){
        if (target[i][j] == num){
          correctColumn = j;
        }
      }
    }

    difference = Math.abs(correctColumn - currentColumn);

    return difference;

  }

  public String toString() {
    String deepArray = "\n";
    for (int[] i : puzzleGrid){
      for (int j : i) {
        deepArray = deepArray + j + " ";
      }
      deepArray = deepArray + "\n";
    }
    return deepArray;
  }
}
