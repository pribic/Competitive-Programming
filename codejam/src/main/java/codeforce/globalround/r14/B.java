package codeforce.globalround.r14;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 02/05/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        if (isValid(n)) {
          System.out.println("YES");
        } else {
          System.out.println("NO");
        }
      }
    }
  }

  private static boolean isValid(int n) {
    if (n == 2 || n == 4)
      return true;
    if (n % 2 == 1 || n < 2)
      return false;
    boolean isValid1 = false;
    isValid1 = isValid1 || (n % 9 == 0 && isValid(n / 9));
    isValid1 = isValid1 || (n % 4 == 0 && isValid(n / 4));
    isValid1 = isValid1 || isValid(n / 2);
    isValid1 = isValid1 || Math.floor(Math.sqrt(n)) == Math.sqrt(n);
    return isValid1;
  }
}