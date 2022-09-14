package codeforce.div2.r788;

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
 * @see <a href="https://codeforces.com/contest/1670/problem/B" target="_top">https://codeforces.com/contest/1670/problem/B</a>
 * @since 06/05/22 8:32 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String pswd = sc.next();
        int k = sc.nextInt();
        Set<Character> special = new HashSet<>();
        for (int i = 0; i < k; i++)
          special.add(sc.next().charAt(0));
        int cnt = 0;
        while (true) {
          List<Integer> specialIDx = new ArrayList<>();
          for (int i = 1; i < pswd.length(); i++)
            if (special.contains(pswd.charAt(i)))
              specialIDx.add(i - 1);
          if (specialIDx.isEmpty())
            break;
          if(specialIDx.size() == 1) {
            cnt += specialIDx.get(0) + 1;
            break;
          }
          cnt++;
          StringBuilder sb = new StringBuilder();
          int skipI = 0;
          for (int i = 0; i < pswd.length(); i++) {
            if (skipI < specialIDx.size() && i == specialIDx.get(skipI))
              skipI++;
            else
              sb.append(pswd.charAt(i));
          }
          pswd = sb.toString();
        }
        System.out.println(cnt);
      }
    }
  }
  /*
 10
9
iloveslim
1 s

7
joobeel -> oel
2 o e

7
basiozi
2 s i
6
khater
1 r
7
abobeih
6 a b e h i o
5
zondl
5 a b c e f
6
shoman
2 a h
7
shetwey
2 h y
5
samez
1 m
6
mouraz
1 m

  
   */

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