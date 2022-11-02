import java.util.LinkedList;
import java.util.NoSuchElementException;

public class LinkedListStack<E> implements Stack<E> {

	//Java built-in LinkedList is a doubly Linke list 
	//removeFirst, removeLast, addFirst, addLast  = O(1)
	private LinkedList<E> theStack;
	
	public LinkedListStack()
	{
		this.theStack = new LinkedList<>();
		
	}
	@Override
	public void push(E e) {
		theStack.addFirst(e);
		
	}

	@Override
	public E pop() throws NoSuchElementException{
		
		if(theStack.isEmpty())
		{
			throw new NoSuchElementException("Stack is empty");
		}
		return theStack.removeFirst();
	}

	@Override
	public E top() throws NoSuchElementException 
	{
		if(theStack.isEmpty())
		{
			throw new NoSuchElementException("Stack is empty");
		}
		return theStack.getFirst();
	}

	@Override
	public int size() {
		return theStack.size();
	}

	@Override
	public boolean isEmpty() {
		
		return theStack.isEmpty();
	}
	
	public String toString()
	{
		StringBuilder r = new StringBuilder("Top of The stack: ");
		for(E element : theStack)
		{
			r.append(element.toString() + " ");
		}
		return r.toString();
	}

}
