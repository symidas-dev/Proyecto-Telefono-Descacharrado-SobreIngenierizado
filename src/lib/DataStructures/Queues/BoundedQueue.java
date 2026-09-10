package lib.DataStructures.Queues;

public interface BoundedQueue<QueueData> extends Queue<QueueData> {

    boolean enqueue(QueueData newData);

    boolean isFull();

}