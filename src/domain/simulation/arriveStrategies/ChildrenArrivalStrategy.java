package domain.simulation.arriveStrategies;

import java.util.Random;

import domain.reception.Visitor;
import domain.simulation.ArrivalStrategy;
import domain.simulation.visitors.Child;
import domain.time.Clock;
import domain.time.Time;
import lib.DataStructures.Queues.UnboundedQueue;

/**
 * ChildrenArrivalStrategy
 */
public class ChildrenArrivalStrategy implements ArrivalStrategy {
    private final UnboundedQueue<Child> queue;

    public ChildrenArrivalStrategy(Clock clock, UnboundedQueue<Child> queue) {
        this.queue = queue;
        this.dayCountSinceLastArrival = 0;
    }

    private static final Time FIRST_SECTION_DURATION = new Time(0, 10);
    private static final double PROBABILITY_OF_CHILD_ARRIVE_FIRST_SECTION = 0.5;
    private static final Time SECOND_SECTION_DURATION = new Time(0, 10);
    private static final int[] RANGE_CHILDREN_PER_MINUTE_FIRST_SECTION = { 0, 3 };
    private int dayCountSinceLastArrival;

    @Override
    public UnboundedQueue<? extends Visitor> apply(Time currentTime) {
        if (currentTime.isLowerThan(FIRST_SECTION_DURATION)) {
            if (new Random().nextDouble() < PROBABILITY_OF_CHILD_ARRIVE_FIRST_SECTION) {
                for (int i = 0; i < childrenThatArrive(RANGE_CHILDREN_PER_MINUTE_FIRST_SECTION[0],
                        RANGE_CHILDREN_PER_MINUTE_FIRST_SECTION[1]); i++) {
                    queue.enqueue(new Child());
                }
            }
        } else if (currentTime.isLowerThan(FIRST_SECTION_DURATION.add(SECOND_SECTION_DURATION))) {
            if (dayCountSinceLastArrival == 0) {
                queue.enqueue(new Child());
            }

            dayCountSinceLastArrival = dayCountSinceLastArrival + 1 % 3;
        }

        return queue;
    }

    private int childrenThatArrive(int min, int max) {
        return new Random().nextInt(min, max);
    }

}
