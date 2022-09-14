package codeforce.assiut.recursion;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @author pribic (Priyank Doshi)
 * @since 10/05/21
 */
public class GPyramid {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        printPyramid(n, 1);
      }
    }
  }

  private static void printPyramid(int n, int i) {
    if (i > n)
      return;
    System.out.print(iStar(n - i, ' '));
    System.out.println(iStar(2*i - 1, '*'));
    printPyramid(n, i + 1);
  }

  private static String iStar(int i, char c) {
    StringBuilder sb = new StringBuilder();
    IntStream.range(0, i).forEach(idx -> sb.append(c));
    return sb.toString();
  }
}