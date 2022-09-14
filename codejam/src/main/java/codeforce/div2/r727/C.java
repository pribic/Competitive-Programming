package codeforce.div2.r727;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1539/problem/C" target="_top">https://codeforces.com/contest/1539/problem/C</a>
 * @since 20/06/21 5:00 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;// sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        long k = sc.nextLong();
        long x = sc.nextLong();
        List<Long> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
          arr.add(sc.nextLong());
        }
        Collections.sort(arr);
        List<Long> diffs = new ArrayList<>();
        for (int i = 1; i < n; i++) {
          if (arr.get(i) - arr.get(i - 1) > x) {
            diffs.add(arr.get(i) - arr.get(i - 1));
          }
        }
        Collections.sort(diffs);
        //System.out.println("diffs = " + diffs);
        int i = 0;
        for (; i < diffs.size(); i++) {
          long diff = diffs.get(i);
          long times = (diff - 1) / x;
          if (times <= k) {
            //we can merge this
            k -= times;
          } else {
            break;
          }
        }
        if (i == diffs.size())
          System.out.println(1);
        else
          System.out.println(diffs.size() + 1 - i);
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