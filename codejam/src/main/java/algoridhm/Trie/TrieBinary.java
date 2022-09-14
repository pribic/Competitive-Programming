package algoridhm.Trie;

import javax.swing.tree.TreeNode;
import java.util.Collections;
import java.util.Scanner;
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
 * @since 13/05/21
 */
public class TrieBinary {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        Trie trie = new Trie();
        int type;
        while ((type = sc.nextInt()) != -1) {
          switch (type) {
            case 1: //insert
              trie.insert(sc.nextInt());
              break;
            case 2: //find
              out.println(trie.find(sc.nextInt()));
              break;
            case 3: //count
              out.println(trie.count(sc.nextInt()));
              break;
            case 4: //max xor for given num
              out.println(trie.maxXor(sc.nextInt()));
              break;
            case 5: //nums less than current num present in tree
              out.println(trie.lessThan(sc.nextInt()));
              break;
            case 6: //delete  
              trie.erase(sc.nextInt());
              break;
          }
        }
      }
    }
  }

  static class TrieNode {
    TrieNode[] children;
    int[] cnt;

    public TrieNode() {
      children = new TrieNode[2];
      cnt = new int[2];
    }

  }

  static class Trie { //numbers inserted <= 10^9, bits <= 30
    TrieNode root;

    public Trie() {
      this.root = new TrieNode();
    }

    void insert(int num) {
      TrieNode cur = root;
      for (int i = 29; i >= 0; i--) {
        int b = ((num >> i) & 1) == 1 ? 1 : 0;
        if (cur.children[b] == null)
          cur.children[b] = new TrieNode();
        cur.cnt[b]++;
        cur = cur.children[b];
      }
    }

    void erase(int num) {

    }

    boolean find(int num) {
      TrieNode cur = root;
      for (int i = 29; i >= 0; i--) {
        int b = ((num >> i) & 1) == 1 ? 1 : 0;
        if (cur.children[b] == null)
          return false;
        cur = cur.children[b];
      }
      return true;
    }

    int count(int num) {
      TrieNode cur = root;
      for (int i = 29; i >= 1; i--) {
        int b = ((num >> i) & 1) == 1 ? 1 : 0;
        if (cur.children[b] == null)
          return 0;
        cur = cur.children[b];
      }
      return cur.cnt[num & 1];
    }

    public int maxXor(int num) {
      throw new RuntimeException();
    }

    //assuming number is present
    public int lessThan(int num) {
      TrieNode cur = root;
      int ans = 0;
      for (int i = 29; i >= 0; i--) {
        int b = ((num >> i) & 1) == 1 ? 1 : 0;
        if (b == 1)
          ans += cur.cnt[0];
        cur = cur.children[b];
      }
      return ans;
    }
  }
}