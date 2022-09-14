package codechef.practic;

import java.util.*;
import java.util.stream.IntStream;

class Solution {
  public static void main(String[] args) {
    int[][] matrix = new int[][]
      {
        {1, 1, 1, 0},
        {0, 1, 0, 1},
        {0, 1, 0, 0},
        {1, 1, 0, 1}
      };
    System.out.println(new Solution().solve(matrix));
  }

  public int[][] solve(int[][] matrix) {
    int r = matrix.length;
    int c = matrix[0].length;
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        int alive = 0;
        for (int dx : new int[]{-1, 0, 1}) {
          for (int dy : new int[]{-1, 0, 1}) {
            if (dx == 0 && dy == 0) continue;
            if (valid(matrix, r, c, i + dx, j + dy)) {
              if (matrix[i + dx][j + dy] == 1)
                alive++;
            }
          }
        }
        //we know i,j cell have "alive" alive neighbours
        if (matrix[i][j] == 1) {
          //alive
          if (alive != 2 && alive != 3)
            matrix[i][j] = 0;
        } else {
          if (alive == 3)
            matrix[i][j] = 1;
        }
      }
    }
    return matrix;
  }

  boolean valid(int[][] matrix, int r, int c, int x, int y) {
    return x >= 0 && y >= 0 && x < r && y < c;
  }

}



