package codeforce.div2.r723;

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
 * @see <a href="https://codeforces.com/contest/1526/problem/D" target="_top">https://codeforces.com/contest/1526/problem/D</a>
 * @since 04/06/21 3:11 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String s = sc.next();
        int[] freq = new int[26];
        for (char c : s.toCharArray())
          freq[c - 'A']++;
        char[] perm = new char[]{'A', 'N', 'O', 'T'};
        int cost = -1;
        String ans = "";
        for (int i = 0; i < 24; i++) {
          int tempCost = findCost(perm, freq, s);
          System.out.println(tempCost + " " + Arrays.toString(perm));
          if (tempCost > cost) {
            cost = tempCost;
            StringBuilder tempans = new StringBuilder();
            for (int j = 0; j < perm.length; j++) {
              for (int k = 0; k < freq[perm[j] - 'A']; k++)
                tempans.append(perm[j]);
            }
            ans = tempans.toString();
          }
          //System.out.println("perm = " + Arrays.toString(perm));
          nextPermutation(perm);
        }
        System.out.println(ans);
      }
    }
  }

  private static int findCost(char[] perm, int[] freq, String s) {
    StringBuilder other = new StringBuilder();
    for (int i = 0; i < perm.length; i++) {
      char ch = perm[i];
      for (int j = 0; j < freq[ch - 'A']; j++)
        other.append(ch);
    }
    //out.println("other = " + other);
    Map<Character, List<Integer>> positionS = buildPositions(s);
    Map<Character, List<Integer>> positionOther = buildPositions(other.toString());
    int cost = 0;
    for (char ch : positionS.keySet()) {
      for (int i = 0; i < positionS.get(ch).size(); i++) {
        cost += Math.abs(positionS.get(ch).get(i) - positionOther.get(ch).get(i));
      }
    }
    return cost;
  }

  private static Map<Character, List<Integer>> buildPositions(String s) {
    Map<Character, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      map.putIfAbsent(ch, new ArrayList<>());
      map.get(ch).add(i);
    }
    return map;
  }

  private static void nextPermutation(char[] perm) {
    for (int i = perm.length - 2; i >= 0; i--) {
      if (perm[i] < perm[i + 1]) {
        for (int j = perm.length - 1; j > i; j--) {
          if (perm[j] > perm[i]) {
            char temp = perm[i];
            perm[i] = perm[j];
            perm[j] = temp;

            //swap i + 1 to end
            for (int l = i + 1, r = perm.length - 1; l < r; l++, r--) {
              temp = perm[l];
              perm[l] = perm[r];
              perm[r] = temp;
            }
            return;
          }

        }
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