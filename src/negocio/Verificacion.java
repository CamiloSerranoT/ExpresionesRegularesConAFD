package negocio;

public class Verificacion {

    Nodo raiz = new Nodo();
    Nodo cola = new Nodo();
    Modelo model = new Modelo();

    public Verificacion() {
        this.raiz = null;
        this.cola = null;
        vaciar();
    }

    public void agregar(String dato) {
        Nodo nuevo = new Nodo(dato);

        if (raiz == null) {
            raiz = nuevo;
            cola = nuevo;
        } else {
            if (raiz.getSig() == null) {
                raiz.setSig(nuevo);
                nuevo.setAnt(raiz);
                cola = nuevo;
            } else {
                cola.setSig(nuevo);
                nuevo.setAnt(cola);
                cola = nuevo;
            }
        }
    }

    public String eliminar() {
        String dato = "";
        if (raiz != null) {
            dato = raiz.getDato();
            if (raiz.getSig() == null) {
                raiz = null;
                cola = null;
            } else {
                if (raiz.getSig() == cola) {
                    raiz.setSig(null);
                    cola.setAnt(null);
                    raiz = cola;
                } else {
                    Nodo opcional = raiz.getSig();
                    raiz.setSig(null);
                    opcional.setAnt(null);
                    raiz = opcional;
                }
            }
        }
        return dato;
    }

    public String metodoEntero(String parametro, boolean valido, String incorrecto) {
        boolean com = true;
        boolean empezarImp = false;

        if (valido == false) {
            for (int i = 0; i < parametro.length(); i++) {
                agregar(Character.toString(parametro.charAt(i)));
            }

            incorrecto = "Entero:\n";
            if (model.busPalabra("\\+|-|\\d", raiz.getDato()) == true) {
                eliminar();
            } else {
                com = false;
                incorrecto = incorrecto + "Todos los caracteres generan error\n\n";
            }

            if (com == true) {
                do {
                    if (model.busPalabra("\\d", raiz.getDato()) == true && empezarImp == false) {
                        eliminar();
                    } else {
                        empezarImp = true;
                        incorrecto = incorrecto + eliminar();
                    }
                } while (raiz != null);
                incorrecto = incorrecto + "\n\n";
            }
        }
        return incorrecto;
    }

    public String metodoReal(String parametro, boolean valido, String incorrecto) {
        boolean com = true;
        int pasos = 0;

        vaciar();
        if (valido == false) {
            for (int i = 0; i < parametro.length(); i++) {
                agregar(Character.toString(parametro.charAt(i)));
            }

            incorrecto = "Real:\n";
            if (model.busPalabra("\\.|/|\\+|-|\\d|pi|e", raiz.getDato()) == true) {
                eliminar();
            } else {
                com = false;
                incorrecto = incorrecto + "Todos los caracteres generan error\n\n";
            }

            if (com == true) {
                do {
                    if (model.busPalabra("\\.|/|\\+|-|\\d|pi|e", raiz.getDato()) == true && pasos != 2) {
                        eliminar();
                    } else {
                        pasos = 2;
                        incorrecto = incorrecto + eliminar();
                    }
                } while (raiz != null);
                incorrecto = incorrecto + "\n\n";
            }
        }

        return incorrecto;
    }

    public String metodoNotCien(String parametro, boolean valido, String incorrecto) {
        boolean com = true;
        int pasos = 0;

        vaciar();
        if (valido == false) {
            for (int i = 0; i < parametro.length(); i++) {
                agregar(Character.toString(parametro.charAt(i)));
            }

            incorrecto = "Notación cientifica:\n";
            if (model.busPalabra("\\.|/|\\+|-|\\d|pi|e", raiz.getDato()) == true) {
                eliminar();
                pasos++;
            } else {
                com = false;
                incorrecto = incorrecto + "Todos los caracteres generan error\n\n";
            }

            if (com == true) {
                do {
                    if (pasos == 1) {
                        if (model.busPalabra("\\.|/|\\+|-|\\d|pi|e", raiz.getDato()) == true) {
                            eliminar();
                        } else {
                            if (model.busPalabra("E", raiz.getDato()) == true) {
                                pasos++;
                                eliminar();
                            } else {
                                pasos = 10;
                            }
                        }
                    } else {
                        if (pasos == 2) {
                            if (model.busPalabra("\\+|-|\\d", raiz.getDato()) == true) {
                                eliminar();
                                pasos++;
                            } else {
                                pasos = 10;
                            }
                        } else {
                            if (pasos == 3) {
                                if (model.busPalabra("\\d", raiz.getDato()) == true) {
                                    eliminar();
                                } else {
                                    pasos = 10;
                                }
                            } else {
                                incorrecto = incorrecto + eliminar();
                            }
                        }
                    }
                } while (raiz != null);
                if (pasos != 10) {
                    incorrecto = incorrecto + "La notación cientifica esta incompleta.\nLo que genera el error";
                }
                incorrecto = incorrecto + "\n\n";
            }
        }
        return incorrecto;
    }

    public String metodoBinario(String parametro, boolean valido, String incorrecto) {
        boolean com = true;
        boolean empezarImp = false;

        vaciar();
        if (valido == false) {
            for (int i = 0; i < parametro.length(); i++) {
                agregar(Character.toString(parametro.charAt(i)));
            }

            incorrecto = "Binario:\n";
            if (model.busPalabra("0|1", raiz.getDato()) == true) {
                eliminar();
            } else {
                com = false;
                incorrecto = incorrecto + "Todos los caracteres generan error\n\n";
            }

            if (com == true) {
                do {
                    if (model.busPalabra("0|1", raiz.getDato()) == true && empezarImp == false) {
                        eliminar();
                    } else {
                        empezarImp = true;
                        incorrecto = incorrecto + eliminar();
                    }
                } while (raiz != null);
                incorrecto = incorrecto + "\n\n";
            }
        }

        return incorrecto;
    }

    public String metodoEmail(String parametro, boolean valido, String incorrecto) {
        int pasos = 0;

        vaciar();
        if (valido == false) {
            for (int i = 0; i < parametro.length(); i++) {
                agregar(Character.toString(parametro.charAt(i)));
            }

            incorrecto = "Email:\n";
            if (model.busPalabra("\\d|[a-zA-Z]", raiz.getDato()) == true) {
                eliminar();
                pasos++;
            } else {
                incorrecto = incorrecto + "Todos los caracteres generan error\n";
            }

            if (pasos == 1) {
                do {
                    if (pasos == 1) {
                        if (model.busPalabra("\\d|[a-zA-Z]", raiz.getDato()) == true) {
                            eliminar();
                        } else {
                            if (model.busPalabra("@", raiz.getDato()) == true) {
                                pasos++;
                                eliminar();
                            } else {
                                pasos = 10;
                            }
                        }
                    } else {
                        if (pasos == 2) {
                            if (model.busPalabra("\\d|[a-zA-Z]", raiz.getDato()) == true) {
                                eliminar();
                                pasos++;
                            } else {
                                pasos = 10;
                            }
                        } else {
                            if (pasos == 3) {
                                if (model.busPalabra("\\d|[a-zA-Z]", raiz.getDato()) == true) {
                                    eliminar();
                                } else {
                                    if (model.busPalabra("\\.", raiz.getDato()) == true) {
                                        pasos++;
                                        eliminar();
                                    } else {
                                        pasos = 10;
                                    }
                                }
                            } else {
                                if (pasos == 4) {
                                    if (model.busPalabra("com|co", raiz.getDato()) == true) {
                                        eliminar();
                                    } else {
                                        pasos = 10;
                                    }
                                } else {
                                    incorrecto = incorrecto + eliminar();
                                }
                            }
                        }
                    }
                } while (raiz != null);
                if (pasos != 10) {
                    incorrecto = incorrecto + "El email esta incompleto.\nLo que genera el error";
                }
            }
            incorrecto = incorrecto + "\n\n";
        }
        return incorrecto;
    }

    public void vaciar() {
        do {
            eliminar();
        } while (raiz != null);
    }
}
