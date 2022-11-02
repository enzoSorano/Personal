
public class Driver {
	//this purpose of this program is to randomly generate a maze and then try to get from the bottom left corner to the top right corner
	//For generating the maze using we use a depth first search, we use a stack to store cells that are neighboring the current open cell. 
	//These neighbors should be added to the stack in a random order.
	//This has the effect of expanding one random neighbor fully, only moving onto the next when we hit a dead end.  
	//
	//to traverse the maze we use a breadth first approach
	//It's a technique that is guaranteed to find the shortest path from start to finish because it explores all cells that are one step away then all cells that are two steps away and so on.
	//You can imagine the search as a ring growing around the start point until it finds the goal. In order to perform breadth first we store elements in a queue.  
	//This has the effect of expanding the neighbors of a cell, then the neighbors of those neighbors, and so on. 
	
	public static void main(String[] args) {

		MazeClass maze = new MazeClass(31,31);
		maze.DFSMaze();
		maze.BFsearch();
		
		
	}

}
