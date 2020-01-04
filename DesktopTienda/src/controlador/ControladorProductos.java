
package controlador;

import Vista.RegistroProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.LinkedList;
import java.util.Vector;
import javax.swing.JOptionPane;


public class ControladorProductos implements ActionListener,MouseListener{

    Vista.RegistroProductos formProducto;   
    modelo.GestorProveedor gestorProveedor;
    modelo.Proveedor proveedor;
    modelo.Producto producto;
    modelo.GestorProducto gestorProducto;
    
    modelo.GestorTipoProducto gestorTipoProducto;        
    modelo.TipoProducto tipoProducto;
    
    public ControladorProductos(RegistroProductos formProducto) {
        this.formProducto = formProducto;
        gestorTipoProducto = new modelo.GestorTipoProducto();
        gestorProducto = new modelo.GestorProducto();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == formProducto.btnRegistrar){
            registrarProducto();
            limpiarCasillas();
        }
        
        if (e.getSource() == formProducto.btnBuscar){
            formProducto.modelo.setRowCount(0);
            buscarProductos();         
        }
        
        if(e.getSource() == formProducto.btnEliminar){
            eliminarProducto();
        }
        

    }
        @Override
    public void mouseClicked(MouseEvent e) {
         if(e.getSource() == formProducto.jTableProductos){
            try {
             cargarProductos();
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
    
    public Vector cargarTipoProducto() {
        return gestorTipoProducto.cargarTipoProductos(0);
    }
    public Vector cargarProveedor(){
        gestorProveedor = new modelo.GestorProveedor();
        return gestorProveedor.cargarProveedorBD(0);
    }
    //Registro de productos
    public void registrarProducto(){
        String nombreProducto=formProducto.txtNombreProducto.getText().toUpperCase();
        if(nombreProducto.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Error, debe agregar un nombre al producto");
        return;             
        }
        
        String descripcion=formProducto.txtDescripcion.getText().toUpperCase();
        if(descripcion.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Error, el producto debe de tener una descripcion");
        return; 
        }
        String precioCosto=formProducto.txtPrecioCosto.getText();
        double precioCost=0;
        try {
            precioCost=Double.parseDouble(precioCosto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Error, el precio de costo no fue ingresado o posee caracteres no permitidos");
        }
        
        String precioVenta=formProducto.txtPrecioDeVenta.getText();
        double precioSell=0;
        try {
            precioSell=Double.parseDouble(precioVenta);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Error, el precio de venta no fue ingresado o posee caracteres no permitidos");
            return;
        }
        
          String stock=formProducto.txtStock.getText();
        int cantidad=0;
        try {
            cantidad = Integer.parseInt(stock);
        } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(null,"Error, el stock no fue ingresado o posee caracteres no permitidos");
        }
        
        tipoProducto= (modelo.TipoProducto)formProducto.cmbTipoProducto.getSelectedItem();
        int idProducto = tipoProducto.getIdTipoProducto();
        if(idProducto < 1){
        JOptionPane.showMessageDialog(null, "Error, el tipo de productor debe ser seleccionado");
        return; 
        }   
        
        proveedor = (modelo.Proveedor)formProducto.cmbProveedor.getSelectedItem();      
        int idProveedor = proveedor.getId();
        if(idProveedor < 1){
        JOptionPane.showMessageDialog(null, "Error el proveedor debe ser seleccionado");
        return;    
        }
        
        Date fechaRegistro = new Date();
        
        Date fechaVencimiento = formProducto.fechaVencimiento.getDatoFecha();
        
        if(fechaVencimiento == null){
            JOptionPane.showMessageDialog(null,"Debe seleccionar una fecha de vencimiento");
            return;
        }
        
        producto = new modelo.Producto(nombreProducto, descripcion, precioCost, precioSell, cantidad, idProducto, idProveedor, fechaRegistro, fechaVencimiento);
        gestorProducto.registroProductoBD(producto);
    }
    
     public void buscarProductos() {

        int parametrosABuscar = 0;
        int idCategoria =0;
        int idProve =0;
        String nombreProduc = "";
        //Realizamos una verificacion sencilla para traer a los proveedores por los parametros nombre proveedor o tipo de producto.
        if (formProducto.cmbProveedor.getSelectedIndex() > 0) {
            parametrosABuscar = 1;  
            proveedor = (modelo.Proveedor) formProducto.cmbProveedor.getSelectedItem();
            idProve=proveedor.getId();
        }
        if (formProducto.cmbTipoProducto.getSelectedIndex() > 0) {
            parametrosABuscar = 2;    
          tipoProducto = (modelo.TipoProducto)formProducto.cmbTipoProducto.getSelectedItem();
          idCategoria=tipoProducto.getIdTipoProducto();
        }
        if(formProducto.cmbProveedor.getSelectedIndex() > 0 && formProducto.cmbTipoProducto.getSelectedIndex() > 0){
            parametrosABuscar = 3;
        }
         //al linkedlist lo llenamos con objetos de tipo producto para luego recorrer los mismos con un for each y asignarselo a la tabla  de Productos.
        LinkedList<modelo.Producto> product = gestorProducto.buscarProducto(parametrosABuscar, idProve,idCategoria);
        
        String dtoProv[] = new String[10];
        for (modelo.Producto pro : product) {
            dtoProv[0] = Integer.toString(pro.getIdProducto());
            dtoProv[1] = pro.getNombreProducto();
            dtoProv[2] = pro.getDescripcionProducto();
            dtoProv[3] = Double.toString(pro.getPrecioCosto());
            dtoProv[4] = Double.toString(pro.getPrecioVenta());
            dtoProv[5] = Integer.toString(pro.getStock());
            dtoProv[6] = Integer.toString(pro.getTipoProducto());
            dtoProv[7] = Integer.toString(pro.getProveedor());
            dtoProv[8] = pro.getFechaIngreso().toString();
            dtoProv[9] = pro.getFechaVencimiento().toString();

            formProducto.modelo.addRow(dtoProv);
            formProducto.modelo.fireTableDataChanged();
        }

    }
     
      public void cargarProductos()throws Exception{
     int fila = formProducto.jTableProductos.getSelectedRow();  
          
    String nombreProducto=formProducto.jTableProductos.getValueAt(fila,1).toString(); 
    formProducto.txtNombreProducto.setText(nombreProducto);
    
    String descripcion=formProducto.jTableProductos.getValueAt(fila,2).toString(); 
    formProducto.txtDescripcion.setText(descripcion);
    
    String precioCosto=formProducto.jTableProductos.getValueAt(fila,3).toString(); 
    formProducto.txtPrecioCosto.setText(precioCosto);
    
    String precioVenta=formProducto.jTableProductos.getValueAt(fila,4).toString(); 
    formProducto.txtPrecioDeVenta.setText(precioVenta);
    
     /*recorremos los tipos de combo box y comparamos sus id con el id obtenido de la
       tabla para poder asignarle la informacion necesaria.
     */  
     
    //Tipo producto
       for (int i = 0; i <=formProducto.cmbTipoProducto.getModel().getSize(); i++) {
    tipoProducto= (modelo.TipoProducto) formProducto.cmbTipoProducto.getItemAt(i);
    int idTipoProductoCombo=tipoProducto.getIdTipoProducto();
    
    int idTipoProductoTabla=Integer.parseInt(formProducto.jTableProductos.getValueAt(fila,6).toString()); 
    
    if(idTipoProductoCombo==idTipoProductoTabla){
        
        formProducto.cmbTipoProducto.setSelectedIndex(i);
        break;
    }          
        } 
       
       //Proveedor
       for (int i = 0; i <=formProducto.cmbProveedor.getModel().getSize(); i++) {
    proveedor = (modelo.Proveedor) formProducto.cmbProveedor.getModel().getElementAt(i);
    int idProveedorCombo=proveedor.getId();
    
     int idProveedor=Integer.parseInt(formProducto.jTableProductos.getValueAt(fila,7).toString()); 
    if(idProveedor==idProveedorCombo){
        formProducto.cmbProveedor.setSelectedIndex(i);
        break;
    }          
        }
    
    String stock=formProducto.jTableProductos.getValueAt(fila,5).toString();
    formProducto.txtStock.setText(stock);
    
    String fechaDeVencimiento=formProducto.jTableProductos.getValueAt(fila,9).toString(); 
    Date fechaConsulta = new Date(fechaDeVencimiento);
    formProducto.fechaVencimiento.setDatoFecha(fechaConsulta);
}
     
     public void eliminarProducto(){
      int fila = formProducto.jTableProductos.getSelectedRow();
      String code = formProducto.jTableProductos.getValueAt(fila,0).toString();
      int codigo = Integer.parseInt(code);
      gestorProducto.eliminarProductoBD(codigo);
        
    }
     
     public void limpiarCasillas(){
         formProducto.cmbProveedor.setSelectedIndex(0);
         formProducto.cmbTipoProducto.setSelectedIndex(0);
         formProducto.txtDescripcion.setText(null);
         formProducto.txtNombreProducto.setText(null);
         formProducto.txtPrecioCosto.setText(null);
         formProducto.txtPrecioDeVenta.setText(null);
         formProducto.txtStock.setText(null);
         formProducto.fechaVencimiento.setDatoFecha(null);
     }


    
}
