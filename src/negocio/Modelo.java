package negocio;

import java.util.regex.*;
import javax.swing.JOptionPane;

public class Modelo {

    private String entero;
    private String real;
    private String notCien;
    private String binario;
    private String email;
    private int lugarArreglo;
    static String funEntero = "(\\+|-)?[0-9]+";
    static String funReal = "(((/|\\.)?)((\\+|-)?[0-9]|e|pi))+";
    static String funNotCien = funReal + "E" + funEntero;
    static String funBinario = "[0-1]+";
    static String funEmail = "(\\d|[a-zA-Z])+@(\\d|[a-zA-Z])+(\\.)(com|co)";
    static int tamaño = 5;
    private int invalidos;

    public String getEntero() {
        return entero;
    }

    public void setEntero(String entero) {
        this.entero = entero;
    }

    public String getReal() {
        return real;
    }

    public void setReal(String real) {
        this.real = real;
    }

    public String getNotCien() {
        return notCien;
    }

    public void setNotCien(String notCien) {
        this.notCien = notCien;
    }

    public String getBinario() {
        return binario;
    }

    public void setBinario(String binario) {
        this.binario = binario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLugarArreglo() {
        return lugarArreglo;
    }

    public void setLugarArreglo(int lugarArreglo) {
        this.lugarArreglo = lugarArreglo;
    }

    public Modelo() {
        this.entero = "";
        this.real = "";
        this.notCien = "";
        this.binario = "";
        this.email = "";
        this.lugarArreglo = 0;
        this.invalidos = 0;
    }

    public void analizador() {
        boolean[] cont = new boolean[tamaño];
        String[] imp = new String[tamaño];
        int correctos = 0;

        cont[lugarArreglo] = busPalabra(funEntero, entero);
        cont[lugarArreglo] = busPalabra(funReal, real);
        cont[lugarArreglo] = busPalabra(funNotCien, notCien);
        cont[lugarArreglo] = busPalabra(funBinario, binario);
        cont[lugarArreglo] = busPalabra(funEmail, email);

        if (invalidos == 0) {
            for (int i = 0; i < cont.length; i++) {
                if (cont[i] == true) {
                    correctos++;
                }
            }

            if (correctos == 5) {
                JOptionPane.showMessageDialog(null, "Todos los campos estan correctos");
            } else {
                if (correctos == 0) {
                    JOptionPane.showMessageDialog(null, "Todos los campos generan error");
                } else {
                    imp = impresion(cont, imp);
                    JOptionPane.showMessageDialog(null, "Los datos correctos son:\n"
                            + imp[0] + imp[1] + imp[2] + imp[3] + imp[4]);
                }
                datosIncorrectos(cont, imp);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Uno o varios de los campos "
                    + "estan vacios\n     Llenarlos antes de volver a intentar");
        }
    }

    private Matcher fraseCompleta(String buscar, String frase) {
        Pattern busqueda = Pattern.compile(buscar);
        Matcher palabra = busqueda.matcher(frase);
        return palabra;
    }

    public boolean busPalabra(String patron, String cadena) {
        boolean comprobar = false;
        if (fraseCompleta(patron, cadena).matches()) {
            comprobar = true;
        } else {
            comprobar = false;
        }
        if (fraseCompleta("", cadena).matches()) {
            invalidos++;
        }
        setLugarArreglo(lugarArreglo + 1);
        return comprobar;
    }

    private String[] impresion(boolean[] cont, String[] imp) {
        for (int i = 0; i < tamaño; i++) {
            if (cont[i] == true) {
                switch (i) {
                    case 0:
                        imp[i] = " * Entero\n";
                        break;
                    case 1:
                        imp[i] = " * Real\n";
                        break;
                    case 2:
                        imp[i] = " * Notacion Cientifica\n";
                        break;
                    case 3:
                        imp[i] = " * Binario\n";
                        break;
                    case 4:
                        imp[i] = " * E-mail";
                        break;
                }
            } else {
                imp[i] = "";
            }
        }
        return imp;
    }

    public void datosIncorrectos(boolean[] cont, String[] imp) {
        Verificacion ver = new Verificacion();
        String incorrecto = "";

        imp[0] = ver.metodoEntero(entero, cont[0], incorrecto);
        imp[1] = ver.metodoReal(real, cont[1], incorrecto);
        imp[2] = ver.metodoNotCien(notCien, cont[2], incorrecto);
        imp[3] = ver.metodoBinario(binario, cont[3], incorrecto);
        imp[4] = ver.metodoEmail(email, cont[4], incorrecto);

        JOptionPane.showMessageDialog(null, "Caracteres que generan error:\n"
                + imp[0] + imp[1] + imp[2] + imp[3] + imp[4]);
    }

}
