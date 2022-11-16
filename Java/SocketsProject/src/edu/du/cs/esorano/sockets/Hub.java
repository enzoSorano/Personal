package edu.du.cs.esorano.sockets;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
/*
 * purpose of hub is to manage connections made to it
 */
public class Hub {
	//local PaintingPanel
	private static PaintingPanel panel = new PaintingPanel();
	//arraylist to store connections made to the Socket
	private static ArrayList<String> msgs = new ArrayList<>();
	//hubThread arraylist to store threads needed for connection
	private static ArrayList<HubConnectionThread> threads = new ArrayList<HubConnectionThread>();

	public static void main(String args[]) {
		
		try {
			//set up the hub and bind it to port 7000
			ServerSocket ss = new ServerSocket(7000);
			//keep the hub up untill we want to shut it down
			while(true) {
				//accept incoming connections
				Socket s = ss.accept(); //blocking command so program will wait here untill there is a socket to accept
				//testing purposes
				System.out.println("Connection Established");
				//get input and output stream of the hub
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				//send the socket, hub input and output streams, the main panel in the hub, and msgs to each thread
				HubConnectionThread t = new HubConnectionThread(s,ois,oos,panel,msgs);
				//add the thread to the arrayList of all of the threads
				threads.add(t);
				//start the thread that has been added, meaning it will continualy run
				t.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
//NOTE: synchronized key word allows all threads to access these methods in order they were started
//this effectively waits for on thread to complete the method and then goes on the next
public static synchronized void addPrimitive(PaintingPrimitive p, HubConnectionThread t) {
	//add primitive to the main panel
	panel.addPrimitive(p);
	//send it to all other panels( calls method in hub)
	sendObject(p, t);
}
public static synchronized void addMsg(String msg, HubConnectionThread t) {
	//add msg to the msgs
	msgs.add(msg);
	//send it the main
	sendObject(msg, t);
}
public static synchronized void sendObject(Object o, HubConnectionThread th) {
    //for all of the connections
	for (HubConnectionThread t : threads) {
        //if the connection is not the one that just sent something
		if (!t.equals(th))
           //send the object(String or PaintingPrimitive)(calls method in HubConnectionThread)
			t.sendObject(o);
    }
}
}

