public class NodeStack<E> implements Stack<E> {
  protected Node<E> top;
  protected int size;

  public NodeStack() {
    top = null;
    size = 0;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    if (top == null) return true;
    return false;
  }

  public void push(E elem) {
    if (top == null) {
      top = new Node<E>(elem, null);
    } else {
      Node<E> temp = new Node<E>(elem, top);
      top = temp;
    }
    size++;
  }

  public E top() {
    if (top == null) return null;
    return top.element;
  }

  public E pop() {
    if (size == 0) {
      System.out.println("Cannot pop an empty stack.");
      return null;
    }

    E temp = top.element;

    if (size == 1) {
      top = null;
    } else {
      top = top.next;
    }
    
    size--;
    return temp;
  }

  public class Node<E> {
    private E element;
    private Node<E> next;

    public Node() {
      this(null, null);
    }

    public Node(E e, Node<E> n) {
      element = e;
      next = n;
    }
  }

}
