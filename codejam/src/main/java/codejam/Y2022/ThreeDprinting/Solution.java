package codejam.Y2022.ThreeDprinting;

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
      int[] arr = new int[4];
      Arrays.fill(arr, Integer.MAX_VALUE);
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 4; j++)
          arr[j] = Math.min(arr[j], sc.nextInt());
      }
      solve(arr);
    }
  }

  private static void solve(int[] arr) {
    for (int i = 0; i < 4; i++) {
      //try ith
      int cursum = getSum(arr);
      if (cursum > 1000000) {
        // try to change ith
        int extra = cursum - 1000000;
        //see if arr[i] has >= extra
        if (arr[i] >= extra) {
          arr[i] -= extra;
        } else {
          arr[i] = 0;
        }
      }
    }
    System.out.println(getSum(arr) == 1000000 ? arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3] : "IMPOSSIBLE");
  }

  private static int getSum(int[] arr) {
    return arr[0] + arr[1] + arr[2] + arr[3];
  }

}
