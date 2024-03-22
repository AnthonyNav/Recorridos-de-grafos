package com.busqueda;

import java.io.File;
import java.util.Scanner;

public class App {
    private static boolean marcas[];
    private static String[] vertices;
    private static Cola colaVertices;
    private static Pila pilaVertices;
    private static ListaLDoble adyacencias; 
    private static boolean isNumeric;

    public static void main( String[] args ){
        Scanner scan = new Scanner(System.in);
        boolean salir = false;
        boolean salirMenu;
        int op;
        String verticeInicial = "";
        int posI = 0;

        while (!salir) {
            boolean cargaCompleta = false;
            isNumeric = false;
            colaVertices = new Cola();
            // Cargamos los datos del grafo
            while (!cargaCompleta) {
                System.out.println("\n\n");
                System.out.println("Ingrese el grafo que desea (1-8):");
                cargaCompleta = cargarGrafo(scan.nextInt());
            }
            salirMenu = false;
            while (!salirMenu) {
                System.out.println("\n\nQue recorrido deseas?\n1)Busqueda primero en profundidad (bpp)\n2)Busqueda primero por anchura (bpa)\n3)Salir");
                op = scan.nextInt();
                if (op != 3) {
                    System.out.println("Dame uno de los siguientes vertices ["+ String.join(" ", vertices) + "] para comenzar a recorrer");
                    scan.nextLine(); // Limpia el buffer
                    verticeInicial = (isNumeric)? Integer.toString(scan.nextInt()):""+Character.toUpperCase(scan.nextLine().replaceAll(" ", "").charAt(0));
                    System.out.println(verticeInicial);
                    posI = posicionVertice(verticeInicial);
                }
                // Si existe el vertice inicial
                if (posI != -1) {
                    switch (op) {
                        case 1: // bpp
                        
                            break;
                        case 2: // bpa
                            busquedaPrimeroEnAnchura(verticeInicial);
    
                            break;
                        case 3:
                            salirMenu = true;
                            System.out.println("Saliendo...");
                            break;
                    
                        default:
                            System.out.println("Opcion no valida");
                            break;
                    }
                } else{
                    System.out.println("El vertice inicial dado no existe");
                }
                
            }
            System.out.println("\n\nDesea probar con otro grafo?\n1)Si\n2)No");
            salir = (scan.nextInt() == 2)? true:salir;   
        }
        scan.close();
    }
    
    // Procediminto (metodo) de bpp
    public static String busquedaPrimeroPorProfundidad(String vertInit){
        
        return null;
    }

    // procedimiento (metodo) de bpa

    // Procedimiento para cargar el contenido de los grafos
    public static boolean cargarGrafo(int gn){
        String direccion = "";
        switch (gn) {
            case 1:
                direccion = "C:/Users/Christian/Documents/Proyectos/BusquedaEnGrafos/grafos/src/main/java/com/busqueda/Grafos/G1.txt";
                break;
            case 2:
                direccion = "C:/Users/Christian/Documents/Proyectos/BusquedaEnGrafos/grafos/src/main/java/com/busqueda/Grafos/G2.txt";
                break;
            case 3:
                direccion = "C:/Users/Christian/Documents/Proyectos/BusquedaEnGrafos/grafos/src/main/java/com/busqueda/Grafos/G3.txt";
                break;
            case 4:
                direccion = "C:/Users/Christian/Documents/Proyectos/BusquedaEnGrafos/grafos/src/main/java/com/busqueda/Grafos/G4.txt";
                isNumeric = true;
                break;
            case 5:
                direccion = "C:/Users/Christian/Documents/Proyectos/BusquedaEnGrafos/grafos/src/main/java/com/busqueda/Grafos/G5.txt";
                break;
            case 6:
                direccion = "C:/Users/Christian/Documents/Proyectos/BusquedaEnGrafos/grafos/src/main/java/com/busqueda/Grafos/G6.txt";
                break;
            case 7:
                direccion = "C:/Users/Christian/Documents/Proyectos/BusquedaEnGrafos/grafos/src/main/java/com/busqueda/Grafos/G7.txt";
                break;
            case 8:
                direccion = "C:/Users/Christian/Documents/Proyectos/BusquedaEnGrafos/grafos/src/main/java/com/busqueda/Grafos/G8.txt";
                isNumeric = true;
                break;
            default:
                System.out.println("Opcion no valida");
                return false;
        }

        try{
            File archivo = new File(direccion);
            // Crear un objeto Scanner para leer el archivo
            Scanner scanner = new Scanner(archivo);
            String linea;
            String[] str;
            adyacencias = new ListaLDoble();
            ListaLDoble aux = adyacencias;
            int i = 0, counter = 0;

            // Leer el archivo l√≠nea por l√≠nea
            while (scanner.hasNextLine()) {
                linea = scanner.nextLine();           
                // System.out.println(linea);                     
                str = linea.split(":");                
                aux.setVertice(str[0].replaceAll(" ", ""));
                // relleno de la lista adyacente
                str = str[1].split(" "); 
                for (i = 0; i < str.length; i++) {
                    if (aux.getInicio() == null) {
                        aux.insertarFinal(str[i]);
                    } else {
                        if (isNumeric) {
                            aux.ascendenteNumerica(Integer.parseInt(str[i]));
                        } else {
                            aux.ascendenteAlfabetica(str[i]);
                        }
                        
                    }
                }
                // System.out.println("Lista "+aux.getVertice()+": " + aux); // Para ver lo como esta leyendo el archivo
                aux.setNext(new ListaLDoble());
                aux = aux.getNext();
                counter++;
            }
            // Creamos nuestro arrglo con la dimesion de cantidad de vertices que tenga el grafo.
            marcas = new boolean[counter];
            // Cerrar el Scanner despu√©s de leer todas las l√≠neas
            scanner.close();
            // Captura de los vertices en formato String en un arreglo
            aux = adyacencias;
            vertices = new String[counter];
            for (i = 0; i < counter; i++) {
                vertices[i] = aux.getVertice();
                //System.out.println(vertices[i]);
                aux = aux.getNext();
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static int posicionVertice(String v){
        int counter = 0;
        for (String n : vertices) {
            if (n.compareTo(v)==0) {
                return counter++;
            }
            counter++;
        }
        return -1; // Retorna -1 si no existe
    }

    // MÈtodo para b˙squeda primero en anchura (BPA)
    public static void busquedaPrimeroEnAnchura(String vertInit) {
    App.BorrarMarcas();    
    String[] arbol = new String[vertices.length];
    int i=0;
    colaVertices = new Cola();
    marcas[posicionVertice(vertInit)] = true; // Marcamos el nodo inicial como visitado
    colaVertices.insertar(vertInit); // Insertamos el nodo inicial en la cola
    arbol[0]=vertInit;

    while (!colaVertices.estaVacia()) {
        String verticeActual;
        verticeActual = colaVertices.eliminar(); // Sacamos el primer elemento de la cola

        //System.out.println("Visitando nodo: " + verticeActual);

        // Obtener los nodos adyacentes al nodo actual
        ListaLDoble listaAdyacente = obtenerListaAdyacente(verticeActual);

        // Iterar sobre los nodos adyacentes
        Nodo nodoAdyacente = listaAdyacente.getInicio();
        while (nodoAdyacente != null) {
            String datoAdyacente = nodoAdyacente.getDato();

            // Si el nodo adyacente no ha sido visitado, marcarlo como visitado y agregarlo a la cola
            if (!marcas[posicionVertice(datoAdyacente)]) {
                marcas[posicionVertice(datoAdyacente)] = true; // Marcamos el nodo como visitado
                colaVertices.insertar(datoAdyacente); // Agregamos el nodo a la cola
                i++;
                arbol[i]=datoAdyacente;
            }

            nodoAdyacente = nodoAdyacente.getNext();
        }
    }
        System.out.println("\nEl arbol de expansion es:\n");
    for(i=0; i<arbol.length; i++)
    {
        System.out.println(arbol[i] + " ");
    }
    }

   // MÈtodo para obtener la lista de adyacencia de un vÈrtice
   public static ListaLDoble obtenerListaAdyacente(String vertice) {
    ListaLDoble listaAdyacente = App.adyacencias;

    // Buscar el vÈrtice en la lista de adyacencia
    while (listaAdyacente != null && !listaAdyacente.getVertice().equals(vertice)) {
        listaAdyacente = listaAdyacente.getNext();
    }

    return listaAdyacente;
    }
   
   public static void BorrarMarcas()
   {
       int i;
       for(i=0; i < marcas.length; i++)
       {
           marcas[i]=false;
       }
   }

}


