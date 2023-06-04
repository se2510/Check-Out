package cajaregistradora;
import java.util.Scanner;
import productos.*;

/**
 * @author David Elias González García
 * PROTECO, Gen 44 :)
 */


public class CajaRegistradora {

    
    public static void main(String[] args) {
        
        menu();
        
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
    
    public static void verArticulos(Productos art[]){ // Esta funcíón imprime los artículos disponibles
        System.out.println("------------ ARTICULOS ------------");
        System.out.println("Nombre \t| Precio \t| Código \t|");
        System.out.println("-----------------------------------");
        for(int i = 0 ; i < art.length ; i++){
            System.out.println(art[i].nombre+"\t\t"+art[i].precio+"\t\t"+art[i].codigo);
        }
        System.out.println("-----------------------------------");
    }
    
    public static void comprar(Productos art[]){
        //List<Productos> carrito = new ArrayList<>(Productos);
        Scanner entrada = new Scanner(System.in);
        
        while (true){
            System.out.println("Por favor ingresa el código del producto a comprar: ");
            int codigo = entrada.nextInt();
            
            for(int i = 0; i< art.length ; i++){
                if ( codigo == art[i].codigo){
                    
                }
            }
            
        }
    }
    
    public static void total(){ // Calcula la suma total a pagar.
        
    }
    
    public static void pagarEfectivo(){ // Paga y devuelve el cambio
        
    }
    
    public static void pagarTarjeta(){ // Paga y devuelve el cambio
        
    }
    
    
}


