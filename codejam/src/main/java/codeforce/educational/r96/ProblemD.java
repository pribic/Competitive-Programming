package codeforce.educational.r96;

import java.util.Scanner;

public class ProblemD {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        String s = sc.next();
        int l = 0;
        int r = n - 1;
        int ans = 0;
        while (l <= r) {
          if(l == r) {
            ans++;
            l++;
            continue;
          }
          //if we delete from left
          int cntLeft = countSameChar(s, l + 1, r);
          //if we delete from right
          int cntRight = countSameChar(s, l, r - 1);
          if(cntLeft <= cntRight) {
            //delete from left
            l++;
          } else {
            //delete from right
            r--;
          }
          l++;
          while (l <= r && s.charAt(l) == s.charAt(l - 1)) l++; 
          ans++;
        }
        System.out.println(ans);
      }
    }
  }
  
  
  
  
  
  private static int countSameChar(String s, int l, int r) {
    int cnt = 0;
    char c = s.charAt(l);
    for (int i = l; i <= r; i++) {
      if (s.charAt(i) == c)
        cnt++;
      else
        break;
    }
    return cnt;
  }
}
