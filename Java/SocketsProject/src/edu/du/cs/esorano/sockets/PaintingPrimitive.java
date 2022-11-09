package edu.du.cs.esorano.sockets;
//parent class
import java.awt.*;
import javax.swing.*;

public abstract class PaintingPrimitive {
	//store the color that we are going to make the circle or the line
	private Color color;
	
	public PaintingPrimitive(Color color) {
		this.color = color;
	}
	//abstract method, sets the color 
	public void draw(Graphics g) {
		System.out.println("the image has been drawn");
		
	}
	
}
