package codeforce.educational.r105;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 02/03/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] sides = new int[] {sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()};
        boolean ans = false;
        for(int i = 0; i < 16; i++) {
          int[] temp = new int[4];
          for(int j = 0; j < 4; j++) {
            if(( (i >> j) & 1) == 1) {
              switch (j) {
                case 0:
                  temp[0]++;
                  temp[3]++;
                  break;
                case 1:
                  temp[0]++;
                  temp[1]++;
                  break;
                case 2:
                  temp[1]++;
                  temp[2]++;
                  break;
                case 3:
                  temp[2]++;
                  temp[3]++;
                  break;
              }
            }
          }
          boolean isValid = true;
          for(int j = 0; j < 4; j++) {
            int leftOver = sides[j] - temp[j];
            if( leftOver > (n - 2) || leftOver < 0) isValid = false;
          }
          if(isValid) {
            ans = true;
            break;
          }
        }
        System.out.println(ans ? "YES" : "NO");
      }
    }
  }
}