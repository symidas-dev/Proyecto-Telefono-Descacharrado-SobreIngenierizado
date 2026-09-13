package lib.Patterns.Dispatcher;

import lib.DataStructures.Lists.UnboundedList;
import lib.DataStructures.Lists.UnboundedSimpleLinkedList;
import lib.Patterns.Common.Event;

public class Dispatcher<DispatchedType> {
    private final UnboundedList<Listener<DispatchedType>> listeners;

    public Dispatcher(UnboundedList<Listener<DispatchedType>> listeners) {
        this.listeners = listeners;
    }

    public Dispatcher() {
        this(new UnboundedSimpleLinkedList<Listener<DispatchedType>>());
    }

    public void add(Listener<DispatchedType> listener) {
        listeners.insert(listener);
    }

    public boolean remove(Listener<DispatchedType> listener) {
        return listeners.remove(listener);
    }

    public void dispatchEvent(Event<? extends EventSource, DispatchedType> dispatchedItem) {
        for (int i = 0; i < listeners.size(); ++i) {
            listeners.get(i).getNotification(dispatchedItem);
        }
    }
}
