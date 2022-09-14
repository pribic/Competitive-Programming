package codeforce.assiut.recursion;

import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;

/**
 * @author pribic (Priyank Doshi)
 * @since 10/05/21
 */
public class HInvertedPyramid {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        printPyramid(2 * n - 1, 0);
      }
    }
  }

  private static void printPyramid(int n, int space) {
    if (n <= 0)
      return;
    out.print(iStar(space, ' '));
    out.println(iStar(n, '*'));
    printPyramid(n - 2, space + 1);
  }

  private static String iStar(int i, char c) {
    return String.join("", Collections.nCopies(i, c + ""));
  }
}