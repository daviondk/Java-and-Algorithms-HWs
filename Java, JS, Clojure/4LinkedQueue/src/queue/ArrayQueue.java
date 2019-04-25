package queue;

public class ArrayQueue extends AbstractQueue {
    private Object[] elements = new Object[2];
    private int head = 0;
    private int tail = 0;

    public void enqueueImpl(Object element) {
        ensureCapacity(size() + 1);
        elements[tail++] = element;
        if (tail == elements.length) {
            tail = 0;
        }
    }

    private void ensureCapacity(int newSize) {
        if (newSize < elements.length) {
            return;
        }
        elements = toArray(2 * size);
        head = 0;
        tail = size();
    }


    public Object elementImpl() {
        assert (size() > 0);
        return elements[head];
    }

    public void remove() {
        head++;
        if (head == elements.length) {
            head = 0;
        }
    }

    public int size() {
        return super.size;
    }

    public void clearImpl() {
        head = 0;
        tail = 0;
    }
}
