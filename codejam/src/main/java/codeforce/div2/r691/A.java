package codeforce.div2.r691;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class A {

  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)) {
          int t = sc.nextInt();
          while (t-- > 0) {
            int n = sc.nextInt();
            char[] r = sc.next().toCharArray();
            char[] b = sc.next().toCharArray();
            int cnt = 0;
            for(int i = 0; i < n; i++) {
              if(r[i] > b[i]) cnt++;
              else if(r[i] < b[i]) cnt--;
            }
            if(cnt > 0) {
              System.out.println("RED");
            } else if(cnt < 0)
              System.out.println("BLUE");
            else
              System.out.println("EQUAL");
          }
    }
  }
}
/**
 * 132
 * 213
 */
 