package edu.du.cs.esorano.sockets;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class PaintingPanel extends JPanel {
	//arrayList of PaintingPrimitives(can be a circle or line)
	private ArrayList<PaintingPrimitive> primitives = new ArrayList<>();
	
	public PaintingPanel() {
		//create a panel and set the background color to white
		this.setBackground(Color.WHITE);
	}
	public void addPrimitive(PaintingPrimitive obj) {
		//add the circle or line to the ArrayList
		this.primitives.add(obj);
}

public void paintComponent(Graphics g) {
        //erases the screen
		super.paintComponent(g);
        Graphics g2D = (Graphics)g;
		for(PaintingPrimitive obj : primitives) {  
			//g is the sheet of paper
			obj.draw(g2D);
        }
		System.out.println("called");
   
}
}
