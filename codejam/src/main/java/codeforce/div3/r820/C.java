package codeforce.div3.r820;

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
 * @see <a href="https://codeforces.com/contest/1729/problem/C" target="_top">https://codeforces.com/contest/1729/problem/C</a>
 * @since 12/09/22 10:12 pm
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String str = sc.next();
        ArrayList<Integer>[] fr = new ArrayList[26];
        for (int i = 0; i < fr.length; i++) {
          fr[i] = new ArrayList<>();
        }
        for (int i = 0; i < str.length(); i++) {
          fr[str.charAt(i) - 'a'].add(i + 1);
        }
        int cost = Math.abs(str.charAt(0) - str.charAt(str.length() - 1));
        int stepCnt = 0;
        List<Integer> steps = new ArrayList<>();
        int i = str.charAt(0) - 'a';
        int dir = str.charAt(0) <= str.charAt(str.length() - 1) ? 1 : -1;
        int n = str.charAt(str.length() - 1) - 'a';
        while (i != n + dir) {
          stepCnt += fr[i].size();
          steps.addAll(fr[i]);
          i += dir;
        }
        System.out.println(cost + " " + stepCnt);
        StringBuilder sb = new StringBuilder();
        for (int idx : steps)
          sb.append(idx).append(" ");
        System.out.println(sb);

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