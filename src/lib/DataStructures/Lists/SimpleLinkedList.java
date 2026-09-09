package lib.DataStructures.Lists;

public class SimpleLinkedList<ListData> implements List<ListData> {

    private Node<ListData> head;

    private int counter = 0;

    private static class Node<NodeElement> {
        public Node(NodeElement newData) {
            this.data = newData;
        }

        Node<NodeElement> next;

        NodeElement data;
    }

    @Override
    public void insert(ListData element) {
        Node<ListData> newNode = new Node<ListData>(element);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        counter++;
    }

    @Override
    public boolean remove(ListData element) {
        if (isEmpty()) {
            return false;
        }

        Node<ListData> current = head;

        if (current.data == element) {
            head = current.next;
            counter--;
            return true;
        }

        while (current.next != null) {
            if (current.next.data == element) {
                current.next = current.next.next;
                counter--;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    @Override
    public ListData get(int indexGoal) {
        if (indexGoal < 0) {
            return null;
        }

        if (indexGoal >= counter) {
            return null;
        }

        Node<ListData> current = head;

        for (int index = 0; index < indexGoal; index++) {
            current = current.next;
        }

        return current.data;
    }

    @Override
    public boolean contains(ListData element) {
        if (isEmpty()) {
            return false;
        }

        Node<ListData> current = head;

        while (current != null) {
            if (current.data == element) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

}
