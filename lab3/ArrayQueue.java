public class ArrayQueue<E> implements Queue<E>{

  	E[] myData;
  	int back, front, size;

  	public ArrayQueue() {
  		myData =(E[]) new Object[10];
  		front = 0;
  		back = 0;
  		size = 0;
  	} //ArrayQueue (constructor)

  	public void add(E e) {
  		if (size == myData.length) {
  			throw new QueueFullException("queue is full -- add");
  		} //if

  		myData[back] = e;
  		back++;
  		size++;

  		if (back == myData.length) {
  			back = 0;
  		} //if
  	} //add

  	public E remove() {
  		if (isEmpty()) {
  			throw new QueueEmptyException("queue is empty -- remove");
  		} //if
  		E temp = myData[front];
  		myData[front] = null;
  		front++;
  		size--;

  		if (front == myData.length) {
  			front = 0;
  		} //if

  		return temp;
  	} //remove

  	public E peek() {
  		if (isEmpty()) {
  			throw new QueueEmptyException("queue is empty -- element");
  		} //if
  		return myData[front];
  	} //element

  	public int size() {
  		return size;
  	} //size

  	public boolean isEmpty() {
  		return (size == 0);
  	} //isEmpty
/*
    public void resize(){
      Object[] temp = new Object[myData.length+2];
      int counter = 0;
      for(int i = 0; i < myData.length; i++){
        temp[i] = myData[counter];
        counter++;
      }
      myData = (E[]) temp;
    }
*/
}
