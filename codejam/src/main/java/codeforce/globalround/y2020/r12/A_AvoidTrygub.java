package codeforce.globalround.y2020.r12;

import java.util.Scanner;

public class A_AvoidTrygub {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n = sc.nextInt();
        String s = sc.next();
        char[] arr = new char[26];
        for (char x : s.toCharArray())
          arr[x - 'a']++;
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
          while (arr[i]-- != 0)
            ans.append((char) ('a' + i));
        }
        System.out.println(ans);
      }
    }
  }
}
