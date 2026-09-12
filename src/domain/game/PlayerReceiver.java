package domain.game;

import lib.DataStructures.Lists.BoundedList;

/**
 * PlayerReceiver
 */
public interface PlayerReceiver {

    void receive(BoundedList<Player> players);

}
