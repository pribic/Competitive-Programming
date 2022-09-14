package codeforce.div3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1675/problem/E" target="_top">https://codeforces.com/contest/1675/problem/E</a>
 * @since 06/05/22 12:41 PM
 */
public class E {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int len = sc.nextInt();
        int k = sc.nextInt();
        char[] str = sc.next().toCharArray();
        for (int i = 0; k > 0 && i < str.length; i++) {
          int diff = str[i] - 'a';
          if (diff <= k) {
            //change everything here to a
            changeToA(str, str[i]); // all letters <= str[i] can be set to a now
            k -= diff;
          } else {
            // partial change
            char targetChar = (char) (str[i] - k);
            for (int j = 0; j < str.length; j++) {
              if(str[j] >= targetChar)
                str[j] = targetChar;
            }
            k = 0;
          }
        }
        System.out.println(String.valueOf(str));
      }
    }
  }

  private static void changeToA(char[] str, char c) {
    for (int i = 0; i < str.length; i++) {
      if (str[i] <= c)
        str[i] = 'a';
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