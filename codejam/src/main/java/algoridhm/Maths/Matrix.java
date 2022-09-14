package algoridhm.Maths;

import java.util.Arrays;
import java.util.StringJoiner;

public class Matrix {
  int mod = (int)1e9 + 7;
  
  int n, m;
  long[][] val;

  Matrix(long[][] val) {
    n = val.length;
    m = val[0].length;
    this.val = val;
  }

  Matrix(int n) {
    val = new long[n][n];
    for (int i = 0; i < n; i++) val[i][i] = 1;
    this.n = n;
    this.m = n;
  }

  Matrix mul(Matrix other) {
    int m1 = n;
    int m2 = m;
    int m3 = other.m;
    long[][] ans = new long[m1][m3];
    for (int i = 0; i < m1; i++) {
      for (int k = 0; k < m2; k++)
        for (int j = 0; j < m3; j++) {
          long val = this.val[i][k] * other.val[k][j];
          if (val >= mod)
            val %= mod;
          ans[i][j] += val;
          if (ans[i][j] >= mod)
            ans[i][j] %= mod;
        }
    }
    return new Matrix(ans);
  }

  Matrix fastExpo(long pow) {
    if (pow == 0) {
      return new Matrix(this.n);
    }
    if (pow % 2 == 0) {
      Matrix half = fastExpo(pow / 2);
      return half.mul(half);
    } else {
      return this.mul(fastExpo(pow - 1));
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (long[] row : val)
      sb.append(Arrays.toString(row)).append("\n");
    return new StringJoiner(", ", "[", "]")
      .add("val=" + sb)
      .toString();
  }
}