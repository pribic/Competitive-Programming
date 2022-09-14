package codeforce.educational.r96;

import java.util.Scanner;

public class ProblemC {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        System.out.println("2");
        if (n == 2) {
          System.out.println("2 1");
          continue;
        }
        System.out.println(n + " " + (n - 1));
        System.out.println(n + " " + (n - 2));
        int a = n - 1;
        for (int i = 0; i < n - 3; i++) {
          System.out.println(a + " " + (a - 2));
          a--;
        }
      }
    }
  }
}
