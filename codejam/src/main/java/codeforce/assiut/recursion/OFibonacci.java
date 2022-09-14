package codeforce.assiut.recursion;

import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;

/**
 * @author pribic (Priyank Doshi)
 * @since 14/05/21
 */
public class OFibonacci {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        out.println(fibo(n));
      }
    }
  }

  private static long fibo(int n) {
    if(n == 1)
      return 0;
    else if(n == 2)
      return 1;
    return (long) ((Math.pow(1.618034, n) - Math.pow(1 - 1.618034, n) ) / Math.sqrt(n));
  }
}