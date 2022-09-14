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
 * @since 10/05/21
 */
public class KMaxNumber {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        out.println(printMAx(arr, arr[0], 1));
      }
    }
  }

  private static int printMAx(int[] arr, int minValue, int idx) {
    if(idx == arr.length)
      return minValue;
    return  printMAx(arr, Math.max(Math.max(minValue, arr[idx]), minValue), idx + 1); 
  }
}