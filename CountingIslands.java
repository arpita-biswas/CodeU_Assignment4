/**
 * Assignment 4: 
 * Given a 2-dimensional (boolean) map of tiles. 
 * Each tile is either land (true) or water (false).
 * Write a function that counts the number of islands.
 * Two tiles belong to the same island if
 * 		 they are both land, and
 * 		 are adjacent horizontally or vertically, but not diagonally.
 * 
 * The function takes as input the number of rows, number of columns, and a 2-dimensional
array of booleans and returns the number of islands.
 */

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class CountingIslands {
	
	/** count() takes as input the number of rows (n_row), number of columns (n_column)
	 *  and boolean 2D "grid" and returns the number of islands in the grid. 
	 *  If the grid is null or empty, then, return 0.
	 *  If the n_row and n_column is not same as the grid dimensions, then return -1.
	 *  Else, return the number of islands.
	 * 
	 * @param n_row
	 * @param n_column
	 * @param grid
	 * @return
	 */
		
	public static int count(int n_row, int n_column, boolean[][] grid){

		//if the grid is null or empty, then no islands can be formed, return 0
		if(grid == null || grid.length == 0){
			return 0;
		}	
		
		//if n_row and n_column does not match the actual dimension of the grid, return -1
		if(grid.length != n_row){
			return -1;
		}
		for(int i=0; i<grid.length; i++){
			if(grid[i].length != n_column){
				return -1;
			}
		}
				
		HashSet<Integer> visited = new HashSet<Integer>();
		int counter = 0;
		for(int i=0; i<n_row; i++){
			for(int j=0; j<n_column; j++){
				if(countHelper(n_row, n_column, grid, new GridPoint(i,j), visited)){
					counter++;					
				}
			}
		}			
		return counter;			
	}
	
	/** countHelper() takes as input dimensions of the grid "n_row" and "n_column", 
	 *  the "grid", a current grid point "currentPoint" and a list of grid points already visited.
	 *  It returns true when a new island is found, and returns false otherwise.
	 * 
	 * @param n_row
	 * @param n_column
	 * @param grid
	 * @param currentPoint
	 * @param visited
	 * @return
	 */
	private static boolean countHelper(int n_row, int n_column, boolean [][] grid, GridPoint currentPoint, HashSet<Integer> visited){
		
		//if the current point is represents water or is already visited, then return false
		if(grid[currentPoint.r][currentPoint.c] == false || visited.contains((currentPoint.r*n_column+currentPoint.c))){
			return false;
		}		
		
		//if the current point is neither water nor visited,
		//then, add this point to the visited list and
		//      go to the neighboring points to flag the connecting land points as visited, and
		//      return true to indicate the group of points is an island.
		visited.add(( currentPoint.r*n_column + currentPoint.c )); //store the row-major index of the current point
		Set<GridPoint> moves = move(currentPoint, n_row, n_column);
		for(GridPoint gp: moves){
			countHelper(n_row, n_column, grid, gp, visited);
		}		
		return true;
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
	private static Set<GridPoint> move(GridPoint currentPoint, int n_row, int n_column){
		int r = currentPoint.r;
		int c = currentPoint.c;
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
	
	public static void main (String args[]){
		testNullGrid();
		testEmptyGrid();
		testInvalidRowColumnDimension();
		testOneCellGrid();
		testNoIsland();
		testOneBigLand();
		testIslandsDisconnectedDiagonally();
		testConnectedCount();	
		testConcentricRingsOfTwoIslands();
		testExample();
	}
	
	@Test
	public static void testNullGrid() {
		boolean [][] grid = null;
		int n_row = 0;
		int n_column = 0;
	    assertEquals(0, CountingIslands.count(n_row, n_column, grid));	    
	}
	
	@Test
	public static void testEmptyGrid() {
		boolean [][] grid = {};
		int n_row = 0;
		int n_column = 0;
	    assertEquals(0, CountingIslands.count(n_row, n_column, grid));	    
	}
	
	@Test
	public static void testInvalidRowColumnDimension() {
		boolean [][] grid = {{true, false, true}, {false, false, true}};
		int n_row = -1;
		int n_column = 2;
	    assertEquals(-1, CountingIslands.count(n_row, n_column, grid));	    
	    
	    n_row = 4;
		n_column = 2;
	    assertEquals(-1, CountingIslands.count(n_row, n_column, grid));	 
	    
	    n_row = 1;
		n_column = 2;
	    assertEquals(-1, CountingIslands.count(n_row, n_column, grid));
	    
	    n_row = 3;
		n_column = -1;
	    assertEquals(-1, CountingIslands.count(n_row, n_column, grid));	  
	    
	    n_row = 3;
		n_column = 4;
	    assertEquals(-1, CountingIslands.count(n_row, n_column, grid));	 
	    
	    n_row = 3;
		n_column = 1;
	    assertEquals(-1, CountingIslands.count(n_row, n_column, grid));
	}
	
	@Test
	public static void testOneCellGrid() {
		
		int n_row = 1;
		int n_column = 1;
		
		boolean [][] grid = {{true}};
	    assertEquals(1, CountingIslands.count(n_row, n_column, grid));
	    
	    boolean [][] grid2 = {{false}};
	    assertEquals(0, CountingIslands.count(n_row, n_column, grid2));
	}
	
	@Test
	public static void testNoIsland() {		
		int n_row = 5;
		int n_column = 5;
		
		boolean [][] grid = new boolean[5][5];
		for(int i=0; i<n_row; i++){
			for(int j=0; j<n_column; j++){
				grid[i][j] = false;
			}
		}
	    assertEquals(0, CountingIslands.count(n_row, n_column, grid));
	}
	
	@Test
	public static void testOneBigLand() {		
		int n_row = 5;
		int n_column = 5;
		
		boolean [][] grid = new boolean[5][5];
		for(int i=0; i<n_row; i++){
			for(int j=0; j<n_column; j++){
				grid[i][j] = true;
			}
		}
	    assertEquals(1, CountingIslands.count(n_row, n_column, grid));
	}
	
	@Test
	public static void testIslandsDisconnectedDiagonally() {
		
		int n_row = 2;
		int n_column = 2;
		
		boolean [][] grid = {{false, true}, {true, false}};
	    assertEquals(2, CountingIslands.count(n_row, n_column, grid));
	}
	
	@Test
	public static void testConnectedCount() {
		
		int n_row = 2;
		int n_column = 2;
		
		boolean [][] grid = {{false, true}, {true, true}};
	    assertEquals(1, CountingIslands.count(n_row, n_column, grid));
	}
		
	@Test
	public static void testConcentricRingsOfTwoIslands() {

		int n_row = 7;
		int n_column = 9;
		
		boolean [][]grid = 
			  { {false, false, true,  true,  true,  true,  true,  false, false},
			    {false, true,  true,  false, false, false, true,  true,  false}, 
				{true,  true,  false, true,  true,  true,  false, true,  true}, 
				{true,  false, false, true,  false, true,  false, false, true}, 
				{true,  true,  false, true,  true,  true,  false, true,  true}, 
				{false, true,  true,  false, false, false, true,  true,  false}, 
				{false, false, true,  true,  true,  true,  true,  false, false} 
			  };
	    assertEquals(2, CountingIslands.count(n_row, n_column, grid));
	}

	@Test
	public static void testExample() {
		
		int n_row = 4;
		int n_column = 4;
		
		boolean [][] grid = {{false, true, false, true},
				 {true, true, false, false},
				 {false, false, true, false},
				 {false, false, true, false}
				};
	    assertEquals(3, CountingIslands.count(n_row, n_column, grid));
	}
}
