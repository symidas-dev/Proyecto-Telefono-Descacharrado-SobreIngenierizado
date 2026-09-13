package lib.DataStructures.Queues;

public class UnboundedSimpleLinkedQueue<QueueData> implements UnboundedQueue<QueueData> {

    private Node<QueueData> head;
    private Node<QueueData> tail;

    private int numberOfElements = 0;

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
     * @see lib.DataStructures.Queues.Queue#enqueue(QueueData)
     */
    @Override
    public void enqueue(QueueData newData) {
        Node<QueueData> newNode = new Node<QueueData>(newData);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.previous = newNode;
            tail = newNode;
        }
        numberOfElements++;
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

            numberOfElements--;
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

    @Override
    public int length() {
        return numberOfElements;
    }

}
