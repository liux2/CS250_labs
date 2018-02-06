public interface Stack{
	// accessor methods
	public int size();
	public boolean isEmpty();
	public Object top() throws StackEmptyException;

	// update methods
	public void push(Object element);
	public Object pop() throws StackEmptyException;
}
