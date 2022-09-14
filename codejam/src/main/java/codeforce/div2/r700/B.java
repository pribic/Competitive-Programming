package codeforce.div2.r700;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 07/02/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int attack = sc.nextInt();
        long health = sc.nextInt();
        int n = sc.nextInt();

        int[] attackPower = new int[n];
        int maxAttackPower = -1;
        for (int i = 0; i < n; i++) {
          attackPower[i] = sc.nextInt();
          maxAttackPower = Math.max(maxAttackPower, attackPower[i]);
        }
        int[] initialHealth = new int[n];
        for (int i = 0; i < n; i++) {
          initialHealth[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
          health -= ((initialHealth[i] + attack - 1L) / attack) * attackPower[i];
        }
        health += maxAttackPower;
        System.out.println(health <= 0 ? "NO" : "YES");
      }
    }
  }
}
/**
 * 1000 1000 4
 * 200 300 400 500
 * 1000 1000 1000 1000
 * <p>
 * <p>
 * <p>
 * 999 999 1
 * 1000
 * 1000
 * <p>
 * <p>
 * 999 999 1
 * 1000000000
 * 999
 */