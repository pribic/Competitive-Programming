package codeforce.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
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
 * @see <a href="https://codeforces.com/contest/817/problem/E" target="_top">https://codeforces.com/contest/817/problem/E</a>
 * @since 15/05/21 2:37 PM
 */
public class p817E {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    int T = 1;//sc.nextInt();
    for (int tt = 1; tt <= T; tt++) {
      int q = sc.nextInt();
      Trie trie = new Trie();
      while (q-- > 0) {
        int type = sc.nextInt();
        int personality, leadership;
        switch (type) {
          case 1:
            personality = sc.nextInt();
            trie.insert(personality);
            break;
          case 2:
            personality = sc.nextInt();
            trie.remove(personality);
            break;
          case 3:
            personality = sc.nextInt();
            leadership = sc.nextInt();
            out.println(trie.maxRespectWarrier(personality, leadership));
            break;
          case 4:
            personality = sc.nextInt();
            out.println(trie.find(personality));
            break;
          case 5:
            trie.printAll();
            break;
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
      br = new BufferedReader(new InputStreamReader(f));
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

  private static class TrieNode {
    TrieNode[] children;
    int[] childCount;

    public TrieNode() {
      children = new TrieNode[2];
      childCount = new int[2];
    }
  }

  private static class Trie {
    TrieNode root;
    int len = 7;
    Set<Integer> numbers = new HashSet<>();

    public Trie() {
      root = new TrieNode();
    }

    public void insert(int num) {
      TrieNode cur = root;
      for (int i = len; i >= 0; i--) {
        int b = getBit(num, i);
        if (cur.children[b] == null)
          cur.children[b] = new TrieNode();
        cur.childCount[b]++;
        cur = cur.children[b];
      }
      numbers.add(num);
    }

    /**
     * we assume that num is already present in trie
     */
    public void remove(int num) {
      numbers.remove(num);
      TrieNode cur = root;
      for (int i = len; i >= 0; i--) {
        int b = getBit(num, i);
        if (cur.childCount[b] == 1) {
          cur.children[b] = null;
          cur.childCount[b] = 0;
          return;
        }
        cur = cur.children[b];
      }
    }

    /**
     * if we can traverse trie according to given input num,
     * then we can say it is present
     */
    public boolean find(int num) {
      TrieNode cur = root;
      for (int i = len; i >= 0; i--) {
        int b = getBit(num, i);
        if (cur.children[b] == null)
          return false;
        cur = cur.children[b];
      }
      return true;
    }

    public void printAll() {
      out.println("numbers.toString() = " + numbers.toString());
    }

    public long maxRespectWarrier(int personality, int leadership) {
      /*printAll();
      out.println("leadership");
      out.println(getX(leadership));
      out.println();
      for (int num : numbers) {
        out.println(getX(num));
        out.println(getX(personality));
        out.println(getX(num ^ personality));
      }*/
      TrieNode cur = root;
      long ans = 0;
      for (int i = len; i >= 0; i--) {
        int bitP = getBit(personality, i);
        int bitL = getBit(leadership, i);
        if (bitL == 1) {
          ans += cur.childCount[bitP];
          if (cur.childCount[1 - bitP] > 0)
            cur = cur.children[1 - bitP];
          else
            return ans;
        } else {
          if (cur.childCount[bitP] == 0)
            return 0;
          cur = cur.children[bitP];
        }
      }
      return ans;
    }

    private String getX(int leadership) {
      return String.format("%8s", Integer.toBinaryString(leadership));
    }

    private int getBit(int num, int i) {
      return (num >> i) & 1;
    }
  }
}