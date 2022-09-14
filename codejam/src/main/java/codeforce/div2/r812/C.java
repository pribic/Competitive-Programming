package codeforce.div2.r812;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.NavigableSet;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1713/problem/C" target="_top">https://codeforces.com/contest/1713/problem/C</a>
 * @since 06/08/22 9:16 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    TreeSet<Integer> squares = new TreeSet<>();
    int sq = 0;
    while (sq * sq <= 2 * 100000) {
      squares.add(sq * sq);
      sq++;
    }
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt() - 1;
        /*
     0   0
     3   1
     2   2
     1   3
     5   4
     4   5
     10  6
     9   7
     8   8
     7   9
     6   10
     14  11
     13  12
     12  13
     11  14
         (n - 1)^(n - 1) + (n - 1)
         */
        List<Integer> ans = new ArrayList<>(n);
        while (n >= 0) {
          n = solve(n, ans, squares);
        }
        Collections.reverse(ans);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.size(); i++)
          sb.append(ans.get(i)).append(" ");
        System.out.println(sb);
      }
    }
  }

  private static int solve(int n, List<Integer> ans, TreeSet<Integer> squares) {
    if (n == 0) {
      ans.add(0);
      return -1;
    }
    //solve for n, with max value we can use is n
    //find upper bound of n
    int lastSq = -1;
    NavigableSet<Integer> ns = squares.tailSet(n, true);
    for (int upper : ns) {
      if (upper - n <= n)
        lastSq = upper;
      else break;
    }
    int remaining = lastSq - n;
    for (int i = remaining; i <= n; i++)
      ans.add(i);
    return remaining - 1;
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