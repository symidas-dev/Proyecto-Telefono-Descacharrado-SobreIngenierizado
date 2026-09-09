package lib.DataStructures.Queues;

public interface Queue<QueueElement> {

    void enqueue(QueueElement newIntegrant);

    QueueElement dequeue();

    QueueElement peek();

    boolean isEmpty();

}