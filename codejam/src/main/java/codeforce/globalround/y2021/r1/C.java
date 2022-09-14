package codeforce.globalround.y2021.r1;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 28/02/21
 */
public class C {

  public static void main(String[] args) {
    C c = (C)find();
    System.out.println(c);
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] trampoline = new int[n];
        for (int i = 0; i < n; i++) {
          trampoline[i] = sc.nextInt();
        }
        boolean allOnes = false;
        int cnt = 0;
        while (!allOnes) {
          boolean anyOne = false;
          for (int i = 0; i < n; i++) {
            if (trampoline[i] > 1) {
              anyOne = true;
              int howMany = 1;
              // . . - . . .
              if(i + trampoline[i]  > n ) howMany = (trampoline[i]  - n + i); 
              simulate(trampoline, i, howMany);
              cnt += howMany;
              break;
            }
          }
          if(!anyOne) allOnes = true;
        }
        System.out.println(cnt);
      }
    }
  }

  private static Object find() {
    return null;
  }

  private static void simulate(int[] trampoline, int i, int howmany) {
    int index = i;
    while (index < trampoline.length) {
      int jump = trampoline[index];
      trampoline[index] = Math.max(trampoline[index] - howmany, 1);
      index += jump;
    }
  }
}