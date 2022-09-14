package hackercup.Y2022.qualification;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Scanner;

public class ProblemB2 {

  public static void main(String[] args) throws IOException {
    try (Scanner sc = new Scanner(System.in)) {
      File f = new File("/Users/pdoshi/hackercup/q2022/problemBOne" + System.currentTimeMillis() + ".output");
      f.createNewFile();

      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        FileWriter fw = new FileWriter(f, true);
        int r = sc.nextInt();
        int c = sc.nextInt();
        char[][] grid = new char[r][c];
        for (int i = 0; i < r; i++)
          grid[i] = sc.next().toCharArray();
        System.out.print("Case #" + tt + ": ");
        fw.append("Case #" + tt + ": ");
        if (!tree(grid)) {
          print(fw, "Possible\n");
          //in this case, we print grid as it is
          printGrid(fw, r, c, grid);
        } else {
          //we have at-least one tree
          if (r == 1 || c == 1)
            print(fw, "Impossible\n");
          else {
            //change empty ones to trees
            for (int i = 0; i < r; i++) {
              for (int j = 0; j < c; j++) {
                if (grid[i][j] == '.')
                  grid[i][j] = '^';
              }
            }
            //bfs goes here
            ArrayDeque<int[]> badCells = new ArrayDeque<>();
            //find all initial bad cells
            for (int i = 0; i < r; i++) {
              for (int j = 0; j < c; j++) {
                if (grid[i][j] == '^') {
                  int tree = findTreeNeighbors(r, c, grid, i, j);
                  if (tree < 2) {
                    //this one is bad node, remove its tree
                    badCells.add(new int[]{i, j});
                  }
                }
              }
            }
            while (!badCells.isEmpty()) {
              int[] badCell = badCells.removeFirst();
              grid[badCell[0]][badCell[1]] = '.';
              //check all its 4 neighbors
              for (int dir = 0; dir < 4; dir++) {
                int tx = badCell[0] + dx[dir];
                int ty = badCell[1] + dy[dir];
                if (!insideGrid(r, c, tx, ty) || grid[tx][ty] != '^')
                  continue;
                int nei = findTreeNeighbors(r, c, grid, tx, ty);
                if (nei < 2)
                  badCells.addFirst(new int[] {tx, ty});
              }
            }
            if (valid(grid)) {
              print(fw, "Possible\n");
              printGrid(fw, r, c, grid);
            } else {
              print(fw, "Impossible\n");
            }
          }
        }
        fw.close();
      }
    }

  }

  private static int findTreeNeighbors(int r, int c, char[][] grid, int i, int j) {
    int tree = 0;
    for (int dir = 0; dir < 4; dir++) {
      int tx = i + dx[dir];
      int ty = j + dy[dir];
      if (insideGrid(r, c, tx, ty)) {
        //valid cell
        if (grid[tx][ty] == '^')
          tree++;
      }
    }
    return tree;
  }

  private static boolean insideGrid(int r, int c, int tx, int ty) {
    return tx >= 0 && ty >= 0 && tx < r && ty < c;
  }

  private static void printGrid(FileWriter fw, int r, int c, char[][] grid) throws IOException {
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        print(fw, grid[i][j] + "");
      }
      print(fw, "\n");
    }
  }

  public static void print(FileWriter fw, String s) throws IOException {
    System.out.print(s);
    fw.append(s);
  }

  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, 1, 0, -1};

  //checks whether the given grid is valid or not, means each tree should have at-least 2 neighbouring trees
  private static boolean valid(char[][] grid) {
    int r = grid.length;
    int c = grid[0].length;

    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        if (grid[i][j] != '^')
          continue;
        int tree = findTreeNeighbors(r, c, grid, i, j);
        if (tree < 2)
          return false;
      }
    }
    return true;
  }

  private static void printAllTree(FileWriter fw, int r, int c) throws IOException {
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        System.out.print("^");
        fw.append("^");
      }
      System.out.println();
      fw.append("\n");
    }
  }

  private static boolean tree(char[][] grid) {
    for (char[] row : grid)
      for (char cell : row)
        if (cell == '^')
          return true;
    return false;
  }

}
