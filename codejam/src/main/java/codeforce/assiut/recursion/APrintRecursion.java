package codeforce.assiut.recursion;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 10/05/21
 */
public class APrintRecursion {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        print(n);
      }
    }
  }

  static void print(int n) {
    if (n == 0)
      return;
    System.out.println("I love Recursion");
    print(n - 1);
  }
}