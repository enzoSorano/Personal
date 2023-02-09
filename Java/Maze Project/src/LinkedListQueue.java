import java.util.LinkedList;

public class LinkedListQueue<E> implements Queue<E> {

	private LinkedList<E> theQueue;

	// constructor
	public LinkedListQueue() {
		// creates a new linked list
		this.theQueue = new LinkedList<>();
	}

	@Override
	public void enqueue(E v) {
		// if que is empty
		// adds first in first out
		//adds to the end of the list
		
		theQueue.add(v);
	}

	@Override
	public E dequeue() {
		// checks if list is empty
		if (theQueue.isEmpty()) {
			return null;
		}
		
		E temp = theQueue.getFirst();
		theQueue.remove(0);

		return temp;
	}

	@Override
	public E first() {
		// checks if list is empty
		if (theQueue.isEmpty()) {
			return null;
		}
		// gests the first element in the list
		
		return theQueue.getFirst();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return theQueue.size();
	}

	@Override
	public boolean isEmpty() {
		
		if (theQueue.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		
		return "" + theQueue;
	}



}
