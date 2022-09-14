package codeforce.assiut.recursion;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 10/05/21
 */
public class BPrintfrom1toN {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        print(n, 1);
      }
    }
  }

  private static void print(int n, int i) {
    if (i > n)
      return;
    System.out.println(i);
    print(n, i + 1);
  }
}