package kickstart.Y2020.round1H.problemA;

import java.util.Scanner;
import java.util.Stack;

public class Solution {

  // 0 0 0 0
  // 0 0 0 0
  // 0 0 0 0
  // 0 0 0 0
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      InputReader ir = new InputReader(sc);
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        long n = sc.nextLong();
        long k = sc.nextLong();
        long s = sc.nextLong();

        System.out.println(k + Math.min(n, k - s + n - s));
      }
    }

  }

 

  static class InputReader {
    Scanner sc;

    InputReader(Scanner sc) {
      this.sc = sc;
    }

    int[] readArrayInt(int size) {
      int[] arr = new int[size];
      for (int i = 0; i < size; i++) {
        arr[i] = sc.nextInt();
      }
      return arr;
    }

    long[] readArrayLong(int size) {
      long[] arr = new long[size];
      for (int i = 0; i < size; i++) {
        arr[i] = sc.nextLong();
      }
      return arr;
    }
  }
}
