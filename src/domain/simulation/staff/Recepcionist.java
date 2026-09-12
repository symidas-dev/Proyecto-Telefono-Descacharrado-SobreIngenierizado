package domain.simulation.staff;

import domain.game.GameManager;
import domain.game.Player;
import domain.game.PlayerReceiver;
import domain.reception.Visitor;
import domain.reception.VisitorReceiver;
import lib.DataStructures.Lists.BoundedList;
import lib.DataStructures.Queues.UnboundedQueue;

/**
 * Receptionist
 */
public class Recepcionist extends Monitor implements VisitorReceiver, PlayerReceiver {
    private final UnboundedQueue<Player> playerQueue;
    private GameManager manager;

    public Recepcionist(String name, GameManager manager, UnboundedQueue<Player> playerQueue) {
        super(name);
        this.playerQueue = playerQueue;
        this.manager = manager;
    }

    @Override
    public void receive(UnboundedQueue<? extends Visitor> visitors) {
        while (!visitors.isEmpty()) {
            visitors.dequeue().inscribeAt(this);
        }
    }

    @Override
    public void inscribeAsPlayer(Player player) {
        Player playerToDeliver = player;

        if (!playerQueue.isEmpty()) {
            playerToDeliver = playerQueue.dequeue();
            playerQueue.enqueue(player);
        }

        if (!manager.isPlaying()) {
            if (!manager.addPlayer(playerToDeliver)) {
                playerQueue.enqueue(playerToDeliver);
            }
        }
    }

    @Override
    public void receive(BoundedList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            inscribeAsPlayer(players.get(i));
        }
    }

}
