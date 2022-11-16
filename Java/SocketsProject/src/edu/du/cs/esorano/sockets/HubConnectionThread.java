package edu.du.cs.esorano.sockets;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class HubConnectionThread extends Thread {
	private Socket s;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private PaintingPanel panel;
	private ArrayList<String> msgs;
	
	public HubConnectionThread(Socket s, ObjectInputStream ois, ObjectOutputStream oos, PaintingPanel panel, ArrayList<String> msgs) {
		this.s = s;
		this.ois = ois;
		this.oos = oos;
		this.panel = panel;
		this.msgs = msgs;
	}
	//NOTE: all of this is happening constantly because we started the thread and never stoped it or joined it
	@Override
	public void run() {
		boolean run = true;
        try {
        	//write the panel and msgs
            oos.writeObject(panel);
            oos.writeObject(msgs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (run) {
            try {
            	//get the object( can be a PaintingPrimitive of String(msg))
                Object in = ois.readObject();
                //check to see which it is
                if (in instanceof PaintingPrimitive) {
                    //call synchronized method
                	Hub.addPrimitive((PaintingPrimitive) in, this);
                } else if (in instanceof String) {
                	//call synchronized method
                	Hub.addMsg((String) in, this);
                }
            } catch (EOFException e) {
                try {
                	//shows that we have closed the connecting
                    System.out.println("Closing connection");
                    //close both streams
                    ois.close();
                    oos.close();
                    //close the socket
                    s.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                run = false;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendObject(Object o) {
        try {
            oos.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	}


