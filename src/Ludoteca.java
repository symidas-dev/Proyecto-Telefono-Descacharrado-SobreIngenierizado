public class Ludoteca {
    private final Organizadora aisha;
    private final Recepcionista lydia;
    private final Pizarra pizarron;

    public static void main(String[] args) {
        Ludoteca ludoteca = new Ludoteca();
        ludoteca.acogerNiño();
    }

    public Ludoteca() {
        pizarron = new Pizarra();
        aisha = new Organizadora.Builder()
                .asignarPizarron(pizarron)
                .build();
        lydia = new Recepcionista.Builder()
                .asignarOrganizadora(aisha)
                .build();
    }

    private void acogerNiño() {
        for (int i = 0; i < 20; i++) {
            Niño niño = new Niño.Builder()
                    .nombreAleatorio()
                    .apellidoAleatorio()
                    .indicarPizarron(pizarron)
                    .build();
            lydia.recibe(niño);
        }
    }
}
