package edu.du.cs.esorano.sockets;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Painter extends JFrame {
// set up our jframe
	public Painter() {

// NOTE: includes to border
// set the size of the frame
		setSize(500, 500);
// quit program when the frame is closed
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
// create the main JPanel
		JPanel pMain = new JPanel(new BorderLayout());
	
//create the holder for the colors
		JPanel colorHolder = new JPanel();
		colorHolder.setLayout(new BorderLayout());

// Create the paints 
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(3, 1)); // 3 by 1
		//----------color panes
// add red paint button
		JButton redPaint = new JButton();
		redPaint.setBackground(Color.RED);
		redPaint.setOpaque(true);
		redPaint.setBorderPainted(false);
		leftPanel.add(redPaint); // Added in next open cell in the grid
// add green button
		JButton greenPaint = new JButton();
		greenPaint.setBackground(Color.GREEN);
		greenPaint.setOpaque(true);
		greenPaint.setBorderPainted(false);
		leftPanel.add(greenPaint); // Added in next open cell in the grid
//add blue button
		JButton bluePaint = new JButton();
		bluePaint.setBackground(Color.BLUE);
		bluePaint.setOpaque(true);
		bluePaint.setBorderPainted(false);
		leftPanel.add(bluePaint); // Added in next open cell in the grid
//add the color buttons to the west pannel(left side) of the main panel
		pMain.add(leftPanel, BorderLayout.WEST);
		setContentPane(colorHolder);
		
		
		//-----------shapes
//create the holder for the shapes
		JPanel shapeHolder = new JPanel();
		shapeHolder.setLayout(new BorderLayout());
// Create the topPanel to hold shapeHolder
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 2)); // 1 by 2
//create the circle button
		JButton circle = new JButton("circle");
		topPanel.add(circle); // Added in next open cell in the grid
//create the line button
		JButton line = new JButton("line");
		topPanel.add(line); // Added in next open cell in the grid
//add topPanel to main
		pMain.add(topPanel, BorderLayout.NORTH);
		setContentPane(shapeHolder);
		
		//------- painting pannel
//create a holder pane for the drawing
		JPanel drawingHolder = new JPanel();
		drawingHolder.setLayout(new BorderLayout());

		
		//add Paintingpanel to the center
		//repaint in mouse released
		
		// sets the main pain
		setContentPane(pMain);
		// make the pain visible so we can see it
		setVisible(true);
	}
		
	public static void main(String args[]) {
		// chat room setup
		//Painter p = new Painter();
		PaintingPanel pp = new PaintingPanel();
		Circle c = new Circle(10,10,10, Color.RED);
		pp.addPrimitive(c);
		
	}
	
}
