package queue;

public class LinkedQueue extends AbstractQueue {
    private Node head;
    private Node tail;

    protected void enqueueImpl(Object element) {
        Node temp = new Node(element, null);
        if (size() == 0) {
            head = temp;
        } else {
            tail.next = temp;
        }
        tail = temp;
    }

    protected Object elementImpl() {
        return head.value;
    }

    protected void clearImpl() {
        tail = new Node(null, null);
        head = new Node(null, tail);
    }

    protected void remove() {
        head = head.next;
    }

    private class Node {
        private Object value;
        private Node next;

        public Node(Object value, Node next) {
            assert value != null;

            this.value = value;
            this.next = next;
        }
    }
}
