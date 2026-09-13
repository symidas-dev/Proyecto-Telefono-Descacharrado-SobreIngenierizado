package domain.simulation;

import domain.time.Time;
import lib.Patterns.Common.EventDTO;

/**
 * SimulationEventDTO
 */
public class SimulationEventDTO implements EventDTO {

    public static enum Type {
        START_SCHEDULE,
        TICK,
        START_GAME,
        CHILD_TURN,
        FINISHED_GAME,
        VISITOR_INSCRIBE_AS_PLAYER,
        VISITORS_ARRIVE,
        PLAYERS_RETURNED
    }

    public final Type TYPE;

    private SimulationEventDTO(Builder builder) {
        this.TYPE = builder.type;
        this.START_TIME = builder.startTime;
        this.STARTER_WORD = builder.starterWord;
        this.NUMBER_OF_PLAYERS = builder.numberOfPlayers;
        this.CURRENT_TEXT = builder.currentText;
        this.BIG_CHALKBOARD_TEXT = builder.bigChalkboardText;
        this.VISITOR_NAME = builder.visitorName;
        this.NUMBER_OF_ARRIVALS = builder.numberOfArrivals;
        this.PLAYER_NAME = builder.playerName;
    }

    private static class Builder {
        private Type type;
        private Time startTime = null;
        private String starterWord = null;
        private Integer numberOfPlayers = null;
        private String currentText = null;
        private String bigChalkboardText = null;
        private String visitorName = null;
        private Integer numberOfArrivals = null;
        private String playerName = null;

        private SimulationEventDTO build() {
            return new SimulationEventDTO(this);
        }

        private Builder setStartTime(Time startTime) {
            this.startTime = startTime;
            return this;
        }

        private Builder setType(Type type) {
            this.type = type;
            return this;
        }

        private Builder setStarterWord(String starterWord) {
            this.starterWord = starterWord;
            return this;
        }

        private Builder setNumberOfPlayers(int numberOfPlayers) {
            this.numberOfPlayers = numberOfPlayers;
            return this;
        }

        private Builder setCurrentText(String smallChalkboardText) {
            this.currentText = smallChalkboardText;
            return this;
        }

        public Builder setBigChalkboardText(String bigChalkboardText) {
            this.bigChalkboardText = bigChalkboardText;
            return this;
        }

        public Builder setVisitorName(String visitorName) {
            this.visitorName = visitorName;
            return this;
        }

        public Builder setNumberOfArrivals(int numberOfArrivals) {
            this.numberOfArrivals = numberOfArrivals;
            return this;
        }

        public Builder setPlayerName(String playerName) {
            this.playerName = playerName;
            return this;
        }
    }

    public final Time START_TIME;

    public static SimulationEventDTO startSchedule(Time startTime) {
        return new SimulationEventDTO.Builder()
                .setType(Type.START_SCHEDULE)
                .setStartTime(startTime)
                .build();
    }

    public final String CURRENT_TEXT;

    public final String STARTER_WORD;
    public final Integer NUMBER_OF_PLAYERS;
    public final String BIG_CHALKBOARD_TEXT;

    public static SimulationEventDTO startGame(
            String starterWord,
            int numberOfPlayers,
            String smallChalkboardText,
            String bigChalkboardText) {
        return new SimulationEventDTO.Builder()
                .setType(Type.START_GAME)
                .setStarterWord(starterWord)
                .setNumberOfPlayers(numberOfPlayers)
                .setCurrentText(smallChalkboardText)
                .setBigChalkboardText(bigChalkboardText)
                .build();
    }

    public final String PLAYER_NAME;

    public static SimulationEventDTO playerTurn(String playerName, String currentText) {
        return new SimulationEventDTO.Builder()
                .setType(Type.CHILD_TURN)
                .setPlayerName(playerName)
                .setCurrentText(currentText)
                .build();
    }

    public static SimulationEventDTO finishGame(String playerName, String bigChalkboardText) {
        return new SimulationEventDTO.Builder()
                .setType(Type.FINISHED_GAME)
                .setPlayerName(playerName)
                .setBigChalkboardText(bigChalkboardText)
                .build();
    }

    public final String VISITOR_NAME;

    public static SimulationEventDTO visitorInscribeAsPlayer(String name) {
        return new SimulationEventDTO.Builder()
                .setType(Type.VISITOR_INSCRIBE_AS_PLAYER)
                .setVisitorName(name)
                .build();
    }

    public final Integer NUMBER_OF_ARRIVALS;

    public static SimulationEventDTO visitorsArrive(int numberOfArrivals) {
        return new SimulationEventDTO.Builder()
                .setType(Type.VISITORS_ARRIVE)
                .setNumberOfArrivals(numberOfArrivals)
                .build();

    }
    public static SimulationEventDTO tick(Time currentTime) {
        return new Builder()
                .setType(Type.TICK)
                .setStartTime(currentTime)
                .build();
    }
    public static SimulationEventDTO playersReturned(int count) {
        return new Builder()
                .setType(Type.PLAYERS_RETURNED)
                .setNumberOfArrivals(count)
                .build();
    }

}
