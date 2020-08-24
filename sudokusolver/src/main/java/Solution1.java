import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution1 {

  private static final long Nine_Ones = (long) (Math.pow(2, 9) - 1);

  private static final List<Long> One_to_Nine = Arrays.asList(
    (long) (Math.pow(2, 0)),
    (long) (Math.pow(2, 1)),
    (long) (Math.pow(2, 2)),
    (long) (Math.pow(2, 3)),
    (long) (Math.pow(2, 4)),
    (long) (Math.pow(2, 5)),
    (long) (Math.pow(2, 6)),
    (long) (Math.pow(2, 7)),
    (long) (Math.pow(2, 8)),
    (long) (Math.pow(2, 9))
  );

  public static void main(String[] args) throws InterruptedException {
    long[][] sudoku = {
      {1, 0, 0, 0, 0, 2, 4, 0, 0},
      {0, 2, 0, 0, 0, 5, 0, 9, 0},
      {0, 0, 4, 0, 0, 9, 0, 0, 3},
      {3, 0, 6, 0, 0, 0, 0, 0, 1},
      {0, 9, 0, 0, 0, 0, 0, 0, 0},
      {5, 0, 0, 2, 0, 0, 0, 3, 7},
      {0, 0, 0, 6, 0, 0, 0, 0, 8},
      {0, 3, 0, 0, 0, 0, 0, 7, 2},
      {0, 0, 0, 0, 5, 3, 6, 0, 0}
    };

    long[][] sudoku1 = {
      {3, 6, 0, 0, 0, 0, 2, 4, 1},
      {8, 0, 0, 4, 0, 3, 0, 5, 0},
      {5, 0, 7, 0, 1, 0, 0, 0, 9},

      {1, 0, 4, 6, 0, 0, 8, 0, 0},
      {0, 0, 8, 0, 4, 0, 0, 6, 0},
      {0, 2, 3, 0, 0, 8, 0, 0, 4},

      {0, 9, 0, 0, 3, 0, 4, 0, 5},
      {7, 8, 0, 5, 2, 4, 0, 0, 0},
      {0, 3, 5, 1, 0, 0, 0, 2, 0}
    };

    long[][] sudoku_empty = {
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},

      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},

      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    long[][] sudoku2 = {
      {0, 6, 0, 0, 7, 0, 0, 1, 0},
      {0, 0, 8, 0, 0, 1, 0, 0, 3},
      {2, 7, 1, 3, 8, 9, 0, 5, 6},

      {8, 1, 0, 9, 2, 7, 0, 0, 0},
      {0, 0, 0, 0, 0, 6, 0, 9, 0},
      {7, 4, 0, 0, 0, 0, 6, 8, 0},

      {9, 2, 3, 7, 0, 8, 0, 0, 0},
      {0, 0, 0, 4, 0, 3, 8, 0, 9},
      {0, 8, 0, 0, 0, 0, 0, 0, 1}
    };

    long[][] sudoku3 = {
      {0, 2, 0, 0, 0, 0, 0, 0, 6},
      {0, 0, 3, 5, 0, 0, 0, 1, 0},
      {0, 0, 0, 0, 1, 3, 0, 0, 4},

      {0, 1, 0, 0, 9, 7, 8, 0, 0},
      {0, 0, 5, 0, 0, 0, 6, 0, 0},
      {0, 9, 0, 0, 4, 0, 0, 0, 0},

      {0, 0, 1, 0, 0, 0, 4, 0, 3},
      {5, 0, 0, 4, 3, 6, 2, 7, 0},
      {0, 0, 0, 7, 0, 0, 0, 0, 9}
    };

    sudoku = sudoku3;

    solve(sudoku);
    printSudoku(sudoku);
  }

  private static void printSudoku(long[][] sudoku) {
    for (int i = 0; i < sudoku.length; i++) {
      for (int j = 0; j < sudoku.length; j++) {
        System.out.print(String.format("%5s ", findSetBits(sudoku[i][j])));
      }
        System.out.println();
      System.out.println();
    }
  }

  private static void solve(long[][] sudoku) {
    int n = sudoku.length;
    fillInitialValue(sudoku);
    int confirmedCell = 0;
    do{
      confirmedCell = 0;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if(notConfirmedCell(sudoku[i][j])) {
            traverseRow(sudoku, i, j);
            traverseCol(sudoku, i, j);
            traverseWithinBox(sudoku, i, j);
          } else {
            confirmedCell++;
          }
        }
      }
      //box by box.
      for(int box = 0; box < n; box++) {
        Map<Character, List<Position>> numberToCellMap = new HashMap<>();
        Map<Character, List<Integer>> numberToRowMap = new HashMap<>();
        Map<Character, List<Integer>> numberToColMap = new HashMap<>();
        // box - startX -> 0 - 0, 1 - 0, 2 - 0, 3-3,4-3,5-3,6-6,7-6,8-6
        // box - startY -> 0-0,1-3,2-6,3-0,4-4,5-6,6-0,7-3,8-6
        int startX = (box / 3) * 3;
        int startY = (box % 3) * 3;
        for(int xx = startX; xx < startX + 3; xx++) {
          for(int yy=startY; yy < startY + 3; yy++) {
            long valueToInspect = sudoku[xx][yy];
            if(notConfirmedCell(valueToInspect)) {
              String setBits = findSetBits(valueToInspect);
              for(char c : setBits.toCharArray()) {
                putInMap(numberToCellMap, c, new Position(xx, yy));
                putInMap(numberToRowMap, c, xx);
                putInMap(numberToColMap, c, yy);
              }
            } else {
              String setBits = findSetBits(valueToInspect);
              for(char c : setBits.toCharArray()) {
                //put confirmed value twice so it won't get picked up
                putInMap(numberToCellMap, c, new Position(xx, yy));
                putInMap(numberToCellMap, c, new Position(xx, yy));
              }
            }
          }
        }
        //check if a number can be present in single cell.
        for (char c : numberToCellMap.keySet()) {
          if (numberToCellMap.get(c).size() == 1) {
             //put it as confirmed number since it can't be anywhere else
            assignFinalValue(sudoku, numberToCellMap.get(c).get(0).x, numberToCellMap.get(c).get(0).y, generateNumberWithSetBitAt(c));
          }
        }
        //
        for(char c : numberToRowMap.keySet()) {
          if(numberToRowMap.get(c).size() == 1) {
            
          }
        }
        for(char c : numberToColMap.keySet()) {
          if(numberToColMap.get(c).size() == 1 && notConfirmedCell(sudoku[numberToCellMap.get(c).get(0).x][numberToCellMap.get(c).get(0).y])) {
            traverseCol(sudoku, box, c, numberToCellMap.get(c).get(0).y);
          }
        }
      }
      printSudoku(sudoku);
      System.out.println("=====");
    }while (confirmedCell < n * n );
  }

  private static void assignFinalValue(long[][] sudoku, int x, int y, long finalValue) {
    sudoku[x][y] = finalValue;
  }

  private static <T> void  putInMap(Map<Character, List<T>> numberToCellMap, char c, T element) {
    if(numberToCellMap.containsKey(c)) {
      List<T> ll = numberToCellMap.get(c);
      ll.add(element);
      numberToCellMap.put(c, ll);
    } else {
      numberToCellMap.put(c, new ArrayList<>(Arrays.<T>asList(element)));
    }
  }

  private static String findSetBits(long valueToInspect) {
    String s = "";
    int index = 1;
    while (valueToInspect > 0) {
      if(valueToInspect%2 == 1)
        s = s + index;
      index++;
      valueToInspect /= 2;
    }
    return s;
  }

  private static void traverseWithinBox(long[][] sudoku, int i, int j) {
    int startX = i - i % 3;
    int startY = j - j % 3;
    for (int k = startX; k < startX + 3; k++) {
      for (int l = startY; l < startY + 3; l++) {
        if (i != k || j != l)
          sudoku[i][j] = removeFromOriginal(sudoku[i][j], sudoku[k][l]);
      }
    }
  }

  private static void traverseCol(long[][] sudoku, int i, int j) {
    for (int row = 0; row < sudoku.length; row++) {
      if (row != i) {
        sudoku[i][j] = removeFromOriginal(sudoku[i][j], sudoku[row][j]);
      }
    }
  }

  private static void traverseCol(long[][] sudoku, int box, char c, int column) {
    int startX = (box / 3) * 3;
    for (int row = 0; row < sudoku.length; row++) {
      if (row < startX || row >= startX + 3) { // this will skip current box
        if(notConfirmedCell(sudoku[row][column])) {
          assignFinalValue(sudoku, row, column, removeFromOriginal(sudoku[row][column], generateNumberWithSetBitAt(c)));
        }
      }
    }
  }


  private static void traverseRow(long[][] sudoku, int i, int j) {
    for (int col = 0; col < sudoku.length; col++) {
      if (col != j) {
        sudoku[i][j] = removeFromOriginal(sudoku[i][j], sudoku[i][col]);
      }
    }
  }

  /**
   * inspects target cell and if it is confirmed then removes it from original cell.
   * if target is not confirmed, leave cellValue untouched
   * @param cellValue
   * @param valueToInspect
   * @return
   */
  private static long removeFromOriginal(long cellValue, long valueToInspect) {
    if (confirmedCell(valueToInspect)) {
      return removeConfirmedValue(cellValue, valueToInspect);
    }
    return cellValue;
  }

  /**
   * confirmedValue -> 32 -> 100000
   * cell value -> 1100100101 
   * We want to remove 1 from cell value. So we are resetting a bit.
   * @param l
   * @param confirmedValue
   * @return
   */
  private static long removeConfirmedValue(long l, long confirmedValue) {
    return l & ~confirmedValue;
  }

  private static boolean confirmedCell(long cellValue) {
    return One_to_Nine.contains(cellValue);
  }

  private static boolean notConfirmedCell(long cellValue) {
    return !confirmedCell(cellValue);
  }

  private static void fillInitialValue(long[][] sudoku) {
    int n = sudoku.length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        long valueToInspect = sudoku[i][j];
        if(valueToInspect == 0)
          sudoku[i][j] = Nine_Ones;
         else 
          sudoku[i][j] = generateNumberWithSetBitAt(valueToInspect);  
      }
    }
  }

  /**
   * intput - output
   * 1 -> 1
   * 2 -> 10 -> 2
   * 3 -> 100 -> 4
   * 4 -> 1000 -> 8
   * 5 -> 10000 -> 16
   * @param valueToInspect
   * @return
   */
  private static long generateNumberWithSetBitAt(long valueToInspect) {
    return (long) Math.pow(2, valueToInspect - 1);
  }

  private static long generateNumberWithSetBitAt(char valueToInspect) {
    return (long) Math.pow(2, valueToInspect - '1');
  }

  private static class Position {
    int x;
    int y;
    Position(int x, int y){
      this.x = x;
      this.y = y;
    }
  }
}
