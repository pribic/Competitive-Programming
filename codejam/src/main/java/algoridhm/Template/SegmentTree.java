package algoridhm.Template;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="SegTree" target="_top">SegTree</a>
 * @since 23/10/21 11:56 PM
 */
public class SegmentTree {

  static class Node {
    int val;

    public Node(int val) {
      this.val = val;
    }
  }

  static class SegTree {
    int size;
    Node[] data;

    SegTree(int n) {
      size = 1;
      while (size < n) size *= 2;
      data = new Node[2 * size];
      for (int i = 0; i < data.length; i++)
        data[i] = new Node(0);
    }

    void set(int idx, int val) {
      set(idx, val, 0, 0, size);
    }

    private void set(int idx, int val, int pos, int lx, int rx) {
      if (rx - lx == 1) {
        data[pos] = new Node(val);
        return;
      }
      int mid = (lx + rx) >> 1;
      if (idx < mid)
        set(idx, val, 2 * pos + 1, lx, mid);
      else
        set(idx, val, 2 * pos + 2, mid, rx);
      data[pos] = merge(data[2 * pos + 1], data[2 * pos + 2]);
    }

    Node get(int l, int r) { // [l, r)
      return get(l, r, 0, 0, size);
    }

    private Node get(int l, int r, int pos, int lx, int rx) {
      if (lx >= r || rx <= l) // out of range
        return new Node(0); // neutral element
      if (lx >= l && rx <= r) { //completely inside
        return data[pos];
      }
      int mid = (lx + rx) >> 1;
      Node left = get(l, r, 2 * pos + 1, lx, mid);
      Node right = get(l, r, 2 * pos + 2, mid, rx);
      return merge(left, right);
    }

    private Node merge(Node left, Node right) {
      return new Node(left.val + right.val);
    }
  }
}