package edu.du.cs.esorano.sockets;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
public class Painter extends JFrame implements  ActionListener, MouseListener, MouseMotionListener, WindowListener {
//this is used to store what type of shape we have
	private enum SHAPE {
		CIRCLE,
		LINE
	}
//stores the color choosen
	private Color colorChoosen;
//stores the shape shoosen, defaults to line
	private SHAPE shapeChoosen;
//stores the starting x and starting y respectively
	private Point startingPoint;
//paintingPanel
	private PaintingPanel pp;
//stores the name of someone who logged on
	private String name;
//stores all the text in the text box
	private ArrayList<String> chatText;
	//
	private JTextArea chatArea;
	private JTextField messageField;
//for communication
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
// set up our jframe
	public Painter(String name, String ipaddr) {
//intalize some variables
		this.shapeChoosen = SHAPE.LINE;
		this.colorChoosen = Color.red;
		chatText = new ArrayList<>();
		this.name = name;
		
		
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
// add red paint button and action listener and action command
		JButton redPaint = new JButton();
		redPaint.addActionListener(this);
		redPaint.setActionCommand("red");
		redPaint.setBackground(Color.RED);
		redPaint.setOpaque(true);
		redPaint.setBorderPainted(false);
		leftPanel.add(redPaint); // Added in next open cell in the grid
// add green button and action listener and action command
		JButton greenPaint = new JButton();
		greenPaint.addActionListener(this);
		greenPaint.setActionCommand("green");
		greenPaint.setBackground(Color.GREEN);
		greenPaint.setOpaque(true);
		greenPaint.setBorderPainted(false);
		leftPanel.add(greenPaint); // Added in next open cell in the grid
//add blue button and action listener and action command
		JButton bluePaint = new JButton();
		bluePaint.addActionListener(this);
		bluePaint.setActionCommand("blue");
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
//create the circle button and add action listener and action command
		JButton circle = new JButton("circle");
		circle.addActionListener(this);
		circle.setActionCommand("circle");
		topPanel.add(circle); // Added in next open cell in the grid
//create the line button and add action listener and action command
		JButton line = new JButton("line");
		line.addActionListener(this);
		line.setActionCommand("line");
		topPanel.add(line); // Added in next open cell in the grid
//add topPanel to main
		pMain.add(topPanel, BorderLayout.NORTH);
		setContentPane(shapeHolder);
		
		//------- painting pannel
//create a painting panel
		pp = new PaintingPanel();
		pp.addMouseListener(this);
//add paintingPanel to the center
		pMain.add(pp, BorderLayout.CENTER);
		
		//--- chat pannel
		JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());

        //creates messagebox
        JLabel messageLabel = new JLabel("Message");
        messageLabel.setBorder(new EmptyBorder(3,3,3,3));
        messageField = new JTextField();
        //create send buttong and add its action listeners
        JButton sendButton = new JButton("Send");
        sendButton.setActionCommand("send");
        sendButton.addActionListener(this);
        sendButton.setBorder(new EmptyBorder(3,3,3,3));
        //were chat will be displayed
        chatArea = new JTextArea(3,100);
        chatArea.setRows(3);
        chatArea.setMaximumSize(new Dimension(500,100));
        //so we can scroll incase we go over the area provided
        JScrollPane chatScroll = new JScrollPane(chatArea);
        chatScroll.setVerticalScrollBarPolicy(20);
        chatScroll.setHorizontalScrollBarPolicy(31);
        chatScroll.setPreferredSize(new Dimension(500,100));
        chatScroll.setBorder(new TitledBorder("Chat"));
        //add everything to the chat panel
        chatPanel.add(messageLabel, BorderLayout.WEST);
        chatPanel.add(messageField, BorderLayout.CENTER);
        chatPanel.add(sendButton, BorderLayout.EAST);
        chatPanel.add(chatScroll, BorderLayout.NORTH);
		//add it to the main
        pMain.add(chatPanel, BorderLayout.SOUTH);
		
		// sets the main pain
		setContentPane(pMain);
		// make the pain visible so we can see it
		setVisible(true);
		//connect to a socketServer
		try {
			//connect to the ip specefied(in our case "localhost")
            socket = new Socket(ipaddr, 7000);
            //instansiaze the output and input stream for the painter
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	//---------------start of all of the listeners
	@Override
	public void actionPerformed(ActionEvent e) {
//string to store the action command
		String action = e.getActionCommand();
//check which button was pressed and change
		if(action.equals("red")) {
			colorChoosen = Color.red;
		}
		else if(action.equals("green")) {
			colorChoosen =Color.green;
			
		}
		else if(action.equals("blue")) {
			colorChoosen = Color.blue;
		}
		else if(action.equals("circle")) {
			shapeChoosen = SHAPE.CIRCLE;
		}
		else if(action.equals("line")) {
			shapeChoosen = SHAPE.LINE;
		}
		else if(action.equals("send")) {
			 //how msg will be displayed	
			String msg = name + ": " + messageField.getText() + "\n";
	         //add the msg to the arrayList of messages
			chatText.add(msg);
	        //display all previous messages and the current one
			updateText();
	            chatArea.updateUI();
	            try {
	                oos.writeObject(msg);
	            } catch (IOException ee) {
	                ee.printStackTrace();
	            }
		}
	}	
	//use this
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//seams to be from 0,0 to 500,500
		startingPoint = e.getPoint();
	}
	//use this
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//have the painter draw the line(or circle)
		Point endPoint = e.getPoint();
		System.out.println(endPoint.getX() + " " + endPoint.getY());
		if(shapeChoosen.equals(SHAPE.CIRCLE)) {
			//get the radius
			int radius = (int)Math.round(Point2D.distance(startingPoint.getX(), startingPoint.getY(), endPoint.getX(), endPoint.getY()));
			//create a circle
			Circle c = new Circle((int)startingPoint.getX(),(int)startingPoint.getY(), radius, colorChoosen);
			//add it to the PaintingPanel
			pp.addPrimitive(c);
			try {
			//write it to the hub
			oos.writeObject((PaintingPrimitive) c);
			} catch(IOException ee) {
				ee.printStackTrace();
			}
		} else if(shapeChoosen.equals(SHAPE.LINE)) {
			//create a line
			Line l = new Line((int)startingPoint.getX(), (int)startingPoint.getY(), (int)endPoint.getX(), (int)endPoint.getY(), colorChoosen);
			//add it to the painting panel
			pp.addPrimitive(l);
			try {
			//add it to the hub
			oos.writeObject((PaintingPrimitive) l);
			} catch(IOException ee) {
				ee.printStackTrace();
			}
		
		}
		pp.updateUI();
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	 
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		//when window closes close the painters streams and socket
		try {
			this.oos.close();
			this.ois.close();
			this.socket.close();
		} catch(IOException ee) {
			ee.printStackTrace();
		}
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void updateText() {
	        chatArea.setText("");
	        for (String s : chatText)
	            chatArea.append(s);
	    }
	
	 public void addPrimitive(PaintingPrimitive p) {
	        pp.addPrimitive(p);
	        pp.updateUI();
	    }
	 
	 public void addText(String s) {
	        chatText.add(s);
	        updateText();
	    }
	 
	public static void main(String args[]) {
		//ask the user for the ip they want to connect too
		String name = JOptionPane.showInputDialog("enter your name:");
		//if they dont enter anything move onto the next
		if(name == null) {
			System.exit(1);
		}
		//get the ip the user wants to connect to
		String ipaddr = JOptionPane.showInputDialog("enter the Server IP: ");
		//if they dont enter anyt
		if(ipaddr == null) {
			System.exit(1);
		}
		//create a new painter with the inputs
		Painter painter = new Painter(name,ipaddr);
	try {	
		// case the painter to an object
		Object in = painter.ois.readObject();
		//if ther eis an instance of PaintingPanel
		if(in instanceof PaintingPanel) {
			//add all the elements of this painter panel
			painter.pp.addAll((PaintingPanel) in);
		}
		//read the othern panels
		in = painter.ois.readObject();
		//if it is an instance of an ArrayList
		if(in instanceof ArrayList<?>) {
			  //if it isnt empty, and the first element is a string
			if (!((ArrayList<?>) in).isEmpty() && ((ArrayList<?>) in).get(0) instanceof String) {
				//add all of the msgs
				painter.chatText.addAll((ArrayList<String>) in);
			  }
		}
	} catch( IOException | ClassNotFoundException e) {
		e.printStackTrace();
	}
	//repaint everything for this painter
	painter.pp.updateUI();
	painter.updateText();
	painter.chatArea.updateUI();
	//send everything to the reader thread
	Thread t = new ReaderThread(painter.ois, painter);
	//start the thread so we are continually pulling from this painter
	t.start();
	
	

	
	
	}
	}

