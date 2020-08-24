package codeforce.div2.r651;

import java.util.Scanner;

public class ProblemA {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      for (int i = 0; i < n; i++) {
        int x = sc.nextInt();
        System.out.println(x / 2);
      }

    }
  }

}
