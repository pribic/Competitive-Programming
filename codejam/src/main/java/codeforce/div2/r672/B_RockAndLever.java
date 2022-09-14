package codeforce.div2.r672;

import java.util.Scanner;

public class B_RockAndLever {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        int[] bits = new int[50]; // won't be higher than this for sure 

        for (int i = 0; i < n; i++) {
          int curNum = arr[i];
          //find position of most significant bit
          int index = -1;
          while (curNum > 0) {
            curNum /= 2;
            index++;
          }
          bits[index]++;
        }
        
        long ans = 0;
        for (int i = 0; i < bits.length; i++) {
          if(bits[i] >= 2) {
            //find nCr
            ans += nCr(bits[i], 2);
          }
        }
        System.out.println(ans);
      }
    }
  }//1 1 1 1 1 1 1 1 1 1
  private static long nCr(int n, int r) {
    long ans = 1;
    for (int i = (n - r) + 1; i <= n; i++) {
      ans *= i;
    }
    for (int i = 1; i <= r; i++) {
      ans /= i;
    }
    return ans;
  }
}
/*

//1 4 3 7 10
0 0 -> 0, 0
1 1 -> 1, 0
0 1 -> 0, 1
1 0 -> 0, 1


  0100 & 0111 -> 0100, 0011
  
  
0001 - 0
0100 - 2
0011 - 1
0111 - 2
1010 - 3

  6 2 5 3

110 -> 2
010 -> 1
101 -> 2
011 -> 1
100 -> 2





1 -> 2
2 -> 3


 */
