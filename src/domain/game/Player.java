package domain.game;

/**
 * Player
 */
public interface Player {

    void cleanSmallChalkboard();

    void write(String text);

    String show();

    void writeIn(String text, WritablePlace writablePlace);

    String getName();

}
