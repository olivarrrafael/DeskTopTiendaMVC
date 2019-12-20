
package modelo;

public class Ciudad {
    private int idCiudad;
    private String NombreCidad;
    private int cP;
    private int provinciaId;

    Ciudad(int id, String nombre) {
        this.idCiudad=id;
        this.NombreCidad=nombre;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombreCidad() {
        return NombreCidad;
    }

    public void setNombreCidad(String NombreCidad) {
        this.NombreCidad = NombreCidad;
    }

    public int getcP() {
        return cP;
    }

    public void setcP(int cP) {
        this.cP = cP;
    }

    public int getProvinciaId() {
        return provinciaId;
    }

    public void setProvinciaId(int provinciaId) {
        this.provinciaId = provinciaId;
    }

    public Ciudad() {
    }

    public Ciudad(String NombreCidad) {
        this.NombreCidad = NombreCidad;
    }

    public Ciudad(int idCiudad, String NombreCidad, int cP, int provinciaId) {
        this.idCiudad = idCiudad;
        this.NombreCidad = NombreCidad;
        this.cP = cP;
        this.provinciaId = provinciaId;
    }
    
    @Override
    public String toString(){
        return this.NombreCidad;
    }
    
    
}
