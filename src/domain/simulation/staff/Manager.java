package domain.simulation.staff;

import domain.game.GameManager;
import domain.game.Player;
import domain.game.PlayerReceiver;
import domain.simulation.SimulationEventDTO;
import domain.simulation.chalkboards.BigChalkboard;
import domain.simulation.chalkboards.SmallChalkboard;
import domain.time.TickObservable;
import domain.time.TickObserver;
import domain.time.Time;
import lib.DataStructures.Lists.BoundedList;
import lib.DataStructures.Lists.BoundedSimpleLinkedList;
import lib.Patterns.Common.Event;
import lib.Patterns.Dispatcher.Dispatcher;
import lib.Patterns.Dispatcher.EventSource;

/**
 * Manager
 */
public class Manager extends Monitor implements GameManager, TickObserver, EventSource {
    private final String STARTER_WORD;

    private final BoundedList<Player> players;

    private boolean isPlaying = false;
    private int minuteOfGame = 0;

    private final BigChalkboard bigChalkboard;
    private final SmallChalkboard smallChalkboard;

    private PlayerReceiver playerReceiver;

    private final Dispatcher<SimulationEventDTO> dispatcher;

    private Manager(String name, BoundedList<Player> players, String starterWord,
            Dispatcher<SimulationEventDTO> dispatcher) {
        super(name);
        this.STARTER_WORD = starterWord;
        this.players = players;
        this.bigChalkboard = new BigChalkboard();
        this.smallChalkboard = new SmallChalkboard();
        this.dispatcher = dispatcher;
    }

    private Manager(Builder builder) {
        this(builder.name, builder.players, builder.starterWord, builder.dispatcher);
    }

    public static class Builder {
        private String name;
        private String starterWord;
        private BoundedList<Player> players;
        private Dispatcher<SimulationEventDTO> dispatcher;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder set(BoundedList<Player> boundedList) {
            this.players = boundedList;
            return this;
        }

        public Builder setStarterWord(String starterWord) {
            this.starterWord = starterWord;
            return this;
        }

        public Builder set(Dispatcher<SimulationEventDTO> dispatcher) {
            this.dispatcher = dispatcher;
            return this;
        }

        public Manager build() {
            return new Manager(this);
        }
    }

    @Override
    public void notifiedTick(Event<TickObservable, Time> timeEvent) {
        if (isPlaying) {
            if (minuteOfGame == 1) {
                Player player = players.get(minuteOfGame - 1);
                player.write(smallChalkboard.show());

                dispatcher.dispatchEvent(new Event<EventSource, SimulationEventDTO>(this,
                        SimulationEventDTO.playerTurn(
                                player.getName(),
                                player.show())));

                minuteOfGame++;
            } else if (minuteOfGame == players.size()) {
                Player lastPlayer = players.get(minuteOfGame - 2);
                Player currentPlayer = players.get(minuteOfGame - 1);
                currentPlayer.writeIn(lastPlayer.show(), bigChalkboard);

                dispatcher.dispatchEvent(new Event<EventSource, SimulationEventDTO>(this,
                        SimulationEventDTO.finishGame(
                                currentPlayer.getName(),
                                bigChalkboard.show())));

                minuteOfGame = 0;
                isPlaying = false;
                drainList();
            } else {
                Player lastPlayer = players.get(minuteOfGame - 2);
                Player currentPlayer = players.get(minuteOfGame - 1);
                currentPlayer.write(lastPlayer.show());

                dispatcher.dispatchEvent(new Event<EventSource, SimulationEventDTO>(this,
                        SimulationEventDTO.playerTurn(
                                currentPlayer.getName(),
                                currentPlayer.show())));

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

            dispatcher.dispatchEvent(new Event<EventSource, SimulationEventDTO>(this,
                    SimulationEventDTO.startGame(
                            STARTER_WORD,
                            players.size(),
                            smallChalkboard.show(),
                            bigChalkboard.show())));

            minuteOfGame++;
        }
    }

    private void drainList() {
        BoundedList<Player> finishedPlayers = new BoundedSimpleLinkedList<Player>(players.size());
        for (int i = 0; i < players.size(); i++) {
            finishedPlayers.insert(players.get(i));
        }
        players.clear();
        playerReceiver.receive(finishedPlayers);
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
