package codeforce.div2.r685;

import java.util.Scanner;

public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        String s = sc.next();
        int[] prefix = new int[n];
        prefix[0] = s.charAt(0) - '0';
        for (int i = 1; i < n; i++) {
          prefix[i] = prefix[i-1] + (s.charAt(i) - '0');
        }
        while (q-- > 0) {
          int l = sc.nextInt() - 1;
          int r = sc.nextInt() - 1;
          if (s.charAt(r) == '1') {
            int cnt = prefix[n-1] - prefix[r];
            if(cnt > 0) {
              System.out.println("YES");
            } else {
              checkAtLeft(s, prefix, l);
            }
          } else {
            int cnt = prefix[n-1] - prefix[r];
            if(cnt < (n -1 - r)) {
              System.out.println("YES");
            } else {
              checkAtLeft(s, prefix, l);
            }
          } 
        }
      }
    }
  }

  private static void checkAtLeft(String s, int[] prefix, int l) {
    if(l == 0) {
      System.out.println("NO");
    }
    else if (s.charAt(l) == '1') {
      int cnt1 = prefix[l - 1];
      System.out.println(cnt1 > 0 ? "YES" : "NO");
    } else {
      int cnt1 = prefix[l - 1];
      System.out.println(cnt1 < l ? "YES" : "NO");
    }
  }
}










