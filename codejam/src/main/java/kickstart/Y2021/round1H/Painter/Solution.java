package kickstart.Y2021.round1H.Painter;

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
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/0000000000435914/00000000008d9a88" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/0000000000435914/00000000008d9a88</a>
 * @since 14/11/21 8:53 AM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  /*
  RED    001 1
  Yellow 010 2
  blue   100 4
  orange 011 3
  purple 101 5
  green  110 6
  gray   111 7
  
   */
  public static void main(String[] args) {
    int[] map = new int[26];
    map['U' - 'A'] = 0;
    map['R' - 'A'] = 1;
    map['Y' - 'A'] = 2;
    map['B' - 'A'] = 4;
    map['O' - 'A'] = 3;
    map['P' - 'A'] = 5;
    map['G' - 'A'] = 6;
    map['A' - 'A'] = 7;
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        String s = sc.next();
        int[] target = new int[n];
        for (int i = 0; i < n; i++)
          target[i] = map[s.charAt(i) - 'A'];
        int[] current = new int[n];
        int cnt = 0;
        for (int l = 0; l < n; l++) {
          //we want to change current to target
          // we check all 3 bits of current and compare them with target
          for (int bit = 0; bit < 3; bit++) {
            if (set(target[l], bit) && !set(current[l], bit)) {
              //this bit is missing in current, use its pen and color it
              //use this pen as much as we can to right side
              int r = l;
              while (r < n && set(target[r], bit)) {
                current[r] = current[r] | (1 << bit);
                r++;
              }
              cnt++;
            }
          }
        }

        System.out.println(cnt);
      }
    }

  }

  static private boolean set(int mask, int i) {
    return ((mask >> i) & 1) == 1;
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