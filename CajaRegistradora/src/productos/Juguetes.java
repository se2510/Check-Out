package productos;

/**
 * @author David Elias González García
 * PROTECO, Gen 44 :)
 */
public class Juguetes extends Productos {
    String Tipo;
    int edad;
    
    // Constructor
    public Juguetes(float precio, String nom, int cod, String tip, int ed) {
        super(precio, nom, cod);
        this.Tipo = tip;
        this.edad = ed;
    }
    
    // Métodos
    @Override
    public String what(){
        String a = "Juguete "+this.nombre+" de "+this.Tipo+", para niños de "+this.edad+", a $"+this.precio;
        //System.out.println("Juguete "+this.nombre+" de "+this.Tipo+", para niños de "+this.edad+", a $"+this.precio)
        return a;
    }
}
