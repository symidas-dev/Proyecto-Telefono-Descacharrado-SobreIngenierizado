package domain.simulation;

import domain.reception.Visitor;
import domain.reception.VisitorReceiver;
import domain.time.TickObservable;
import domain.time.TickObserver;
import domain.time.Time;
import lib.DataStructures.Queues.UnboundedQueue;
import lib.Patterns.Common.Event;
import lib.Patterns.Dispatcher.Dispatcher;

/**
 * Ludoteca
 */
public class Ludoteca implements TickObserver, SimulationEventSource {
    private final VisitorReceiver receiver;
    private final ArrivalStrategy arrivalStrategy;

    private UnboundedQueue<? extends Visitor> visitors;
    private Dispatcher<SimulationEventDTO> dispatcher;

    public Ludoteca(ArrivalStrategy arrivalStrategy, VisitorReceiver receiver,
            Dispatcher<SimulationEventDTO> dispatcher) {
        this.arrivalStrategy = arrivalStrategy;
        this.receiver = receiver;
        this.dispatcher = dispatcher;
    }

    public Ludoteca(Builder builder) {
        this(builder.arrivalStrategy, builder.receiver, builder.dispatcher);
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
        dispatcher.dispatchEvent(
                new Event<SimulationEventSource, SimulationEventDTO>(this,
                        SimulationEventDTO.visitorsArrive(visitors.length())));
        receiver.receive(visitors);
    }

    public static class Builder {
        private ArrivalStrategy arrivalStrategy;
        private VisitorReceiver receiver;
        private Dispatcher<SimulationEventDTO> dispatcher;

        public Builder set(ArrivalStrategy arrivalStrategy) {
            this.arrivalStrategy = arrivalStrategy;
            return this;
        }

        public Builder set(VisitorReceiver receiver) {
            this.receiver = receiver;
            return this;
        }

        public Builder set(Dispatcher<SimulationEventDTO> dispatcher) {
            this.dispatcher = dispatcher;
            return this;
        }

        public Ludoteca build() {
            return new Ludoteca(this);
        }
    }

}
