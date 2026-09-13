package lib.Patterns.Dispatcher;

import lib.Patterns.Common.Event;

public interface Listener<DispatchedType> {

    void getNotification(Event<? extends EventSource, DispatchedType> dispatchedItem);

}
