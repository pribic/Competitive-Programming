package unacadamy.test.dp;

import java.util.Arrays;

public class A {

  public static void main(String[] args) {
    int[] arr = new int[]{-1, 5, 4, 0, -5, 0, 0, -3, -3, -4, 3, -2, 1, -2, -2, -1, -3};
    int[] prefixSum = new int[arr.length + 1];
    int curSum = 0;
    for (int i = 0; i < arr.length; i++) {
      prefixSum[i] = curSum;
      curSum += arr[i];
    }
    prefixSum[arr.length] = curSum; //entire array sum
    int cnt = 0;
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j <= arr.length; j++) {
        //find sum from i to j 
        if (prefixSum[j] == prefixSum[i]) {
          cnt++;
        }
      }
    }
    System.out.println(cnt);
  }
}
