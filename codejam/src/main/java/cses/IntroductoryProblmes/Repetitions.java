package cses.IntroductoryProblmes;

import java.util.Scanner;

public class Repetitions {
  public static void main(String[] args) {

    try (Scanner sc = new Scanner(System.in)) {
      String n = sc.next();
      int cnt = 1;
      int ans = 1;
      for (int i = 0; i < n.length()-1; i++) {
        if(n.charAt(i) == n.charAt(i+1))
          cnt++;
        else {
          ans = Math.max(ans, cnt);
          cnt = 1;
        }
      }
      System.out.println(Math.max(ans, cnt));
    }
  }

}
