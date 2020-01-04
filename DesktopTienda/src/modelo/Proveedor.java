
package modelo;

public class Proveedor {
    
    private int id;
    private long numDoc;
    private int codTipoDoc;
    private String nombreProv;
    private String apellidoProv;
    private String nombreComercial;
    private String direccion;
    private int codCiudad;
    private int codProvincia;
    private long telefono;

    public Proveedor(int id) {
        this.id = id;
    }

    public Proveedor(int id, long numDoc, int codTipoDoc, String nombreProv, String apellidoProv, String nombreComercial, String direccion, int codCiudad, int codProvincia, long telefono) {
        this.id = id;
        this.numDoc = numDoc;
        this.codTipoDoc = codTipoDoc;
        this.nombreProv = nombreProv;
        this.apellidoProv = apellidoProv;
        this.nombreComercial = nombreComercial;
        this.direccion = direccion;
        this.codCiudad = codCiudad;
        this.codProvincia = codProvincia;
        this.telefono = telefono;
    }

    public Proveedor(long numDoc, int codTipoDoc, String nombreProv, String apellidoProv, String nombreComercial, String direccion, int codCiudad, int codProvincia, long telefono) {
        this.numDoc = numDoc;
        this.codTipoDoc = codTipoDoc;
        this.nombreProv = nombreProv;
        this.apellidoProv = apellidoProv;
        this.nombreComercial = nombreComercial;
        this.direccion = direccion;
        this.codCiudad = codCiudad;
        this.codProvincia = codProvincia;
        this.telefono = telefono;
    }

    public Proveedor() {
    }

    Proveedor(int id, String nombreProv) {
     this.id=id;
     this.nombreProv=nombreProv;
             
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(long numDoc) {
        this.numDoc = numDoc;
    }

    public int getCodTipoDoc() {
        return codTipoDoc;
    }

    public void setCodTipoDoc(int codTipoDoc) {
        this.codTipoDoc = codTipoDoc;
    }

    public String getNombreProv() {
        return nombreProv;
    }

    public void setNombreProv(String nombreProv) {
        this.nombreProv = nombreProv;
    }

    public String getApellidoProv() {
        return apellidoProv;
    }

    public void setApellidoProv(String apellidoProv) {
        this.apellidoProv = apellidoProv;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodCiudad() {
        return codCiudad;
    }

    public void setCodCiudad(int codCiudad) {
        this.codCiudad = codCiudad;
    }


    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public int getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(int codProvincia) {
        this.codProvincia = codProvincia;
    }

    @Override
    public String toString() {
        return nombreProv;
    }
    
  
}
