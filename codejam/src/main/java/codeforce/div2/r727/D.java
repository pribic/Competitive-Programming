package codeforce.div2.r727;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
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
import java.util.stream.LongStream;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/problemset/problem/1539/D" target="_top">https://codeforces.com/problemset/problem/1539/D</a>
 * @since 15/12/21 11:17 AM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        long[] ai = new long[n];
        long[] bi = new long[n];
        for (int i = 0; i < n; i++) {
          ai[i] = sc.nextLong();
          bi[i] = sc.nextLong();
        }
        long total = LongStream.of(ai).sum();
        //those product where we need >= total, we will never find discount so buy them upfront so that they will
        // increase count of items bought so far and might help for other items
        // so idea is , there is no harm in buying those items earlier we dont know if it will definately be helpful 
        // but all we know is greedily consuming them first increases our chances.

        long consumed = 0;
        long cost = 0;
        List<long[]> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
          if (bi[i] >= total) {
            consumed += ai[i];
            cost += 2 * ai[i];
          } else {
            items.add(new long[]{ai[i], bi[i]});
          }
        }
        items.sort((a, b) -> {
          if (a[1] == b[1])
            return 0;
          else if (a[1] < b[1])
            return 1;
          else
            return -1;
        });
        for (long[] item : items) {
          if (consumed >= item[1]) {
            cost += item[0]; // we get this for 1 rubble
          } else {
            if (consumed + item[0] <= item[1])
              cost += 2 * item[0];
            else
              cost += 2 * (item[1] - consumed) + item[0] - item[1] + consumed;
          }
          consumed += item[0];
        }
        System.out.println(cost);
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