package domain.time;

public interface TickObservable {

    void addTickObserver(TickObserver observer);

    void removeTickObserver(TickObserver observer);

}