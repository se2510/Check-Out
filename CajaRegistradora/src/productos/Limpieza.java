package productos;

/**
 * @author David Elias González García
 * PROTECO, Gen 44 :)
 */
public class Limpieza extends Productos {
    String porcentaje;
    
    // Constructor
    public Limpieza(float precio, String nom, int cod, String por) {
        super(precio, nom, cod);
        this.porcentaje = por;
    }
    
    // Métodos
    @Override
    public void what(){
        // Qué es el producto
    }
}
