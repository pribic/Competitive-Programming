package codeforce.div2.r654;

import java.util.Scanner;

public class ProblemB {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        long n = sc.nextInt();
        long r = sc.nextInt();
        if (r == 1) { //width less {
          System.out.println(1);
        } else if (r < n) {
          System.out.println(r * (r + 1) / 2);
        } else {
          long min = Math.min(r, n);
          long ans = (min) * (min - 1) / 2;
          System.out.println(ans + 1);
        }
      }
    }
  }
}
