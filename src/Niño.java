import utils.RandomUtils;

/**
 * Niño
 */
public class Niño {
    private String nombre;
    private String apellido;

    private Pizarra pizarron;
    private Pizarra pizarrin;

    private Niño(Builder builder) {
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;

        this.pizarron = builder.pizarron;
        this.pizarrin = builder.pizarrin;
    }

    public void limpiarPizarrin() {
        pizarrin.limpiar();
    }

    public Pizarra lee(Pizarra pizarrin) {
        char[] palabra = pizarrin.leer();
        alterar(palabra);
        this.pizarrin.escribir(palabra);
        return this.pizarrin;
    }

    private void alterar(char[] palabra) {
        int letrasACambiar = RandomUtils.valorAleatorio(0, 2);
        int letrasCambiadas = 0;
        while (letrasCambiadas < letrasACambiar) {
            int posicion = RandomUtils.valorAleatorio(0, palabra.length - 1);
            palabra[posicion] = (char) RandomUtils.valorAleatorio('a', 'z');
            letrasCambiadas++;
        }
    }

    public void escribePizarron() {
        char[] palabra = pizarrin.leer();
        pizarron.escribir(palabra);
    }

    public static class Builder {
        private final String[] NOMBRES = { "Juan", "Pedro", "Maria", "Luis", "Ana", "Jose", "Marta", "Carlos",
                "Sofía" };
        private final String[] APELLIDOS = { "García", "Pérez", "López", "Martínez", "Gómez", "Rodríguez", "Fernández",
                "Hernández", "Martínez", "González" };

        private String nombre, apellido;
        private Pizarra pizarron, pizarrin;

        public Builder nombreAleatorio() {
            this.nombre = NOMBRES[RandomUtils.valorAleatorio(0, NOMBRES.length - 1)];
            return this;
        }

        public Builder apellidoAleatorio() {
            this.apellido = APELLIDOS[RandomUtils.valorAleatorio(0, APELLIDOS.length - 1)];
            return this;
        }

        public Builder indicarPizarron(Pizarra pizarron) {
            this.pizarron = pizarron;
            return this;
        }

        public Niño build() {
            this.pizarrin = new Pizarra();
            return new Niño(this);
        }
    }

}
