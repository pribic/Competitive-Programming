package codeforce.globalround.y2021.r1;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 28/02/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] binaryArray = new int[n];
        int[] cnt = new int[2];
        for (int i = 0; i < n; i++) {
          binaryArray[i] = sc.nextInt();
          cnt[binaryArray[i]]++;
        }
        while (q-- > 0) {
          int operation = sc.nextInt();
          switch (operation) {
            case 1 :
              int index = sc.nextInt() - 1;
              int old = binaryArray[index];
              binaryArray[index] = 1 - binaryArray[index];
              cnt[old]--;
              cnt[1 - old]++;
              break;
            case 2:
              int k = sc.nextInt();
              if(k <= cnt[1])
                System.out.println(1);
              else
                System.out.println(0);
              break;
            default:
          }
        }
      }
    }
  }
}