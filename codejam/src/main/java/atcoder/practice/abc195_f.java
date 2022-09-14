package atcoder.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://atcoder.jp/contests/abc195/tasks/abc195_f" target="_top">https://atcoder.jp/contests/abc195/tasks/abc195_f</a>
 * @since 27/11/21 3:07 PM
 */
public class abc195_f {
  static FastScanner sc = new FastScanner(System.in);
  static long ans = 0;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;
      //sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long a = sc.nextLong();
        long b = sc.nextLong();
        List<Long> odd = LongStream.range(a, b + 1).filter(num -> num % 2 == 1).boxed().collect(toList());
        List<Long> even = LongStream.range(a, b + 1).filter(num -> num % 2 == 0).boxed().collect(toList());
        ans = 1 + even.size();
        //build graph
        for (long e : even) {
          List<Long> filteredOdd = odd.stream().filter(num -> gcd(num, e) == 1).collect(toList());
          extracted(filteredOdd);
        }
        extracted(odd);
        System.out.println(ans);
      }
    }
  }

  private static void extracted(List<Long> filteredOdd) {
    int[][] graph = buildGraph(filteredOdd);
    int lenFirst = graph.length / 2;
    //left half dp
    boolean[] dpFirst = new boolean[1 << lenFirst];
    for (int mask = 1; mask < (1 << lenFirst); mask++) {
      List<Integer> cur = new ArrayList<>(); // stores index of all set bits
      for (int i = 0; i < lenFirst; i++) {
        if (set(mask, i)) {
          cur.add(i);
        }
      }
      dpFirst[mask] = isValid(cur, graph);
    }
    //right half dp
    int lenSecond = graph.length - lenFirst;
    boolean[] dpSecond = new boolean[1 << lenSecond];
    for (int mask = 1; mask < (1 << lenSecond); mask++) {
      List<Integer> cur = new ArrayList<>();
      for (int i = 0; i < lenSecond; i++) {
        if (set(mask, i)) {
          cur.add(lenFirst + i);
        }
      }
      dpSecond[mask] = isValid(cur, graph);
    }
    long[] countDpSecond = new long[dpSecond.length];
    //add from right only
    for (int m = 1; m < dpSecond.length; m++)
      if (dpSecond[m]) {
        ans++;
        countDpSecond[m] = 1;
      }

    //sos dp
    for (int i = 0; i < lenSecond; i++)
      for (int mask = 0; mask < (1 << lenSecond); mask++) {
        if (set(mask, i))
          countDpSecond[mask] += countDpSecond[mask - (1 << i)];
      }
    //add from left only
    for (int m = 1; m < dpFirst.length; m++)
      if (dpFirst[m]) {
        ans++;
        int targetMask = generateTargetMask(graph, lenSecond, lenFirst, m);
        ans += countDpSecond[targetMask];
      }
  }

  private static boolean isValid(List<Integer> cur, int[][] graph) {
    for (int i = 0; i < cur.size() - 1; i++) {
      for (int j = i + 1; j < cur.size(); j++) {
        if (graph[i][j] == 0)
          return false;
      }
    }
    return true;
  }

  // we initialize targetmask with all 1s, number of 1 is same as total people in this graph
  // for each of the person, we find 0s for them and we reset that bit in target  mask
  private static int generateTargetMask(int[][] graph, int targetMaskLen, int maskLen, int mask) { // we will consider [start, graph.len) numbers
    int targetmask = (1 << targetMaskLen) - 1; // 1 2 3 4 5 6 7
    for (int i = 0; i < maskLen; i++) {
      if (set(mask, i)) {
        //for this person, find out all 0s
        for (int j = 0; j < graph.length; j++) {
          if (graph[i][j] == 0 && set(targetmask, j)) {
            //reset jth bit
            targetmask -= 1 << j;
          }
        }
      }
    }
    return targetmask;
  }

  static boolean set(int mask, int i) {
    return ((mask >> i) & 1) == 1;
  }

  private static int[][] buildGraph(List<Long> odd) {
    int n = odd.size();
    int[][] graph = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (gcd(odd.get(i), odd.get(j)) == 1) {
          graph[i][j] = graph[j][i] = 1;
        }

      }
    }
    return graph;
  }

  private static long gcd(Long a, Long b) {
    return b == 0 ? a : gcd(b, a % b);
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
/*
5 8

5 6 7 8

{}
5
6
7
8
5 6
5 7
6 7
5 6 7
5 8
7 8
5 7 8
 */