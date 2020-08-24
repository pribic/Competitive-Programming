public class hey {
  public static void main(String[] args) {
    new hey().countSquares(new int[][]{{0, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}});
  }

  public int countSquares(int[][] matrix) {
    int ans = 0;
    //creating dp of +1 size so we don't have to worry about 0th row and column of matrix
    int[][][] dp = new int[matrix.length + 1][matrix[0].length + 1][3];
    for (int r = 0; r < matrix.length; r++) {
      for (int c = 0; c < matrix[r].length; c++) {
        if (matrix[r][c] == 1) {
          int rr = r + 1;
          int cc = c + 1;
          dp[rr][cc][0] = dp[rr][cc - 1][0] + 1;
          dp[rr][cc][1] = dp[rr - 1][cc][1] + 1;
          dp[rr][cc][2] = min(dp[rr - 1][cc - 1][2], dp[rr - 1][cc][1], dp[rr][cc - 1][0]) + 1;
          ans += dp[rr][cc][2];
        }
      }
    }
    return ans;
  }

  int min(int... arr) {
    int m = Integer.MAX_VALUE;
    for (int i : arr)
      m = Math.min(m, i);
    return m;
  }

  /**
   * This solution is without dp and runs in O(n^3)
   * @param matrix
   * @return
   */
  public int countSquares1(int[][] matrix) {
    int ans = 0;
    //creating dp of +1 size so we don't have to worry about 0th row and column of matrix
    int[][][] dp = new int[matrix.length + 1][matrix[0].length + 1][2];
    for (int r = 0; r < matrix.length; r++) {
      for (int c = 0; c < matrix[r].length; c++) {
        if (matrix[r][c] == 1) {
          int rr = r + 1; // dp is +1 size. So it needs an offset of 1. So matrix(0,0) <-> dp(1,1)
          int cc = c + 1;
          dp[rr][cc][0] = dp[rr][cc - 1][0] + 1;
          dp[rr][cc][1] = dp[rr - 1][cc][1] + 1;
          int distance = 0;
          while (true) {
            int dr = rr - distance;
            int dc = cc - distance;
            if (dr <= 0 || dc <= 0 || Math.min(dp[dr][cc][0], dp[rr][dc][1]) <= distance++)
              break;
            ans++;
          }
        }
      }
    }
    return ans;
  }
}
