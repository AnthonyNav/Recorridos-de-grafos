package com.busqueda;

import java.io.File;
import java.util.Scanner;

public class App {
    private static boolean marcas[];
    private static String nodos;
    private static Cola verticesC;
    private static Pila verticesP;
    private static ListaLDoble adyacencias; 
    private static boolean isNumeric;

    public static void main( String[] args ){
        Scanner scan = new Scanner(System.in);
        boolean salir = false;
        boolean salirMenu;
        int op;
        String verticeInicial;

        while (!salir) {
            boolean cargaCompleta = false;
            isNumeric = false;
            verticesC = new Cola();
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
                    System.out.println("Dame uno de los siguientes vertices "+ nodos + " para comenzar a recorrer");
                    scan.nextLine();
                    verticeInicial = (isNumeric)? Integer.toString(scan.nextInt()):scan.nextLine();
                    System.out.println(verticeInicial);
                }
                switch (op) {
                    case 1: // bpp
                        
                        break;
                    case 2: // bpa

                        break;
                    case 3:
                        salirMenu = true;
                        System.out.println("Saliendo...");
                        break;
                
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }
            }
            System.out.println("\n\nDesea probar con otro grafo?\n1)Si\n2)No");
            salir = (scan.nextInt() == 2)? true:salir;   
        }
    }
    
    // Procediminto (metodo) de bpp

    // procedimiento (metodo) de bpa

    // Procedimiento para cargar el contenido de los grafos
    public static boolean cargarGrafo(int gn){
        String direccion = "";
        switch (gn) {
            case 1:
                direccion = "grafos/src/main/java/com/busqueda/Grafos/G1.txt";
                break;
            case 2:
                direccion = "grafos/src/main/java/com/busqueda/Grafos/G2.txt";
                break;
            case 3:
                direccion = "grafos/src/main/java/com/busqueda/Grafos/G3.txt";
                break;
            case 4:
                direccion = "grafos/src/main/java/com/busqueda/Grafos/G4.txt";
                isNumeric = true;
                break;
            case 5:
                direccion = "grafos/src/main/java/com/busqueda/Grafos/G5.txt";
                break;
            case 6:
                direccion = "grafos/src/main/java/com/busqueda/Grafos/G6.txt";
                break;
            case 7:
                direccion = "grafos/src/main/java/com/busqueda/Grafos/G7.txt";
                break;
            case 8:
                direccion = "grafos/src/main/java/com/busqueda/Grafos/G8.txt";
                isNumeric = true;
                break;
            default:
                System.out.println("Opción no válida");
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
            nodos = "";
            int i = 0;

            // Leer el archivo línea por línea
            while (scanner.hasNextLine()) {
                linea = scanner.nextLine();           
                // System.out.println(linea);                     
                str = linea.split(":");
                nodos = nodos + str[0].replace(" ", ""); // Agregandolo en la lista de nodos
                
                aux.setNodo(str[0].replaceAll(" ", ""));
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
                System.out.println("Lista "+aux.getNodo()+": " + aux);
                aux.setNext(new ListaLDoble());
                aux = aux.getNext();
            }
            aux = null;
            // Creamos nuestro arrglo con la dimesion de cantidad de vertices que tenga el grafo.
            marcas = new boolean[nodos.length()];
            // Cerrar el Scanner después de leer todas las líneas
            scanner.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}


