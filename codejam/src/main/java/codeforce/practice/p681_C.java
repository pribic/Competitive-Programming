package codeforce.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.PriorityQueue;
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
 * @see <a href="https://codeforces.com/problemset/problem/681/C" target="_top">https://codeforces.com/problemset/problem/681/C</a>
 * @since 14/01/22 11:17 PM
 */
public class p681_C {
  private static final String INSERT = "insert";
  private static final String GET_MIN = "getMin";
  private static final String REMOVE_MIN = "removeMin";
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
          String operation = sc.next();
          switch (operation) {
            case INSERT:
              int val = sc.nextInt();
              pq.add(val);
              ans.add(INSERT + " " + val);
              break;
            case GET_MIN:
              val = sc.nextInt();
              while (!pq.isEmpty() && pq.peek() < val) {
                ans.add(REMOVE_MIN);
                pq.remove();
              }
              if (pq.isEmpty() || pq.peek() > val) {
                pq.add(val);
                ans.add(INSERT + " " + val);
              }
              ans.add(GET_MIN + " " + val);
              break;
            case REMOVE_MIN:
              if (pq.isEmpty()) {
                ans.add(INSERT + " 0");
                pq.add(0);
              }
              pq.remove();
              ans.add(REMOVE_MIN);
              break;
          }
        }
        System.out.println(ans.size());
        StringBuilder sb = new StringBuilder();
        for (String aa : ans)
          sb.append(aa).append("\n");
        System.out.println(sb);
      }
    }
  }

  private static void insertandget(PriorityQueue<Integer> pq, List<String> ans, int val) {
    ans.add(INSERT + " " + val);
    pq.add(val);
    ans.add(GET_MIN + " " + val);
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