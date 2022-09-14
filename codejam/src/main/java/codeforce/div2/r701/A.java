package codeforce.div2.r701;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 12/02/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int a = sc.nextInt(), b = sc.nextInt();
        int ans = 50;
        int[] ff = new int[33];
        for(int x = b; x < b + 33; x++) {
          ff[x - b] = find(a, x);
        }
        for(int x = 0; x < 33; x++) {
          ans = Math.min(ans, ff[x] + x);
        }
        System.out.println(ans);
      }
    }
  }

  private static int find(int a, int b) {
    if(b == 1) return Integer.MAX_VALUE;
    int cnt = 1;
    while (a / b > 0) {
      a = a / b;
      cnt++;
    }
    return cnt;
  }
}