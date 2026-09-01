/**
 * Organizadora
 */
public class Organizadora {
    private final int MAX_NIÑOS = 5;
    private ColaNiños colaNiños = new ColaNiños().withMaximo(MAX_NIÑOS);
    private Pizarra pizarron;
    private Pizarra pizarrin = new Pizarra();

    public Organizadora(Builder builder) {
        this.pizarron = builder.pizarron;
    }

    public void recibe(Niño niño) {
        colaNiños.encolar(niño);
        if (colaNiños.isCompleto()) {
            jugar();
        }
    }

    private void jugar() {
        pizarron.limpiar();
        pedirNiñosLimpiarPizarrines();
        pizarrin.escribir("bacalaosss".toCharArray());
        enseñarPizarrin(colaNiños.getPosicionPrimerNiño());
    }

    private void enseñarPizarrin(PosicionNiño posicionNiño) {
        posicionNiño.lee(pizarrin);
    }

    private void pedirNiñosLimpiarPizarrines() {
        PosicionNiño posicionNiñoActual = colaNiños.getPosicionPrimerNiño();
        posicionNiñoActual.getNiño().limpiarPizarrin();

        while (!colaNiños.esUltimo(posicionNiñoActual)) {
            posicionNiñoActual = posicionNiñoActual.getSiguiente();
            posicionNiñoActual.getNiño().limpiarPizarrin();
        }
    }

    public boolean estaLibre() {
        return !this.colaNiños.isCompleto();
    }

    public static class Builder {
        Pizarra pizarron;

        public Builder asignarPizarron(Pizarra pizarron) {
            this.pizarron = pizarron;
            return this;
        }

        public Organizadora build() {
            return new Organizadora(this);
        }
    }

}
