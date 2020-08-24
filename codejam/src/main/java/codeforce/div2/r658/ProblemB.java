package codeforce.div2.r658;

import java.util.Scanner;

public class ProblemB {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int i = 0; i < t; i++) {
        int n = sc.nextInt();
        int cnt = 0;
        boolean flag = false; //false means player 1's turn before the round starts
        boolean flag1 = true;
        for (int j = 0; j < n - 1; j++) {
          int val = sc.nextInt();
          if (flag1 && val == 1)
            flag = !flag;
          else
            flag1 = false;
        }
        sc.nextInt();
        System.out.println(!flag ? "First" : "Second");
      }
    }
  }
}
