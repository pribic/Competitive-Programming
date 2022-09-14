package codechef.longC.NOV21;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringJoiner;
import java.util.StringTokenizer;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.codechef.com/NOV21B/problems/WLDRPL" target="_top">https://www.codechef.com/NOV21B/problems/WLDRPL</a>
 * @since 06/11/21 3:28 PM
 */
public class WLDRPL {
  static FastScanner sc = new FastScanner(System.in);

  static Map<Integer, Integer> mapping;
  static Map<String, Node> memoize;
  static Node[] nodeMapping;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        mapping = new HashMap<>();
        memoize = new HashMap<>();
        String exp = sc.next();
        buildMapping(exp);
        Node root = build(exp, 0, exp.length() - 1);
        nodeMapping = new Node[exp.length()];
        root.parents[0] = root;
        for (int i = 0; i < 21; i++)
          dfs(root, root, i);
        int q = sc.nextInt();
        while (q-- > 0) {
          int l = sc.nextInt() - 1;
          int r = sc.nextInt() - 1;
          if (l == r) {
            System.out.print("1 ");
            continue;
          }
          Node lNode = nodeMapping[exp.indexOf('?', l)];
          Node rNode = null;
          for (int i = r; i >= 0; i--)
            if (exp.charAt(i) == '?') {
              rNode = nodeMapping[i];
              break;
            }
          System.out.print(lca(lNode, rNode).max + " ");

        }
        System.out.println();
      }
    }
  }

  static Node lca(Node one, Node other) {
    int l = 0; // false
    int r = Integer.MAX_VALUE; // true
    while (r > l + 1) {
      //System.out.println(l + " " + r);
      int mid = l + (r - l) / 2;
      if (kthParent(one, mid) == kthParent(other, mid))
        r = mid;
      else
        l = mid;
    }
    return kthParent(one, r);
  }

  private static Node kthParent(Node node, int k) {
    for (int i = 0; i < 21; i++) {
      if (((k >> i) & 1) == 1) {
        node = node.parents[i];
      }
    }
    return node;
  }

  private static void buildMapping(String exp) { // adds mapping for each ( and ) brackets
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < exp.length(); i++) {
      if (exp.charAt(i) == '(') {
        stack.push(i);
      } else if (exp.charAt(i) == ')') {
        int matchIdx = stack.pop();
        mapping.put(matchIdx, i);
      }
    }
  }

  private static void dfs(Node root, Node parent, int parentDistance) {
    if (root == null)
      return;
    dfs(root.left, root, parentDistance);
    if (root.idx != -1)  // shows we are at leaf node
      nodeMapping[root.idx] = root;
    if (parentDistance == 0)
      root.parents[0] = parent;
    else
      root.parents[parentDistance] = root.parents[parentDistance / 2].parents[parentDistance / 2];
    dfs(root.right, root, parentDistance);
  }

  private static Node build(String exp, int st, int end) { // build for exp[st, end] range
    if (end == st) {
      return Node.leaf(st);
    }
    if (!memoize.containsKey(st + ":" + end)) {
      Node returnNode = null;
      if (exp.charAt(st) == '(') {
        int endIdx = mapping.get(st);
        if (endIdx == end) { // (validExp) case
          returnNode = build(exp, st + 1, end - 1);
        } else {
          //(validExp op validExp) case
          // so now we have [st, endIdx] range here
          Node left = build(exp, st, endIdx);
          Node right = build(exp, endIdx + 2, end);
          returnNode = merge(left, right, exp.charAt(endIdx + 1));
        }
      } else if (exp.charAt(st) == '?') {
        Node left = Node.leaf(st);
        Node right = build(exp, st + 2, end);
        returnNode = merge(left, right, exp.charAt(st + 1));
      } else if (exp.charAt(end) == '?') {
        Node left = build(exp, st, end - 2);
        Node right = Node.leaf(end);
        returnNode = merge(left, right, exp.charAt(end - 1));
      }
      memoize.put(st + ":" + end, returnNode);
    }
    return memoize.get(st + ":" + end);
  }


  static Node merge(Node left, Node right, char operator) {
    int[] vals;
    if (operator == '+') {
      vals = new int[]{left.min + right.min, left.min + right.max, left.max + right.min, left.max + right.max};
    } else {
      vals = new int[]{left.min - right.min, left.min - right.max, left.max - right.min, left.max - right.max};
    }
    Arrays.sort(vals);
    return new Node(operator, left, right, vals[0], vals[3], -1);
  }

  static class Node {
    char type; // this will be + - or ?
    Node left, right;
    int min, max; // min and max value possible if this node is the root node of any expression
    int idx;
    Node[] parents;

    public Node(char type, Node left, Node right, int min, int max, int idx) {
      this.type = type;
      this.left = left;
      this.right = right;
      this.min = min;
      this.max = max;
      this.idx = idx;
      parents = new Node[21];
    }

    public static Node leaf(int idx) {
      return new Node('?', null, null, 0, 1, idx);
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
        .add("type=" + type)
        .add("left=" + left)
        .add("right=" + right)
        .add("min=" + min)
        .add("max=" + max)
        .toString();
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