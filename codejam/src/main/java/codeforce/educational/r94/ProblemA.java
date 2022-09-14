package codeforce.educational.r94;

import java.util.Scanner;

public class ProblemA {

  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)){
      int t = sc.nextInt();
      for (int i = 0; i < t; i++) {
        int n = sc.nextInt();
        String s = sc.next();
        for (int j = 0; j < n; j++) {
          System.out.print(s.charAt(n - 1));
        }
        System.out.println();
      }
    }
  }
}
