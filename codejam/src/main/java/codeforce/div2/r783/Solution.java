package codeforce.div2.r783;

import java.util.ArrayList;
import java.util.List;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solution("abcabcabcabc"));
    System.out.println(solution("abccbaabccba"));
  }
    public static int solution(String x) {
    	int len = x.length();
    	List<Integer> factors = factors(len);
    	int max = 1;
    	for(int fact : factors)
    	    if(divide(x, fact))
    	        max = Math.max(max, len / fact);
    	return max;
    }
    
    private static boolean divide(String x, int fact) {
        // want to check if x can be divided in fact part
        int parts = x.length() / fact;
        for(int i = 0; i < fact; i++) {
            for(int p = 1; p < parts; p++) {
                //check if this character is same as 
                // related character in first part
                if(x.charAt(i) != x.charAt(p * fact + i))
                    return false;
            }
        }
        return true;
    }
    
    private static List<Integer> factors(int len) {
        List<Integer> ls = new ArrayList<>();
        for(int i = 1; i <= len; i++) {
            if(len % i == 0)
                ls.add(i);
        }
        return ls;
    }
}