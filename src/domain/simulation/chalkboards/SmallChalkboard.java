package domain.simulation.chalkboards;

import domain.game.WritablePlace;

/**
 * Pizarra
 */
public class SmallChalkboard implements WritablePlace {

    private char[] text;

    public SmallChalkboard() {
        text = "".toCharArray();
    }

    public void clean() {
        text = "".toCharArray();
    }

    @Override
    public void write(String string) {
        text = string.toCharArray();
    }

    public String show() {
        return new String(text);
    }

}
