package codeforce.assiut.recursion;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 10/05/21
 */
public class CPrintfromNto1 {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        print(n);
      }
    }
  }

  private static void print(int n) {
    if(n == 1) {
      System.out.println(n);
      return;
    }
    System.out.print(n + " ");
    print(n - 1);
  }
}