package codechef.virtualContest;

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
 * @since 11/05/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        String s = sc.next();
        String c = sc.next();
        String t = sc.next();
        int minCost = Integer.MAX_VALUE / 2;
        for (int i = 0; i < s.length(); i++) {
          if (s.charAt(i) == t.charAt(0)) {
            minCost = Math.min(minCost, (c.charAt(i) - '0') + solve(s, c, t, i + 1, 1));
          }
        }
        if (minCost >= Integer.MAX_VALUE / 2)
          out.println(-1);
        else
          out.println(minCost);
      }
    }
  }
  
  
  private static int solve(String s, String c, String t, int idxS, int idxT) {
    if (idxT == t.length())
      return 0;
    if (idxS >= s.length())
      return Integer.MAX_VALUE / 2;
    int minCost = Integer.MAX_VALUE / 2;
    for (int i = idxS; i < s.length(); i++) {
      if (s.charAt(i) == t.charAt(idxT))
        minCost = Math.min(minCost, (c.charAt(i) - '0') + solve(s, c, t, i + 1, idxT + 1));
    }
    return minCost;
  }
}