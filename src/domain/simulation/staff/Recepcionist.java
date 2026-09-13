package domain.simulation.staff;

import domain.game.GameManager;
import domain.game.Player;
import domain.game.PlayerReceiver;
import domain.reception.Visitor;
import domain.reception.VisitorReceiver;
import domain.simulation.SimulationEventDTO;
import lib.DataStructures.Lists.BoundedList;
import lib.DataStructures.Queues.UnboundedQueue;
import lib.Patterns.Common.Event;
import lib.Patterns.Dispatcher.Dispatcher;
import lib.Patterns.Dispatcher.EventSource;

/**
 * Receptionist
 */
public class Recepcionist extends Monitor implements VisitorReceiver, PlayerReceiver, EventSource {
    private final UnboundedQueue<Player> playerQueue;
    private GameManager manager;
    private final Dispatcher<SimulationEventDTO> dispatcher;

    private Recepcionist(String name, GameManager manager, UnboundedQueue<Player> playerQueue,
            Dispatcher<SimulationEventDTO> dispatcher) {
        super(name);
        this.playerQueue = playerQueue;
        this.manager = manager;
        this.dispatcher = dispatcher;
    }

    private Recepcionist(Builder builder) {
        this(builder.name, builder.manager, builder.playersQueue, builder.dispatcher);
    }

    @Override
    public void receive(UnboundedQueue<? extends Visitor> visitors) {
        while (!visitors.isEmpty()) {
            visitors.dequeue().inscribeAt(this);
        }
    }

    @Override
    public void inscribeAsPlayer(Player player) {
        playerQueue.enqueue(player);
        if (!manager.isPlaying()) {
            dispatchWaitingPlayersToManager();
        }
    }

    private void dispatchWaitingPlayersToManager() {
        while (!playerQueue.isEmpty()) {
            Player nextPlayer = playerQueue.peek();
            if (manager.addPlayer(nextPlayer)) {
                playerQueue.dequeue();
                dispatcher.dispatchEvent(
                        new Event<EventSource, SimulationEventDTO>(this,
                                SimulationEventDTO.visitorInscribeAsPlayer(nextPlayer.getName())));
            } else {
                break;
            }
        }
    }

    @Override
    public void receive(BoundedList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            playerQueue.enqueue(players.get(i));
        }
        dispatcher.dispatchEvent(
                new Event<EventSource, SimulationEventDTO>(this,
                        SimulationEventDTO.playersReturned(players.size())));
        if (!manager.isPlaying()) {
            dispatchWaitingPlayersToManager();
        }
    }

    public static class Builder {
        private String name;
        private GameManager manager;
        private UnboundedQueue<Player> playersQueue;
        private Dispatcher<SimulationEventDTO> dispatcher;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder set(GameManager manager) {
            this.manager = manager;
            return this;
        }

        public Builder set(UnboundedQueue<Player> playersQueue) {
            this.playersQueue = playersQueue;
            return this;
        }

        public Builder set(Dispatcher<SimulationEventDTO> dispatcher) {
            this.dispatcher = dispatcher;
            return this;
        }

        public Recepcionist build() {
            return new Recepcionist(this);
        }

    }

}
