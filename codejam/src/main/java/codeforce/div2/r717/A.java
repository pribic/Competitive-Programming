package codeforce.div2.r717;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 21/04/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        long sum = 0;
        for(int i = 0; i < n - 1; i++) {
          if(k >= arr[i]) {
            System.out.print("0 ");
            k -= arr[i];
            sum += arr[i];
          } else {
            System.out.print(arr[i] - k + " ");
            sum += k;
            k = 0;
          }
        }
        System.out.println(arr[n - 1] + sum);
      }
    }
  }
}