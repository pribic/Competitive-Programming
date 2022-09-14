package codechef.snackdown.y2021.round1B;

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
 * @see <a href="https://www.codechef.com/SNCK1B21/problems/LARGESTLADDU" target="_top">https://www.codechef.com/SNCK1B21/problems/LARGESTLADDU</a>
 * @since 30/10/21 3:54 PM
 */
public class LARGESTLADDU {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        long[] arr = new long[1 << n];
        long sum = 0;
        for (int i = 0; i < 1 << n; i++) {
          arr[i] = sc.nextInt();
          sum += arr[i];
        }
        if (n == 0) {
          System.out.println("YES");
          continue;
        }
        Arrays.sort(arr);
        long[] cur = {sum};
        long[] next = new long[0];
        for (int i = 0; i < n; i++) {
          next = new long[cur.length * 2];
          for (int j = 0; j < cur.length; j++) {
            if (cur[j] % 2 == 0) {
              next[2 * j] = next[2 * j + 1] = cur[j] / 2;
            } else {
              next[2 * j] = cur[j] / 2;
              next[2 * j + 1] = (cur[j] + 1) / 2;
            }
          }
          cur = next;
        }
        Arrays.sort(next);
        System.out.println(Arrays.equals(arr, next) ? "YES" : "NO");
      }
    }
  }

  /*
  
  4
  1
  1 1
  2
  2 1 1 1 // 5 -> 2 | 3 -> 1 1 1 2
  2
  2 3 3 4 // 12 -> 6 6 -> 3 3 3 3
  0
  3
  
  
   */
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