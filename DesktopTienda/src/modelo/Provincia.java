
package modelo;


public class Provincia {
    private int idProvincia;
    private String nombrePronvicia;

    public Provincia() {
    }

    public Provincia(int idProvincia, String nombrePronvicia) {
        this.idProvincia = idProvincia;
        this.nombrePronvicia = nombrePronvicia;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNombrePronvicia() {
        return nombrePronvicia;
    }

    public void setNombrePronvicia(String nombrePronvicia) {
        this.nombrePronvicia = nombrePronvicia;
    }
    
    
    @Override
    public String toString(){
    
    return nombrePronvicia;
}
    
    
}
