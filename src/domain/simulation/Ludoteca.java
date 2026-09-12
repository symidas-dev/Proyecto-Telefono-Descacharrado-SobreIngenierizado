package domain.simulation;

import domain.reception.Visitor;
import domain.reception.VisitorReceiver;
import domain.time.TickObservable;
import domain.time.TickObserver;
import domain.time.Time;
import lib.DataStructures.Queues.UnboundedQueue;
import lib.Patterns.Observer.Event;

/**
 * Ludoteca
 */
public class Ludoteca implements TickObserver {
    private final VisitorReceiver receiver;
    private final ArrivalStrategy arrivalStrategy;

    private UnboundedQueue<? extends Visitor> visitors;

    public Ludoteca(ArrivalStrategy arrivalStrategy, VisitorReceiver receiver) {
        this.receiver = receiver;
        this.arrivalStrategy = arrivalStrategy;
    }

    /**
     * (non-Javadoc)
     * 
     * @see domain.time.TickObserver#notifiedTick()
     */
    @Override
    public void notifiedTick(Event<TickObservable, Time> event) {
        Time currentTime = event.getEvent();
        visitors = arrivalStrategy.apply(currentTime);
        receiver.receive(visitors);
    }

}
