package hackerearth.practice;

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
 * @see <a href="a" target="_top">a</a>
 * @since 06/07/21 8:30 PM
 */
public class a {
  static FastScanner sc = new FastScanner(System.in);

  
  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = 4;
        int[] cards = new int[n];
        for (int i = 0; i < n; i++) {
          cards[i] = sc.nextInt();
        }
        System.out.println(new a().judgePoint24(cards));
      }
    }
  }

  public boolean judgePoint24(int[] cards) {
    boolean[] used = new boolean[4];
    List<Character> op = new ArrayList<>();
    List<Integer> digits = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      for (int j = i + 1; j < 4; j++) {
        used[i] = true;
        used[j] = true;
        add(digits, op, cards, i, j, '+');
        if (isPossible(used, cards, (double) cards[i] + cards[j], op, digits))
          return true;
        remove(digits, op);
        add(digits, op, cards, i, j, '-');
        if (isPossible(used, cards, (double) cards[i] - cards[j], op, digits))
          return true;
        remove(digits, op);
        add(digits, op, cards, j, i, '-');
        if (isPossible(used, cards, (double) cards[j] - cards[i], op, digits))
          return true;
        remove(digits, op);
        add(digits, op, cards, i, j, '*');
        if (isPossible(used, cards, (double) cards[i] * cards[j], op, digits))
          return true;
        remove(digits, op);
        add(digits, op, cards, i, j, '/');
        if (isPossible(used, cards, (double) cards[i] / cards[j], op, digits))
          return true;
        remove(digits, op);
        add(digits, op, cards, j, i, '/');
        if (isPossible(used, cards, (double) cards[j] / cards[i], op, digits))
          return true;
        remove(digits, op);
        used[i] = false;
        used[j] = false;
      }
    }
    return false;
  }

  private void remove(List<Integer> digits, List<Character> op) {
    if (!digits.isEmpty())
      digits.remove(digits.size() - 1);
    if (!digits.isEmpty())
      digits.remove(digits.size() - 1);
    op.remove(op.size() - 1);
  }

  private void add(List<Integer> digits, List<Character> op, int[] cards, int i, int j, char c) {
    digits.add(cards[i]);
    digits.add(cards[j]);
    op.add(c);
  }

  private void add(List<Integer> digits, List<Character> op, int[] cards, int i, char c) {
    digits.add(cards[i]);
    op.add(c);
  }

  boolean isPossible(boolean[] used, int[] cards, double cur, List<Character> op, List<Integer> digits) {
    //System.out.println(cur);
    if (allUsed(used))
      if (Math.abs(24 - cur) <= 0.0000000001) {
        System.out.println(op);
        out.println(digits);
        return true;
      } else {
        return false;
      }
    for (int i = 0; i < 4; i++) {
      if (!used[i]) {
        used[i] = true;
        add(digits, op, cards, i, '+');
        if (isPossible(used, cards, cur + cards[i], op, digits))
          return true;
        remove(digits, op);
        add(digits, op, cards, i, '-');
        if (isPossible(used, cards, cur - cards[i], op, digits))
          return true;
        remove(digits, op);
        add(digits, op, cards, i, '*');
        if (isPossible(used, cards, cur * cards[i], op, digits))
          return true;
        remove(digits, op);
        add(digits, op, cards, i, '/');
        if (isPossible(used, cards, cur / cards[i], op, digits))
          return true;
        remove(digits, op);
        add(digits, op, cards, i, '-');
        if (isPossible(used, cards, cards[i] - cur, op, digits))
          return true;
        remove(digits, op);
        add(digits, op, cards, i, '/');
        if (isPossible(used, cards, (double)cards[i] / cur, op, digits))
          return true;
        remove(digits, op);
        used[i] = false;
      }
    }
    return false;

  }

  boolean allUsed(boolean[] used) {
    int cnt = 0;
    for (boolean bool : used) {
      if (bool) cnt++;
    }
    return cnt == 4;
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