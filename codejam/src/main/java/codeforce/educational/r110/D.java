package codeforce.educational.r110;

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
 * @see <a href="https://codeforces.com/contest/1535/problem/D" target="_top">https://codeforces.com/contest/1535/problem/D</a>
 * @since 05/06/21 10:09 AM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int k = sc.nextInt();
        char[] gameState = sc.next().toCharArray();
        for(int l = 0, r = gameState.length - 1; l < r; l++, r--){
          char temp = gameState[l];
          gameState[l] = gameState[r];
          gameState[r] = temp;
        }
        //System.out.println(gameState.length);
        int[] winners = new int[(1 << (k + 1))];
        for (int i = 1 << k; i < (1 << (k + 1)); i++)
          winners[i] = 1;
        //System.out.println(winners.length);
        for (int i =  (1 << k) - 2; i >= 0; i--) {
          int node = i + 1;
          //System.out.println("node = " + node);
          if (gameState[i] == '1') {
            winners[node] = winners[2 * node];
          } else if (gameState[i] == '0') {
            winners[node] = winners[2 * node + 1];
          } else {
            winners[node] = winners[2 * node] + winners[2 * node + 1];
          }
          //System.out.println("winners[node] = " + winners[node]);
        }
        //System.out.println("winners = " + Arrays.toString(winners));
        int q = sc.nextInt();
        while (q-- > 0) {
          int p = sc.nextInt();
          //System.out.println("p = " + p);
          char c = sc.next().charAt(0);
          //System.out.println("c = " + c);
          p = (1<<k)  - p;
          //System.out.println("p = " + p);
          gameState[p - 1] = c;
          //
          while (p != 0) {
            if (gameState[p - 1] == '0') {
              winners[p] = winners[2 * p + 1];
            } else if (gameState[p-1] == '1') {
              winners[p] = winners[2 * p];
            } else {
              winners[p] = winners[2 * p] + winners[2 * p + 1];
            }
            p = p / 2;
          }
          //

          System.out.println(winners[1]);
          //System.out.println("winners = " + Arrays.toString(winners));
        }
      }
    }
  }

  private static boolean isLeaf(int len, int node) {
    return node * 2 >= len;
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