package com.busqueda;

public class Nodo {
    private String dato;
    private Nodo next;
    private Nodo prev;

    public Nodo(String dato) {
        this.dato = dato;
        next = null;
        prev = null;
    }

    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }

    public Nodo getPrev() {
        return prev;
    }

    public void setPrev(Nodo prev) {
        this.prev = prev;
    }

    public String getDato() {
        return dato;
    }
}
