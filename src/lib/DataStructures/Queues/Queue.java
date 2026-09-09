package lib.DataStructures.Queues;

public interface Queue<QueueData> {

    void enqueue(QueueData newIntegrant);

    QueueData dequeue();

    QueueData peek();

    boolean isEmpty();

}