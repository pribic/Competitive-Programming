package Q2021.Reversort;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 26/03/21
 */
public class Solution {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        int ans = getAns(arr);
        System.out.println(ans);
      }
    }
  }

  public static int getAns(int[] arr) {
    int ans = 0;
    for (int i = 0; i < arr.length - 1; i++) {
      int minIndex = findMin(arr, i);
      swap(arr, i, minIndex);
      ans += minIndex - i + 1;
    }
    return ans;
  }

  private static void swap(int[] arr, int i, int j) {
   while (i < j) {
     int t = arr[i];
     arr[i] = arr[j];
     arr[j] = t;
     i++;
     j--;
   }
  }

  private static int findMin(int[] arr, int i) {
    int ans = i;
    int min = arr[i];
    for(int j = i + 1; j < arr.length; j++) {
      if(arr[j] < min) {
        min = arr[j];
        ans = j;
      }
    }
    return ans;
  }
}