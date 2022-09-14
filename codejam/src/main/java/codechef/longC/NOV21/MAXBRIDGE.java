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
 * @see <a href="https://www.codechef.com/NOV21B/problems/MAXBRIDGE" target="_top">https://www.codechef.com/NOV21B/problems/MAXBRIDGE</a>
 * @since 06/11/21 12:07 PM
 */
public class MAXBRIDGE {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<Integer>[] edges = new ArrayList[n + 1];
        for (int i = 0; i < edges.length; i++)
          edges[i] = new ArrayList<>();
        for (int i = 1; i < n; i++)
          edges[i].add(i + 1);
        m -= n - 1;
        for (int j = 3; m > 0 && j <= n; j++) {
          //we are adding an edge ending at j from [1, j - 1)
          for (int i = 1; m > 0 && i < j - 1; i++) {
            edges[i].add(j);
            m--;
          }
        }
        printAns(edges);
      }
    }
  }

  private static void printAns(ArrayList<Integer>[] edges) {
    StringBuffer ans = new StringBuffer();
    for (int i = 1; i < edges.length; i++) {
      for (int j : edges[i])
        ans.append(i).append(" ").append(j).append("\n");
    }
    out.print(ans);
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