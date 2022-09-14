package codeforce.div3.r780;

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
 * @see <a href="https://codeforces.com/contest/1660/problem/C" target="_top">https://codeforces.com/contest/1660/problem/C</a>
 * @since 31/03/22 8:31 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String str = sc.next();
        int len = str.length();
        // dp[i] = min number of chars deleted to make string till i even
        // dp[i] = dp[j] + distance between prevIndex[i] and i - because these many chars we need to delete
        //    OR = dp[i - 1]
        int[] dp = new int[len];
        for (int i = 0; i < len; i++)
          dp[i] = i + 1; // in worst case, we delete everything till i, which is max deletion

        int[] prevIndex = new int[len];
        prevIndex[0] = -1; // -1 means no prevIndex
        int[] prevC = new int[26]; // for each one we store when they appeared previously
        Arrays.fill(prevC, -1);
        prevC[str.charAt(0) - 'a'] = 0;
        for (int i = 1; i < len; i++) {
          prevIndex[i] = prevC[str.charAt(i) - 'a'];
          prevC[str.charAt(i) - 'a'] = i;
        }
        //System.out.println("prevIndex = " + Arrays.toString(prevIndex));
        for (int i = 1; i < len; i++) {
          // find its prevIndex index.
          int p = prevIndex[i];
          if (p == -1) { // this is first occurence, we can't pair it with any prevIndex, so delete it
            dp[i] = dp[i - 1] + 1; // make i - 1 even and delete this one
          } else {
            if (p == 0)
              dp[i] = Math.min(dp[i - 1] + 1, i - p - 1);
            else
              dp[i] = Math.min(dp[i - 1] + 1, (i - p - 1) + dp[p - 1]);
          }
        }
        System.out.println(dp[len - 1]);
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