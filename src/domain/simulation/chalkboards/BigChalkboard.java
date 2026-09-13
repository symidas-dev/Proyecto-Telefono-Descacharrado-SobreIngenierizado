package domain.simulation.chalkboards;

import domain.game.WritablePlace;

/**
 * BigChalkboard
 */
public class BigChalkboard implements WritablePlace {
    private char[] text;

    public BigChalkboard() {
        text = "".toCharArray();
    }

    public void clean() {
        text = "".toCharArray();
    }

    @Override
    public void write(String text) {
        this.text = text.toCharArray();
    }

    public String show() {
        return new String(text);
    }

}
