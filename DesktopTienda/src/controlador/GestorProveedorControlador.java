package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Vector;
import javax.swing.JOptionPane;
import modelo.Ciudad;
import modelo.Provincia;
import modelo.TipoDeDocumento;

public class GestorProveedorControlador implements ActionListener,MouseListener {

    Vista.RegistrarProveedor formProveedor;
    
    modelo.GestorTipoDeDocumento gestorDoc;
    modelo.TipoDeDocumento documento;
    
    modelo.Proveedor proveedorModelo;
    modelo.GestorProveedor gestorProv;
   
    modelo.GestorCiudad gestorCity;
    modelo.Ciudad ciudad;
    
    modelo.GestorProvincia gestorProvincia; 
    modelo.Provincia provincia;
    
    
    public GestorProveedorControlador(Vista.RegistrarProveedor formprovedoor) {
        this.formProveedor = formprovedoor;
        gestorProv = new modelo.GestorProveedor();
     
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //metodo para registro de proveedores
        if (e.getSource() == formProveedor.BtnRegistrar) {            
            registrarProveedor();
            limpiar();
            
        }
        //metodo para la busqueda de proveedores
        if (e.getSource() == formProveedor.BtnBuscar) {
            formProveedor.tabla.setNumRows(0);
            buscarProveedor();           
        }
        //metodo para Modificar proveedores
         if (e.getSource() == formProveedor.BtnModificar) {
               ModificarProveedor();  
        }
         //metodo para eliminar proveedores
        if(e.getSource() == formProveedor.BtnEliminar){
            if(formProveedor.TblProveedores.getSelectedRow() >= 0){
            eliminarProveedor();
            }else{
                JOptionPane.showMessageDialog(null,"Debe seleccionar el proveedor en la tabla para proceder con la solicitud");
            }           
        }

    }
    
     @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == formProveedor.TblProveedores){
            try {
             cargarProveedor();
            } catch (Exception ep) {
                System.err.println(ep.getMessage());
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
  
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
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
        gestorProvincia = new modelo.GestorProvincia();
        return gestorProvincia.cargarPronvinciasDeBD(0);
    }
     //Metodo para registrar Proveedores   
     public void registrarProveedor(){
               
         String doc = formProveedor.TxtNumeroDocumento.getText();
         long numDoc = 0;
         try {
             numDoc = Long.parseLong(doc);
         } catch (NumberFormatException ex) {
             JOptionPane.showMessageDialog(null,
                     "Error de Numero de documento,el campo esta vacio o contiene caracteres no numericos");
             return;
         }
         
         documento = (modelo.TipoDeDocumento) formProveedor.CmbTipoDoc.getSelectedItem();
        int idTipoDoc=documento.getId();
        if(idTipoDoc<1){
            JOptionPane.showMessageDialog(null, "error el tipo de documento es obligatorio");
            return;
        }
         
         String nombre = formProveedor.TxtNombres.getText().toUpperCase();
         if (nombre.trim().isEmpty()) {
             JOptionPane.showMessageDialog(null, "Error, El Nombre es Obligatorio");
             return;
         }
         
         String apellido=formProveedor.TxtApellidos.getText().toUpperCase();
         if (apellido.trim().isEmpty()) {
             JOptionPane.showMessageDialog(null, "Error, El Apellido es Obligatorio");
             return;
         }
         
         String nombreComercial = formProveedor.TxtNombreComercial.getText().toUpperCase();    
         if (nombreComercial.trim().isEmpty()) {
             JOptionPane.showMessageDialog(null, "Error, El Nombre Comercial es Obligatorio");
             return;
         }
                
         String direccion=formProveedor.TxtDireccion.getText().toUpperCase();
         if (direccion.trim().isEmpty()) {
             JOptionPane.showMessageDialog(null, "Error, La direccion es Obligatorio");
             return;
         }
         
         //Ciudad
        ciudad = (modelo.Ciudad) formProveedor.CmbCiudad.getSelectedItem();
        int idCiudad=ciudad.getIdCiudad();
        if(idCiudad<1){
            JOptionPane.showMessageDialog(null, "error el tipo de documento es obligatorio");
            return;        
        }
        
         //provincia
        provincia = (modelo.Provincia) formProveedor.CmbProvincia.getSelectedItem();
        int idProvincia=provincia.getIdProvincia();
        if(idProvincia<1){
            JOptionPane.showMessageDialog(null, "error el tipo de documento es obligatorio");
            return;
        }
        
         String tel=formProveedor.txtTelefono.getText(); 
        long tele = 0;
         try {
             tele = Long.parseLong(tel);
         } catch (NumberFormatException ex) {
             JOptionPane.showMessageDialog(null, "Error de Numero de telefono,el campo esta vacio o contiene caracteres no numericos");
             return;
         }
        
        proveedorModelo = new modelo.Proveedor(numDoc, idTipoDoc, nombre, apellido, nombreComercial, direccion, idCiudad, idProvincia, tele);
        gestorProv.registroProveedorBD(proveedorModelo);                
    } 
     //Metodo para buscar proveedores
    public void buscarProveedor() {

        int parametro = 0;
        String valor = "";
        //Realizamos una verificacion sencilla para traer a los proveedores por num de documento.
        if (formProveedor.TxtNumeroDocumento.getText().length() > 0) {
            parametro = 1;
            valor = formProveedor.TxtNumeroDocumento.getText();
        }

        if (formProveedor.TxtNumeroDocumento.getText().length() < 0) {
            parametro = 2;
            
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
            dtoProv[8] = Integer.toString(pro.getCodProvincia());
            dtoProv[9] = Long.toString(pro.getTelefono());

            formProveedor.getTableModel().addRow(dtoProv);
            formProveedor.getTableModel().fireTableDataChanged();
        }

    }
    
    public void cargarProveedor()throws Exception{
     int fila = formProveedor.TblProveedores.getSelectedRow();
     formProveedor.txtId.setText(formProveedor.TblProveedores.getValueAt(fila,0).toString());
     
     int docFila=Integer.parseInt(formProveedor.TblProveedores.getValueAt(fila,2).toString()); 
     int idCiudadCombo=Integer.parseInt(formProveedor.TblProveedores.getValueAt(fila,7).toString()); 
     int idProvinciaCombo=Integer.parseInt(formProveedor.TblProveedores.getValueAt(fila,8).toString()); 
     
     /*recorremos los tipos de combo box y comparamos sus id con el id obtenido de la
       tabla para poder asignarle la informacion necesaria.
     */ 
     //tipo Documento
       for (int i = 0; i <=formProveedor.CmbTipoDoc.getModel().getSize(); i++) {
    documento = (modelo.TipoDeDocumento) formProveedor.CmbTipoDoc.getItemAt(i);
    int tipoDocumento=documento.getId();
    
    if(tipoDocumento==docFila){
        
        formProveedor.CmbTipoDoc.setSelectedIndex(i);
        break;
    }          
        }  
       //Ciudad
       for (int i = 0; i <=formProveedor.CmbCiudad.getModel().getSize(); i++) {
   ciudad = (modelo.Ciudad) formProveedor.CmbCiudad.getModel().getElementAt(i);
    int ciudadId=ciudad.getIdCiudad();
    
    if(ciudadId==idCiudadCombo){
        formProveedor.CmbCiudad.setSelectedIndex(i);
        break;
    }          
        }
       
       //Provincia
       for (int i = 0; i <=formProveedor.CmbProvincia.getModel().getSize(); i++) {
   provincia = (modelo.Provincia) formProveedor.CmbProvincia.getModel().getElementAt(i);
    int provinciaId=provincia.getIdProvincia();
    
    if(provinciaId==idProvinciaCombo){
        formProveedor.CmbProvincia.setSelectedIndex(i);
        break;
    }          
        }
       
    String numeroDoc=formProveedor.TblProveedores.getValueAt(fila,1).toString(); 
    formProveedor.TxtNumeroDocumento.setText(numeroDoc);
    
    String nombre=formProveedor.TblProveedores.getValueAt(fila,3).toString(); 
    formProveedor.TxtNombres.setText(nombre);
    String apellido=formProveedor.TblProveedores.getValueAt(fila,4).toString(); 
    formProveedor.TxtApellidos.setText(apellido);
    String nomComercial=formProveedor.TblProveedores.getValueAt(fila,5).toString(); 
    formProveedor.TxtNombreComercial.setText(nomComercial);
    String direccion=formProveedor.TblProveedores.getValueAt(fila,6).toString();
    formProveedor.TxtDireccion.setText(direccion);
    String telefono=formProveedor.TblProveedores.getValueAt(fila,9).toString(); 
    formProveedor.txtTelefono.setText(telefono);    
}    
    //Metodo para modificar datos de los proveedores
    public void ModificarProveedor(){
         int id=Integer.parseInt(formProveedor.txtId.getText());
         
         String doc = formProveedor.TxtNumeroDocumento.getText();
         
         long numDoc = 0;
         try {
             numDoc = Long.parseLong(doc);
         } catch (NumberFormatException ex) {
             JOptionPane.showMessageDialog(null,
                     "Error de Numero de documento,el campo esta vacio o contiene caracteres no numericos");
             return;
         }
         
         documento = (modelo.TipoDeDocumento) formProveedor.CmbTipoDoc.getSelectedItem();
        int idTipoDoc=documento.getId();
        if(idTipoDoc<1){
            JOptionPane.showMessageDialog(null, "error el tipo de documento es obligatorio");
            return;
        }
         
         String nombre = formProveedor.TxtNombres.getText().toUpperCase();
         if (nombre.trim().isEmpty()) {
             JOptionPane.showMessageDialog(null, "Error, El Nombre es Obligatorio");
             return;
         }
         
         String apellido=formProveedor.TxtApellidos.getText().toUpperCase();
         if (apellido.trim().isEmpty()) {
             JOptionPane.showMessageDialog(null, "Error, El Apellido es Obligatorio");
             return;
         }
         
         String nombreComercial = formProveedor.TxtNombreComercial.getText().toUpperCase();    
         if (nombreComercial.trim().isEmpty()) {
             JOptionPane.showMessageDialog(null, "Error, El Nombre Comercial es Obligatorio");
             return;
         }
                
         String direccion=formProveedor.TxtDireccion.getText().toUpperCase();
         if (direccion.trim().isEmpty()) {
             JOptionPane.showMessageDialog(null, "Error, La direccion es Obligatorio");
             return;
         }
         
         //Ciudad
        ciudad = (modelo.Ciudad) formProveedor.CmbCiudad.getSelectedItem();
        int idCiudad=ciudad.getIdCiudad();
        if(idCiudad<1){
            JOptionPane.showMessageDialog(null, "error,la ciudad es obligatorio");
            return;        
        }
        
         //provincia
        provincia = (modelo.Provincia) formProveedor.CmbProvincia.getSelectedItem();
        int idProvincia=provincia.getIdProvincia();
        if(idProvincia<1){
            JOptionPane.showMessageDialog(null, "error,la provincia es obligatorio");
            return;
        }
        
         String tel=formProveedor.txtTelefono.getText(); 
        long tele = 0;
         try {
             tele = Long.parseLong(tel);
         } catch (NumberFormatException ex) {
             JOptionPane.showMessageDialog(null, "Error de Numero de telefono,el campo esta vacio o contiene caracteres no numericos");
             return;
         }
        JOptionPane.showMessageDialog(null,id +" "+ numDoc +" "+ idTipoDoc + " "+nombre +" "+ apellido + nombreComercial +" "+ direccion + " "+idCiudad + " "+idProvincia + " "+tele);
                
        proveedorModelo = new modelo.Proveedor(id,numDoc, idTipoDoc, nombre, apellido, nombreComercial, direccion, idCiudad, idProvincia, tele);
        gestorProv.modificarProveedorBD(proveedorModelo);                
    }
    //Metodo para eliminar proveedores
    public void eliminarProveedor(){
      int fila = formProveedor.TblProveedores.getSelectedRow();
      String code = formProveedor.TblProveedores.getValueAt(fila,0).toString();
      int codigo = Integer.parseInt(code);
      gestorProv.eliminarProveedorBD(codigo);
        
    }
    public void limpiar(){
    formProveedor.TxtApellidos.setText(null);
    formProveedor.TxtNombres.setText(null);
    formProveedor.TxtNumeroDocumento.setText(null);
    formProveedor.TxtNombreComercial.setText(null);
    formProveedor.TxtDireccion.setText(null);
    formProveedor.txtTelefono.setText(null);
    
    formProveedor.CmbCiudad.setSelectedIndex(0);
    formProveedor.CmbProvincia.setSelectedIndex(0);
    formProveedor.CmbTipoDoc.setSelectedIndex(0);
    }

}
