package codeforce.educational.r94;

import java.util.Arrays;
import java.util.Scanner;

public class ProblemD {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }

        int[] freq = new int[3001];
        int[] left = new int[n];
        for (int i = n - 2; i >= 0; i--) {
          freq[arr[i + 1]]++;
          left[i] = freq[arr[i]];
        }
        freq[arr[0]]++;
        System.out.println("freq = " + Arrays.toString(freq));
        System.out.println("left = " + Arrays.toString(left));
        long ans = 0;
        for (int i = 0; i < n - 1; i++) {
          for (int j = i + 1; j < n; j++) {
            ans += left[i] * left[j];
          }
        }
        System.out.println(ans);
      }
    }
  }
}
