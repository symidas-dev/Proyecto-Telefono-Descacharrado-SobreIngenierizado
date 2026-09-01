/**
 * Recepcionista
 */
public class Recepcionista {
    private Organizadora organizadoraAsociada;
    private ColaNiños colaNiños;

    private Recepcionista(Recepcionista.Builder builder) {
        this.organizadoraAsociada = builder.organizadora;
        this.colaNiños = builder.colaNiños;
    }

    public void recibe(Niño niño) {
        colaNiños.encolar(niño);

        if (organizadoraAsociada.estaLibre()) {
            Niño unNiño = colaNiños.desencolar();
            organizadoraAsociada.recibe(unNiño);
        }
    }

    public static class Builder {
        private Organizadora organizadora;
        private ColaNiños colaNiños;

        public Builder asignarOrganizadora(Organizadora organizadora) {
            this.organizadora = organizadora;
            return this;
        }

        public Recepcionista build() {
            this.colaNiños = new ColaNiños();
            return new Recepcionista(this);
        }
    }

}
