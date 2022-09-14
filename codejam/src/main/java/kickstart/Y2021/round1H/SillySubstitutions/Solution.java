package kickstart.Y2021.round1H.SillySubstitutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Objects;
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
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/0000000000435914/00000000008d94f5" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/0000000000435914/00000000008d94f5</a>
 * @since 14/11/21 9:09 AM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        String s = sc.next();
        Node first = build(s);
        Map<Integer, Set<Node>> mapping = buildMapping(first);
        boolean change = false;
        while (true) {
          change = false;
          for (Integer i = 0; i < 10; i++) {
            //try to change all node with i and i + 1
            Iterator<Node> itr = mapping.get(i).iterator();
            while (itr.hasNext()) {
              Node node = itr.next();
              //see if its next node is candidate
              change = isChange(mapping, change, itr, node);
              if (change)
                itr.remove();
            }
          }
          if (!change)
            break;
        }
        System.out.println(convert(first));
      }
    }
  }

  private static boolean isChange(Map<Integer, Set<Node>> mapping, boolean change, Iterator<Node> itr, Node node) {
    if (node.nxt == null)
      return false;
    Node cur = node.nxt;
    Node last = null;
    while (cur.nxt != null && cur.val == cur.nxt.val) {
      mapping.get(cur.val).remove(cur);
      mapping.get(cur.nxt.val).remove(cur.nxt);
      last = cur.nxt;
      if (cur.nxt.nxt == null || (cur.nxt.val + 1) % 10 != cur.nxt.nxt.val)
        break;
      cur = cur.nxt.nxt;
    }
    
    //now we traverse 2 nodes at once
    if (node.nxt != null && (node.val + 1) % 10 == node.nxt.val) {
      //remove them from their own list
      mapping.get(node.nxt.val).remove(node.nxt);
      node.val = (node.nxt.val + 1) % 10;
      node.nxt = node.nxt.nxt;
      mapping.get(node.val).add(node);
      change = true;
    }
    return change;
  }

  private static String convert(Node first) {
    StringBuilder sb = new StringBuilder();
    while (first != null) {
      sb.append(first.val);
      first = first.nxt;
    }
    return sb.toString();
  }

  private static Map<Integer, Set<Node>> buildMapping(Node first) {
    Node cur = first;
    Map<Integer, Set<Node>> map = new HashMap<>();
    for (Integer i = 0; i < 10; i++)
      map.put(i, new HashSet<>());
    while (cur != null) {
      int val = cur.val;
      map.putIfAbsent(val, new HashSet<>());
      map.get(val).add(cur);
      cur = cur.nxt;
    }
    return map;
  }

  private static Node build(String s) {
    Node first = new Node(0, 0, null);
    Node cur = first;
    for (int i = 0; i < s.length(); i++) {
      Node next = new Node(s.charAt(i) - '0', i, null);
      cur.nxt = next;
      cur = cur.nxt;
    }
    return first.nxt;
  }

  static class Node {
    int val, id;
    Node nxt;

    public Node(int val, int id, Node nxt) {
      this.val = val;
      this.id = id;
      this.nxt = nxt;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Node node = (Node) o;
      return id == node.id;
    }

    @Override
    public int hashCode() {
      return Objects.hash(id);
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