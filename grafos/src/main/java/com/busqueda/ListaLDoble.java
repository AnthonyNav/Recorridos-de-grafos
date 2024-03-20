package com.busqueda;

public class ListaLDoble {
    private Nodo inicio;
    private ListaLDoble next;
    private String nodo;
    
    // constructor
    public ListaLDoble() {
        inicio = null;
        next = null;
        nodo = null;
    }
    
    // getters y setters
    public Nodo getInicio() {
        return inicio;
    }

    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    public void setNext(ListaLDoble next){
        this.next = next;
    }

    public ListaLDoble getNext() {
        return next;
    }

    public String getNodo() {
        return nodo;
    }
    
    public void setNodo(String nodo) {
        this.nodo = nodo;
    }
    // Algunos metodos dependen de otros, es por eso que se mantienen
    public void insertarInicio(String dato){
        Nodo n = new Nodo(dato);
        if (estaVacia()) { // Si esta vacia
            this.inicio = n; // Apuntamos al nuevo nodo
        } else { // Sino ligamos y referenciamos al nuevo nodo
            n.setNext(this.inicio); 
            this.inicio.setPrev(n); 
            this.inicio = n;
        }
    }

    public void insertarFinal(String dato){
        Nodo n = new Nodo(dato);
        if (this.estaVacia()) { // Si esta vacia
            this.inicio = n; // Solo se referencia a n
        } else { // Sino se recorre hasta el ultimo nodo
            Nodo auxNodo = this.inicio;
            while (auxNodo.getNext() != null) {
                auxNodo = auxNodo.getNext();
            }
            auxNodo.setNext(n); // Se inserta al final
            n.setPrev(auxNodo);
        }
    }

    public boolean insertarIzqX(String dato , String x){ // Si se inserta cuando x es la primera posicion falla
        Nodo auxNodo = this.inicio;
        while (auxNodo.getNext()!=null) {
            if (auxNodo.getDato() == x) {
                break;
            }
            auxNodo = auxNodo.getNext();
        }
        if (auxNodo.getDato() == x) {
            if (auxNodo == this.inicio) {
                insertarInicio(dato);
            } else {
                Nodo n = new Nodo(dato);
                n.setNext(auxNodo);
                if (auxNodo.getPrev() != null) {
                    auxNodo.getPrev().setNext(n);
                }
                n.setPrev(auxNodo.getPrev());
                auxNodo.setPrev(n);
            }
            
            return true;
        }
        return false;
    }
    // Para insertar de forma ordenada en el caso de nodos representados con numeros
    public void ascendenteNumerica(int dato){  // La funcion supone que esta previamente ordenada la lista y que no este vacia
        Nodo auxNodo = this.inicio;

        while (auxNodo.getNext() != null) { // Recorre hasta encontrar un valor mayor al dato proporcionado
            if (Integer.parseInt(auxNodo.getDato()) > dato) {
                break;
            }
            auxNodo = auxNodo.getNext();
        }
        if (Integer.parseInt(auxNodo.getDato()) > dato) { // Si se encuentra, el dato se inserta antes de ese valor
            this.insertarIzqX(String.valueOf(dato), auxNodo.getDato());
        } else {
            this.insertarFinal(String.valueOf(dato));
        }
    }

    public void ascendenteAlfabetica(String dato){  // La funcion supone que esta previamente ordenada la lista y que no este vacia
        Nodo auxNodo = this.inicio;

        while (auxNodo.getNext() != null) { // Recorre hasta encontrar un valor mayor al dato proporcionado
            if (auxNodo.getDato().compareTo(dato) > 0) {
                break;
            }
            auxNodo = auxNodo.getNext();
        }
        if (auxNodo.getDato().compareTo(dato) > 0) { // Si se encuentra, el dato se inserta antes de ese valor
            this.insertarIzqX(dato, auxNodo.getDato());
        } else {
            this.insertarFinal(dato);
        }
    }

    @Override
    public String toString(){ // Asume que no esta vacia la lista
        String output = "";
        Nodo auxNodo = this.inicio;
        while (auxNodo.getNext() != null) {
            output = output + " " + auxNodo.getDato();
            auxNodo = auxNodo.getNext();
        }
        output = output + " " + auxNodo.getDato();
        return output;
    }
     
    public boolean estaVacia(){
        return inicio==null;
    }
}
