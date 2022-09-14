package hackercup.Y2021.qualification;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ProblemA {
/*
FOXEN -> 
FBHC


 */
  public static void main(String[] args) throws IOException {
    try (Scanner sc = new Scanner(System.in)) {
      File f = new File("/Users/pdoshi/hackercup/q2021/problemA" + System.currentTimeMillis() + ".txt");
      f.createNewFile();

      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        FileWriter fw = new FileWriter(f, true);
        fw.append("Case #" + tt + ": ");
        String str = sc.next();
        int cost = Integer.MAX_VALUE;
        int[] freq = new int[26];
        for (char c : str.toCharArray())
          freq[c - 'A']++;
        for (char ch = 'A'; ch <= 'Z'; ch++) {
          int curcost = 0;
          for (char c : str.toCharArray()) {
            if (c != ch) {
              if (isVovel(ch) == isVovel(c)) {
                curcost += 2;
              } else {
                curcost += 1;
              }
            }
          }
          cost = Math.min(cost, curcost);
        }
        print(cost, fw);
        fw.append("\n");
        fw.close();
      }

    }

  }

  private static boolean isVovel(Character ch) {
    return ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
  }

  private static void print(Object o, FileWriter fw) throws IOException {
    System.out.println(o);
    fw.write(o.toString());
  }
}
