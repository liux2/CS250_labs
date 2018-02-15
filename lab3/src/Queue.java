public interface Queue<E> {
  public void add(E e) throws QueueFullException;
  public E remove() throws QueueEmptyException;
  public E peek() throws QueueEmptyException;
  public int size();
  public boolean isEmpty();
}
