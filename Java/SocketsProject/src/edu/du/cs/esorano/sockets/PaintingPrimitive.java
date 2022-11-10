package edu.du.cs.esorano.sockets;
//parent class
import java.awt.*;
import javax.swing.*;

public abstract class PaintingPrimitive  {
	//store the color that we are going to make the circle or the line
	private Color color;
	//constructor used when we call super
	public PaintingPrimitive(Color color) {
		this.color = color;
	}
	//abstract draw geometry
	public void drawGeometry(Graphics g) {
		System.out.println("if this prints, draw geometry was not overiden");
		
	}
	
	//abstract method, sets the color 
	public void draw(Graphics g) {
		//set the color of the graphic
		g.setColor(this.color);
		drawGeometry(g);
		System.out.println("the image has been drawn");
		
	}
	
}
