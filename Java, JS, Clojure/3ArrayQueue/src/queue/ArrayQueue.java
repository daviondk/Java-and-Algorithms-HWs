package queue;

public class ArrayQueue {
    private Object[] elements = new Object[2];
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    public void enqueue(Object element) {
        assert(element != null);
        ensureCapacity(size + 1);
        elements[tail++] = element;
        if (tail == elements.length) {
            tail = 0;
        }
        size++;
    }

    public Object dequeue() {
        assert(size > 0);
        Object temp = elements[head++];
        if (head == elements.length) {
            head = 0;
        }
        size--;
        return temp;
    }

    private void ensureCapacity(int newSize) {
        if (newSize < elements.length) {
            return;
        }
        elements = toArray(2 * size);
        head = 0;
        tail = size;
    }

    public Object[] toArray() {
        return toArray(size);
    }

    private Object[] toArray(int cap) {
        Object[] temp = new Object[cap];
        if (head <= tail) {
            System.arraycopy(elements, head, temp, 0, size);
        } else {
            System.arraycopy(elements, head, temp, 0 ,elements.length - head);
            System.arraycopy(elements, 0, temp, elements.length - head, tail);
        }
        return temp;
    }


    public Object element() {
        assert(size > 0);
        return elements[head];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        head = 0;
        tail = 0;
    }
}
