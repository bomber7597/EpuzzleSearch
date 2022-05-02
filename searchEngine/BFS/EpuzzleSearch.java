import java.util.*;

public class EpuzzleSearch extends Search {

  private int[][] problem = new int[3][3];
  private int[][] target = new int[3][3];

  public EpuzzleSearch(int[][] pro, int[][] tar) {

    problem = pro;
    target = tar;

  }

  public int[][] getProblem() {

    return problem;

  }

  public int[][] getTarget() {

    return target;

  }

}
