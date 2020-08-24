package cses.IntroductoryProblmes;

import java.util.Scanner;

public class Permutations {
  public static void main(String[] args) {

    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      StringBuilder ans = new StringBuilder();
      if (n == 2 || n == 3)
        System.out.println("NO SOLUTION");
      else {
        long start = (n % 2 == 0) ? 2 : 1;
        for (int i = 0; i < n; i++) {
          ans.append(start + " ");
          start += 2;
          if (start > n) {
            start = (n % 2 == 0) ? 1 : 2;
          }
        }
      }
      System.out.println(ans);
    }
  }
}
