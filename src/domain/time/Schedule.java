package domain.time;

/**
 * Schedule
 */
public class Schedule {

    public final Time startTime;
    public final Time endTime;
    public final Clock clock;

    public Schedule(Builder builder) {
        this.startTime = builder.resolvedStartTime;
        this.endTime = builder.resolvedEndTime;
        this.clock = builder.clock;
    }

    public static class Builder {
        private Time startTime;
        private Time endTime;
        private Time openFor;

        private Time resolvedStartTime;
        private Time resolvedEndTime;
        private Clock clock;

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
    }

    public void start() {
        while (startTime.add(clock.getCurrentTime()).isBetweenIncludedBounderies(startTime, endTime)) {
            clock.nextTick();
        }
    }

}
