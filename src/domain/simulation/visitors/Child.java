package domain.simulation.visitors;

import java.util.Random;

import domain.game.Player;
import domain.game.WritablePlace;
import domain.reception.Visitor;
import domain.reception.VisitorReceiver;
import domain.simulation.chalkboards.SmallChalkboard;

/**
 * Child
 */
public class Child implements Visitor, Player {
    private final String name;
    private final SmallChalkboard smallChalkboard = new SmallChalkboard();

    public Child(String name) {
        this.name = name;
    }

    @Override
    public void inscribeAt(VisitorReceiver receiver) {
        receiver.inscribeAsPlayer(this);
    }

    @Override
    public void cleanSmallChalkboard() {
        smallChalkboard.clean();
    }

    @Override
    public void write(String text) {
        text = alter(text);
        smallChalkboard.write(text);
    }

    private String alter(String text) {
        char[] chars = text.toCharArray();
        int letrasACambiar = new Random().nextInt(0, 3);

        for (int letrasCambiadas = 0; letrasCambiadas < letrasACambiar; letrasCambiadas++) {
            chars[new Random().nextInt(chars.length)] = (char) ('a' + new Random().nextInt(26));
        }

        return new String(chars);
    }

    @Override
    public String show() {
        return smallChalkboard.show();
    }

    @Override
    public void writeIn(String text, WritablePlace writeablePlace) {
        writeablePlace.write(text);
    }

    @Override
    public String getName() {
        return name;
    }

}
