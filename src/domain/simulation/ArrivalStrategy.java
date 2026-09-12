package domain.simulation;

import domain.reception.Visitor;
import domain.time.Time;
import lib.DataStructures.Queues.UnboundedQueue;

/**
 * ArrivalStrategy
 */
public interface ArrivalStrategy {

    UnboundedQueue<? extends Visitor> apply(Time currentTime);

}
