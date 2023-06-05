package cajaregistradora;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import productos.*;

/**
 * @author David Elias González García
 * PROTECO, Gen 44 :)
 */


public class CajaRegistradora {

    
    public static void main(String[] args) {
        
        
        Scanner entrada = new Scanner(System.in); // Se abre la entrada
        int opcion; // Variable donde se almacenará la entrada
        
        do {
            menu(); // impresión del menu
            opcion = entrada.nextInt(); // Lectura de la entrada

            switch (opcion) {
                case 1: // Ver artículos
                    System.out.println("Has seleccionado la opción 'Ver artículos'");
                    break;
                case 2: // Comprar
                    System.out.println("Has seleccionado la opción 'Comprar'");
                    break;
                case 3: // Salir
                    System.out.println("Has seleccionado la opción 'Salir'");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (opcion != 3);
        
        
        
    }
    
    // ---------------------------    Funciones    -----------------------------
    
    public static void menu(){
        System.out.println("\t-----------------------------------");
        System.out.println("\t Bienvenide a mi proyecto todo feo");
        System.out.println("\t     Que deseas hacer, guap@?:");
        System.out.println("\t 1) Ver artículos");
        System.out.println("\t 2) Comprar");
        System.out.println("\t 3) Salir");
        System.out.println("\t-----------------------------------");
        
    }
    
    public static void verArticulos(List<Productos> art) {
        System.out.println("------------ ARTICULOS ------------");
        System.out.println("Nombre \t| Precio \t| Código \t|");
        System.out.println("-----------------------------------");

        for (Productos producto : art) {
            System.out.println(producto.nombre + "\t\t" + producto.precio + "\t\t" + producto.codigo);
            System.out.println("Descripción: " + producto.what());
        }

        System.out.println("-----------------------------------");
    }
    
    public static void comprar(List<Productos> art) {
        List<Productos> carrito = new ArrayList<>();
        Scanner entrada = new Scanner(System.in);

        while (true) {
            System.out.println("Por favor ingresa el código del producto a comprar (ingresa 0 para salir): ");
            int codigo = entrada.nextInt();

            if (codigo == 0) {
                break; // Salir del bucle si se ingresa 0
            }

            boolean encontrado = false;
            for (Productos producto : art) {
                if (codigo == producto.codigo) {
                    carrito.add(producto); // Agregar el producto al carrito
                    encontrado = true;
                    break; // Salir del bucle si se encuentra el producto
                }
            }

            if (!encontrado) {
                System.out.println("Código de producto no válido");
            }
        }

    // Imprimir los productos en el carrito
    System.out.println("Productos en el carrito:");
    for (Productos producto : carrito) {
        System.out.println(producto.nombre);
    }
}

    
    public static void total(){ // Calcula la suma total a pagar.
        
    }
    
    public static void pagarEfectivo(){ // Paga y devuelve el cambio
        
    }
    
    public static void pagarTarjeta(){ // Paga y devuelve el cambio
        
    }
    
}


