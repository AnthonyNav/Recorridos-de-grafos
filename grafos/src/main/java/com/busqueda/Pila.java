package com.busqueda;

public class Pila {
    private Nodo inicio;

    public Pila() {
        inicio = null;
    }

    public Nodo getInicio() {
        return inicio;
    }

    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }
    
    public void push(String dato){
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

    private String eliminarInicio(){ 
        String element = this.inicio.getDato();
        this.inicio = this.inicio.getNext();
        this.inicio.setPrev(null);
        return element;
    }

    public String pop(){
        String element = "";
        Nodo auxNodo = this.inicio;
        while (auxNodo.getNext() != null) { // Recorrer
            auxNodo = auxNodo.getNext();
        }
        if (auxNodo == this.inicio) { // Caso inicio
            return this.eliminarInicio();
        } else { // Caso normal
            element = auxNodo.getDato();
            auxNodo.getPrev().setNext(null); // desligar
            return element;
        }
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
