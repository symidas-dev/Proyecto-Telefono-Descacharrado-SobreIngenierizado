package lib.DataStructures.Queues;

interface Queue<QueueData> {

    QueueData dequeue();

    QueueData peek();

    boolean isEmpty();

}