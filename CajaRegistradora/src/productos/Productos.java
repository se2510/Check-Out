package productos;

/**
 * @author David Elias González García
 * PROTECO, Gen 44 :)
 */
public abstract class Productos {
    
    public float precio;
    public String nombre;
    public int codigo;
    
    // El constructor
    public Productos(float precio, String nom, int cod){
	this.precio = precio;
	this.nombre = nom;
	this.codigo = cod;
    }
    
    // Métodos
    /**
    * Este método describe al producto
    * @return No retorna ningún valor
    */
    public abstract String what();
}
