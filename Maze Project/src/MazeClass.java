import edu.du.dudraw.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class MazeClass {
	// stores cell value
	protected enum CellValue {
		Wall, Open, Explored;
	}

	// sets up canvas
	private Draw canvas;
	// width and height of the maze
	private int width;
	private int height;

	// 2d array of type cell value
	private cell[][] cells;

	// inner class conating a cells position
	public class cell {
		private int x;
		private int y;
		private CellValue value;

		// constructor that just sets x and y
		public cell(int x, int y, CellValue v) {
			this.x = x;
			this.y = y;
			this.value = v;
		}

		// setter for column
		public void setX(int x) {
			this.x = x;
		}

		// setter for row
		public void setY(int y) {
			this.y = y;
		}

		// get col
		public int getX() {
			return this.x;
		}

		// gets row
		public int getY() {
			return this.y;
		}

		// get cellValue
		public CellValue getValue() {
			return this.value;
		}
		// set cellValue

		public void setValue(CellValue v) {
			this.value = v;
		}

		public void draw() {
			if (this.value.equals(CellValue.Wall)) {
				// draw black square
				canvas.setPenColor(canvas.BLACK);
				canvas.filledSquare(this.x, this.y, .5);
				// draw the squares value
				// canvas.setPenColor(canvas.WHITE);
				// canvas.text(this.x + 1, this.y + 1, "(" + this.x + "," + this.y + ")");
			}
			if (this.value.equals(CellValue.Open)) {
				// draw white square
				canvas.setPenColor(canvas.WHITE);
				canvas.filledSquare(this.x, this.y, .5);
				// draw the squares value
				// canvas.setPenColor(canvas.BLACK);
				// canvas.text(this.x + 1, this.y + 1, "(" + this.x + "," + this.y + ")");
			}
			if (this.value.equals(CellValue.Explored)) {
				// draw green square
				canvas.setPenColor(canvas.GREEN);
				canvas.filledSquare(this.x, this.y, .5);

				// draw the squares value
				// canvas.setPenColor(canvas.WHITE);
				// canvas.text(this.x + 1, this.y + 1, "(" + this.x + "," + this.y + ")");

			}
		}

		public String toString() {
			return "value:" + this.value + " x:" + this.x + " y:" + this.y;
		}

	}

	// constructor
	public MazeClass(int w, int h) {
		// creates a memory adress for the canvas
		this.canvas = new Draw();
		// sets dimensions of canvas
		this.canvas.setCanvasSize(500, 500);
		this.canvas.setXscale(0, w);
		this.canvas.setYscale(0, h);
		// set width and height equal to inputed values
		this.width = w;
		this.height = h;
		// intalize the array of cells with the values
		cells = new cell[w][h];
		// set all CellValues to wall - works
		for (int r = 0; r < cells.length; r++) {
			for (int c = 0; c < cells[0].length; c++) {
				this.cells[c][r] = new cell(c, r, CellValue.Wall);

			}
		}

	}

	// generateMaze
	public void DFSMaze() {
		// linkedList Stack for storing the data
		LinkedListStack<cell> stack = new LinkedListStack<cell>();
		// makes the current cell the cell at (1,1)
		cells[1][1].setValue(CellValue.Open);
		// stores the currentCell
		cell currentCell;
		// push current cell onto the stack
		stack.push(cells[1][1]);
		// keeps running untill stack is empty
		while (!stack.isEmpty()) {
			// makes the current cell the top of the stack
			currentCell = stack.pop();
			// creates ArrayList of neighbors
			ArrayList<cell> neighbors = new ArrayList<cell>();

			// checks if neighboring cells are inside of the canvas and are a wall, if so
			// then add to the stack
			if (currentCell.getX() + 2 < width
					&& cells[currentCell.getX() + 2][currentCell.getY()].getValue().equals(CellValue.Wall)) {
				// sets cell value to open and adds it to the Neighbors list
				cells[currentCell.getX() + 2][currentCell.getY()].setValue(CellValue.Open);
				neighbors.add(cells[currentCell.getX() + 2][currentCell.getY()]);
				// set the cell inbetween equal to "remove"(open)
				cells[currentCell.getX() + 1][currentCell.getY()].setValue(CellValue.Open);

			}
			if (currentCell.getX() - 2 > 0
					&& cells[currentCell.getX() - 2][currentCell.getY()].getValue().equals(CellValue.Wall)) {
				// sets cell value to open and adds it to the Neighbor list
				cells[currentCell.getX() - 2][currentCell.getY()].setValue(CellValue.Open);
				neighbors.add(cells[currentCell.getX() - 2][currentCell.getY()]);
				// set the cell inbetween equal to "remove"(open)
				cells[currentCell.getX() - 1][currentCell.getY()].setValue(CellValue.Open);

			}
			if (currentCell.getY() + 2 < height
					&& cells[currentCell.getX()][currentCell.getY() + 2].getValue().equals(CellValue.Wall)) {
				// sets cell value to open and adds it to the neighbor list
				cells[currentCell.getX()][currentCell.getY() + 2].setValue(CellValue.Open);
				neighbors.add(cells[currentCell.getX()][currentCell.getY() + 2]);
				// set the cell inbetween equal to "remove"(open)
				cells[currentCell.getX()][currentCell.getY() + 1].setValue(CellValue.Open);

			}
			if (currentCell.getY() - 2 >= 0
					&& cells[currentCell.getX()][currentCell.getY() - 2].getValue().equals(CellValue.Wall)) {
				// sets the neighboring cell to open and adds it to the array
				cells[currentCell.getX()][currentCell.getY() - 2].setValue(CellValue.Open);
				neighbors.add(cells[currentCell.getX()][currentCell.getY() - 2]);
				// set the cell inbetween equal to remove
				cells[currentCell.getX()][currentCell.getY() - 1].setValue(CellValue.Open);

			}

			// shuffles the array list
			Collections.shuffle(neighbors);
			// push all contents of arrayList onto the stack
			for (cell e : neighbors) {
				stack.push(e);

			}
			// clear the neighbors arrayList
			neighbors.clear();

			// loop through neighbors

		}
		// draw all of the cells
		for (int r = 0; r < cells.length; r++) {
			for (int c = 0; c < cells[0].length; c++) {
				cells[c][r].draw();
			}
		}

	}

	// solve using a que, works buts runs too slow
	public void BFsearch() {
		// queue for breathFirst search
		LinkedListQueue<cell> queue = new LinkedListQueue<cell>();
		// start at bottom left corner
		cell startingPoint = cells[1][1];
		cell endingPoint = cells[width - 2][height - 2];
		// cell that will be searcher
		cell currentCell = startingPoint;
		currentCell.setValue(CellValue.Explored);
		System.out.println(startingPoint.getValue());
		// enqueue current cell on the queue
		queue.enqueue(currentCell);
		// run while que is not empty
		while (!queue.isEmpty()) {

			currentCell = queue.dequeue();

			// if we found the end goal, end the loop
			if (currentCell.equals(endingPoint)) {
				return;
			}

			// look at neighboring cells and check that they are not a wall and not explored
			if (currentCell.getY() + 1 < height
					&& cells[currentCell.getX()][currentCell.getY() + 1].getValue().equals(CellValue.Open)) {
				// set the direct neighboring cell equal to explored
				cells[currentCell.getX()][currentCell.getY() + 1].setValue(CellValue.Explored);
				// draw the cell
				cells[currentCell.getX()][currentCell.getY() + 1].draw();
				// enqueue the cell on the queue
				queue.enqueue(cells[currentCell.getX()][currentCell.getY() + 1]);
			}
			if (currentCell.getX() + 1 < width
					&& cells[currentCell.getX() + 1][currentCell.getY()].getValue().equals(CellValue.Open)) {
				// set the direct neighboring cell equal to explored
				cells[currentCell.getX() + 1][currentCell.getY()].setValue(CellValue.Explored);
				// draw the cell
				cells[currentCell.getX() + 1][currentCell.getY()].draw();
				// enqueue the cell on the queue
				queue.enqueue(cells[currentCell.getX() + 1][currentCell.getY()]);
			}
			if (currentCell.getY() - 1 > 0
					&& cells[currentCell.getX()][currentCell.getY() - 1].getValue().equals(CellValue.Open)) {

				// set the direct neighboring cell equal to explored
				cells[currentCell.getX()][currentCell.getY() - 1].setValue(CellValue.Explored);
				// draw the cell
				cells[currentCell.getX()][currentCell.getY() - 1].draw();
				// enqueue the cell on the queue
				queue.enqueue(cells[currentCell.getX()][currentCell.getY() - 1]);
			}
			if (currentCell.getX() - 1 > 0
					&& cells[currentCell.getX() - 1][currentCell.getY()].getValue().equals(CellValue.Open)) {
				// set the direct neighboring cell equal to explored
				cells[currentCell.getX() - 1][currentCell.getY()].setValue(CellValue.Explored);
				// draw the cell
				cells[currentCell.getX() - 1][currentCell.getY()].draw();
				// enqueue the cell on the queue
				queue.enqueue(cells[currentCell.getX() - 1][currentCell.getY()]);
			}

		}

	}

	

}
