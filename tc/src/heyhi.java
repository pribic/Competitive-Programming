public class heyhi {

  static int n;
  public static void main(String[] args) throws InterruptedException {
    int[][] board4 = {
      { 0, 0, 2, 0},
      { 0, 0, 0, 1},
      { 1, 0, 0, 1},
      { 0, 0, 0, 0}
    };
    int[][] board6Zero = {
      { 0, 0, 0, 0, 0, 0},
      { 0, 0, 0, 0, 0, 0},
      { 0, 0, 0, 0, 0, 0},
      { 0, 0, 0, 0, 0, 0},
      { 0, 0, 0, 0, 0, 0},
      { 0, 0, 0, 0, 0, 0}
    };
    int[][] board6 = {
      { 0, 0, 0, 0, 0, 2},
      { 0, 1, 1, 0, 0, 0},
      { 0, 0, 2, 0, 2, 2},
      { 0, 0, 0, 1, 0, 0},
      { 0, 0, 0, 0, 0, 0},
      { 0, 0, 0, 0, 0, 0}
    };
    int[][] board8Zero = {
      { 0, 0, 0, 0, 0, 0, 0, 0},
      { 0, 0, 0, 0, 0, 0, 0, 0},
      { 0, 0, 0, 0, 0, 0, 0, 0},
      { 0, 0, 0, 0, 0, 0, 0, 0},
      { 0, 0, 0, 0, 0, 0, 0, 0},
      { 0, 0, 0, 0, 0, 0, 0, 0},
      { 0, 0, 0, 0, 0, 0, 0, 0},
      { 0, 0, 0, 0, 0, 0, 0, 0}
    };
    int[][] board8 = {
      { 2, 0, 0, 2, 0, 0, 0, 0},
      { 0, 1, 0, 0, 0, 0, 2, 0},
      { 0, 0, 0, 0, 0, 0, 2, 2},
      { 0, 0, 0, 0, 0, 1, 0, 0},
      { 2, 0, 2, 0, 0, 0, 1, 0},
      { 0, 0, 0, 0, 0, 0, 0, 1},
      { 1, 1, 0, 0, 1, 2, 0, 0},
      { 0, 1, 0, 0, 0, 0, 1, 0}
    };
    
    int[][] board = board8;
    
    n = board.length;
    solve(board);
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++){
        if(board[i][j] == 1) 
          System.err.print(board[i][j] + " ");
        else
          System.out.print(board[i][j] + " ");
        Thread.sleep(10);
      }
      Thread.sleep(100);
      System.out.println();
    }
  }

  private static void solve(int[][] board) {
    int changed = 0;
    do {
      changed = 0;
      changed += sureshotRow(board);
      changed += sureshotCol(board);
      changed += checkRow2(board);
      changed += checkCol2(board);
      changed += checkIdenticalRow(board);
      changed += checkIdenticalCol(board);
    } while (changed > 0);
  }

  private static int sureshotCol(int[][] board) {
    int changed = 0;
    do {
      changed = 0;
      for(int col = 0; col< n; col++) {
        int[] freq = { 0, 0, 0};
        for(int row = 0; row < n; row++){
          freq[board[row][col]]++;
        }
        if(freq[1] == n/2) {
          //blue is full. add red for rest of places.
          for(int row = 0; row < n; row++){
            if(board[row][col] == 0) {
              board[row][col] = 2;
              changed++;
            }
          }
        }
        if(freq[2] == n/2) {
          //red is full. add blue for rest of places.
          for(int row = 0; row < n; row++){
            if(board[row][col] == 0) {
              board[row][col] = 1;
              changed++;
            }
          }
        }
      }
    } while (changed > 0);

    return changed;
  }

  private static int sureshotRow(int[][] board) {
    int changed = 0;
    int totalChanged = 0;
    do {
      totalChanged += changed;
      changed = 0;
      for(int i = 0; i< n; i++) {
        int[] freq = { 0, 0, 0};
        for(int j = 0; j < n; j++){
          freq[board[i][j]]++;
        }
        if(freq[1] == n/2) {
          //red is full. add blue for rest of places.
          for(int j = 0; j < n; j++){
            if(board[i][j] == 0) {
              board[i][j] = 2;
              changed++;
            }
          }
        }
        if(freq[2] == n/2) {
          //blue is full. add red for rest of places.
          for(int j = 0; j < n; j++){
            if(board[i][j] == 0) {
              board[i][j] = 1;
              changed++;
            }
          }
        }
      }
    } while (changed > 0);
    
    return totalChanged;
  }

  private static int checkIdenticalRow(int[][] board) {
    int changed = 0;
    for(int targetRow = 0 ; targetRow < n; targetRow++) {
      for( int sourceRow = 0; sourceRow < n; sourceRow++) {
        if( targetRow == sourceRow)
          continue;
        boolean only2left1 = checkOnlyLeftRow(board, targetRow, 2);
        boolean only0left2 = checkOnlyLeftRow(board, sourceRow, 0);
        if(only2left1 && only0left2) {
          //has to be same column except empty cell
          int cnt = 0;
          for (int x = 0; x < n; x++) {
            if (board[targetRow][x] != 0) {
              cnt = cnt + (board[targetRow][x] == board[sourceRow][x] ? 1 : 0);
            }
          }
          if (cnt == (n - 2)) {
            //only 2 cells are different others are same. So we can use the strategy.
            for (int x = 0; x < n; x++) {
              //find empty slot
              if (board[targetRow][x] == 0) {
                board[targetRow][x] = 3 - board[sourceRow][x];
                changed++;
              }
            }
          }
        }

      }
    }
    return changed;
  }


  private static int checkIdenticalCol(int[][] board) {
    int changed = 0;
    for(int targetCol = 0 ; targetCol < n; targetCol++) {
      for( int sourceColumn = 0; sourceColumn < n; sourceColumn++) {
        if(targetCol==sourceColumn)
          continue;
        boolean only2left1 = checkOnlyLeftCol(board, targetCol, 2);
        boolean only0left2 = checkOnlyLeftCol(board, sourceColumn, 0);
        if(only2left1 && only0left2) {
          //has to be same column except empty cell
          int cnt = 0;
          for (int x = 0; x < n; x++) {
            if (board[x][targetCol] != 0) {
              cnt = cnt + (board[x][targetCol] == board[x][sourceColumn] ? 1 : 0);
            }
          }
          if (cnt == (n - 2)) {
            //only 2 cells are different others are same. So we can use the strategy.
            for (int x = 0; x < n; x++) {
              //find empty slot
              if (board[x][targetCol] == 0) {
                board[x][targetCol] = 3 - board[x][sourceColumn];
                changed++;
              }
            }
          }
        }
      }
    }
    return changed;
  }

  private static boolean checkOnlyLeftCol(int[][] board, int i, int howmanyleft) {
    int cnt = 0;
    for(int x = 0; x < n; x++)
      cnt = cnt + (board[x][i] == 0 ? 1 : 0);
    return cnt == howmanyleft;
  }


  private static boolean checkOnlyLeftRow(int[][] board, int i, int howmanyleft) {
    int cnt = 0;
    for(int x = 0; x < n; x++)
      cnt = cnt + (board[i][x] == 0 ? 1 : 0);
    return cnt == howmanyleft;
  }


  private static int checkCol2(int[][] board) {
    int changed = 0;
    int totalChanged = 0;
    do {
      totalChanged += changed;
      changed = 0;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (board[i][j] == 0) {
            //do for unsolved cell only
            if (validIndex(i-1, i-2) && notzero(board[i - 1][j]) && board[i - 1][j] == board[i - 2][j]) {
              board[i][j] = 3 - board[i - 1][j];
              changed++;
            }
            if (validIndex(i+1, i + 2) && notzero(board[i + 1][j]) && board[i + 1][j] == board[i + 2][j]) {
              board[i][j] = 3 - board[i + 1][j];
              changed++;
            }
            if (validIndex(i-1, i+1) && notzero(board[i - 1][j]) && board[i - 1][j] == board[i + 1][j]) {
              board[i][j] = 3 - board[i - 1][j];
              changed++;
            }
          }
        }
      }
    } while (changed > 0);
    return totalChanged;
  }

  private static int checkRow2(int[][] board) {
    int changed = 0;
    int totalChanged = 0;
    do {
      totalChanged += changed;
      changed = 0;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (board[i][j] == 0) {
            //do for unsolved cell only
            if (validIndex(j-1, j-2) && notzero(board[i][j-1]) && board[i][j - 1] == board[i][j - 2]) {
              board[i][j] = 3 - board[i][j - 1];
              changed++;
            }
            if (validIndex(j+1, j + 2) && notzero(board[i][j+1]) && board[i][j + 1] == board[i][j + 2]) {
              board[i][j] = 3 - board[i][j + 1];
              changed++;
            }
            if (validIndex(j-1, j+1) && notzero(board[i][j-1]) && board[i][j - 1] == board[i][j + 1]) {
              board[i][j] = 3 - board[i][j - 1];
              changed++;
            }
          }
        }
      }
    } while (changed > 0);
    return totalChanged;
  }

  private static boolean notzero(int i) {
    return i != 0;
  }

  static boolean validIndex(int index){
    return index < n && index >= 0;
  }
  
  static boolean validIndex(int index1, int index2) {
    return validIndex(index1) && validIndex(index2);
  }
  
}
