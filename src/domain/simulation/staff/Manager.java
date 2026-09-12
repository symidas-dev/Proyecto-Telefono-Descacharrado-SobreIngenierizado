package domain.simulation.staff;

import domain.game.GameManager;
import domain.game.Player;
import domain.game.PlayerReceiver;
import domain.simulation.telefonoDescacharrado.BigChalkboard;
import domain.simulation.telefonoDescacharrado.SmallChalkboard;
import domain.time.TickObservable;
import domain.time.TickObserver;
import domain.time.Time;
import lib.DataStructures.Lists.BoundedList;
import lib.Patterns.Observer.Event;

/**
 * Manager
 */
public class Manager extends Monitor implements GameManager, TickObserver {
    private final String STARTER_WORD;

    private final BoundedList<Player> players;

    private boolean isPlaying = false;
    private int minuteOfGame = 0;

    private final BigChalkboard bigChalkboard;
    private final SmallChalkboard smallChalkboard;

    private PlayerReceiver playerReceiver;

    public Manager(String name, BoundedList<Player> players, String starterWord) {
        super(name);
        this.STARTER_WORD = starterWord;
        this.players = players;
        this.bigChalkboard = new BigChalkboard();
        this.smallChalkboard = new SmallChalkboard();
    }

    @Override
    public void notifiedTick(Event<TickObservable, Time> event) {
        if (isPlaying) {
            if (minuteOfGame == 1) {
                Player player = players.get(minuteOfGame - 1);
                player.write(smallChalkboard.show());
                minuteOfGame++;
            } else if (minuteOfGame == players.size()) {
                Player lastPlayer = players.get(minuteOfGame - 2);
                Player currentPlayer = players.get(minuteOfGame - 1);
                currentPlayer.writeIn(lastPlayer.show(), bigChalkboard);
                minuteOfGame = 0;
                isPlaying = false;
                drainList();
            } else {
                Player lastPlayer = players.get(minuteOfGame - 2);
                Player currentPlayer = players.get(minuteOfGame - 1);
                currentPlayer.write(lastPlayer.show());
                minuteOfGame++;
            }
        } else if (players.isFull()) {
            isPlaying = true;
            minuteOfGame = 0;

            bigChalkboard.clean();
            smallChalkboard.clean();

            for (int i = 0; i < players.size(); i++) {
                Player player = players.get(i);
                player.cleanSmallChalkboard();
            }

            smallChalkboard.write(STARTER_WORD);
            minuteOfGame++;
        }
    }

    private void drainList() {
        playerReceiver.receive(players);
        players.clear();
    }

    @Override
    public boolean isPlaying() {
        return isPlaying;
    }

    @Override
    public boolean addPlayer(Player player) {
        return players.insert(player);
    }

    public void setReceiver(PlayerReceiver receiver) {
        this.playerReceiver = receiver;
    }

}
