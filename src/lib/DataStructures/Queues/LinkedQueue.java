package lib.DataStructures.Queues;

public class LinkedQueue<QueueElement> implements Queue<QueueElement> {

    private Node<QueueElement> head;
    private Node<QueueElement> tail;

    private static class Node<NodeElement> {
        public Node(NodeElement newData) {
            this.data = newData;
        }

        Node<NodeElement> previous;

        NodeElement data;
    }

    /**
     * (non-Javadoc)
     * 
     * @see lib.DataStructures.Queues.Queue#enqueue(QueueElement)
     */
    @Override
    public void enqueue(QueueElement newData) {
        Node<QueueElement> newNode = new Node<QueueElement>(newData);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.previous = newNode;
            tail = newNode;
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see lib.DataStructures.Queues.Queue#dequeue()
     */
    @Override
    public QueueElement dequeue() {
        if (!isEmpty()) {
            QueueElement data = head.data;
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
    public QueueElement peek() {
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
