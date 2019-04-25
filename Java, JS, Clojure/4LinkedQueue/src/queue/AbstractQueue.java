package queue;

public abstract class AbstractQueue implements Queue {
    protected int size;

    public void enqueue(Object element) {
        assert element != null;
        enqueueImpl(element);
        size++;
    }

    protected abstract void enqueueImpl(Object element);

    public Object dequeue() {
        assert size > 0;
        Object temp = element();
        size--;
        remove();
        return temp;
    }

    protected abstract void remove();

    public Object element() {
        assert size > 0;
        return elementImpl();
    }

    protected abstract Object elementImpl();

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        clearImpl();
        size = 0;
    }

    protected abstract void clearImpl();

    public Object[] toArray() {
        return toArray(size);
    }

    protected Object[] toArray(int cap) {
        Object[] temp = new Object[cap];
        for (int i = 0; i < size; i++) {
            temp[i] = dequeue();
            enqueue(temp[i]);
        }
        return temp;
    }
}
