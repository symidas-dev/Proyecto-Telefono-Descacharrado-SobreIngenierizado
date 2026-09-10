package lib.Patterns.Observer;

import lib.DataStructures.Lists.UnboundedList;
import lib.DataStructures.Lists.UnboundedSimpleLinkedList;

public class Observable<Data> {
    UnboundedList<Observer<Data>> observers = new UnboundedSimpleLinkedList<Observer<Data>>();

    public void addObserver(Observer<Data> observer) {
        observers.insert(observer);
    }

    public void removeObserver(Observer<Data> observer) {
        observers.remove(observer);
    }

    public void notifyAllObservers(Data data) {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(new Event<Data>(this, data));
        }
    }

}
