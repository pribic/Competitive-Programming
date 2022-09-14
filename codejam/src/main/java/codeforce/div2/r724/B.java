package codeforce.div2.r724;

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
import java.util.TreeSet;
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
 * @see <a href="https://codeforces.com/contest/1536/problem/B" target="_top">https://codeforces.com/contest/1536/problem/B</a>
 * @since 06/06/21 8:35 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {

    Set<String> allPossibilities = new TreeSet<>((a,b) -> {
      if(a.length() < b.length())
        return -1;
      if(b.length() < a.length())
        return 1;
      return a.compareTo(b);
    });
    allPossibilities.add("");
    for (int i = 0; i < 3; i++) {
      Set<String> newStrings = new HashSet<>();
      for (int j = 0; j < 26; j++) {
        for (String s : allPossibilities) {
          newStrings.add(s + (char)(j + 'a'));
        }
      }
      allPossibilities.addAll(newStrings);
    }
    allPossibilities.remove("");

    //out.println(allPossibilities);
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      outer:
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String str = sc.next();
        Trie trie = new Trie();
        for(int len = 1; len <= 3; len++) {
          for(int i = 0; i < str.length() - len + 1; i++) {
            trie.insert(str, i, i + len);
          }
        }
        
        for(String s : allPossibilities){
          if(!trie.find(s)){
            System.out.println(s);
            break ;
          }
        }
      }
    }
  }
  
  static class TrieNode {
    TrieNode[] children;
    boolean isEnd;

    public TrieNode() {
      this.children = new TrieNode[26];
    }
  }
  static class Trie {
    
    TrieNode root;

    public Trie() {
      root = new TrieNode();
    }
  
    void insert(String s, int st, int end) {
      TrieNode cur = root;
      for(int i = st; i < end; i++) {
        int idx = s.charAt(i) - 'a';
        if(cur.children[idx] == null)
          cur.children[idx] = new TrieNode();
        cur = cur.children[idx];
      }
      cur.isEnd = true;
    }
    
    void insert(String s) {
      TrieNode cur = root;
      for(int i = 0; i < s.length(); i++) {
        int idx = s.charAt(i) - 'a';
        if(cur.children[idx] == null)
          cur.children[idx] = new TrieNode();
        cur = cur.children[idx];
      }
      cur.isEnd = true;
    }
    
    boolean find(String s) {
      TrieNode cur = root;
      for(int i = 0; i < s.length(); i++) {
        int idx = s.charAt(i) - 'a';
        if(cur.children[idx] == null)
          return false;
        cur = cur.children[idx];
      }
      return cur.isEnd;
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