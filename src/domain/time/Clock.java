package domain.time;

import domain.simulation.SimulationEventDTO;
import lib.DataStructures.Lists.UnboundedList;
import lib.DataStructures.Lists.UnboundedSimpleLinkedList;
import lib.Patterns.Common.Event;
import lib.Patterns.Dispatcher.Dispatcher;
import lib.Patterns.Dispatcher.EventSource;
/**
 * Clock
 */
public class Clock implements TickObservable, EventSource {

    private final UnboundedList<TickObserver> observers;
    private Time currentTime;
    private Dispatcher<SimulationEventDTO> dispatcher;

    public Clock(Time currentTime, UnboundedList<TickObserver> observers, Dispatcher<SimulationEventDTO> dispatcher) {
        this.observers = observers;
        this.currentTime = currentTime;
        this.dispatcher = dispatcher;
    }

    public Clock(Time currentTime, UnboundedList<TickObserver> observers) {
        this(currentTime, observers, null);
    }

    public Clock(Dispatcher<SimulationEventDTO> dispatcher) {
        this(new Time(0, 0), new UnboundedSimpleLinkedList<TickObserver>(), dispatcher);
    }

    public Clock() {
        this(new Time(0, 0), new UnboundedSimpleLinkedList<TickObserver>());
    }

    /**
     * (non-Javadoc)
     * 
     * @see domain.time.TickObservable#addTickObserver(domain.time.TickObserver)
     */
    @Override
    public void addTickObserver(TickObserver observer) {
        observers.insert(observer);
    }

    /**
     * (non-Javadoc)
     * 
     * @see domain.time.TickObservable#removeTickObserver(domain.time.TickObserver)
     */
    @Override
    public void removeTickObserver(TickObserver observer) {
        observers.remove(observer);
    }
    public void setDispatcher(Dispatcher<SimulationEventDTO> dispatcher) {
        this.dispatcher = dispatcher;
    }


    public void nextTick() {
        currentTime = currentTime.nextTick();
        if (dispatcher != null) {
            dispatcher.dispatchEvent(new Event<EventSource, SimulationEventDTO>(this, SimulationEventDTO.tick(currentTime)));
        }
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).notifiedTick(new Event<TickObservable, Time>(this, currentTime));
        }
    }

    public Time getCurrentTime() {
        return currentTime;
    }

}
