package codeforce.div4.r790;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contests/1676/problem/E" target="_top">https://codeforces.com/contests/1676/problem/E</a>
 * @since 10/05/22 8:00 PM
 */
public class E {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        sort(arr);
        int[] ps = new int[n];
        int cs = 0;
        for (int i = 0; i < n; i++) {
          cs += arr[i];
          ps[i] = cs;
        }
        //System.out.println(Arrays.toString(ps));
        while (q-- > 0) {
          int ask = sc.nextInt();
          if (ps[0] >= ask) {
            System.out.println(1);
          } else if (ps[n - 1] < ask) {
            System.out.println(-1);
          } else {
            int l = 0;
            int r = n - 1;
            while (r > l + 1) {
              int mid = l + (r - l) / 2;
              if (ps[mid] < ask)
                l = mid;
              else
                r = mid;
            }
            System.out.println(r + 1);
          }
        }
      }
    }
  }

  private static void sort(int[] arr) {
    List<Integer> ls = new ArrayList<>();
    for (int num : arr)
      ls.add(num);
    Collections.sort(ls, Collections.reverseOrder());
    for (int i = 0; i < arr.length; i++)
      arr[i] = ls.get(i);
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