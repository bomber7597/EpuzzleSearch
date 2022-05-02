import java.util.*;

public class RunEpuzzleAStarSearch {

  public static void main(String[] arg) {

    int[][] p = {{4,1,3},{7,2,5},{0,8,6}};

    int[][] tar = {{1,2,3},{4,5,6},{7,8,0}};

    EpuzzGen g = new EpuzzGen(12345);

    EpuzzleSearch searcher = new EpuzzleSearch(p, tar);
    SearchState initState = (SearchState) new EpuzzleState(g.puzzGen(6));

    float resd = searcher.runSearchE(initState, "breadthFirst");
    System.out.println(resd);

  }

}
