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
 * @see <a href="https://codeforces.com/contest/471/problem/D" target="_top">https://codeforces.com/contest/471/problem/D</a>
 * @since 09/12/21 1:43 PM
 */
public class p471_D_MUH_and_Cube_Walls {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] bears = new int[n];
        for (int i = 0; i < n; i++) {
          bears[i] = sc.nextInt();
        }
        int[] elephants = new int[m];
        for (int i = 0; i < m; i++) {
          elephants[i] = sc.nextInt();
        }
        int[] db = new int[n - 1];
        int[] de = new int[m - 1];
        for (int i = 0; i < n - 1; i++)
          db[i] = bears[i] - bears[i + 1];
        for (int i = 0; i < m - 1; i++)
          de[i] = elephants[i] - elephants[i + 1];
        int[] combo = new int[n - 1 + m];
        for (int i = 0; i < de.length; i++) {
          combo[i] = de[i];
        }
        combo[de.length] = Integer.MAX_VALUE / 2;
        for (int i = 0; i < db.length; i++) {
          combo[de.length + 1 + i] = db[i];
        }
        int[] zf = z_function(combo);
        int cnt = 0;
        for (int num : zf)
          if (num == de.length)
            cnt++;
        System.out.println(cnt);
      }
    }
  }

  static int[] z_function(int[] arr) {
    int[] z = new int[arr.length];
    for (int i = 1, l = 0, r = 0; i < arr.length; i++) {
      if (r > i)
        z[i] = Math.min(z[i - l], r - i + 1);
      while (i + z[i] < arr.length && arr[i + z[i]] == arr[z[i]])
        z[i]++;
      if (i + z[i] - 1 > r) {
        l = i;
        r = i + z[i] - 1;
      }
    }
    return z;
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