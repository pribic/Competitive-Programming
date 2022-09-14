package codeforce.div3.r731;

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
 * @see <a href="https://codeforces.com/contest/1547/problem/E" target="_top">https://codeforces.com/contest/1547/problem/E</a>
 * @since 10/07/21 9:50 PM
 */
public class E {
  static FastScanner sc = new FastScanner(System.in);

  static class Pair {
    int i, val;

    public Pair(int i, int val) {
      this.i = i;
      this.val = val;
    }
  }

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] position = new int[k];
        int[] ans = new int[n];
        for (int i = 0; i < k; i++) {
          position[i] = sc.nextInt() - 1;
        }
        int[] temp = new int[k];
        for (int i = 0; i < k; i++) {
          temp[i] = sc.nextInt();
        }
        Pair[] pair = new Pair[k];
        for (int i = 0; i < k; i++) {
          pair[i] = new Pair(position[i], temp[i]);
        }
        Arrays.sort(pair, (p1, p2) -> p1.i - p2.i);
        for (int i = 0; i < k; i++) {
          position[i] = pair[i].i;
          temp[i] = pair[i].val;
        }


        Arrays.fill(ans, Integer.MAX_VALUE);
        for (int i = 0; i < k; i++) {
          ans[position[i]] = temp[i];
        }
       // System.out.println(Arrays.toString(ans));
        int cur = temp[0] + 1;
        int idx = 1;
        for (int i = position[0] + 1; i < n; i++) {
          ans[i] = Math.min(ans[i], cur);
          cur = Math.min(ans[i], cur);
          cur++;
          if (idx < position.length && i == position[idx]) {
            cur = temp[idx] + 1;
            idx++;
          }
        }

        //

      //  System.out.println(Arrays.toString(ans));
        cur = temp[temp.length - 1] + 1;
        idx = temp.length - 2;
        for (int i = position[position.length - 1] - 1; i >= 0; i--) {
          ans[i] = Math.min(ans[i], cur);
          cur = Math.min(ans[i], cur);
          cur++;
          if (idx >= 0 && i == position[idx]) {
            cur = temp[idx] + 1;
            idx--;
          }
        }
        for(int num : ans)
          System.out.print(num + " ");
        System.out.println();
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