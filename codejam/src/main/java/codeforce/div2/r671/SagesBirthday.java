package codeforce.div2.r671;

import java.util.Arrays;
import java.util.Scanner;

public class SagesBirthday {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = sc.nextInt();
      }
      Arrays.sort(arr);
      System.out.println(n/2);
      int[] ans = new int[n];
      int cnt = 0;
      for(int i = 1; i < n; i += 2) {
        ans[i] = arr[cnt];
        cnt++;
      }
      for (int i = 0; i < n; i += 2) {
        ans[i] = arr[cnt];
        cnt++;
      }
      for (int i = 0; i < n; i++) {
        System.out.print(ans[i] + " ");
      }
    }
  }
}
