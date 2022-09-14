package codeforce.globalround.y2021.r16;

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
 * @see <a href="https://codeforces.com/contest/1566/problem/D1" target="_top">https://codeforces.com/contest/1566/problem/D1</a>
 * @since 12/09/21 9:18 PM
 */
public class D1 {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Pair[] sight = new Pair[m];
        for (int i = 0; i < m; i++) {
          int s = sc.nextInt();
          sight[i] = new Pair(i, s);
        }
        Arrays.sort(sight, (p1, p2) -> {
          if (p1.s == p2.s)
            return p2.idx - p1.idx;
          return p1.s - p2.s;
        });
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
          ans[sight[i].idx] = i;
          // we know that sight[i].idx person will seat on i + 1 position
        }
        //for (int seat : ans)
        //System.out.print(seat + " ");

        int[] occupancies = new int[m];
        int inconvenience = 0;
        for (int i = 0; i < m; i++) {
          //lets see where ith person will seat
          int targetSeat = ans[i];
          //out of current occupancies, how many seats have value < targetSeat
          for (int j = 0; j < targetSeat; j++)
            inconvenience += occupancies[j];
          occupancies[targetSeat] = 1;
        }
        System.out.println(inconvenience);
      }
    }
  }

  static class Pair {
    int idx, s;

    public Pair(int idx, int s) {
      this.idx = idx;
      this.s = s;
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