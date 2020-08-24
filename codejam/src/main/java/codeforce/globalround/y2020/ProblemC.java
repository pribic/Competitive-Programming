package codeforce.globalround.y2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class ProblemC {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        List<Node> arr = new ArrayList<>();
        Map<Integer, Node> mapping = new HashMap<>();
        for (int i = 0; i < n; i++) {
          Node node = new Node(sc.nextInt(), i);
          arr.add(node);
          mapping.put(node.val, node);
        }
        int minValue = 1;
        boolean noPrint = false;
        int k = 0;
        while (k++ < n - 1) {
          Node min = mapping.get(minValue);
          if(min.index == 0) {
            System.out.println("YES");
            break;
          }
          if(min.index == n - 1) {
            System.out.println("NO");
            break;
          }
          minValue++;
          
        }
      }
    }


  }

  static class Node {
    int val;
    int index;

    public Node(int val, int index) {
      this.val = val;
      this.index = index;
    }

    @Override
    public String toString() {
      return val + " " + index;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Node node = (Node) o;

      if (val != node.val) return false;
      return index == node.index;
    }

    @Override
    public int hashCode() {
      int result = val;
      result = 31 * result + index;
      return result;
    }
  }
}
