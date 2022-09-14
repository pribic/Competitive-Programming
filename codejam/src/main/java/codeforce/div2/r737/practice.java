package codeforce.div2.r737;

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
import java.util.Stack;
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
 * @see <a href="practice" target="_top">practice</a>
 * @since 17/08/21 3:04 PM
 */
public class practice {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        Tree node1 = new Tree(1);
        Tree node2 = new Tree(2);
        Tree node3 = new Tree(3);
        Tree node4 = new Tree(4);
        Tree node5 = new Tree(5);
        Tree node6 = new Tree(6);
        Tree node7 = new Tree(7);
        Tree node8 = new Tree(8);
        Tree node9 = new Tree(9);
        Tree node10 = new Tree(10);
        node1.left = node2;
        node1.right = node3;
        node2.left = node6;
        node2.right = node7;
        node3.left = node4;
        node3.right = node5;
        node6.left = node8;
        node7.left = node9;
        node7.right = node10;
        preorder(node1);
        System.out.println();
        inorder(node1);
        System.out.println();
        postrder(node1);
        System.out.println();
      }
    }
  }

  private static void postrder(Tree node) {
    Stack<Tree> stack = new Stack<>();
    stack.push(node);
    while (!stack.isEmpty()) {
      Tree cur = stack.pop();
      if (cur != null) {
        if (!cur.visited) {
          stack.push(cur);
          stack.push(cur.right);
          stack.push(cur.left);
          cur.visited = true;
        } else {
          out.print(cur.val + " ");
        }
      }
    }
  }

  private static void inorder(Tree node) {
    Stack<Tree> stack = new Stack<>();
    stack.push(node);
    while (!stack.isEmpty()) {
      Tree cur = stack.pop();
      if (cur != null) {
        if (!cur.visited) {
          stack.push(cur.right);
          stack.push(cur);
          stack.push(cur.left);
          cur.visited = true;
        } else {
          out.print(cur.val + " ");
        }
      }
    }
  }

  private static void preorder(Tree node) {
    Stack<Tree> stack = new Stack<>();
    stack.push(node);
    while (!stack.isEmpty()) {
      Tree cur = stack.pop();
      if (cur != null) {
        out.print(cur.val + " ");
        stack.push(cur.right);
        stack.push(cur.left);
      }
    }
  }

  static class Tree {
    int val;
    boolean visited;
    Tree left, right;

    public Tree(int val) {
      this.val = val;
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