package kickstart.Y2022.round1F.SorttheFabrics;

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
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/00000000008cb409/0000000000beefbb" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/00000000008cb409/0000000000beefbb</a>
 * @since 18/09/22 10:49 pm
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        String[] color = new String[n];
        Integer[] durability = new Integer[n];
        Integer[] idx = new Integer[n];
        Integer[] ada = new Integer[n];
        Integer[] charles = new Integer[n];
        for (int i = 0; i < n; i++) {
          color[i] = sc.next();
          durability[i] = sc.nextInt();
          idx[i] = sc.nextInt();
          ada[i] = charles[i] = i;
        }
        Arrays.sort(ada, (i, j) -> color[i].compareTo(color[j]) == 0 ? idx[i] - idx[j] : color[i].compareTo(color[j]));
        Arrays.sort(charles, (i, j) -> durability[i].equals(durability[j]) ? idx[i] - idx[j] : durability[i] - durability[j]);
        int same = 0;
        for (int i = 0; i < n; i++)
          if (ada[i] == charles[i])
            same++;
        System.out.println(same);
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