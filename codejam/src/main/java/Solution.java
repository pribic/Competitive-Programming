class Solution {
  public static void main(String[] args) {
    new Solution().isHappy(19);
  }
  public boolean isHappy(int n) {
    int index =20;
    while(index-- > 0 ) {
      int sum = 0;
      int nn = n;
      while(nn > 0) {
        sum = sum + (nn%10) * (nn%10);
        nn = nn/10;
      }
      n = sum;
    }
    return n==1;
  }
}