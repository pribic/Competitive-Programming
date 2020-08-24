package codeforce.div2.r654;

import java.util.Scanner;

public class ProblemC {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        long a = sc.nextLong();
        long b = sc.nextLong();
        long n = sc.nextLong();
        long m = sc.nextLong();
        if (a + b == 0 || n > (a + b) || m > (a + b) || (n + m) > (a + b) || m > Math.min(a, b))
          System.out.println("No");
        else {
          System.out.println("Yes");
        }
      }
    }
  }
}
