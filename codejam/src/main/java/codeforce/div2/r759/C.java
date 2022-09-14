package codeforce.div2.r759;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.List;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1591/problem/C" target="_top">https://codeforces.com/contest/1591/problem/C</a>
 * @since 12/12/21 9:06 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      StringBuilder ans = new StringBuilder();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        long dist = Long.MAX_VALUE;
        List<Integer> positive = IntStream.of(arr).filter(num -> num > 0).sorted().boxed().collect(Collectors.toList());
        List<Integer> negative = IntStream.of(arr).filter(num -> num < 0).map(num -> -1 * num).sorted().boxed().collect(Collectors.toList());
        long bestPos = calc(positive, k);
        long bestNeg = calc(negative, k);
        long extreme = 0;
        if (positive.size() > 0)
          extreme = positive.get(positive.size() - 1);
        if (negative.size() > 0)
          extreme = Math.max(extreme, negative.get(negative.size() - 1));
        dist = Math.min(dist, bestPos + bestNeg - extreme);
        ans.append(dist).append("\n");
      }
      System.out.print(ans);
    }
  }


  private static long calc(List<Integer> nums, int k) {
    if (nums.isEmpty())
      return 0;
    long dist = 0;
    for(int i = nums.size() - 1; i >= 0; i -= k)
      dist += 2L * nums.get(i);
    return dist;
  }

  private static void negate(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      arr[i] *= -1;
    }
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