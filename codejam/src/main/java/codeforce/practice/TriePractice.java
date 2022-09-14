package codeforce.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="Trie" target="_top">Trie</a>
 * @since 15/05/21 4:53 PM
 */
public class TriePractice {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    long ans = 0;
    for(int i = 100; i > 1; i--)
      ans += 100000000/i ;
    System.out.println(ans);
    System.out.println(6 * 100000000);
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int type;
        while ((type = sc.nextInt()) != -1) {
          switch (type) {
            case 1: //insert

          }
        }
      }
    }
  }

  static class TrieNode {
    int pre, end;
    Map<Character, TrieNode> children;

    public TrieNode() {
      children = new HashMap<>();
    }
  }

  static class Trie {
    TrieNode root;

    public Trie() {
      this.root = new TrieNode();
    }

    boolean find(String str) {
      return false;
    }

    void insert(String str) {
      TrieNode cur = root;
      for (char c : str.toCharArray()) {
        if (cur.children.get(c) == null)
          cur.children.put(c, new TrieNode());
        cur = cur.children.get(c);
        cur.pre++;
      }
      cur.end = 1;

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
}