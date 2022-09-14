package codeforce.div4.r2;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 28/01/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String directions = sc.next();
        int curDir = 0;
        String ans = "ESWN";
        for(char direction : directions.toCharArray()) {
          if(direction == '0') curDir++;
          else curDir--;
          curDir = (curDir + 4) % 4;
        }
        System.out.println(ans.charAt(curDir));
      }
    }
  }
}