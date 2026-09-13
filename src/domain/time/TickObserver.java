package domain.time;

import lib.Patterns.Common.Event;

/**
 * TickObserver
 */
public interface TickObserver {

    void notifiedTick(Event<TickObservable, Time> event);

}
