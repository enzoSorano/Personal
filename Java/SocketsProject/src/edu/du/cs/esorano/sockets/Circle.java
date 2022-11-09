package edu.du.cs.esorano.sockets;

import javax.swing.*;
import java.awt.*;

public class Circle extends PaintingPrimitive {
	//stores x coordinate of the center of the circle
	private int x;
	//stores the y componenet of the center of the circle
	private int y;
	//stores radius of the circle
	private int radius;
	//stores the color
	private Color color;
	//constructor that intializes the circle
	public Circle(int x, int y, int radius, Color color) {
		//set the color in the abstract class
		super(color);
		this.x = x;
		this.y =y;
		this.radius = radius;
		//super up the collor
		//super(color)
	}
	@Override
	public void draw(Graphics g) {
		//set the color of the circle
		g.setColor(this.color);
		//draw a circle
		g.drawOval(x-(radius/2), y-(radius/2), radius, radius);
	}
	
	/*
	//called every time we resize or minimize
	public void paintComponent(Graphics g) {
		//needed to draw properly
		super.paintComponent(g);
		//draw a line
		g.setColor(Color.BLUE);
		g.drawLine(10,20,100,300);
		
		System.out.println("paint component called");
	}
	*/
	
}

/*
//create a button
JButton b1 = new JButton("click here");
p.add(b1,BorderLayout.NORTH);
//add an action listener with an inner class
b1.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("click here was pressed");
	}
	
});
*/
/*
//paint a circle
Circle c = new Circle();
c.setBackground(Color.green);
p.add(c, BorderLayout.CENTER);
*/

//repaint the circle()


//sets the main pane
