package codechef.practic;

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
 * @see <a href="https://www.codechef.com/problems/SEAD" target="_top">https://www.codechef.com/problems/SEAD</a>
 * @since 21/05/21 2:06 PM
 */
public class Main {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    int T = 1;//sc.nextInt();
    for (int tt = 1; tt <= T; tt++) {
      int n = sc.nextInt();
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = sc.nextInt();
      }
      int q = sc.nextInt();
      int[] diff = new int[n];
      for (int i = 1; i < n; i++)
        diff[i] = arr[i] - arr[i - 1];
      SparseTable st = new SparseTable(diff);
      while (q-- > 0) {
        int t = sc.nextInt();
        int d = sc.nextInt();

        //bs last element such that a[i] <= t

        int l = 0;
        int r = n;
        while (r > l + 1) {
          int mid = l + (r - l) / 2;
          if (arr[mid] <= t) {
            l = mid;
          } else {
            r = mid;
          }
        }
        int rightBound = l;

        //bs left range

        l = -1; // false
        r = rightBound; // true
        while (r > l + 1) {
          int mid = l + (r - l) / 2;
          if (st.rangeMax(mid + 1, rightBound) <= d)
            r = mid;
          else
            l = mid;
        }
        out.println(r + 1);
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
      br = new BufferedReader(new InputStreamReader(f));
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

  private static class SparseTable {
    int[][] table;
    int[] log;

    public SparseTable(int[] arr) {
      table = new int[arr.length][17];
      log = new int[1000001];
      for (int i = 2; i < log.length; i++)
        log[i] = log[i / 2] + 1;
      for (int i = 0; i < table.length; i++) {
        table[i][0] = arr[i];
      }
      for (int j = 1; j < 17; j++) {
        for (int i = 0; i + (1 << j) <= arr.length; i++)
          table[i][j] = Math.max(table[i][j - 1], table[i + (1 << j - 1)][j - 1]);
      }
    }

    int rangeMax(int l, int r) {
      int len = r - l + 1;
      //out.println(l + " " + r);
      //out.println(log[len]);
      return Math.max(table[l][log[len]], table[r - (1 << log[len]) + 1][log[len]]);
    }
  }
}