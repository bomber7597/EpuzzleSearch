import java.util.*;

public class RunEpuzzleAStarSearch {

  public static void main(String[] arg) {

    int[][] p1 = {{1,0,3},{4,2,6},{7,5,8}};
    int[][] p2 = {{4,1,3},{7,2,5},{0,8,6}};
    int[][] p3 = {{2,3,6},{1,5,8},{4,7,0}};

    int[][] tar = {{1,2,3},{4,5,6},{7,8,0}};

    EpuzzGen gen = new EpuzzGen(12345);
    int easy = 6;
    int medium = 8;
    int difficult = 10;

    int[][] puzzle = gen.puzzGen(difficult);

    EpuzzleSearch searcher = new EpuzzleSearch(puzzle, tar);
    SearchState initState = (SearchState) new EpuzzleState(puzzle);

    float resd = searcher.runSearchE(initState, "AStar");
    System.out.println(resd);


  }

}
