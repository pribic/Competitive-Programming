package codeforce.educational.r102;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 14/01/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String a = sc.next();
        String b = sc.next();
        int alen = a.length();
        int blen = b.length();
        int lcm = lcm(alen, blen);
        String aLcm = repeat(a, lcm / alen);
        String bLcm = repeat(b, lcm / blen);
        System.out.println(aLcm.equals(bLcm) ? aLcm : -1);
      }
    }
  }

  private static String repeat(String a, int times) {
    StringBuilder sb = new StringBuilder();
    while (times-- > 0) sb.append(a);
    return sb.toString();
  }

  private static int lcm(int alen, int blen) {
    int i = 1;
    while (alen * i++ % blen != 0) ;
    return alen * (i - 1);
  }
}