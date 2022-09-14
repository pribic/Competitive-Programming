package codeforce.div2.r789;

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
 * @see <a href="https://codeforces.com/contest/1678/problem/B1" target="_top">https://codeforces.com/contest/1678/problem/B1</a>
 * @since 09/05/22 3:47 PM
 */
public class B1 {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String str = sc.next();
        List<Integer> lens = new ArrayList<>();
        int cnt = 1;
        int prev = str.charAt(0);
        for (int i = 1; i < n; i++) {
          if (str.charAt(i) == prev) {
            cnt++;
          } else {
            lens.add(cnt);
            cnt = 1;
          }
          prev = str.charAt(i);
        }
        lens.add(cnt);
        int steps = 0;
        for (int i = 0; i < lens.size() - 1; i++) {
          if (lens.get(i) % 2 + lens.get(i + 1) % 2 == 2) {
            lens.set(i, lens.get(i) - 1);
            lens.set(i + 1, lens.get(i + 1) + 1);
            steps++;
          }
        }
        int minOdd = n;
        int maxOdd = -1;
        boolean any = false;
        for (int i = 0; i < lens.size(); i++) {
          if (lens.get(i) % 2 == 1) {
            minOdd = Math.min(minOdd, i);
            maxOdd = Math.max(maxOdd, i);
            any = true;
          }
        }
        System.out.println(steps + (any ?  maxOdd - minOdd : 0));
      }
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