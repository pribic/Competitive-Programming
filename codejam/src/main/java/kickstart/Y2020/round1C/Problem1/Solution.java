package kickstart.Y2020.round1C.Problem1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    for(int i = 1; i <= T; i++) {
      int N = sc.nextInt();
      int K = sc.nextInt();
      int[] arr = new int[N];
      for(int j = 0; j < N; j++)
        arr[j] = sc.nextInt();
      int ans = 0;
      for(int j = 0; j < N; j++) {
        if(arr[j] != K)
          continue;
        if(isKCountDown(arr, K, j)) 
          ans++;
      }
      System.out.println(String.format("Case #%d: %d", i, ans));
    }
    
    sc.close();
  }

  private static boolean isKCountDown(int[] arr, int k, int j) {
    int compare = k;
    for(int i = 0; i < k; i++ ) {
      if( (j + i <= arr.length -1) && (arr[j + i] == compare)) {
        compare--;
      } else 
        return false;
    }
    return true;
  }


}

