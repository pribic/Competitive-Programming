package codeforce.technocup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class B {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] rows = new int[n][m];
        sc.nextLine();
        for (int i = 0; i < n; i++) {
          String s = sc.nextLine();
          String[] tokens = s.split(" ");
          for (int j = 0; j < m; j++) {
            rows[i][j] = Integer.parseInt(tokens[j]);
          }
        }
        int[] valToCol = new int[n * m + 1];
        int[][] cols = new int[m][n];
        for (int i = 0; i < m; i++) {
          String s = sc.nextLine();
          String[] tokens = s.split(" ");
          for (int j = 0; j < n; j++) {
            cols[i][j] = Integer.parseInt(tokens[j]);
            valToCol[cols[i][j]] = i;
          }
        }
        List<Integer> gridCol = new ArrayList<>();
        for (int i = 0; i < m; i++) {
          int colVal = rows[0][i];
          //find this in cols
          int colIndex = valToCol[colVal];
          gridCol.add(colIndex);
        }

        for (int i = 0; i < n; i++) {
          StringBuilder sb = new StringBuilder();
          for (int j = 0; j < m; j++) {
            sb.append(cols[gridCol.get(j)][i]).append(" ");
          }
          System.out.println(sb);
        }
      }
    }
  }
}
