package codeforce.div2.r751;

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

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1602/problem/B" target="_top">https://codeforces.com/contest/1602/problem/B</a>
 * @since 25/10/21 12:08 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] prevF = new int[2001];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          prevF[arr[i]]++;
        }
        int q = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        List<int[]> states = new ArrayList<>();
        states.add(arr);
        int[] prev = arr;
        //simulate
        while (true) {
          int[] nxt = new int[n];
          int[] nxtF = new int[2001];
          for (int i = 0; i < n; i++) {
            nxt[i] = prevF[prev[i]];
            nxtF[nxt[i]]++;
          }
          if (Arrays.equals(nxt, prev)) {
            break;
          }
          states.add(nxt);
          prevF = nxtF;
          prev = nxt;
        }
        while (q-- > 0) {
          int x = sc.nextInt() - 1;
          int k = sc.nextInt();
          k = Math.min(k, states.size() - 1);
          sb.append(states.get(k)[x]).append("\n");
        }
        System.out.print(sb);
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