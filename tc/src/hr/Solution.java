package hr;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the climbingLeaderboard function below.
    static int[] climbingLeaderboard(int[] scores, int[] alice) {
        int[] rank = new int[scores[0] + 1];
        Arrays.fill(rank, 0);
        int curRank = 1;
        for(int i=0; i< scores.length - 1; i++) {
            rank[scores[i]] = curRank;
            if(scores[i] != scores[i+1])
                curRank++; 
        }
        int[] result = new int[alice.length];
        rank[scores[scores.length-1]] = curRank;
        for(int i=0; i < alice.length; i++) {
            int aliceScore = alice[i];// 50
            //binary search.
        //    100 90 90 80 75 60
        //    50 65 77 90 102
            if(scores[0] <= aliceScore ) {
                result[i] = 1;
                continue;
             }
            if(rank[aliceScore] != 0)
                result[i] = rank[aliceScore];
            int l = 0; //6
            int r = scores.length - 1; //5 
            while (l <= r) {
                int mid = l + (r - l) / 2; //5
                //scores[mid] = 60
                if(scores[mid] == aliceScore) {
                    result[i] = rank[aliceScore];
                    break;
                }
                if(scores[mid] < aliceScore){
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            if(result[i] == 0) {
                //this shows we haven't found matching score yet.
                // so we will return rank as -1 of left
                if( l <= 0 || r <= 0) {
                    result[i] = 1;
                }
                if( l >= scores.length || r >= scores.length) {
                    result[i] = rank[scores[scores.length - 1]] + 1;
                }
                else if( l > r) {
                    result[i] = rank[scores[l]];
                }
            }
        }
        return result;
    }


    public static void main(String[] args) throws IOException {


        int[] scores = {100, 100, 50, 40, 40, 20, 10};


        int[] alice = {  5, 25, 50, 120};

        int[] result = climbingLeaderboard(scores, alice);
        for(int i : result)
            System.out.println(i);

    }
}
