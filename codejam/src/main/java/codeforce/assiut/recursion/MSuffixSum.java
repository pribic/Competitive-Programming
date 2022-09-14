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
public class MSuffixSum {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        out.println(suffixSum(arr, m, n - 1));
      }
    }
  }

  private static long suffixSum(int[] arr, int m, int lastIndex) {
    if(m == 0)
      return 0;
    return arr[lastIndex] + suffixSum(arr, m - 1, lastIndex - 1);
  }
}