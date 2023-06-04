package pagos;

/**
 * @author David Elias González García
 * PROTECO, Gen 44 :)
 */
public abstract class PagoTarjeta {
    
    private String nombre;
    private int numero;
    private int NIP;

    public PagoTarjeta(String nombre, int numero, int NIP) {
        this.nombre = nombre;
        this.numero = numero;
        this.NIP = NIP;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getNumero() {
        return numero;
    }

    public int getNIP() {
        return NIP;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNIP(int NIP) {
        this.NIP = NIP;
    }

}
