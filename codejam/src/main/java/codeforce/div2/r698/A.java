package codeforce.div2.r698;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 28/01/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        int ans = 1;
        int cnt = 1;
        for(int i = 1; i < n; i++) {
          if(arr[i] == arr[i-1]) {
            cnt++;
          } else {
            ans = Math.max(ans, cnt);
            cnt = 1;
          }
        }
        ans = Math.max(cnt, ans);
        System.out.println(ans);
      }
    }
  }
}