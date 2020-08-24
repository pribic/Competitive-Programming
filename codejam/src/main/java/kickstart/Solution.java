package kickstart;

class Solution {
    public static void main(String[] args) {
       // [[1,0,0,0],[1,1,1,1],[1,0,0,0],[1,0,0,0]]
        System.out.println(new Solution().minSwaps(new int[][]{{1,0,0,0},{1,1,1,1},{1,0,0,0},{1,0,0,0}}));
    }
    
    
    public int minSwaps(int[][] grid) {
        int[] arr = new int[grid.length];
        for(int i = 0; i < grid.length; i++) {
            int cnt = 0;
            for(int j = grid[i].length -1 ; j >= 0; j--) {
                if(grid[i][j] == 0)
                    cnt++;
                else {
                    break;
                }
            } 
        }
        int ans = 0;
        for(int i = 0; i < arr.length -1; i++) {
            //find in arr where value is i
            int found = -1;
            for(int j = 0; j < arr.length; j++) {
                if(arr[j] == i) {
                    found = j;
                    break;
                }
            }
            if(found == -1) {
                return -1;
            }
            
            for(int j = found; j < arr.length -1 - i ; j++) {
                //swap j and j+1
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
                ans++;
            }
        }
        return ans;
    }
}