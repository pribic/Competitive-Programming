package codeforce.div2.r718;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 24/04/21
 */
public class D {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int[][][] cost = new int[n][m][4];
        //dir 0,1,2,3 represents NESW
        readRowCosts(sc, n, m, cost);
        readColCosts(sc, n, m, cost);
        // end of reading
        
        
      }
    }
  }

  private static void readColCosts(Scanner sc, int n, int m, int[][][] cost) {
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < m; j++) {
        int cst = sc.nextInt();
        //cst represents cost between i,j and i+1, j
        cost[i][j][2] = cst;
        cost[i + 1][j][0] = cst;
      }
    }
  }

  private static void readRowCosts(Scanner sc, int n, int m, int[][][] cost) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m - 1; j++) {
        int cst = sc.nextInt();
        // it represents cost from i,j to i,j+1
        cost[i][j][1] = cst;
        cost[i][j + 1][3] = cst;
      }
    }
  }
}