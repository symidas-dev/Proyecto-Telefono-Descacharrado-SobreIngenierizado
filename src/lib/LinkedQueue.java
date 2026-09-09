package lib;

public class LinkedQueue<QueueElement> implements Queue<QueueElement> {

    private Node<QueueElement> head;
    private Node<QueueElement> tail;

    private static class Node<ItemElement> {
        public Node(ItemElement newIntegrant) {
            this.item = newIntegrant;
        }

        Node<ItemElement> previous;

        ItemElement item;
    }

    /**
     * (non-Javadoc)
     * 
     * @see lib.Queue#enqueue(QueueElement)
     */
    @Override
    public void enqueue(QueueElement newIntegrant) {
        Node<QueueElement> newNode = new Node<QueueElement>(newIntegrant);
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
     * @see lib.Queue#dequeue()
     */
    @Override
    public QueueElement dequeue() {
        if (!isEmpty()) {
            QueueElement item = head.item;
            head = head.previous;

            if (head == null) {
                tail = null;
            }

            return item;
        } else {
            return null;
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see lib.Queue#peek()
     */
    @Override
    public QueueElement peek() {
        if (!isEmpty()) {
            return head.item;
        } else {
            return null;
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see lib.Queue#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

}
