package field;

import java.util.ArrayList;

import cell.Cell;

public class Field {

	private int width;
	private int height;
	private Cell[][] field;
	
	public Field(int width, int height) {
		this.width = width;
		this.height = height;
		field = new Cell[height][width];
	}
	
	public int getWidth() {return this.width;}
	public int getHeight() {return this.height;}
	
	public void place(int x, int y, Cell c) {
		field[x][y] = c;
	}
	public Cell get(int x, int y) {
		return field[x][y];
	}
	
	public Cell[] getNeighbours(int x, int y) {
		
		ArrayList<Cell> list = new ArrayList<Cell>();
		
		for(int i=-1; i<2; i++) {
			for(int j=-1; j<2; j++) {
				int row = x+i;
				int col = y+j;
				if(row>-1 && row<height && col>-1 && col<width && !(row==x && col==y)) {
					list.add(field[row][col]);
				}
			}
		}
	
		return list.toArray(new Cell[list.size()]);
	}
	
	
	
	
	
	
	
	
	
	

}
