package domain.time;

import lib.DataStructures.Lists.UnboundedList;
import lib.DataStructures.Lists.UnboundedSimpleLinkedList;
import lib.Patterns.Observer.Event;

/**
 * Clock
 */
public class Clock implements TickObservable {

    private final UnboundedList<TickObserver> observers;
    private Time currentTime;

    public Clock(Time currentTime, UnboundedList<TickObserver> observers) {
        this.observers = observers;
        this.currentTime = currentTime;
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

    public void nextTick() {
        currentTime = currentTime.nextTick();
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).notifiedTick(new Event<TickObservable, Time>(this, currentTime));
        }
    }

    public Time getCurrentTime() {
        return currentTime;
    }

}
