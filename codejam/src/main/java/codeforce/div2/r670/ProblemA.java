package codeforce.div2.r670;

import java.util.Scanner;

public class ProblemA {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int i = 0; i < t; i++) {
        int n = sc.nextInt();
        int[] arr = new int[101];
        int[] freq = new int[101];
        for (int j = 0; j < n; j++) {
          arr[j] = sc.nextInt();
          freq[arr[j]]++;
        }
        int ans = 0;

        int A = 101;
        for (int j = 0; j < 101; j++) {
          if (freq[j] == 0) {
            A = j;
            break;
          } else {
            freq[j]--;
          }
        }
        int B = 101;
        for (int j = 0; j < 101; j++) {
          if (freq[j] == 0) {
            B = j;
            break;
          } else {
            freq[j]--;
          }
        }
        ans = A + B;

        System.out.println(ans);
      }
    }
  }
}
