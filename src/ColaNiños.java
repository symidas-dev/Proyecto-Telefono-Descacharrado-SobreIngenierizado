/**
 * ColaNiños
 */
public class ColaNiños {
    private final static int FLAG_SIN_LIMITE = -1;
    private final int MAX_NIÑOS;
    private int cantidadNiños = 0;

    private PosicionNiño primerNiño;
    private PosicionNiño ultimoNiño;

    public ColaNiños() {
        this(FLAG_SIN_LIMITE, null, null);
    }

    private ColaNiños(int maxNiños, PosicionNiño primerNiño, PosicionNiño últimoNiño) {
        this.MAX_NIÑOS = maxNiños;
        this.primerNiño = primerNiño;
        this.ultimoNiño = últimoNiño;
    }

    public void encolar(Niño niño) {
        assert !isCompleto() : "No hay espacio en la cola";

        PosicionNiño nuevaPosicionNiño = new PosicionNiño(this, niño);
        if (cantidadNiños == 0) {
            this.ultimoNiño = nuevaPosicionNiño;
            this.primerNiño = nuevaPosicionNiño;
        } else {
            ultimoNiño.setSiguiente(nuevaPosicionNiño);
            this.ultimoNiño = nuevaPosicionNiño;
        }
        this.cantidadNiños++;
    }

    public Niño desencolar() {
        assert !isVacio() : "No puedes desencolar una cola vacía";
        assert primerNiño != null : "No hay niñ@s en la cola";

        PosicionNiño posiciónNiño = this.primerNiño;
        this.primerNiño = posiciónNiño.getSiguiente();
        this.cantidadNiños--;
        return posiciónNiño.getNiño();
    }

    public ColaNiños withMaximo(int maxNiños) {
        return new ColaNiños(maxNiños, this.primerNiño, this.ultimoNiño);
    }

    public boolean isCompleto() {
        assert this.MAX_NIÑOS != -1 : "No hay limite en una cola infinita. Hay algún error lógico";

        return this.cantidadNiños >= this.MAX_NIÑOS;
    }

    private boolean isVacio() {
        return cantidadNiños <= 0;
    }

    public PosicionNiño getPosicionPrimerNiño() {
        return this.primerNiño;
    }

    public boolean esUltimo(PosicionNiño posicionNiñoActual) {
        return posicionNiñoActual == ultimoNiño;
    }

}
