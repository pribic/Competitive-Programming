package kickstart.Y2021.round1G.BananaBunches;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.minBy;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/00000000004362d6/00000000008b44ef" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/00000000004362d6/00000000008b44ef</a>
 * @since 16/10/21 6:12 PM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] bunches = new int[n];
        boolean anySameK = false;
        for (int i = 0; i < n; i++) {
          bunches[i] = sc.nextInt();
          anySameK = anySameK || bunches[i] == k;
        }
        if (anySameK) {
          System.out.println(1);
          continue;
        }
        int[] ps = new int[n];
        int cs = 0;
        for (int i = 0; i < n; i++) {
          cs += bunches[i];
          ps[i] = cs;
        }
        int minLen = Integer.MAX_VALUE;
        Map<Integer, Integer> subArraySums = new HashMap<>(); // stores sum vs min length of sub array to achieve that sum
        subArraySums.put(0, 0);
        for (int start2 = 0; start2 < n; start2++) {
          for (int end2 = start2; end2 < n; end2++) {
            int secondSum = sum(ps, start2, end2);
            if (secondSum > k)
              break;
            Integer val = subArraySums.get(k - secondSum);
            if (val != null)
              minLen = Math.min(minLen, val + end2 - start2 + 1);
          }
          for (int i = start2; i >= 0; i--) {
            int firstSum = sum(ps, i, start2);
            if (firstSum > k)
              break;
            if (start2 - i + 1 < subArraySums.getOrDefault(firstSum, Integer.MAX_VALUE))
              subArraySums.put(firstSum, start2 - i + 1);
          }
        }
        System.out.println(minLen == Integer.MAX_VALUE ? -1 : minLen);
      }
    }
  }


  private static int sum(int[] ps, int a, int b) {
    if (a == 0)
      return ps[b];
    return ps[b] - ps[a - 1];
  }

  public static void main1(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] bunches = new int[n];
        for (int i = 0; i < n; i++) {
          bunches[i] = sc.nextInt();
        }
        int[][][][] dp = new int[n][2][2][k + 1];

        //base cases
        for (int[][][] one : dp)
          for (int[][] two : one)
            for (int[] three : two)
              Arrays.fill(three, Integer.MAX_VALUE / 2);

        if (bunches[0] <= k)
          dp[0][0][1][bunches[0]] = 1;
        if (bunches[0] <= k)
          dp[1][1][0][bunches[0]] = 1;
        if (bunches[1] <= k)
          dp[1][0][1][bunches[1]] = 1;
        if (bunches[0] + bunches[1] <= k)
          dp[1][1][1][bunches[0] + bunches[1]] = 2;
        for (int i = 2; i < n; i++) {
          for (int kk = 1; kk <= k; kk++) {
            //don't take i and i-1
            dp[i][0][0][kk] = min(dp[i - 2][0][0][kk], dp[i - 2][0][1][kk], dp[i - 2][1][0][kk], dp[i - 2][1][1][kk]);
            //don't take i, take i - 1
            if (kk - bunches[i - 1] >= 0)
              dp[i][1][0][kk] = 1 + min(dp[i - 2][0][0][kk - bunches[i - 1]], dp[i - 2][0][1][kk - bunches[i - 1]], dp[i - 2][1][0][kk - bunches[i - 1]]);
            //take i
            if (kk - bunches[i] >= 0) {
              //take i but don't take i - 1
              dp[i][0][1][kk] = 1 + min(dp[i - 2][0][0][kk - bunches[i]], dp[i - 2][0][1][kk - bunches[i]], dp[i - 2][1][0][kk - bunches[i]], dp[i - 2][1][1][kk - bunches[i]]);
              //take i and i - 1
              dp[i][1][1][kk] = 1 + dp[i - 1][0][1][kk - bunches[i]];
            }
          }
        }
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
          minCost = min(minCost, dp[i][0][0][k], dp[i][0][1][k], dp[i][1][0][k], dp[i][1][1][k]);
        }
        System.out.println(minCost > n ? -1 : minCost);
      }
    }
  }

  private static int min(int a, int b, int c, int d, int e) {
    return min(a, min(b, c, d, e));
  }

  private static int min(int a, int b, int c, int d) {
    return min(min(a, b), min(c, d));
  }

  private static int min(int a, int b, int c) {
    return min(min(a, b), c);
  }

  private static int min(int a, int b) {
    return Math.min(a, b);
  }

  static class FastScanner {
    BufferedReader br;
    StringTokenizer st;

    public FastScanner(File f) {
      try {
        br = new BufferedReader(new FileReader(f));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }

    public FastScanner(InputStream f) {
      br = new BufferedReader(new InputStreamReader(f), 32768);
    }

    String next() {
      while (st == null || !st.hasMoreTokens()) {
        String s = null;
        try {
          s = br.readLine();
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (s == null)
          return null;
        st = new StringTokenizer(s);
      }
      return st.nextToken();
    }

    boolean hasMoreTokens() {
      while (st == null || !st.hasMoreTokens()) {
        String s = null;
        try {
          s = br.readLine();
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (s == null)
          return false;
        st = new StringTokenizer(s);
      }
      return true;
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }
  }
}