package lib.Patterns.Observer;

/**
 * Event
 */
public class Event<SourceType, EventType> {
    SourceType source;
    EventType event;

    public Event(SourceType source, EventType event) {
        this.source = source;
        this.event = event;
    }

    public SourceType getSource() {
        return source;
    }

    public EventType getEvent() {
        return event;
    }

}
