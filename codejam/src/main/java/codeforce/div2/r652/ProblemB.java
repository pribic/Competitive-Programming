package codeforce.div2.r652;

import java.util.Scanner;

public class ProblemB {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int i = 0; i < t; i++) {
        String s = sc.next();
        int cntZero = s.replace("1", "").length();
        int cntOne = s.replace("0", "").length();
        int n = s.length();

        int min = Math.min(cntOne, cntZero);
        System.out.println(min % 2 == 0 ? "NET" : "DA");
      }
    }
  }
}
