package lib.Patterns.Observer;

import lib.DataStructures.Lists.SimpleLinkedList;

public class Observable {
    SimpleLinkedList<Observer> observers = new SimpleLinkedList<Observer>();

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
