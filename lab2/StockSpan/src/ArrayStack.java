public class ArrayStack implements Stack{
	// Implementation of stack interface using an array
	public static final int CAPACITY = 1024;
			// default capacity of stack
	private int N;		// maximum capacity of the stack
	private Object S[];		// S holds the elements of the stack
	private int t = -1; 	// the top element of the stack
	public ArrayStack(){		// initialize the stack with the default capacity
		this(CAPACITY);
	}
	public ArrayStack(int cap){		// initialize the stack with given capacity
		N = cap;
		S = new Object[N];
	}
	public int size(){				// returns the current stack size
		return (t+1);
	}
	public boolean isEmpty(){		// returns true if the stack is empty
		return (t<0);
	}
	public void push(Object obj) throws StackFullException{		// push a new element on the stack
		if (size() == N)
			throw new StackFullException("Stack overflow");
		S[++t] = obj;
	}
	public Object pop() throws StackEmptyException{		// pop off the stack element
		Object elem;
		if (isEmpty())
			throw new StackEmptyException("Stack is empty");
		elem = S[t];
		S[t--] = null;		// dereference S[top] and decrement top
		return elem;
	}
	public Object top() throws StackEmptyException{
		return S[t];
	}

}
