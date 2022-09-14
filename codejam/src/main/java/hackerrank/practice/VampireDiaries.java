package hackerrank.practice;

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
 * @see <a href="https://www.hackerrank.com/contests/execute19/challenges/vampire-diaries" target="_top">https://www.hackerrank.com/contests/execute19/challenges/vampire-diaries</a>
 * @since 21/05/21 5:28 PM
 */
public class VampireDiaries {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long q = sc.nextLong();
        Trie trie = new Trie();
        StringBuilder ans = new StringBuilder();
        while (q-- > 0) {
          int type = sc.nextInt();
          //System.out.println("query starts " + q + " type " + type);
          switch (type) {
            case 1: // arr[x] = y
              long x = sc.nextLong();
              long y = sc.nextLong();
              trie.insert(x, y, y - trie.find(x));
              break;
            case 2: // sum[1,x]
              long r = sc.nextLong();
              ans.append(trie.sum(r + 1)).append("\n");
              break;
          }
          //System.out.println("query ends " + q);
        }
        System.out.println(ans);

      }
    }
  }

  static class TrieNode {
    TrieNode[] children;
    long[] sum;
    long val;

    public TrieNode() {
      children = new TrieNode[2];
      sum = new long[2];
    }
  }

  static class Trie {
    TrieNode root;
    int len = 60;
    public Trie() {
      this.root = new TrieNode();
    }

//1 2 3 4 5 6 7 8
//6 - - - 8    

    void insert(long num, long y, long diff) {
      TrieNode cur = root;
      for (int i = len; i >= 0; i--) {
        int bit = (int) ((num >> i) & 1); //will return 0 or 1
        if (cur.children[bit] == null)
          cur.children[bit] = new TrieNode();
        cur.sum[bit] += diff;
        cur = cur.children[bit];
      }
      //we will be at the leaf node
      cur.val = y;
     // mapping.put(num, y);
    }


    long find(long num) {
      TrieNode cur = root;
      for (int i = len; i >= 0; i--) {
        int bit = (int) ((num >> i) & 1);
        if (cur.children[bit] == null)
          return 0L;
        cur = cur.children[bit];
      }
      return cur.val;
    }

    long sum(long num) {
      TrieNode cur = root;
      long ans = 0;
      for (int i = len; i >= 0; i--) {
        int bit = (int) ((num >> i) & 1);
        if (bit == 1)
          ans += cur.sum[0];
        if (cur.children[bit] != null)
          cur = cur.children[bit];
        else
          break;
      }
      return ans;
    }
  }

  /*
  1 a[i] = x
  2 sum[1,i] - first i element
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