package domain.simulation.telefonoDescacharrado;

/**
 * BigChalkboard
 */
public class BigChalkboard {
    private char[] text;

    public BigChalkboard() {
        text = "".toCharArray();
    }

    public void clean() {
        text = "".toCharArray();
    }

    public void write(String text) {
        this.text = text.toCharArray();
    }

}
