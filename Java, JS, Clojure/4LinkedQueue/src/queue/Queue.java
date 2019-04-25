package queue;

public interface Queue {
    // Pre: true
    void enqueue(Object element);
    // Post: size == size' + 1
    // && tail == element
    // && all other elements do not change

    // Pre: size > 0
    Object dequeue();
    // Post: size == size' - 1
    // R = head'
    // && head = element after head'
    // && all elements other
    // do not change except the first (new) one

    // Pre: size > 0
    Object element();
    // Post: size = size'
    // && R = head
    // && all elements still the same

    // Pre: true
    int size();
    // Post: size = size'
    // && R == size
    // && all elements still the same

    // Pre: true
    boolean isEmpty();
    // Post: size == size
    // && all elements still the same

    // Pre: true
    void clear();
    // Post: size == 0

    // Pre: true
    Object[] toArray();
    // Post: R == array of all elements in order of queue
    // && all elements still the same

}
