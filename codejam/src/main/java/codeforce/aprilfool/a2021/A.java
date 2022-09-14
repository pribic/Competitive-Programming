package codeforce.aprilfool.a2021;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 01/04/21
 */
public class A {

  public static void main(String[] args) {
    for (int a = 1; a <= 1000; a++) {
      for (int b = 1; b <= 1000; b++) {
        System.out.println(a + " " + b);
        simulate(a, b, 1);
      }
    }
  }

  private static void simulate(int a, int b, int cnt) {
    if (cnt == 10 && a != b) {
      System.exit(1);
    }
    if (a == b) {
      return;
    }

    simulate(a + 1, 2*b, cnt + 1);
    simulate(a * 2, b + 1, cnt + 1);
  }
}

