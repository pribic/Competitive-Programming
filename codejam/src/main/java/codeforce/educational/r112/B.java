package codeforce.educational.r112;

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
 * @see <a href="https://codeforces.com/contest/1555/problem/B" target="_top">https://codeforces.com/contest/1555/problem/B</a>
 * @since 30/07/21 8:46 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int w1 = sc.nextInt();
        int h1 = sc.nextInt();
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();
        int w = sc.nextInt();
        int h = sc.nextInt();

        int move = Integer.MAX_VALUE;
        if (w + x2 - x1 > w1 && h + y2 - y1 > h1) {
          System.out.println(-1);
          continue;
        } else if (isInside(x1, y1, x2, y2, w, h)) {
          move = Math.min(move, w - x1);
          move = Math.min(move, h - y1);
        } else if (isInside(x1, y1, x2, y2, w1 - w, h)) {
          move = Math.min(move, x2 - (w1 - w));
          move = Math.min(move, h - y1);
        } else if (isInside(x1, y1, x2, y2, w1 - w, h1 - h)) {
          move = Math.min(move, x2 - (w1 - w));
          move = Math.min(move, y2 - (h1 - h));
        } else if (isInside(x1, y1, x2, y2, w, h1 - h)) {
          move = Math.min(move, w - x1);
          move = Math.min(move, y2 - (h1 - h));
        } else {
          System.out.println(0);
          continue;
        }
        System.out.println(move);
      }
    }
  }


  static boolean isInside(int x1, int y1, int x2, int y2, int x, int y) {
    return x >= x1 && x <= x2 && y >= y1 && y <= y2;
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