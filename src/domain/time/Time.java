package domain.time;

/**
 * Time
 */
public class Time {
    private final static int MINUTES_PER_HOUR = 60;

    private final int TOTAL_MINUTES;

    public Time(int hours, int minutes) {
        this.TOTAL_MINUTES = hours * MINUTES_PER_HOUR + minutes;
    }

    private Time(int totalMinutes) {
        this.TOTAL_MINUTES = totalMinutes;
    }

    public Time add(Time time) {
        return new Time(this.TOTAL_MINUTES + time.TOTAL_MINUTES);
    }

    public boolean isLowerThan(Time time) {
        return this.TOTAL_MINUTES < time.TOTAL_MINUTES;
    }

    public Time nextTick() {
        return new Time(TOTAL_MINUTES + 1);
    }

    public boolean isBetweenIncludedBounderies(Time startTime, Time endTime) {
        return (isHigherThan(startTime) || isEqual(startTime)) && (isEqual(endTime) || isLowerThan(endTime));
    }

    public boolean isEqual(Time startTime) {
        return TOTAL_MINUTES == startTime.TOTAL_MINUTES;
    }

    public boolean isHigherThan(Time startTime) {
        return TOTAL_MINUTES > startTime.TOTAL_MINUTES;
    }
    @Override
    public String toString() {
        int hours = TOTAL_MINUTES / MINUTES_PER_HOUR;
        int minutes = TOTAL_MINUTES % MINUTES_PER_HOUR;
        return String.format("%02d:%02d", hours, minutes);
    }

}
