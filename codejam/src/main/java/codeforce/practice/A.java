package codeforce.practice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author pribic (Priyank Doshi)
 * @since 13/04/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long a = sc.nextInt();
        long b = sc.nextInt();
        System.out.println(min(b + 1, Math.max(b + 1, 2 * a + 1), a - Math.abs(a - b) - 1));
      }
    }
  }

  private static long min(long... nums) {
    return Arrays.stream(nums).filter(num -> num >= 0 && num <= 1_000_000_000).min().orElseGet(() -> -1);
  }

  private static long ans(long b) {
    return b <= 1_000_000_000 ? b : -1;
  }
}