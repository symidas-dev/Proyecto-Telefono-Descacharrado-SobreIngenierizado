package domain.simulation.arriveStrategies;

import java.util.Random;

import domain.reception.Visitor;
import domain.simulation.ArrivalStrategy;
import domain.simulation.visitors.Child;
import domain.time.Clock;
import domain.time.Time;
import lib.DataStructures.Queues.UnboundedQueue;
import lib.Generators.RandomNameGenerator;

/**
 * ChildrenArrivalStrategy
 */
public class ChildrenArrivalStrategy implements ArrivalStrategy {
    private final UnboundedQueue<Child> queue;

    public ChildrenArrivalStrategy(Clock clock, UnboundedQueue<Child> queue) {
        this.queue = queue;
        this.minuteCountSinceLastArrival = 0;
    }

    private static final Time FIRST_SECTION_DURATION = new Time(0, 10);
    private static final Time SECOND_SECTION_DURATION = new Time(0, 20);
    private static final int[] RANGE_CHILDREN_PER_MINUTE_FIRST_SECTION = { 0, 3 };
    private int minuteCountSinceLastArrival;

    @Override
    public UnboundedQueue<? extends Visitor> apply(Time currentTime) {
        Random random = new Random();
        if (currentTime.isLowerThan(FIRST_SECTION_DURATION)) {
            int arrivals = childrenThatArrive(RANGE_CHILDREN_PER_MINUTE_FIRST_SECTION[0],
                    RANGE_CHILDREN_PER_MINUTE_FIRST_SECTION[1]);
            for (int i = 0; i < arrivals; i++) {
                queue.enqueue(new Child(RandomNameGenerator.apply()));
            }
        } else if (currentTime.isLowerThan(FIRST_SECTION_DURATION.add(SECOND_SECTION_DURATION))) {
            minuteCountSinceLastArrival = (minuteCountSinceLastArrival + 1) % 3;
            if (minuteCountSinceLastArrival == 0 && random.nextDouble() < 0.5) {
                queue.enqueue(new Child(RandomNameGenerator.apply()));
            }
        }

        return queue;
    }

    private int childrenThatArrive(int min, int max) {
        return new Random().nextInt(min, max);
    }

}
