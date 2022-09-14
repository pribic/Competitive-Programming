package codeforce.educational.r114;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
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
 * @see <a href="https://codeforces.com/contest/1574/problem/D" target="_top">https://codeforces.com/contest/1574/problem/D</a>
 * @since 21/09/21 12:18 AM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    Comparator<int[]> comparator = (a, b) -> {
      if (a[0] != b[0])
        return a[0] - b[0];
      for (int i = 1; i < a.length; i++) {
        if (a[i] != b[i])
          return b[i] - a[i];
      }
      return 0;
    };

    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[][] slots = new int[n][];
        for (int i = 0; i < n; i++) {
          int c = sc.nextInt();
          slots[i] = sc.nextIntArr(c);
        }

        int m = sc.nextInt();
        TreeSet<int[]> banned = new TreeSet<>(comparator);
        for (int i = 0; i < m; i++) {
          int[] ban = sc.nextIntArr(n);
          banned.add(ban);
        }

        int[] candidate = new int[n + 1];
        for (int i = 0; i < n; i++) {
          candidate[i + 1] = slots[i].length - 1;
          candidate[0] += slots[i][slots[i].length - 1];
        }
        TreeSet<int[]> queue = new TreeSet<>((a, b) -> {
          if (a[0] != b[0])
            return b[0] - a[0];
          for (int i = 1; i < a.length; i++) {
            if (a[i] != b[i])
              return b[i] - a[i];
          }
          return 0;
        });
        queue.add(candidate);
        while (!queue.isEmpty()) {
          int[] top = queue.first();
          queue.remove(top);
          int[] topForBanned = new int[top.length - 1];
          for (int i = 1; i < top.length; i++)
            topForBanned[i - 1] = top[i] + 1;
          if (!banned.contains(topForBanned)) {
            //found our ans, print it
            StringBuilder ans = new StringBuilder();
            for (int i = 0; i < n; i++)
              ans.append(top[i + 1] + 1).append(" ");
            ans.append("\n");
            System.out.println(ans);
            break;
          } else {
            // generate all next possible configs
            for (int i = 1; i <= n; i++) {
              if (top[i] == 0) continue; // we have already taken everything from this slot.
              int[] another = top.clone();
              another[0] -= slots[i - 1][another[i]];
              another[i]--;
              another[0] += slots[i - 1][another[i]];
              queue.add(another);
            }
          }
        }

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

    int[] nextIntArr(int n) {
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = nextInt();
      }
      return arr;
    }

    long[] nextLongArr(int n) {
      long[] arr = new long[n];
      for (int i = 0; i < n; i++) {
        arr[i] = nextLong();
      }
      return arr;
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