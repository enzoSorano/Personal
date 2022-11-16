package edu.du.cs.esorano.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ReaderThread extends Thread {
	 private ObjectInputStream ois;
	 private Painter p;

	    public ReaderThread(ObjectInputStream ois, Painter p) {
	        this.ois = ois;
	        this.p = p;
	    }

	    @Override
	    public void run(){
	        while (true) {
	            try {
	                //get the painter
	            	Object in = ois.readObject();
	                System.out.println(in);
	               //check its type and send it to the hub
	                if (in instanceof PaintingPrimitive) {
	                    p.addPrimitive((PaintingPrimitive) in);
	                } else if (in instanceof String) {
	                    p.addText((String) in);
	                }
	            } catch (ClassNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }

}
