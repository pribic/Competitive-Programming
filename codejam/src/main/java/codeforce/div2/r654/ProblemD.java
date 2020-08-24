package codeforce.div2.r654;

import java.util.Arrays;
import java.util.Scanner;

public class ProblemD {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        long k = sc.nextInt();
        int[][] matrix = new int[n][n];
        int length = n;
        int startx = 0;
        int starty = 0;
        int turn = 0;

        for(int i = 0; i < length && k > 0; i++) {
          matrix[startx++][starty++] = 1;
          k--;
        }
        length--;
        while(length > 0 && k > 0) {
          startx = 0;
          starty = turn + 1;

          for(int i = 0; i < length && k > 0; i++) {
            matrix[startx++][starty++] = 1;
            k--;
          }
          
          startx = turn + 1;
          starty = 0;

          for(int i = 0; i < length && k > 0; i++) {
            matrix[startx++][starty++] = 1;
            k--;
          }
          
          turn++;
          length --;
        }

        for(int[] a : matrix) {
          for (int b : a) {
            System.out.print(b + " ");
          }
          System.out.println();
        }
      }
    }
  }
}
