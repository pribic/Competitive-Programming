package hr;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution1 {

    private static int[][] ms =  {
        {8, 3, 4},
        {1, 5, 9},
        {6, 7, 2}
    };
    /**
    rotates matrics 90 degree clockwise
    */
    private static int[][] rotate(int[][] s) {
        int[][] sr = new int[3][3];
        for(int i=0;i<3;i++){
            sr[0][2 - i] = s[i][0];
            sr[1][2 - i] = s[i][1];
            sr[2][2 - i] = s[i][2];
        }
        return sr;
    }
    // Complete the formingMagicSquare function below.
    static int formingMagicSquare(int[][] s) {
        int diff = 200;
     
        for(int k = 0; k < 4; k++) {
            int curDiff = 0;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    curDiff += (int)Math.abs(ms[i][j] - s[i][j]);
                }
            }
            diff = Math.min(diff, curDiff);
            ms = rotate(ms);
        }
        return diff;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int[][] s = {
          {    4 ,8 ,2},
          {4, 5, 7},
          {6 ,1 ,6}
        };
        int result = formingMagicSquare(s);
        System.out.println(result);

        scanner.close();
    }
}
