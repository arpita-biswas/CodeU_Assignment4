package CodeU_Assignment4;

import java.util.HashSet;
import java.util.Set;

/**
 * Implemented GridPoint class to represent a cell in the grid.
 * It consists of a row and column number specifying location in the grid.
 *
 */
public class GridPoint {
	int r;
	int c;
	public GridPoint(int r, int c){
		this.r = r;
		this.c = c;
	}
	
	public GridPoint(int r, int c, int i){
		this.r = r;
		this.c = c;
	}
	

	/**
	 * move() takes as input "currentPoint" (r,c), dimensions of the grid ("n_row" and "n_column")
	 * and returns a set of valid next grid points to continue search.
	 * 
	 * @param currentPoint
	 * @param n_row
	 * @param n_column
	 * @return
	 */
	public Set<GridPoint> move(int n_row, int n_column){
		int r = this.r;
		int c = this.c;
		Set<GridPoint> valid_directions = new HashSet<GridPoint>();
		if(r != 0 ){//up
			valid_directions.add(new GridPoint(r-1, c));
		}
		if(c != n_column-1){//right
			valid_directions.add(new GridPoint(r, c+1));
		}
		if(r != n_row-1){//down
			valid_directions.add(new GridPoint(r+1, c));
		}
		if(c != 0){//left
			valid_directions.add(new GridPoint(r, c-1));
		}
		return valid_directions;		
	}
	
	@Override
	public boolean equals(Object o){
		GridPoint g = (GridPoint)o;
		if(this.r == g.r && this.c == g.c){
			return true;
		}
		return false;		
	}
	
	@Override
	public String toString(){
		return "("+this.r+","+this.c+")";		
	}
}
