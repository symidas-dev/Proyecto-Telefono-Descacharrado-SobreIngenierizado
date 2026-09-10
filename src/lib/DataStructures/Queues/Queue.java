package lib.DataStructures.Queues;

public interface Queue<QueueData> {

    QueueData dequeue();

    QueueData peek();

    boolean isEmpty();

}