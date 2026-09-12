package domain.reception;

import domain.game.Player;
import lib.DataStructures.Queues.UnboundedQueue;

/**
 * VisitorReceiver
 */
public interface VisitorReceiver {

    void receive(UnboundedQueue<? extends Visitor> visitors);

    void inscribeAsPlayer(Player player);

}
