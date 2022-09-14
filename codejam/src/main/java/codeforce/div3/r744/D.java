package codeforce.div3.r744;

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
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
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
 * @see <a href="https://codeforces.com/contest/1579/problem/D" target="_top">https://codeforces.com/contest/1579/problem/D</a>
 * @since 28/09/21 8:49 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        PriorityQueue<int[]> ts = new PriorityQueue<>((a, b) -> { // {freq, idx}
          if (a[0] != b[0])
            return b[0] - a[0];
          return b[1] - a[1];
        });
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          if (arr[i] > 0)
            ts.add(new int[]{arr[i], i + 1});
        }
        List<Integer> peopleTalk = new ArrayList<>();
        while (ts.size() > 1) {
          int[] first = ts.remove();
          int[] second = ts.remove();
          peopleTalk.add(first[1]);
          peopleTalk.add(second[1]);
          first[0]--;
          second[0]--;
          if (first[0] > 0)
            ts.add(first);
          if (second[0] > 0)
            ts.add(second);
        }
        System.out.println(peopleTalk.size() / 2);
        for (int i = 0; i < peopleTalk.size(); i += 2) {
          System.out.println(peopleTalk.get(i) + " " + peopleTalk.get(i + 1));
        }
      }
    }
  }

  private static void insert(TreeMap<Integer, Set<Integer>> map, int num, int idx) {
    map.putIfAbsent(num, new HashSet<>());
    map.get(num).add(idx);
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