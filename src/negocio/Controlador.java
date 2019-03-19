package negocio;

public class Controlador {

    public Controlador() {
    }

    public void validar(String entero, String real, String notCien, String binario, String email) {
        Modelo modelo = new Modelo();
        modelo.setEntero(entero);
        modelo.setReal(real);
        modelo.setNotCien(notCien);
        modelo.setBinario(binario);
        modelo.setEmail(email);

        modelo.analizador();
    }

    public void salir() {
        System.exit(0);
    }

}
