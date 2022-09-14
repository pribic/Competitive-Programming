package codeforce.p1000;

import java.util.Scanner;

public class CardGame {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
        char[] s = sc.next().toCharArray();
        char[] c1 = sc.next().toCharArray();
        char[] c2 = sc.next().toCharArray();
        String mapping = "6789TJQKA";
        if(c1[1] == c2[1] && c1[1] == s[0]) {
          System.out.println(mapping.indexOf(c1[0]) > mapping.indexOf(c2[0]) ? "YES" : "NO");
        }
        else if(c1[1] == s[0])
          System.out.println("YES");
        else if(c1[1] == c2[1] && mapping.indexOf(c1[0]) > mapping.indexOf(c2[0]))
          System.out.println("YES");
        else
          System.out.println("NO");
    }
  }
}
