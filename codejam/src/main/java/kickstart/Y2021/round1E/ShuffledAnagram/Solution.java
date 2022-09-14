package kickstart.Y2021.round1E.ShuffledAnagram;

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
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/000000000043585c/000000000085a152" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/000000000043585c/000000000085a152</a>
 * @since 22/08/21 9:16 AM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        char[] s = sc.next().toCharArray();
        ArrayList<Pair> freq = new ArrayList<>();
        for (char ch = 'a'; ch <= 'z'; ch++)
          freq.add(new Pair(ch, new ArrayList<>()));
        for (int i = 0; i < s.length; i++)
          freq.get(s[i] - 'a').indexes.add(i);
        Collections.sort(freq, Collections.reverseOrder());
        /*for (Pair p : freq) {
          if(p.indexes.size() > 0)
            System.out.println(p.ch + " " + p.indexes);
        }*/

        char[] anagram = new char[s.length];
        List<Integer> targetIdx = new ArrayList<>();
        for (int i = 1; i < freq.size(); i++) {
          Pair p = freq.get(i);
          targetIdx.addAll(p.indexes);
        }
        targetIdx.addAll(freq.get(0).indexes);
        //System.out.println("targetIdx = " + targetIdx);
        
        int idx = 0;
        for (Pair p : freq) {
          for (int i = 0; i < p.indexes.size(); i++) {
            anagram[targetIdx.get(idx++)] = p.ch;
          }
        }
        //System.out.println("anagram = " + Arrays.toString(anagram));
        boolean valid = true;
        for (int i = 0; i < s.length; i++) {
          if (s[i] == anagram[i])
            valid = false;
        }
        System.out.println(valid ? String.valueOf(anagram) : "IMPOSSIBLE");
      }
    }

  }

  static class Pair implements Comparable<Pair> {
    char ch;
    List<Integer> indexes;

    public Pair(char ch, List<Integer> indexes) {
      this.ch = ch;
      this.indexes = indexes;
    }

    @Override
    public int compareTo(Pair o) {
      return this.indexes.size() - o.indexes.size();
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