
public class Matrix {
	
	char[][] grid;
	int x, y;
	
	public Matrix(int x, int y, char[] values) {
		this.x = x;
		this.y = y;
		grid = new char[x][y];
		for(int i = 0; i < values.length; i++) {
			grid[i/y][i%x] = values[i];
		}
	}
	
	public void printMatrx() {
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
	}
	
}