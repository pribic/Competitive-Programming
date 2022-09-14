package codeforce.practice;// { Driver Code Starts
//Initial Template for Java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class GFG {
  public static void main(String args[]) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(read.readLine());
    while (t-- > 0) {
      int N = Integer.parseInt(read.readLine());
      String input_line[] = read.readLine().trim().split("\\s+");
      int A[] = new int[N];
      for (int i = 0; i < N; i++)
        A[i] = Integer.parseInt(input_line[i]);
      Solution ob = new Solution();
      long ans = ob.countTuples(N, A);
      System.out.println(ans);
    }
  }
}// } Driver Code Ends


//User function Template for Java
class Solution {
  long countTuples(int N, int A[]) {
    List<Pair> doubles = new ArrayList<>(N * N);
    for (int i = 0; i < N - 1; i++) {
      for (int j = i + 1; j < N; j++) {
        doubles.add(new Pair(i, j, A[i] + A[j]));
      }
    }
    //System.out.println(doubles);
    long ans = 0;
    PriorityQueue<Pair1> pq = new PriorityQueue<>((Pair1 p1, Pair1 p2) -> {
      if(p1.pt < p2.pt)
        return -1;
      if(p1.pt == p2.pt) 
        return p1.idx - p2.idx;
      return 1;
    } );
    int n = N;
    for (int i = 0; i < doubles.size() - 1; i++) {
      for (int j = i + 1; j < doubles.size(); j++) {
        Pair p1 = doubles.get(i);
        Pair p2 = doubles.get(j);
        if (p1.x < p1.y && p1.y < p2.x && p2.x < p2.y) {
          int curSum = p1.sum + p2.sum;
          if (curSum % 2 == 0 && isSumPresentAsNumber(A, p1, p2, curSum/2)) {
            //System.out.println(p1.x + " " + p1.y + " " + p2.x + " " + p2.y);
            ans++;
          }
        }
      }
    }
    return ans;

  }
  class Pair1 {
    int idx, et, pt;
    Pair1(int idx, int et, int pt) {
      this.idx = idx;
      this.et = et;
      this.pt = pt;
    }

  }
  private boolean isSumPresentAsNumber(int[] A, Pair p1, Pair p2, int curSum) {
    return A[p1.x] == curSum || A[p1.y] == curSum || A[p2.x] == curSum || A[p2.y] == curSum;
  }

  static class Pair {
    int x, y, sum;

    public Pair(int x, int y, int sum) {
      this.x = x;
      this.y = y;
      this.sum = sum;
    }

    public String toString() {
      return x + ":" + y + "=" + sum;
    }
  }
} 
/*
4
5
2 4 6 12 18
6
2 4 6 12 18 20
5
18 4 6 12 2
6
20 18 12 6 4 2

 */
 




 

