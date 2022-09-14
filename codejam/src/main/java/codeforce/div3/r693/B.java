package codeforce.div3.r693;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 05/01/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        long sum = 0;
        int[] cnt = new int[3];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          sum += arr[i];
          cnt[arr[i]]++;
        }
        if (sum % 2 == 1 || cnt[2] % 2 == 1 && cnt[1] <= 1) {
          System.out.println("NO");
        } else {
            System.out.println("YES");
        }
      }
    }
  }
}