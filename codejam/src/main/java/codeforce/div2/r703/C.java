package codeforce.div2.r703;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 18/02/21
 */
public class C {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int l;
        int r;
        int smx = getAns(sc, 1, n);
        int smx2 = getAns(sc, 1, smx);
        if (smx == smx2) {
          l = 1;
          r = smx - 1;
        } else {
          l = smx;
          r = n;
        }
        while (r > l + 1) {
          int mid = l + (r - l) / 2;
          int ans = getAns(sc, smx, mid);
          if (smx == ans) {
            r = mid;
          } else {
            l = mid;
          }
        }
        printAns(r);
      }
      System.out.flush();
    }
  }


  private static int getAns(Scanner sc, int l, int r) {
    query(l, r);
    return sc.nextInt();
  }

  private static String key(int a, int b) {
    return a + ":" + b;
  }

  private static void printAns(int ans) {
    System.out.println("! " + ans);
  }

  private static void query(int l, int r) {
    System.out.println("?" + " " + l + " " + r);
  }
}