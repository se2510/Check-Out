package cajaregistradora;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        List<Productos> Catalogo = generarProductos();
        
        do {
            menu(); // impresión del menu

            // Verificar si la entrada es un número válido
            if (entrada.hasNextInt()) {
                opcion = entrada.nextInt(); // Lectura de la entrada

                List<Productos> carrito = new ArrayList<>();  // Lista del carrito

                switch (opcion) {
                    case 1: // Ver artículos
                        System.out.println("Has seleccionado la opción 'Ver artículos'");
                        verArticulos(Catalogo);
                        break;
                    case 2: // Comprar
                        System.out.println("Has seleccionado la opción 'Comprar'");
                        carrito = comprar(Catalogo); // Articulos a comprar se almacenarán en el carrito
                        int opcPago;

                        do {
                            System.out.println("Elige tu método de pago: ");
                            System.out.println("1) Pago en efectivo");
                            System.out.println("2) Pago con tarjetini ");

                            // Verificar si la entrada es un número válido
                            if (entrada.hasNextInt()) {
                                opcPago = entrada.nextInt();

                                switch (opcPago) {
                                    case 1:
                                        pagarEfectivo(carrito);
                                        opcPago = 3;
                                        break;
                                    case 2:
                                        pagarTarjeta(carrito);
                                        opcPago = 3;
                                        break;
                                    default:
                                        System.out.println("Opción no válida.");
                                        break;
                                }
                                opcion = 3;
                            } else {
                                System.out.println("Número inválido. Por favor, intenta de nuevo.");
                                entrada.next(); // Descartar entrada inválida
                                opcPago = 0;
                            }
                        } while (opcPago != 3);
                        break;
                    case 3: // Salir
                        System.out.println("Gracias por acompañarnos, buen día! :D");
                        break;
                    default:
                        System.out.println("Opción no válida");
                        break;
                }
            } else {
                System.out.println("Número inválido. Por favor, intenta de nuevo.");
                entrada.next(); // Descartar entrada inválida
                opcion = 0;
            }
        } while (opcion != 3);

        
        
        
    }
    
    // ---------------------------    Funciones    -----------------------------------------------------
    
    public static void menu(){
        System.out.println("\t===================================================");
        System.out.println("\t      Bienvenid@ a mi tienda, foraster@...");
        System.out.println("\t            Que vas a querer hoy?:");
        System.out.println("\t              1) Ver artículos");
        System.out.println("\t              2) Comprar");
        System.out.println("\t              3) Salir");
        System.out.println("\t====================================================");
        
    }
    
    // --------------------------------------------- Ver Artículos --------------------------------------
    /**
    * Esta función imprime el catálogo de artículos de la tienda >:D
    * Imprime cómo será la tabla, para posteriormente, con un for each, recorre la lista
    * y la imprime en ordem: primero el nombre del producto, luego el precio, y luego el código
    *
    * @param art El primer y único parámetro que se le pasa es la lista del catálogo
    */
    public static void verArticulos(List<Productos> art) {
        System.out.println("======================================= ARTICULOS =============================================");
        System.out.println("| Nombre                | Precio         | Código \t|");
        System.out.println("===============================================================================================");

        for (Productos producto : art) { // for each :0
            // Espaciando correctamente (como en tablita) con el String.format()
            System.out.println(String.format("%-30s %-15s %-15s", producto.nombre, producto.precio, producto.codigo));
            System.out.println("Descripción: " + producto.what());
            System.out.println("-----------------------------------------------------------------------------------------------");
        }
    }
    
    // ------------------------------------------------ Comprar ----------------------------------------
    /**
    * Esta función realiza la compra de los artículos, 
    *
    * @param art Recibe una lista de tipo de la clase Productos, que sería el catálogo donde
    * comparará el código recibido con el catálogo, verificando que esté.
    * @return Retorna la lista del carrito, los objetos a comnprar.
    */
    public static List<Productos> comprar(List<Productos> art) {
        List<Productos> carrito = new ArrayList<>();
        Scanner entrada = new Scanner(System.in);
        float montoTotal = 0.0f;

        while (true) {
            System.out.println("Por favor ingresa el código del producto a comprar (0 para salir): ");
            int codigo = entrada.nextInt();

            if (codigo == 0) {
                break;
            }

            // Noi encontado el producto, puesto como la base
            boolean encontrado = false; 
            
            for (Productos producto : art) {
                if (producto.codigo == codigo) { // Si el codigo ingresado es igual al codigo encontrado
                    carrito.add(producto); // Se añade al carrito
                    montoTotal += producto.precio; // Se le suma al variable de montoTotal el precio del articulo
                    encontrado = true; 
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("Producto no encontrado. Por favor, intenta de nuevo.");
            }
        }

        System.out.println("Monto total: $" + montoTotal); // Fuera del ciclo

        // Guardar carrito en un archivo de texto
        try {
            FileWriter writer = new FileWriter("carrito.txt");
            for (Productos producto : carrito) {
                writer.write(producto.what() + "\n"); // Escribe el carrito en un archivo de texto
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return carrito;
    }


    // ----------------------------------- Generar Ticket -------------------------------------------------------
    // Sus argumentos son La lista del carrito, el monto total, si es que hay cambio, y el tipo de pago que se hizo
    /**
    * Esta función genera el ticket en un archivo de texto, poniendo la fecha y la hora de compra.
    *
    * @param carrito Lista del catálogo.
    * @param montoTotal Monto total del carrito.
    * @param cambio Monto del cambio (dinero a regresar mediante el pago en efectivo).
    * @param tipoPago Efectivo o por tarjeta.
    * @throws IOException Error al generar el ticket.
    */
    public static void generarTicket(List<Productos> carrito, float montoTotal, float cambio, String tipoPago) {
        try {
            // Obtener la fecha actual, pero se necesitan las librerias Date y SimpleDateFormat
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
            Date fechaActual = new Date();

            // Creando el nombre del archivo usando la fecha actual
            String nombreArchivo = "ticket_" + dateFormat.format(fechaActual) + ".txt";

            // Crear el archivo de texto
            File archivo = new File(nombreArchivo); // Tambien se necesita la libreria File
            FileWriter escritor = new FileWriter(archivo);

            // Escribir el contenido del ticket
            escritor.write("================== TICKET DE COMPRA ================\n");
            escritor.write("Fecha: " + dateFormat.format(fechaActual) + "\n");
            escritor.write("====================================================\n");

            // Escribir los productos comprados
            escritor.write("Artículos comprados:\n");
            for (Productos producto : carrito) {
                escritor.write(String.format("%-30s %-15s %-15s\n", producto.nombre, producto.precio, producto.codigo));
                //escritor.write("- " + producto.nombre+"\t---"+ producto.precio+ "\n");
            }

            escritor.write("====================================================\n");

            // Escribir el monto total
            escritor.write("Monto total: $" + montoTotal + "\n");

            // Escribir el tipo de pago y el mensaje de despedida
            if (tipoPago.equals("efectivo")) {
                escritor.write("Cambio: $" + cambio + "\n");
                escritor.write("¡Gracias por su compra! Regrese pronto.\n");
            } else if (tipoPago.equals("tarjeta")) {
                escritor.write("Tipo de pago: Tarjeta\n");
                escritor.write("¡Gracias por su compra! Esperamos verlo nuevamente.\n");
            }
            
            escritor.close(); // Cerrar el archivo

            System.out.println("Se ha generado el ticket de compra: " + nombreArchivo);
        } catch (IOException e) { // Obteniendo el error en la variable e
            System.out.println("Error al generar el ticket de compra.");
            e.printStackTrace();
        }
    }

    // ------------------------------------------------- Pagar en Efectivo -------------------------
    /**
    *
    * Realiza el pago en efectivo y calcula el cambio.
    * @param carrito Lista de productos comprados.
    */
    public static void pagarEfectivo(List<Productos> carrito) { // Pago en efectivo que da cambio
        Scanner entrada = new Scanner(System.in);
        float montoTotal = 0.0f;

        // Calcular el monto total
        for (Productos producto : carrito) {
            montoTotal += producto.precio;
        }

        System.out.println("Monto total a pagar: $" + montoTotal);

        float montoPagado = 0.0f;
        float cambio = 0.0f;

        while (montoPagado < montoTotal) { // Ciclo para que siga pagando el cliente
            System.out.println("Ingresa el valor de la moneda/billete con el que deseas pagar: ");
            System.out.println("Monedas que puedes ingresar: $1 , $2, $5, $10 ");
            System.out.println("Billetes que puedes ingresar: $20, $50, $100, $200, $500");
            int valor = entrada.nextInt();

            if (valor == 1 || valor == 2 || valor == 5 || valor == 10 || valor == 20 || valor == 50 ||
                    valor == 100 || valor == 200 || valor == 500) {
                montoPagado += valor;
                cambio = montoPagado - montoTotal;

                if (montoPagado < montoTotal) {
                    System.out.println("Monto pagado hasta ahora: $" + montoPagado);
                    System.out.println("Falta por pagar: $" + (montoTotal - montoPagado));
                }
            } else {
                System.out.println("Valor no válido. Por favor, intenta de nuevo.");
            }
        }

        System.out.println("¡Pago realizado con éxito!");
        System.out.println("Cambio: $" + cambio);
        
        generarTicket(carrito, montoTotal, cambio, "efectivo"); // Genera el ticket B)
    }

    // -------------------------------------------- Pagar con Tarjeta -------------------------------
    /**
    * Realiza el pago utilizando una tarjeta de crédito o débito.
    * @param carrito Lista de productos comprados.
    */
    public static void pagarTarjeta(List<Productos> carrito) { // Pago con tarjetini
        Scanner entrada = new Scanner(System.in);
        float montoTotal = 0.0f;

        // Calculando el monto total
        for (Productos producto : carrito) {
            montoTotal += producto.precio;
        }

        System.out.println("Monto total a pagar: $" + montoTotal);

        while(true){
            System.out.println("Ingresa el número de tarjeta (16 dígitos): ");
            long numeroTarjeta = entrada.nextLong();

            System.out.println("Ingresa el NIP (4 dígitos): ");
            int nip = entrada.nextInt();

            // Verifica que los datos de la tarjetini sean correctos
            if (String.valueOf(numeroTarjeta).length() == 16 && String.valueOf(nip).length() == 4) {
                System.out.println("¡Pago con tarjeta realizado con éxito!");

                generarTicket(carrito, montoTotal, 0.0f, "tarjeta"); // Generar ticket de compra
                break;
            } else {
                System.out.println("Error en los datos de la tarjeta. El pago no pudo ser procesado.");
            }
        }
    }

    // ---------------------------- Generación del catálogo de productos -----------------------------
    /**
     * Genera una lista de productos con ejemplos de diferentes categorías.
     * @return Lista de productos generados.
     */
    public static List<Productos> generarProductos() {
        List<Productos> productos = new ArrayList<>();

        // Generando 10 productos de tipo Bebida
        productos.add(new Bebida(10.0f, "Refresco Cola", 1, "Cola", 500));
        productos.add(new Bebida(15.0f, "Agua Mineral Natural", 2, "Natural", 1000));
        productos.add(new Bebida(12.0f, "Jugo Naranja", 3, "Naranja", 300));
        productos.add(new Bebida(18.0f, "Refresco Limónsito", 4, "Limón", 500));
        productos.add(new Bebida(20.0f, "Agua Mineral con Gas", 5, "Con Gas", 1000));
        productos.add(new Bebida(15.0f, "Ocha (tesito verdesito)", 6, "Té Verde", 250));
        productos.add(new Bebida(10.0f, "Refresco Manzana (Manzanita Sol)", 7, "Manzana", 500));
        productos.add(new Bebida(18.0f, "Agua Mineral Saborizada", 8, "Saborizada", 1000));
        productos.add(new Bebida(12.0f, "Jugo Uva (Delaware)", 9, "Uva", 300));
        productos.add(new Bebida(15.0f, "Refresco Naranja (Fanta)", 10, "Naranja", 500));

        // Generando 10 productos de tipo Limpieza
        productos.add(new Limpieza(20.0f, "Detergente Líquido", 11, 5.0f));
        productos.add(new Limpieza(12.0f, "Limpiavidrios Spray", 12, 2.5f));
        productos.add(new Limpieza(15.0f, "Desinfectante", 13, 4.0f));
        productos.add(new Limpieza(18.0f, "Jabón en Polvo", 14, 3.0f));
        productos.add(new Limpieza(22.0f, "Desodorante de Ambientes", 15, 6.0f));
        productos.add(new Limpieza(10.0f, "Esponja Abrasiva", 16, 1.5f));
        productos.add(new Limpieza(16.0f, "Lavandina", 17, 3.5f));
        productos.add(new Limpieza(14.0f, "Detergente en Polvo", 18, 4.5f));
        productos.add(new Limpieza(19.0f, "Limpiador Multiusos", 19, 4.0f));
        productos.add(new Limpieza(8.0f, "Toallas de Papel", 20, 1.0f));

        // Generando 10 productos de tipo Juguetes
        productos.add(new Juguetes(30.0f, "Peluche Osito", 21, "Osito", 3));
        productos.add(new Juguetes(25.0f, "Muñeca Barbie", 22, "Barbie", 5));
        productos.add(new Juguetes(18.0f, "Rompecabezas", 23, "Rompecabezas", 7));
        productos.add(new Juguetes(35.0f, "Terreneitor", 24, "Carro", 8));
        productos.add(new Juguetes(28.0f, "Pelota de Fútbol", 25, "Deportivo", 6));
        productos.add(new Juguetes(22.0f, "Legos", 26, "Construcción", 5));
        productos.add(new Juguetes(32.0f, "Peluche Perrito", 27, "Perro", 4));
        productos.add(new Juguetes(27.0f, "Max Steel", 28, "Acción", 7));
        productos.add(new Juguetes(20.0f, "Mai Lirul Pony", 29, "Muñecas", 4));
        productos.add(new Juguetes(30.0f, "Rompecabezas 3D", 30, "Rompecabezas", 9));

        return productos; // regresando la lista para guardarla en otra
    }
    
}


