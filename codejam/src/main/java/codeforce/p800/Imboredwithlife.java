package codeforce.p800;

import java.util.Scanner;

public class Imboredwithlife {

  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)) {
      long a = sc.nextLong();
      long b = sc.nextLong();
      System.out.println(fact(Math.min(a, b)));
    }
  }

  private static long fact(long min) {
    long ans = 1;
    for (int i = 2; i <= min; i++) {
      ans *= i;
    }
    return ans;
  }
}
