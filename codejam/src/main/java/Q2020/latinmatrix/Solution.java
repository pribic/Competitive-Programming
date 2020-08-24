package Q2020.latinmatrix;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Solution {
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    sol(sc, t);
    /*for(int n = 2; n <=5; n++) {
      for(int k = n; k <= n*n; k++) {
        sol(n , k);
      }
    }*/
    sc.close();
    
  }

  private static void sol(Scanner sc, int t) {
    for(int tt=1;tt<=t;tt++) {
      int n = sc.nextInt();
      int trace = sc.nextInt();
      int[][] matrix = new int[n][n];
      try {
        List<Integer> abc = new ArrayList<>();
        for(int i = 1; i <= n; i++)
          abc.add(i);
        findLatin(matrix,  0, trace);
        System.out.println("Case #" + tt + ": IMPOSSIBLE");
      } catch (Exception e) {
        if(e.getMessage().equals("0")) {
          System.out.println("Case #" + tt + ": POSSIBLE");
          printMatrix(matrix, trace);
        }
        else if(e.getMessage().equals("1"))
        {
          System.out.println("Case #" + tt + ": POSSIBLE");
          mirrorMatrix(matrix);
          printMatrix(matrix, trace);
        } else {
          System.out.println("Case #" + tt + ": IMPOSSIBLE");
        }
      }

    }
  }

  private static void mirrorMatrix(int[][] matrix) {
    for(int i = 0; i < matrix.length; i++) {
      for(int j=0; j < matrix.length/2; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[i][matrix.length -1 - j];
        matrix[i][matrix.length -1 - j] = temp;
      }
    }
  }

  private static void findLatin(int[][] matrix, int position, int trace) {
      if(position == matrix.length * matrix.length)
      {
        int res = verifyTrace(matrix, trace);
        if(res == 0 || res == 1) {
          throw new RuntimeException(res + "");
        }
        return;
      }
      else {
        int curPosX = position/matrix.length;
        int curPosY = position%matrix.length;
        List<Integer> validValues = new ArrayList<>(matrix.length);
        Set<Integer> presentValues = new HashSet<>(); 
        for(int i = 0; i < curPosX; i++) {
          presentValues.add(matrix[i][curPosY]);
        }
        for(int j = 0; j < curPosY; j++) {
          presentValues.add(matrix[curPosX][j]);
        }
        for(int i = 1; i <= matrix.length; i++) {
          if(!presentValues.contains(i))
            validValues.add(i);
        }
        for(Integer  v : validValues) {
          matrix[curPosX][curPosY] = v;
          findLatin(matrix, position + 1, trace);
          matrix[curPosX][curPosY] = 0;
        }
      }
  }


  private static void printMatrix(int[][] matrix, int trace) {
    for(int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  static int verifyTrace(int[][] matrix, int trace) {
    int ans = 0;
    for(int i=0; i < matrix.length; i++) {
      ans += matrix[i][i];
    }
    if(ans == trace)
      return 0;
    ans = 0;
    for(int i=0; i < matrix.length; i++) {
      ans += matrix[i][matrix.length - 1 - i];
    }
    return ans == trace ? 1 : 2;
  }

}

