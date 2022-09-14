package codeforce.educational.r96;

import java.util.Scanner;

public class ProblemA {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        long n = sc.nextInt();
        if (n == 1 || n == 2 || n == 4) {
          System.out.println("-1");
          continue;
        }
        outer:
        for (int i = 0; i <= 1000; i++) {
          for (int j = 0; j <= 1000; j++) {
            for (int k = 0; k <= 1000; k++) {
              if (3 * i + 5 * j + 7 * k == n) {
                System.out.println(i + " " + j + " " + k);
                break outer;
              }
            }
          }
        }
      }
    }
  }
}
