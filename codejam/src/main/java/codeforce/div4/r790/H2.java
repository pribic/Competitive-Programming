package codeforce.div4.r790;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contests/1676/problem/F" target="_top">https://codeforces.com/contests/1676/problem/F</a>
 * @since 10/05/22 8:00 PM
 */
public class H2 {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        BIT bit = new BIT(n);
        //count inversion count
        // i < j but ai >= aj
        long count = 0;
        for (int i = 0; i < n; i++) {
          count += bit.get(n) - bit.get(arr[i] - 1);
          bit.update(arr[i], 1);
        }
        System.out.println(count);
      }
    }
  }

  //starts with 1
  static class BIT {
    int[] data;

    BIT(int n) {
      data = new int[n + 1];
    }

    void update(int idx, int val) { // update value at idx with val diff
      while (idx < data.length) {
        data[idx] += val;
        idx += idx & -idx;
      }
    }

    // 1 based indexing
    int get(int idx) { // sum from [1, idx]
      int sum = 0;
      while (idx > 0) {
        sum += data[idx];
        idx -= idx & -idx;
      }
      return sum;
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