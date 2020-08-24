package Y2019.round1A.pylons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    for(int tt = 1; tt<= t; tt++){
      int r = sc.nextInt();
      int c = sc.nextInt();
      int[][] grid = new int[r][c];
      grid[0][0] = 1;
      if (solve(grid, 0 ,0 , 1)) {
        System.out.println(String.format("Case #%d: POSSIBLE", tt));
        List<Point> path = new ArrayList<>();
        for (int i = 0; i < r; i++) {
          for (int j = 0; j < c; j++) {
            path.add(new Point(i + 1 , j+1 , grid[i][j]));
            System.out.print(grid[i][j] + "  ");
          }
          System.out.println();
        }
        Collections.sort(path);
        for(Point p : path)
          ;//System.out.println(p.x + " " + p.y);
      }
      else
        System.out.println(String.format("Case #%d: IMPOSSIBLE", tt));
      
    }
    sc.close();
  }

  /**
   * we can prove that all valid solutions can start with 0 0 only. no need to start with all cell.
   * @param grid
   * @return
   */
  private static boolean solve(int[][] grid, int x, int y, int len) {
    int r = grid.length;
    int c = grid[0].length;
    if(len == r * c) {
      return true;
    }
    //first try night's move, otherwise bruteforce
    int[] dx = { 1, -1, 1, -1, 2, 2, -2, -2};
    int[] dy = { 2, 2, -2, -2, 1, -1, 1, -1};
    for (int tr = 0; tr < dx.length; tr++) {
      int i = x + dx[tr];
      int j = y + dy[tr];
      if (validIndex(grid,i, j)) {
        if (grid[i][j] == 0 && !(i == x || j == y || Math.abs(x - i) == Math.abs(y - j) || x + i == y + j)) {
          grid[i][j] = len + 1;
          boolean solved = solve(grid, i, j, len + 1);
          if (solved)
            return solved;
          //marking grid cell as available
          grid[i][j] = 0;
        }
      }
    }
    for (int i = 0; i < r; i++) {
      if(i == x)
        continue;
      for (int j = 0; j < c; j++) {
        if(j == y)
          continue;
        //check if it is valid position. if yes, move there
        if(grid[i][j] == 0 && !(i == x || j == y || Math.abs(x - i) == Math.abs(y - j) || x + i == y + j)) {
          grid[i][j] = len + 1;
          boolean solved = solve(grid, i, j, len + 1);
          if(solved)
            return solved;
          //marking grid cell as available
          grid[i][j] = 0;
        }
      }
    }
    return false;
  }

  private static boolean validIndex(int[][] grid, int i, int j) {
    try {
      int x = grid[i][j];
      return true;
    }catch (ArrayIndexOutOfBoundsException e){
      return false;
    }
  }


  private static class Point implements Comparable<Point> {
    int x;
    int y;
    int index;
    
    Point(int x, int y, int index) {
      this.x = x;
      this.y = y;
      this.index = index;
    }
    
    @Override
    public int compareTo(Point o) {
      return this.index - o.index;
    }
  }
}
