package codeforce.div3.r653;

import java.util.Scanner;

public class ProblemB {

  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        int ans = 0;
        while (true) {
          if (n % 2 == 1) {
            n *= 2;
            ans++;
          }
          else if( n % 6 == 2 || n % 6 == 4) {
            System.out.println("-1");
            break;
          } else {
            
          }
        }
      }
    }
  }
}
