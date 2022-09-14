package codeforce.div2.r785;

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

import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1673/problem/B" target="_top">https://codeforces.com/contest/1673/problem/B</a>
 * @since 30/04/22 8:34 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String str = sc.next();
        boolean valid = true;
        ArrayList<Integer>[] indexes = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
          indexes[i] = new ArrayList<>();
        }
        Set<Character> alphabet = new HashSet<>();
        int[][] frequency = new int[26][str.length()];
        for (int i = 0; i < str.length(); i++) {
          int key = str.charAt(i) - 'a';
          indexes[key].add(i);
          alphabet.add(str.charAt(i));
        }
        for (char c = 'a'; c <= 'z'; c++) {
          int cs = 0;
          for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
              cs++;
            }
            frequency[c - 'a'][i] = cs;
          }
        }

        for (char c = 'a'; c <= 'z'; c++) {
          for (int i = 1; i < indexes[c - 'a'].size(); i++) {
            //see if any character is missing here
            int idx1 = indexes[c - 'a'].get(i - 1);
            int idx2 = indexes[c - 'a'].get(i);
            //check frequency for each between this range
            for (char cc : alphabet) {
              int fr = frequency[cc - 'a'][idx2] - frequency[cc - 'a'][idx1];
              if(fr == 0)
                valid = false;
            }
          }
        }
        System.out.println(valid ? "YES" : "NO");
      }
    }
  }

  // abcabcabc

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