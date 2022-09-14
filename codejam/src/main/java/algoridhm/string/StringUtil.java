package algoridhm.string;

public class StringUtil {
  static int[] prefix_function(String s) {
    return prefix_function(s, 0, s.length());
  }

  // s[st..end)
  static int[] prefix_function(String s, int st, int end) {
    //out.println("s = " + s);
    int n = end - st;
    int[] pi = new int[n];
    for (int i = 1; i < n; i++) {
      int j = pi[i - 1];
      while (j > 0 && s.charAt(st + i) != s.charAt(st + j))
        j = pi[j - 1];
      if (s.charAt(st + i) == s.charAt(st + j))
        j++;
      pi[i] = j;
    }
    return pi;
  }

  static int[] z_function(String s) {
    int n = s.length();
    int[] z = new int[n];
    for (int i = 1, l = 0, r = 0; i < n; i++) { //[l,r] represents substring which is prefix also
      if (r >= i)
        z[i] = Math.min(r - i + 1, z[i - l]);
      while (i + z[i] < s.length() && s.charAt(i + z[i]) == s.charAt(z[i]))
        z[i]++;
      if (i + z[i] - 1 > r) {
        l = i;
        r = i + z[i] - 1;
      }
    }
    return z;
  }
  
}