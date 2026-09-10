package lib.Patterns.Observer;

public class Event<Data> {
    private final Observable<Data> SOURCE;
    private final Data DATA;

    public Event(Observable<Data> source, Data data) {
        this.SOURCE = source;
        this.DATA = data;
    }

    public Observable<Data> getSource() {
        return SOURCE;
    }

    public Data getData() {
        return DATA;
    }
}
