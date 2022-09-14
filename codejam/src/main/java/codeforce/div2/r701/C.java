package codeforce.div2.r701;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 12/02/21
 */
public class C {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long x = sc.nextInt(), y = sc.nextInt();
        long sqrt = (long) Math.sqrt(x);
        long cnt = (sqrt * (sqrt - 1)) / 2;
        for (long i = sqrt + 1; i <= y; i++) {
          if (i * i > x)
            cnt += x / (i + 1);
          else
            cnt += (i - 1);
        }
        System.out.println(cnt);
      }
    }
  }
  /**
   * 1000000000 1000000000
   * 10438801384
   */
} 