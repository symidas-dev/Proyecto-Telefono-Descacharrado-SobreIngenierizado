package domain.simulation;

import domain.time.Clock;
import domain.time.Time;
import lib.Patterns.Common.Event;
import lib.Patterns.Dispatcher.Dispatcher;
import lib.Patterns.Dispatcher.EventSource;

/**
 * Schedule
 */
public class Schedule implements SimulationEventSource {

    public final Time startTime;
    public final Time endTime;
    public final Clock clock;
    private final Dispatcher<SimulationEventDTO> dispatcher;

    public Schedule(Builder builder) {
        this.startTime = builder.resolvedStartTime;
        this.endTime = builder.resolvedEndTime;
        this.clock = builder.clock;
        this.dispatcher = builder.dispatcher;
    }

    public static class Builder {
        private Time startTime;
        private Time endTime;
        private Time openFor;

        private Time resolvedStartTime;
        private Time resolvedEndTime;
        private Clock clock;
        private Dispatcher<SimulationEventDTO> dispatcher;

        public Schedule build() {
            if (startTime == null) {
                throw new IllegalArgumentException("startTime is required");
            }

            if (endTime != null && openFor != null) {
                throw new IllegalArgumentException("endTime and openFor can't be used together");
            }

            if (endTime == null && openFor == null) {
                throw new IllegalArgumentException("endTime or openFor is required");
            }

            this.resolvedStartTime = startTime;

            if (openFor != null) {
                resolvedEndTime = startTime.add(openFor);
            }

            if (endTime != null) {
                resolvedEndTime = endTime;
            }

            return new Schedule(this);
        }

        public Builder setStartTime(Time startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder setOpenForTime(Time openFor) {
            this.openFor = openFor;
            return this;
        }

        public Builder set(Clock clock) {
            this.clock = clock;
            return this;
        }

        public Builder set(Dispatcher<SimulationEventDTO> dispatcher) {
            this.dispatcher = dispatcher;
            return this;
        }
    }

    public void start() {
        dispatcher.dispatchEvent(
                new Event<EventSource, SimulationEventDTO>(this, SimulationEventDTO.startSchedule(startTime)));
        while (startTime.add(clock.getCurrentTime()).isBetweenIncludedBounderies(startTime, endTime)) {
            clock.nextTick();
        }
    }

}
