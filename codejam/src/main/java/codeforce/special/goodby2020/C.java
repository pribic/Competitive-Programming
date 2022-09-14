package codeforce.special.goodby2020;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class C {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        String poem = sc.next();
        int len = poem.length();
        boolean[][] dp = new boolean[len+1][len + 1];
        //substring 1 is marked true
        for(int l = 1; l <= len; l++) {
          for (int start = 0; start < len; start++) {
              int end = start + l;
              if(end > len)
                continue;
              //consider substring poem[start, end)
              if (end == start + 1) {
                //substring of len 1
                dp[start][end] = true;
              } else if (end == start + 2) {
                //substring of len 2
                dp[start][end] = poem.charAt(start) == poem.charAt(end - 1);
              } else {
                //substring of len > 2
                if (poem.charAt(start) == poem.charAt(end - 1)) {
                  dp[start][end] = dp[start + 1][end - 1];
                }
              }
          }
        }
        
        for( boolean[] xx : dp) {
          for(boolean x : xx)
            System.out.print(x + " ");
          System.out.println();
        }
        //traverse dp table and build frequency array
        int[] freq = new int[len + 1];
        for (int i = 0; i < len + 1; i++) {
          for (int j = 0; j < len + 1; j++) {
            if (dp[i][j]) {
              //means substring[i,j) is palindrome. increase every k such that i<=k<j by 1
              for(int k = i; k < j; k++)
                freq[k]++;
            }
          }
        }
        //find index with max value and change it to something else - also all the intervals passing via this index - mark them as false too
        


        /**
         * abcdcba
         */
      }
    }
  }
}
