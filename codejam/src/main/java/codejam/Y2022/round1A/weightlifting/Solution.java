package codejam.Y2022.round1A.weightlifting;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author pdoshi
 * @since 02/04/22
 */
public class Solution {


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    for (int tt = 1; tt <= T; tt++) {
      System.out.print("Case #" + tt + ": ");
      int e = sc.nextInt();
      int w = sc.nextInt();
      int[][] ex = new int[e][w];
      for (int i = 0; i < e; i++) {
        for (int j = 0; j < w; j++) {
          ex[i][j] = sc.nextInt();
        }
      }
      solve(e, w, ex);
    }
  }

  private static void solve(int e, int w, int[][] ex) {
      
  }

  private static int getSum(int[] arr) {
    return arr[0] + arr[1] + arr[2] + arr[3];
  }

}
