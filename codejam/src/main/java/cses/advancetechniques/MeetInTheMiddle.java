package cses.advancetechniques;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://cses.fi/problemset/task/1628" target="_top">https://cses.fi/problemset/task/1628</a>
 * @since 04/10/21 8:30 PM
 */
public class MeetInTheMiddle {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        long x = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        long ways = 0;
        int half = n / 2;
        List<Long> first = sumToFreq(arr, half, 0);
        List<Long> second = sumToFreq(arr, n - half, half);
        Collections.sort(first);
        Collections.sort(second);
        int idx1 = 0;
        int idx2 = second.size() - 1;
        while (idx1 < first.size() && idx2 >= 0) {
          long curSum = first.get(idx1) + second.get(idx2);
          if (curSum == x) {
            ways++;
          }
          if (curSum < x) {
            idx1++;
          } else {
            idx2--;
          }
        }
        while (idx1 < first.size()) {
          if (first.get(idx1) == x)
            ways++;
          else if (first.get(idx1) > x)
            break;
          idx1++;
        }
        while (idx2 >= 0) {
          if (second.get(idx2) == x)
            ways++;
          else if (second.get(idx2) < x)
            break;
          idx2--;
        }
        System.out.println(ways);
      }
    }
  }

  private static List<Long> sumToFreq(int[] arr, int half, int offset) {
    List<Long> mapping = new ArrayList<>();
    for (int i = 1; i < (1 << half); i++) {
      long curSum = 0L;
      for (int j = 0; j < half; j++) {
        if (((i >> j) & 1) == 1) {
          curSum += arr[offset + j];
        }
      }
      mapping.add(curSum);
    }
    return mapping;
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