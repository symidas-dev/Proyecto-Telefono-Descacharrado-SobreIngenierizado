import domain.game.Player;
import domain.simulation.Ludoteca;
import domain.simulation.Schedule;
import domain.simulation.SimulationEventDTO;
import domain.simulation.arriveStrategies.ChildrenArrivalStrategy;
import domain.simulation.staff.Manager;
import domain.simulation.staff.Recepcionist;
import domain.simulation.visitors.Child;
import domain.time.Clock;
import domain.time.Time;
import lib.DataStructures.Lists.BoundedSimpleLinkedList;
import lib.DataStructures.Queues.UnboundedSimpleLinkedQueue;
import lib.Patterns.Dispatcher.Dispatcher;
import presentation.presenters.SimulationPresenter;
import presentation.views.ConsoleSimulationView;
import presentation.views.SimulationView;

public class Main {
        public static void main(String[] args) {
                Dispatcher<SimulationEventDTO> dispatcher = new Dispatcher<>();
                SimulationView view = new ConsoleSimulationView();
                SimulationPresenter presenter = new SimulationPresenter(view);
                dispatcher.add(presenter);

                Clock clock = new Clock(dispatcher);
                Time startTime = new Time(12, 0);
                Time openFor = new Time(2, 0);
                Schedule schedule = new Schedule.Builder()
                                .setStartTime(startTime)
                                .setOpenForTime(openFor)
                                .set(clock)
                                .set(dispatcher)
                                .build();

                Manager manager = new Manager.Builder()
                                .setName("Aisha")
                                .set(new BoundedSimpleLinkedList<Player>(5))
                                .setStarterWord("PEDAZOPETA")
                                .set(dispatcher)
                                .build();

                Recepcionist receiver = new Recepcionist.Builder()
                                .setName("Lydia")
                                .set(manager)
                                .set(new UnboundedSimpleLinkedQueue<Player>())
                                .set(dispatcher)
                                .build();

                Ludoteca ludoteca = new Ludoteca.Builder()
                                .set(new ChildrenArrivalStrategy(clock, new UnboundedSimpleLinkedQueue<Child>()))
                                .set(receiver)
                                .set(dispatcher)
                                .build();

                manager.setReceiver(receiver);
                clock.addTickObserver(ludoteca);
                clock.addTickObserver(manager);
                schedule.start();
        }

}
