package kickstart.Y2021.round1G.StayingHydrated;

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
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/00000000004362d6/00000000008b3a1c" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/00000000004362d6/00000000008b3a1c</a>
 * @since 16/10/21 5:43 PM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int k = sc.nextInt();
        List<Integer> xC = new ArrayList<>();
        List<Integer> yC = new ArrayList<>();
        Map<Integer, Integer> xStart = new HashMap<>();
        Map<Integer, Integer> yStart = new HashMap<>();
        Map<Integer, Integer> xEnd = new HashMap<>();
        Map<Integer, Integer> yEnd = new HashMap<>();
        for (int i = 0; i < k; i++) {
          int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt(), d = sc.nextInt();
          xC.add(a);
          xC.add(c);
          yC.add(b);
          yC.add(d);
          xStart.put(a, xStart.getOrDefault(a, 0) + 1);
          yStart.put(b, yStart.getOrDefault(b, 0) + 1);
          xEnd.put(c, xEnd.getOrDefault(c, 0) + 1);
          yEnd.put(d, yEnd.getOrDefault(d, 0) + 1);
        }
        Collections.sort(xC);
        Collections.sort(yC);
        System.out.println(getAns(xC, xStart, xEnd, k) + " " + getAns(yC, yStart, yEnd, k));

      }
    }
  }

  private static long getAns(List<Integer> points, Map<Integer, Integer> start, Map<Integer, Integer> end, int k) {
    if (points.size() % 2 == 1)
      return points.get(points.size() / 2);
    return points.get((points.size() - 1) / 2);
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