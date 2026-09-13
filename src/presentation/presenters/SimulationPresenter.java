package presentation.presenters;

import domain.simulation.SimulationEventDTO;
import lib.Patterns.Common.Event;
import lib.Patterns.Dispatcher.EventSource;
import lib.Patterns.Dispatcher.Listener;
import presentation.views.SimulationView;

public class SimulationPresenter implements Listener<SimulationEventDTO> {
    private SimulationView view;

    public SimulationPresenter(SimulationView view) {
        this.view = view;
    }

    @Override
    public void getNotification(Event<? extends EventSource, SimulationEventDTO> dispatchedItem) {
        SimulationEventDTO event = dispatchedItem.getEvent();
        switch (event.TYPE) {
            case START_SCHEDULE -> {
                view.startSchedule(event.START_TIME);
            }
            case TICK -> {
                view.updateTime(event.START_TIME);
            }
            case START_GAME -> {
                view.startGame(
                        event.STARTER_WORD,
                        event.NUMBER_OF_PLAYERS,
                        event.CURRENT_TEXT,
                        event.BIG_CHALKBOARD_TEXT);
            }
            case CHILD_TURN -> {
                view.playerTurn(event.PLAYER_NAME, event.CURRENT_TEXT);
            }
            case FINISHED_GAME -> {
                view.finishGame(event.PLAYER_NAME, event.BIG_CHALKBOARD_TEXT);
            }
            case VISITOR_INSCRIBE_AS_PLAYER -> {
                view.visitorInscribeAsPlayer(event.VISITOR_NAME);
            }
            case VISITORS_ARRIVE -> {
                view.visitorsArrive(event.NUMBER_OF_ARRIVALS);
            }
            case PLAYERS_RETURNED -> {
                view.playersReturned(event.NUMBER_OF_ARRIVALS);
            }
            default -> {
                throw new IllegalStateException("Event should have a TYPE");
            }
        }
    }

}
