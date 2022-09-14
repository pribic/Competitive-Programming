package codejam.Y2022.round1A.equalSum;

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
      int n = sc.nextInt();
      int[] ours = new int[n];
      int[] theirs = new int[n];
      solve(ours, theirs);
    }
  }

  private static void solve(int[] ours, int[] theirs) {
    int sum1 = 0;
    int sum2 = Arrays.stream(theirs).sum();
    
  }


  private static int getSum(int[] arr) {
    return arr[0] + arr[1] + arr[2] + arr[3];
  }

}
