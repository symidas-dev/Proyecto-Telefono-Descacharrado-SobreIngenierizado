package presentation.views;

import domain.time.Time;

public interface SimulationView {

    void updateTime(Time currentTime);

    void startSchedule(Time startTime);

    void startGame(String starterWord, Integer numberOfPlayers, String currentText, String bigChalkboardText);

    void playerTurn(String playerName, String currentText);

    void finishGame(String playerName, String bigChalkboardText);

    void visitorInscribeAsPlayer(String visitorName);

    void visitorsArrive(Integer numberOfArrivals);

    void playersReturned(Integer count);

}
