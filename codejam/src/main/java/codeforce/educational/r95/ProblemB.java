package codeforce.educational.r95;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProblemB {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
          arr[i] = new Pair();
          arr[i].val = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
          arr[i].locked = sc.nextInt();
        }

        List<Pair> unlockedPairs = Arrays.stream(arr)
          .filter(p -> p.locked == 0)
          .sorted((p1, p2) -> p2.val - p1.val)
          .collect(Collectors.toList());

        int index = 0;
        for (int i = 0; i < n; i++) {
          System.out.print(" " + (arr[i].locked == 1 ? arr[i].val : unlockedPairs.get(index++).val));
        }
        System.out.println();
      }
    }
  }

  static class Pair {
    int val, locked;
  }
}
