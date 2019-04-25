package queue;

public class ArrayQueueModule {
    private static Object[] elements = new Object[2];
    private static int head = 0;
    private static int tail = 0;
    private static int size = 0;

    public static void enqueue(Object element) {
        assert(element != null);
        ensureCapacity(size + 1);
        elements[tail++] = element;
        if (tail == elements.length) {
            tail = 0;
        }
        size++;
    }

    public static Object dequeue() {
        assert(size > 0);
        Object temp = elements[head++];
        if (head == elements.length) {
            head = 0;
        }
        size--;
        return temp;
    }

    private static void ensureCapacity(int newSize) {
        if (newSize < elements.length) {
            return;
        }
        elements = toArray(2 * size);
        head = 0;
        tail = size;
    }

    public static Object[] toArray() {
        return toArray(size);
    }

    private static Object[] toArray(int cap) {
        Object[] temp = new Object[cap];
        if (head <= tail) {
            System.arraycopy(elements, head, temp, 0, size);
        } else {
            System.arraycopy(elements, head, temp, 0 ,elements.length - head);
            System.arraycopy(elements, 0, temp, elements.length - head, tail);
        }
        return temp;
    }


    public static Object element() {
        assert(size > 0);
        return elements[head];
    }

    public static int size() {
        return size;
    }

    public static boolean isEmpty() {
        return size == 0;
    }

    public static void clear() {
        size = 0;
        head = 0;
        tail = 0;
    }
}


/*
if (elements.length < 4) {
                return;
            }
            Object[] newElements = new Object[elements.length / 2];
            if (head < tail && tail - head <= elements.length / 2) {
                System.arraycopy(elements, head, newElements, 0, tail - head);
                tail -= head;
                head = 0;
                elements = newElements;
                //for (int i = 0; i < elements.length; i++)
                //    System.out.format("%s%n", elements[i]);
                //System.out.format("CAPACITY DECREASED: %d, HEAD IS: %d, TAIL IS: %d%n", elements.length, head, tail);
            } else if (head > tail && elements.length - head + tail <= elements.length / 2) {
                System.arraycopy(elements, head, newElements, 0, elements.length - head);
                System.arraycopy(elements, 0, newElements, elements.length - head, tail);
                tail += elements.length - head;
                head = 0;
                elements = newElements;
                //for (int i = 0; i < elements.length; i++)
                //    System.out.format("%s%n", elements[i]);
                //System.out.format("CAPACITY DECREASED: %d, HEAD IS: %d, TAIL IS: %d%n", elements.length, head, tail);
            }
 */
