package com.busqueda;

public class Cola {
    private Nodo inicio;

    public Cola() {
        inicio = null;
    }

    public Nodo getinicio() {
        return inicio;
    }

    public void setinicio(Nodo inicio) {
        this.inicio = inicio;
    }

    public void insertar(String dato){
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

    // Asume que no esta vacia la cola
    public String eliminar(){ 
        String element = this.inicio.getDato();
        this.inicio = this.inicio.getNext();
        this.inicio.setPrev(null);
        return element;
    }

    public boolean estaVacia(){
        return inicio==null;
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
}
