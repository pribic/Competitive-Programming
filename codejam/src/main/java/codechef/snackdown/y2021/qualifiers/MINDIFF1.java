package codechef.snackdown.y2021.qualifiers;

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
import java.util.Comparator;
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
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.codechef.com/SNCKQL21/problems/MINDIFF1" target="_top">https://www.codechef.com/SNCKQL21/problems/MINDIFF1</a>
 * @since 16/10/21 8:54 AM
 */
public class MINDIFF1 {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] deg = new int[n][2];
        ArrayList<Integer>[] al = new ArrayList[n];
        for (int i = 0; i < n; i++) {
          al[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
          int u = sc.nextInt() - 1;
          int v = sc.nextInt() - 1;
          al[u].add(v);
          al[v].add(u);
          deg[u][0]++;
          deg[v][0]++;
          deg[u][1] = u;
          deg[v][1] = v;
        }
        Arrays.sort(deg, comparingInt(arr -> -arr[0]));
        /*for(int[] d : deg)
          System.out.println(Arrays.toString(d));
        System.out.println(); */
        int[] finalC = new int[n];
        for (int i = 0; i < n; i++) {
          finalC[deg[i][1]] = i;
        }
        // node i has new number finalC[i]
        int maxD = 0;
        for (int i = 0; i < n; i++) {
          int curD = 0;
          for (int v : al[finalC[i]]) {
            if (finalC[i] > finalC[v])
              curD++;
          }
          maxD = Math.max(maxD, curD);
        }
        System.out.println(maxD);
        for (int i = 0; i < n; i++) {
          System.out.print(++finalC[i] + " ");
        }

        System.out.println();
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