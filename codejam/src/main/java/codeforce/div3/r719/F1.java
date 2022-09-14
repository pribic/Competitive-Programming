package codeforce.div3.r719;

import java.util.Scanner;

/**
 * 0 0 0 1 1 
 * @author pribic (Priyank Doshi)
 * @since 05/05/21
 */
public class F1 {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int aa = sc.nextInt();
        int k = sc.nextInt();
        int l = 0;
        int r = n + 1;
        while (l < r) {
          int mid = l + (r - l) / 2;
          int ans = query(sc, l, mid);
          if (ans < k) {
            k -= ans;
            l = mid + 1;
          } else {
            r = mid;
          }
        }
        printAns(l);
      }
    }
  }

  private static void printAns(int l) {
    System.out.println("! " + l);
    System.out.flush();
  }

  private static int query(Scanner sc, int l, int r) {
    System.out.println("? " + l + " " + r);
    System.out.flush();
    return sc.nextInt();
  }
}