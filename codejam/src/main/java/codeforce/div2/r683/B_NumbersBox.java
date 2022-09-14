package codeforce.div2.r683;

import java.util.Scanner;

public class B_NumbersBox {

  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][m];
        int min = Integer.MAX_VALUE;
        long sum = 0;
        int negativeCnt = 0;
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
            grid[i][j] = sc.nextInt();
            min = Math.min(min, Math.abs(grid[i][j]));
            sum += Math.abs(grid[i][j]);
            if(grid[i][j] < 0)
              negativeCnt++;
          }
        }
        if(negativeCnt % 2 == 0)
          System.out.println(sum);
        else {
          System.out.println(sum - 2*min);          
        }
        
      }
    }
  }
}
