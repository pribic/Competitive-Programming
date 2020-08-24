import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class ThreeNeighbors {

	public static void main(String[] args) {
		new ThreeNeighbors().construct(1);
	}
	public String[] construct(int N) {
		char[][] grid = new char[50][50];
		char[] ch = {'.','X','X','.','.'};
		for(int i = 0; i < 50; i++) {
			for(int j=0; j < 50; j++) {
				grid[j][i] = ch[i%5];
			}
		}
		String[] ans = new String[50];
		
		int a = 10000 * 50;

		int row = 0;
		int col = 0;
		int NN = 48;
		col = NN/96 * 5 + NN/48; 
		if(between(N, NN * 0, NN*1)) {
			col = 2;
		} else if(between(N, NN*1+1, NN*2 )) {
			col = 4;
		} else if(between(N, NN*2+1, NN*3 )) {
			col = 7;
		} else if(between(N, NN*3+1, NN*4 )) {
			col = 9;
		} else if(between(N, NN*4+1, NN*5 )) {
			col = 12;
		} else if(between(N, NN*5+1, NN*6 )) {
			col = 14;
		} else if(between(N, NN*6+1, NN*7 )) {
			col = 17;
		} else if(between(N, NN*7+1, NN*8 )) {
			col = 19;
		} else if(between(N, NN*8+1, NN*9 )) {
			col = 22;
		} else if(between(N, NN*9+1, NN*10 )) {
			col = 24;
		} else if(between(N, NN*10+1, NN*11 )) {
			col = 27;
		}

		
		int valid = 0;
		boolean oneTimeValid = false;
		boolean makeAll = false;
		int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
		for(int i = 0; i < grid.length; i++) {
			for(int j=0; j <grid[i].length; j++) {
				if(grid[j][i] == '.') {
					//check valid nei
					int liveN = 0;
					for(int x = 0 ; x < 8; x++) {
						int tx = i + dx[x];
						int ty = j + dy[x];
						
						if(valid(grid, tx, ty) && grid[tx][ty] == 'X') {
							liveN++;
						}
					}
					if(liveN == 3) {
						if(makeAll && !oneTimeValid) {
							grid[j][i] = 'X';
						}
						if(oneTimeValid) {
							oneTimeValid = false;
						}
						valid++;
					}
					if(valid == N) {
						oneTimeValid = true;
						makeAll = true;
						
						System.out.println(i  + " " + j + " valid 500 ");
					}
				}
			}
		}


		int index = 0;
		int rr = 0;

		for( char[] cc : grid) {
			int ccc = 0;
			String s = "";
			for(char c : cc) {
				if(rr == 25 && ccc == 48 ) {
					System.err.print(c + " ");
				}
				else {
					System.out.print(c + " ");
				}

				s = s + c;
				ccc++;
			}
			ans[index] = s;
			index++;
			System.out.println();
			rr++;
		}
		
		
		System.out.println(valid + "  valid ");
		return ans;
	}

	private boolean between(int n, int i, int i1) {
		return n >= i && n <= i1;
	}

	private boolean valid(char[][] grid, int tx, int ty) {
		int r = grid.length;
		int c = grid[0].length;
		return tx >= 0 && tx < r && ty >=0 && ty < c;
	}
}
