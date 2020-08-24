package cses.IntroductoryProblmes;

import java.util.Scanner;

public class IncreasingArray {
  public static void main(String[] args) {

    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      long expected = sc.nextInt();
      long turn = 0;
      for (int i = 0; i < n - 1; i++) {
        int x = sc.nextInt();
        if (x <= expected) {
          turn += (expected - x);
        } else
          expected = x;
      }
      System.out.println(turn);
    }
  }

}
