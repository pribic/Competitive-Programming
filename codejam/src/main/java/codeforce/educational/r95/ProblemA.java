package codeforce.educational.r95;

import java.util.Scanner;

public class ProblemA {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        long x = sc.nextLong();
        long y = sc.nextLong();
        long k = sc.nextLong();
        System.out.println((y * k + k - 1 + x - 2) / (x - 1) + k);
      }
    }
  }
}
