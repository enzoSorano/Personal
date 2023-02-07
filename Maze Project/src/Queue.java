
public interface Queue<E> {
	public void enqueue(E v);
	public E dequeue();
	public E first();
	public int size();
	public boolean isEmpty();
	public String toString();
	
}
