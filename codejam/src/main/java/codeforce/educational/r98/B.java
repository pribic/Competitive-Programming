package codeforce.educational.r98;

import java.util.Scanner;

public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        long sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
          int val = sc.nextInt();
          arr[i] = val;
          sum += val;
          min = Math.min(min, val);
          max = Math.max(max, val);
        }
        
        long diff = 0;

        for (int i = 0; i < n; i++) {
          diff += max - arr[i];
        }
        //0 3 0
        
        //0 3 3
        
        //total diff
        
        //remove min
        
        diff -= (max - min);

        System.out.println(Math.max(0, Math.min((max - min), diff) * (n-1)));
        
        //2 6 6 6
        
        //5
        
      }
    }
  }
}

//i need 3
//1 4 4 4 


