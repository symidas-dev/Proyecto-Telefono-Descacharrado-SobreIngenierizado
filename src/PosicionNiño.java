/**
 * PosicionNiño
 */
public class PosicionNiño {
    private ColaNiños colaNiños;
    private PosicionNiño niñoSiguiente;
    private PosicionNiño niñoAnterior;
    private Niño niñoAsociado;

    public PosicionNiño(ColaNiños colaNiños, Niño niño) {
        this.colaNiños = colaNiños;
        this.niñoAsociado = niño;
    }

    public void setSiguiente(PosicionNiño niño) {
        this.niñoSiguiente = niño;
    }

    public Niño getNiño() {
        return niñoAsociado;
    }

    public PosicionNiño getSiguiente() {
        return niñoSiguiente;
    }

    public void lee(Pizarra pizarrin) {
        Pizarra pizarrinActual = niñoAsociado.lee(pizarrin);
        if (!colaNiños.esUltimo(this)) {
            niñoSiguiente.lee(pizarrinActual);
        } else {
            niñoAsociado.escribePizarron();
        }
    }

}
