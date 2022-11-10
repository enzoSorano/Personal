package edu.du.cs.esorano.sockets;

import java.awt.*;
import javax.swing.*;

public class Line extends PaintingPrimitive {
		//store the starting point
		protected Point start;
		//store the ending point
		protected Point end;
		//constructor that intializes the circle
		public Line(int startX, int startY, int endX, int endY, Color color) {
			//super the color up to the parent class
			super(color);
			//create the starting point
			this.start = new Point(startX, startY);
			//create the ending point
			this.end = new Point(endX, endY);
		}
		
		public void drawGeometry(Graphics g) {
			//draw a line
			g.drawLine(this.start.x, this.start.y, this.end.x, this.end.y);
			System.out.println("overide succesfull: line drawn");
		}
		
		
		
}
