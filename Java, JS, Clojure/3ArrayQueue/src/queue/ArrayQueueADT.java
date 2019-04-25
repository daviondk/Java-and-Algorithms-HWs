package queue;


// I: Adding to the end, deleting from the beginning
// All elements != null
public class ArrayQueueADT {
    private Object[] elements = new Object[2];
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    // Pre: element != null
    // && queue != null
    // Post: size == size' + 1
    // && elements[size - 1] == element
    // && elements[i] == elements'[i] for i = 0..size - 1
    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert (element != null);
        ensureCapacity(queue, queue.size + 1);
        queue.elements[queue.tail++] = element;
        if (queue.tail == queue.elements.length) {
            queue.tail = 0;
        }
        queue.size++;
    }

    // Pre: size > 0
    // && queue != null
    // Post: size == size' - 1
    // && R == elements[size]
    // && elements[i] == elements'[i] for i = 0..size - 1
    public static Object dequeue(ArrayQueueADT queue) {
        assert(queue.size > 0);
        Object temp = queue.elements[queue.head++];
        if (queue.head == queue.elements.length) {
            queue.head = 0;
        }
        queue.size--;
        return temp;
    }

    // Pre: newSize >= elements.length
    // && queue != null
    // Post: elements.length == elements.length' * 2
    // && elements[i] == elements'[i] for i = 0..size - 1
    // && size' == size
    private static void ensureCapacity(ArrayQueueADT queue, int newSize) {
        if (newSize < queue.elements.length) {
            return;
        }
        queue.elements = toArray(queue, 2 * queue.size);
        queue.head = 0;
        queue.tail = queue.size;
    }

    public static Object[] toArray(ArrayQueueADT queue) {
        return toArray(queue, queue.size);
    }

    // Pre: queue != null
    // Post: if (head <= tail) R[i] = elements[j] for j = head..tail and i = j - head
    // && if (head > tail) R[i] = elements[j] for j = head..elements.length - 1 and i = j - head
    // && if (head > tail) R[i] = elements[j] for j = 0..tail and i = elements.length - head + j
    // && elements[i] == elements'[i] for i = 0..size - 1
    // && size' == size
    private static Object[] toArray(ArrayQueueADT queue, int cap) {
        Object[] temp = new Object[cap];
        if (queue.head <= queue.tail) {
            System.arraycopy(queue.elements, queue.head, temp, 0, queue.size);
        } else {
            System.arraycopy(queue.elements, queue.head, temp, 0 ,queue.elements.length - queue.head);
            System.arraycopy(queue.elements, 0, temp, queue.elements.length - queue.head, queue.tail);
        }
        return temp;
    }

    // Pre: size > 0
    // && queue != null
    // Post: R = elements[head];
    // && elements[i] == elements'[i] for i = 0..size - 1
    // && size' == size
    public static Object element(ArrayQueueADT queue) {
        assert (queue.size > 0);
        return queue.elements[queue.head];
    }

    // Pre: queue != null
    // Post: size' == size
    // && elements[i] == elements'[i] for i = 0..size - 1
    // && R == size
    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }

    // Pre:
    // && queue != null
    // Post: size' == size
    // && elements[i] == elements'[i] for i = 0..size - 1
    // && R == (size == 0)
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }

    // Pre:
    // && queue != null
    // Post: size == 0
    public static void clear(ArrayQueueADT queue) {
        queue.size = 0;
        queue.head = 0;
        queue.tail = 0;
    }
}
