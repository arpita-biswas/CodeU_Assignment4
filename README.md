# CodeU_Assignment4 : Counting Islands  
Given a 2-dimensional (boolean) map of tiles. Each tile is either land (true) or water (false). Write a function that counts the number of islands. Two tiles belong to the same island if they are both land, and are adjacent horizontally or vertically, but not diagonally. The function takes as input the number of rows, number of columns, and a 2-dimensional array of booleans and returns the number of islands.  

## Solution:  
function count()  
1. If the grid is null or empty, return 0.  
2. If the grid dimension does not match with the given number of rows and number of columns then return -1 to flag an error.    
3. Else, for each cell in the grid check if the cell denotes water or has been already visited.  
    i) If YES, go to the next cell.  
    ii) If NO, increase the island-count by 1 and (call function isNewIsland() to) perform a depth first search to explore all the (true) cells that can be reached by eventually traversing horizontally or vertically. All such cells are marked visited.  
4. Return island-count.  

Time complexity: O(mxn) where m and n are the number of rows and columns of the grid respectively.
