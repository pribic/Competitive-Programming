package codeforce.div3.r702;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author pribic (Priyank Doshi)
 * @since 16/02/21
 */
public class C {

  public static void main(String[] args) {
    Set<Long> cubes = new TreeSet<>();
    for (long i = 1; i <= 10000; i++) cubes.add(i * i * i);
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long x = sc.nextLong();
        boolean found = false;
        for (long cube : cubes) {
          if (cube > x) break;
          if (cubes.contains(x - cube)) {
            found = true;
            break;
          }
        }
        System.out.println(found ? "YES" : "NO");
      }
    }
  }
}