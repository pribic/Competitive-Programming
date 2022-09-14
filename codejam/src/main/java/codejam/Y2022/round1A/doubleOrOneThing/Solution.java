package codejam.Y2022.round1A.doubleOrOneThing;

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
      String str = sc.next();
      solve(str);
    }
  }

  private static void solve(String arr) {
    char prev = 'A' - 1;
    int count = 0;
    StringBuilder ans = new StringBuilder();
    for (char ch : arr.toCharArray()) {
      if (ch != prev) {
        if (prev < ch) {
          //double
          for (int i = 0; i < 2 * count; i++)
            ans.append(prev);
        } else {
          //as it is
          for (int i = 0; i < count; i++)
            ans.append(prev);
        }
        count = 1;
      } else {
        count++;
      }
      prev = ch;
    }
    for (int i = 0; i < count; i++)
      ans.append(prev);
    System.out.println(ans);
  }

  private static int getSum(int[] arr) {
    return arr[0] + arr[1] + arr[2] + arr[3];
  }

}
