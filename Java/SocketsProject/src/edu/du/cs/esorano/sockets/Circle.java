package edu.du.cs.esorano.sockets;

import javax.swing.*;
import java.awt.*;

public class Circle extends PaintingPrimitive {
	//store the radius
	private int radius;
	//stores the center point
	 protected Point center;
	//stores the radiusPoint
	Point radiusPoint;
	//stores the color
	private Color color;
	//constructor that intializes the circle
	public Circle(int x,int y,int radius, Color color) {
		//set the color in the abstract class
		super(color);
		//create the center point
		this.center = new Point(x,y);
		//create the radius point
		//NOTE: this may need to be a different value
		this.radiusPoint = new Point(x+radius,y);	
	
	}
	@Override
	public void drawGeometry(Graphics g) {
        int radius = (int) Math.abs(center.distance(radiusPoint));
        g.drawOval(center.x - radius, center.y - radius, radius*2, radius*2);  
        System.out.println("overide succesfull: circle drawn");
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
