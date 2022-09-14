package codeforce.div2.deltix;

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
 * @see <a href="https://codeforces.com/contest/1523/problem/D" target="_top">https://codeforces.com/contest/1523/problem/D</a>
 * @since 30/05/21 9:40 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int p = sc.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextLong();
        }

        int sz1 = m / 2;
        int sz2 = m - sz1;
        List<Integer> one = new ArrayList<>();
        for (int i = 0; i < (1 << sz1); i++) {
          one.add(i);
        }
        System.out.println("one = " + one);
        Map<Integer, List<Long>> two = new HashMap<>();
        for (long i = 0; i < (1L << sz2); i++) {
          //int bitcnt = Long.bitCount(i - (1L << sz1));
          int bitcnt = Long.bitCount(i);
          two.putIfAbsent(bitcnt, new ArrayList<>());
          two.get(bitcnt).add(i);
        }
        for (int k : two.keySet()) {
          System.out.println(k + " " + two.get(k));
        }
        long max = -1;
        long ans = -1;
        for (int on : one) { //1000
          List<Long> candidates = two.get(p - Integer.bitCount(on));
          if (candidates != null && !candidates.isEmpty()) {
            for (long candidate : candidates) {
              //configuration is on + candidate
              //take sz1 last bits from
              for (int i = sz2 - 1; i >= 0; i--) {
                on <<= 1;
                if ((candidate >> i & 1) == 1) {
                  on += 1;
                }
              }
              int cnt = 0;
              for (long num : arr) {
                long anding = on & num;
                if (anding >= on)
                  cnt++;
              }
              if (cnt > max) {
                max = cnt;
                ans = on;
              }
            }
          }
        }
        System.out.println(Long.toBinaryString(ans));
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