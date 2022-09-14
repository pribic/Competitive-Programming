package codeforce.educational.r95;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProblemC {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }

        int ans = 0;
        boolean friendTurn = true;
        for (int i = 0; i < n; ) {
          if (friendTurn) {
            if (arr[i] == 1) {
              ans++;
            }
            i++;
            if (i < n && arr[i] == 0)
              i++;
          } else {
            i++;
            if (i < n && arr[i] == 1)
              i++;
          }
          friendTurn = !friendTurn;
        }
        System.out.println(ans);
      }
    }
  }
}
