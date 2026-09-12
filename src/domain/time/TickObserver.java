package domain.time;

import lib.Patterns.Observer.Event;

/**
 * TickObserver
 */
public interface TickObserver {

    void notifiedTick(Event<TickObservable, Time> event);

}
