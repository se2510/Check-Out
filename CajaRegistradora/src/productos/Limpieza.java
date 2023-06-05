package productos;

/**
 * @author David Elias González García
 * PROTECO, Gen 44 :)
 */
public class Limpieza extends Productos {
    float porcentaje;
    
    // Constructor
    public Limpieza(float precio, String nom, int cod, float por) {
        super(precio, nom, cod);
        this.porcentaje = por;
    }
    
    // Métodos
    @Override
    public String what(){
        String a =this.nombre+" con un porcentaje de limpieza del %"+this.porcentaje+", a $"+this.precio;
        return a;
    }
}
