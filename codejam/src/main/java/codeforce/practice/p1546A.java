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
 * @see <a href="https://codeforces.com/contest/1546/problem/A" target="_top">https://codeforces.com/contest/1546/problem/A</a>
 * @since 12/07/21 2:02 PM
 */
public class p1546A {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
          a[i] = sc.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
          b[i] = sc.nextInt();
        }
        List<Integer> negative = new ArrayList<>();
        List<Integer> positive = new ArrayList<>();
        for (int i = 0; i < n; i++) {
          if (a[i] - b[i] < 0) negative.add(i);
          else if (a[i] - b[i] > 0) positive.add(i);
        }
        List<Integer> ans = new ArrayList<>();
        while (!negative.isEmpty() && !positive.isEmpty()) {
          int idx1 = positive.get(0);
          int idx2 = negative.get(0);
          ans.add(idx1 + 1);
          ans.add(idx2 + 1);
          a[idx2]++;
          a[idx1]--;
          if (a[idx1] == b[idx1]) positive.remove(0);
          if (a[idx2] == b[idx2]) negative.remove(0);
        }
        if(negative.isEmpty() && positive.isEmpty()) {
          System.out.println(ans.size()/2);
          for (int idx = 0; idx < ans.size(); idx += 2)
            System.out.println(ans.get(idx) + " " + ans.get(idx + 1)+ " ");
        } else
          System.out.println(-1);
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