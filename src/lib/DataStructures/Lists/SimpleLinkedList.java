package lib.DataStructures.Lists;

public class SimpleLinkedList<ListData> implements List<ListData> {

    private Node<ListData> head;

    private int contador = 0;

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
        contador++;
    }

    @Override
    public boolean remove(ListData element) {
        if (isEmpty()) {
            return false;
        }

        Node<ListData> current = head;

        if (current.data == element) {
            head = current.next;
            contador--;
            return true;
        }

        while (current.next != null) {
            if (current.next.data == element) {
                current.next = current.next.next;
                contador--;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    @Override
    public ListData get(int goal) {
        int index = 0;
        Node<ListData> current = head;
        while (index < goal) {
            if (current == null) {
                return null;
            }

            current = current.next;
        }
        return current.data;
    }

    @Override
    public boolean conteins(ListData element) {
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
        return contador;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

}
