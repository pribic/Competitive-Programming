package codechef.JAN21C;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 01/01/21
 */
public class DIVTHREE {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt(); int  k = sc.nextInt(); int d = sc.nextInt();
        int sum = 0;
        for (int i = 0; i < n; i++) {
          sum += sc.nextInt();
        }
        int perDay = sum/k;
        System.out.println(Math.min(d, perDay));
      }
    }
  }
}