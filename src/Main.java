import domain.game.Player;
import domain.simulation.Ludoteca;
import domain.simulation.arriveStrategies.ChildrenArrivalStrategy;
import domain.simulation.staff.Manager;
import domain.simulation.staff.Recepcionist;
import domain.simulation.visitors.Child;
import domain.time.Clock;
import domain.time.Schedule;
import domain.time.Time;
import lib.DataStructures.Lists.BoundedSimpleLinkedList;
import lib.DataStructures.Queues.UnboundedSimpleLinkedQueue;

public class Main {
    public static void main(String[] args) {
        Clock clock = new Clock();
        Time startTime = new Time(12, 0);
        Time openFor = new Time(2, 0);
        Schedule schedule = new Schedule.Builder()
                .setStartTime(startTime)
                .setOpenForTime(openFor)
                .set(clock)
                .build();

        Manager manager = new Manager("Aisha", new BoundedSimpleLinkedList<Player>(5), "PEDAZOPETA");
        Recepcionist receiver = new Recepcionist("Lydia", manager, new UnboundedSimpleLinkedQueue<Player>());
        manager.setReceiver(receiver);

        Ludoteca ludoteca = new Ludoteca(new ChildrenArrivalStrategy(clock, new UnboundedSimpleLinkedQueue<Child>()),
                receiver);

        clock.addTickObserver(ludoteca);
        clock.addTickObserver(manager);
        schedule.start();
    }

}
