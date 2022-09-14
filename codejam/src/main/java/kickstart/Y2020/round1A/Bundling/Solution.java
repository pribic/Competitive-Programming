package kickstart.Y2020.round1A.Bundling;

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
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/000000000019ffc7/00000000001d3ff3" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/000000000019ffc7/00000000001d3ff3</a>
 * @since 22/05/21 11:47 PM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int k = sc.nextInt();
        Trie trie = new Trie();
        while (n-- > 0) {
          String word = sc.next();
          trie.insert(word);
        }
        System.out.println(trie.sum(k));
      }
    }
  }

  static class Trie {
    TrieNode root;

    public Trie() {
      root = new TrieNode();
    }

    long sum(int k) {
      return dfs(root, k);
    }

    long dfs(TrieNode node, int k) {
      if (node == null)
        return 0;
      long ans = 0;
      for (int i = 0; i < 26; i++) {
        ans += node.count[i] / k + dfs(node.children[i], k);
      }
      return ans;
    }

    public void insert(String word) {
      TrieNode cur = root;
      for (char c : word.toCharArray()) {
        int idx = c - 'A';
        cur.count[idx]++;
        if (cur.children[idx] == null)
          cur.children[idx] = new TrieNode();
        cur = cur.children[idx];
      }
      cur.end = true;
    }
  }

  static class TrieNode {
    TrieNode[] children;
    int[] count;
    boolean end;

    public TrieNode() {
      children = new TrieNode[26];
      count = new int[26];
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