package codeforce.assiut.recursion;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 10/05/21
 */
public class EBaseConverssion {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        printBinary(n);
        System.out.println();
      }
    }
  }

  private static void printBinary(int n) {
    if(n == 0 || n == 1) {
      System.out.print(n);
      return;
    }
    printBinary(n / 2);
    System.out.print(n % 2);
  }
}