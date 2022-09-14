package codeforce.educational.r114;

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
import java.util.TreeMap;
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
 * @see <a href="https://codeforces.com/contest/1574/problem/C" target="_top">https://codeforces.com/contest/1574/problem/C</a>
 * @since 20/09/21 9:09 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        long total = 0;
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
          long hero = sc.nextLong();
          set.add(hero);
          total += hero;
        }
        set.add(Long.MIN_VALUE / 2);
        set.add(Long.MAX_VALUE / 2);
        int m = sc.nextInt();
        StringBuilder ans = new StringBuilder();
        while (m-- > 0) {
          long defense = sc.nextLong();
          long attack = sc.nextLong();
          long ceilHero = set.ceiling(defense);
          long floorHero = set.floor(defense);
          long diff1 = Math.max(0, attack - total + ceilHero);
          long diff2 = defense - floorHero + Math.max(0, attack - total + floorHero);
          ans.append(Math.min(diff1, diff2)).append("\n");
        }
        System.out.println(ans);
      }
    }
  }

  private static void add(Map<Long, Long> multiset, long hero) {
    if (multiset.containsKey(hero))
      multiset.put(hero, multiset.get(hero) + 1);
    else
      multiset.put(hero, 1L);
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