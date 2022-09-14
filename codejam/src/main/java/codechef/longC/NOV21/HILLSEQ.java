package codechef.longC.NOV21;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;
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
 * @see <a href="https://www.codechef.com/NOV21B/problems/HILLSEQ" target="_top">https://www.codechef.com/NOV21B/problems/HILLSEQ</a>
 * @since 06/11/21 11:05 AM
 */
public class HILLSEQ {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        Map<Integer, Integer> freq = new HashMap<>();
        int max = -1;
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          max = Math.max(max, arr[i]);
          freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
        }
        boolean anyMoreThanTwo = false;
        for (int val : freq.values())
          if (val > 2)
            anyMoreThanTwo = true;

        if (anyMoreThanTwo || freq.get(max) > 1) {
          System.out.println(-1);
        } else {
          Set<Integer> left = new TreeSet<>();
          Set<Integer> right = new TreeSet<>(Collections.reverseOrder());
          for (int num : freq.keySet()) {
            if (freq.get(num) == 1)
              right.add(num);
            else {
              left.add(num);
              right.add(num);
            }
          }
          StringBuilder ans = new StringBuilder();
          append(left, ans);
          append(right, ans);
          System.out.println(ans);
        }

      }
    }
  }

  private static void append(Set<Integer> left, StringBuilder ans) {
    for (int num : left)
      ans.append(num).append(" ");
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