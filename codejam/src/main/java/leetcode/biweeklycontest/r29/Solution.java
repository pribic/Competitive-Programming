package leetcode.biweeklycontest.r29;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public static void main(String[] args) {
        new Solution().minNumberOfSemesters(4, new int[][] {{2,1},{3,1},{1,4}}, 2);
        System.out.println();
        new Solution().minNumberOfSemesters(5, new int[][] {{2,1},{3,1},{4,1},{1,5}}, 2);
        System.out.println();
        new Solution().minNumberOfSemesters(4, new int[][] {{1,2}, {4,2}}, 1);
        System.out.println();
        new Solution().minNumberOfSemesters(4, new int[][] {{2,1},{2,4}}, 2);
    }
    public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
        if(dependencies.length == 0 || dependencies[0].length == 0) {
            int cnt = 0;
            int total = 0;
            for(int i = 1; i < 20; i++) {
                total = total + k;
                if(total >= n)
                    return i;
            }
        }
        Set<Integer> inputNodes = new HashSet<>();
        List<Integer> L = new ArrayList<>();
        int[][] dep = new int[n+1][n+1];
        for(int[] arr : dependencies) {
            dep[arr[0]][arr[1]] = 1;
        }
        Set<Integer> S = startNode(dep);
        S.add(-1);
        while(S.size() >= 2) {
            int nn = S.iterator().next();
            S.remove(nn);
            if(nn == -1) {
                S.add(-1);
                L.add(-1);
                continue;
            }
            L.add(nn);
            List<Integer> mm = edgeFrom(nn, dep);
            for(Integer m : mm) {
                dep[nn][m] = 0;
                int cnt = incomingEdge(m, dep);
                if(cnt == 0) {
                    S.add(m);
                }
            }
        }
        int sem = 0;
        int consumedInOneSem = 0;
        L.add(-1);
        for(int i = 0; i < L.size(); i++) {
            if(L.get(i) != -1) {
                consumedInOneSem++;
                if(consumedInOneSem == k) {
                    sem++;
                    consumedInOneSem = 0;
                }
            } else {
                //if some pending courses are there
                if(consumedInOneSem > 0)
                    sem++;
                consumedInOneSem = 0;
            }
        }
        L.stream().forEachOrdered(System.out::println);
        System.out.println("sem = " + sem);
        return sem;
    }

    int incomingEdge(int m, int[][] dep) {
        //int cnt = 0;
        for(int i = 0; i < dep.length; i++) {
            if(dep[i][m] == 1)
                return 1;
        }
        return 0;
    }
    
    List<Integer> edgeFrom(int n, int[][] dep) {
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < dep[0].length; i++) {
            if(dep[n][i] == 1) 
                ans.add(i);
        }
        return ans;
    }
    
    Set<Integer> startNode(int[][] dep) {
        int r = dep.length;
        int c = dep[0].length;
        Set<Integer> ans = new LinkedHashSet<>();
        for(int i = 1; i < c; i++) {
            boolean add = true;
            for(int j = 0; j < r; j++) {
                if(dep[j][i] == 1) {
                    add = false;
                    break;
                }
            }
            if(add)
                ans.add(i);
        }
        return ans;
    }
}