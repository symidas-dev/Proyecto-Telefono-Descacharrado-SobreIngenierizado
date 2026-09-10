package lib.Patterns.Observer;

import lib.DataStructures.Lists.UnboundedList;
import lib.DataStructures.Lists.UnboundedSimpleLinkedList;

public class Observable {
    UnboundedList<Observer> observers = new UnboundedSimpleLinkedList<Observer>();

    public void addObserver(Observer observer) {
        observers.insert(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyAllObservers() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update();
        }
    }
}
