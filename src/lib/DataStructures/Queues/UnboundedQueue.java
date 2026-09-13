package lib.DataStructures.Queues;

public interface UnboundedQueue<QueueData> extends Queue<QueueData> {

    void enqueue(QueueData newIntegrant);

    int length();

}