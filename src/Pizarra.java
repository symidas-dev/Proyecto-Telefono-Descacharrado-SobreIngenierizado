/**
 * Pizarra
 */
public class Pizarra {
    private Palabra palabraAsociada;

    public void limpiar() {
        palabraAsociada = null;
    }

    public void escribir(char[] palabra) {
        palabraAsociada = new Palabra(palabra);
    }

    public char[] leer() {
        assert palabraAsociada != null : "No puedes mostrar si no se ha escrito nada";

        return palabraAsociada.getCharacters().clone();
    }

}
