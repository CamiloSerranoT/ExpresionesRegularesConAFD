
package negocio;

public class Nodo {

    Nodo ant;
    String dato;
    Nodo sig;

    public Nodo() {
        this.ant = null;
        this.dato = "";
        this.sig = null;
    }

    public Nodo(String dato) {
        this.ant = null;
        this.dato = dato;
        this.sig = null;
    }

    public Nodo getAnt() {
        return ant;
    }

    public void setAnt(Nodo ant) {
        this.ant = ant;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public Nodo getSig() {
        return sig;
    }

    public void setSig(Nodo sig) {
        this.sig = sig;
    }
    
}
