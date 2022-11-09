package edu.du.cs.esorano.sockets;

import java.awt.*;
import javax.swing.*;

public class Line extends PaintingPrimitive {

		//stores x coordinate of the center of the circle
		private int startX;
		//stores the y componenet of the center of the circle
		private int startY;
		//stores radius of the circle
		private int endX;
		//stores the color
		private int endY;
		//constructor that intializes the circle
		public Line(int startX, int startY, int endX, int endY, Color color) {
			//super the color up to the parent class
			super(color);
			this.startX = startX;
			this.startY = startY;
			this.endX = endX;
			this.endY = endY;
		}
		
		@Override
		public void draw(Graphics g) {
			//draw a line
			g.drawLine(this.startX, this.startY, this.endX, this.endY);
			
		}
		
}
