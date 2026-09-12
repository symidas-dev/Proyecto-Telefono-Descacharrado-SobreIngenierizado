package domain.simulation.telefonoDescacharrado;

/**
 * Pizarra
 */
public class SmallChalkboard {

    private char[] text;

    public SmallChalkboard() {
        text = "".toCharArray();
    }

    public void clean() {
        text = "".toCharArray();
    }

    public void write(String string) {
        text = string.toCharArray();
    }

    public String show() {
        return new String(text);
    }

}
