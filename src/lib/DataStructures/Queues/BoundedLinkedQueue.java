package lib.DataStructures.Queues;

public class BoundedLinkedQueue<QueueData> implements BoundedQueue<QueueData> {

    private Node<QueueData> head;
    private Node<QueueData> tail;

    private final int CAPACITY;
    private int size;

    private static class Node<NodeElement> {
        public Node(NodeElement newData) {
            this.data = newData;
        }

        Node<NodeElement> previous;

        NodeElement data;
    }

    public BoundedLinkedQueue(int capacity) {
        this.CAPACITY = capacity;
    }

    /**
     * (non-Javadoc)
     * 
     * @see lib.DataStructures.Queues.BoundedQueue#enqueue(java.lang.Object)
     * @return true if the queue is not full, false otherwise
     */
    @Override
    public boolean enqueue(QueueData newData) {
        if (!isFull()) {
            Node<QueueData> newNode = new Node<QueueData>(newData);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.previous = newNode;
                tail = newNode;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see lib.DataStructures.Queues.BoundedQueue#isFull()
     */
    @Override
    public boolean isFull() {
        return size >= CAPACITY;
    }

    /**
     * (non-Javadoc)
     * 
     * @see lib.DataStructures.Queues.Queue#dequeue()
     */
    @Override
    public QueueData dequeue() {
        if (!isEmpty()) {
            QueueData data = head.data;
            head = head.previous;

            if (head == null) {
                tail = null;
            }

            return data;
        } else {
            return null;
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see lib.DataStructures.Queues.Queue#peek()
     */
    @Override
    public QueueData peek() {
        if (!isEmpty()) {
            return head.data;
        } else {
            return null;
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see lib.DataStructures.Queues.Queue#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

}
