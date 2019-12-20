package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Vector;

public class GestorProveedorControlador implements ActionListener {

    Vista.RegistrarProveedor formProveedor;

    modelo.GestorTipoDeDocumento gestorDoc;
    modelo.GestorProveedor gestorProv;
    modelo.GestorCiudad gestorCity;
    modelo.GestorProvincia gestorProvincia; 
    public GestorProveedorControlador(Vista.RegistrarProveedor formprovedoor) {
        this.formProveedor = formprovedoor;
        gestorProv = new modelo.GestorProveedor();
        gestorCity = new modelo.GestorCiudad();
        gestorProvincia = new modelo.GestorProvincia();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == formProveedor.BtnRegistrar) {

        }
        if (e.getSource() == formProveedor.BtnBuscar) {
            buscarProveedor();
        }

    }

    public Vector cargarTipoDocumento() {
        gestorDoc = new modelo.GestorTipoDeDocumento();
        return gestorDoc.cargarTipoDeDocumentoBD(0);
    }
    public Vector cargarCiudad() {
        gestorCity = new modelo.GestorCiudad();
        return gestorCity.cargarCiudadesDeBD(0);
    }
    
    public Vector cargarProvincia() {
        gestorCity = new modelo.GestorCiudad();
        return gestorCity.cargarCiudadesDeBD(0);
    }

    /* public void registrarProveedor(){
        
        String doc=formProveedor.TxtNumeroDocumento.getText();
        long numDoc=0;       
        try {
            numDoc=Long.parseLong(doc);
        } catch (Exception e) {        
            e.printStackTrace();
        }
        
        String 
    } */
    public void buscarProveedor() {

        int parametro = 0;
        String valor = "";

        if (formProveedor.TxtNumeroDocumento.getText().length() > 0) {
            parametro = 1;
            valor = formProveedor.TxtNumeroDocumento.toString();
        }

        if (formProveedor.TxtNombreComercial.getText().length() > 0) {
            parametro = 2;
            valor = formProveedor.TxtNombreComercial.toString();
        }

        LinkedList<modelo.Proveedor> prov = gestorProv.getProveedorBy(parametro, valor);
        
        String dtoProv[] = new String[10];
        for (modelo.Proveedor pro : prov) {
            dtoProv[0] = Integer.toString(pro.getId());
            dtoProv[1] = Long.toString(pro.getNumDoc());
            dtoProv[2] = Integer.toString(pro.getCodTipoDoc());
            dtoProv[3] = pro.getNombreProv();
            dtoProv[4] = pro.getApellidoProv();
            dtoProv[5] = pro.getNombreComercial();
            dtoProv[6] = pro.getDireccion();
            dtoProv[7] = Integer.toString(pro.getCodCiudad());
            dtoProv[8] = Integer.toString(pro.getCodDepartamento());
            dtoProv[9] = Long.toString(pro.getTelefono());

            formProveedor.getTableModel().addRow(dtoProv);
            formProveedor.getTableModel().fireTableDataChanged();
        }

    }

}
