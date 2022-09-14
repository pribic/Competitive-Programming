package codechef.contest.div3.START20B;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.codechef.com/START20B/problems/VKMPAIRS" target="_top">https://www.codechef.com/START20B/problems/VKMPAIRS</a>
 * @since 15/12/21 8:57 PM
 */
public class VKMPAIRS {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int p = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        int[] arr2 = new int[m];
        for (int i = 0; i < m; i++) {
          arr2[i] = sc.nextInt();
        }
        Trie trie = new Trie();
        for (int num : arr)
          trie.insert(num);
        trie.buildCount();
        int pairs = 0;
        for (int num : arr2)
          pairs += trie.find(num, p);
        System.out.println(pairs);
      }
    }
  }

  static class TrieNode {
    TrieNode[] children;
    boolean terminal;
    int frequency;
    int val;

    public TrieNode() {
      children = new TrieNode[2];
    }
  }

  //binary trie
  static class Trie {
    TrieNode root;
    int LOG = 20;

    Trie() {
      root = new TrieNode();
    }

    public void insert(int num) {
      TrieNode cur = root;
      for (int i = LOG - 1; i >= 0; i--) {
        int idx = bit(num, i);
        if (cur.children[idx] == null)
          cur.children[idx] = new TrieNode();
        cur = cur.children[idx];
      }
      cur.terminal = true;
      cur.frequency++;
      cur.val = num;
    }

    public int find(int num, int p) {
      TrieNode cur = root;
      List<TrieNode> candidates = new ArrayList<>();
      for (int i = LOG - 1; i >= 0; i--) {
        if (cur == null)
          break;
        int bit1 = bit(num, i);
        int bit2 = bit(p, i);
        if (bit2 == 1) {
          candidates.add(cur.children[bit1]);
          cur = cur.children[1 - bit1];
        } else {
          if (cur.terminal)
            candidates.add(cur);
          cur = cur.children[bit1];
        }
      }
      return candidates.stream().mapToInt(node -> dfs(node, num, p)).sum();
    }

    void buildCount() {
      buildCount(root);
    }

    private void buildCount(TrieNode root) {
      if (root == null)
        return;
      if (root.terminal)
        return;
      for (TrieNode child : root.children)
        buildCount(child);
      for (TrieNode child : root.children)
        if (child != null)
          root.frequency += child.frequency;
    }

    private int dfs(TrieNode cur, int num, int p) {
      if (cur == null)
        return 0;
      if (cur.terminal)
        return (cur.val * (cur.val ^ num) - 1) % p == 0 ? cur.frequency : 0;
      return dfs(cur.children[0], num, p) + dfs(cur.children[1], num, p);
    }

    int bit(int mask, int i) {
      return ((mask >> i) & 1);
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