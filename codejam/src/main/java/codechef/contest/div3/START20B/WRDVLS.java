package codechef.contest.div3.START20B;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.codechef.com/START20B/problems/WRDVLS" target="_top">https://www.codechef.com/START20B/problems/WRDVLS</a>
 * @since 15/12/21 8:22 PM
 */
public class WRDVLS {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        Map<Integer, List<Integer>> indices = new HashMap<>();
        for (int i = 0; i < n; i++) {
          indices.putIfAbsent(arr[i], new ArrayList<>());
          indices.get(arr[i]).add(i);
        }
        long weirdness = 0;
        for (int num : indices.keySet())
          weirdness += calcW(num, n, indices);
        System.out.println(weirdness);
      }
    }
  }

  private static long calcW(int num, int n, Map<Integer, List<Integer>> indices) {
    long w = 0;
    List<Integer> numIndices = indices.get(num);
    int prev = -1;
    for (int st = 0; st + num - 1 < numIndices.size(); st++) {
      int lst = st + num - 1;
      int left = numIndices.get(st) - prev; // [L, R] intervals
      int right = lst == numIndices.size() - 1 ? n - numIndices.get(lst) : numIndices.get(lst + 1) - numIndices.get(lst);
      w += (long) num * left * right;
      prev = numIndices.get(st);
    }
    return w;
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