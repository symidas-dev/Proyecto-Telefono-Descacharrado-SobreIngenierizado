package domain.game;

import domain.simulation.telefonoDescacharrado.BigChalkboard;

/**
 * Player
 */
public interface Player {

    void cleanSmallChalkboard();

    void write(String text);

    String show();

    void writeIn(String text, BigChalkboard bigChalkboard);

}
