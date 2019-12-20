
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GestorPrincipalControlador implements ActionListener {

    Vista.Principal formPrincipal;
    Vista.RegistrarProveedor proveedorRegistro;
    
    public GestorPrincipalControlador(Vista.Principal formprincipal) {
        
        this.formPrincipal=formprincipal;
        proveedorRegistro=new Vista.RegistrarProveedor();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==formPrincipal.proveedorFrame){
            
            proveedorRegistro.setVisible(true);
            
            
        }
        
        
    }
    
}
