package lib.Patterns.Observer;

public interface Observer<Data> {

    public void update(Event<Data> event);

}
