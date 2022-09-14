package codeforce.educational.r98;

import java.util.Scanner;

public class C {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) { 
        String s = sc.next();
        int cnt1 = 0;
        int cnt2 = 0;
        int ans = 0;
        for(char x : s.toCharArray()) {
          switch (x) {
            case '(' :
              cnt1++;
              break;
            case '[' :
              cnt2++;
              break;
            case ')' :
              if(cnt1 > 0) {
                ans++;
                cnt1--;
              }
              break;
            case ']' :
              if(cnt2 > 0) {
                ans++;
                cnt2--;
              }
          }
        }
        System.out.println(ans);
      }
    }
  }
}

//10101010

//01101010 