/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import logica.CarreraLogicaLocal;
import modelo.Carrera;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

/**
 *
 * @author DILOVE
 */
@ManagedBean(name = "carreraVista")
@RequestScoped
public class CarreraVista {
    private InputText txtNumeroCarrera;
    private InputText txtNombreCarrera;
    private CommandButton btnRegistrar;
    private CommandButton btnModificar;
    private CommandButton btnEliminar;
    private CommandButton btnLimpiar;
    private List<Carrera> listaCarreras;
    private Carrera selectedCarrera;  

    @EJB
    private CarreraLogicaLocal carreraLogica;
    /**
     * Creates a new instance of CarreraVista
     */
    public CarreraVista() {
    }

    public InputText getTxtNumeroCarrera() {
        return txtNumeroCarrera;
    }

    public void setTxtNumeroCarrera(InputText txtNumeroCarrera) {
        this.txtNumeroCarrera = txtNumeroCarrera;
    }

    public InputText getTxtNombreCarrera() {
        return txtNombreCarrera;
    }

    public void setTxtNombreCarrera(InputText txtNombreCarrera) {
        this.txtNombreCarrera = txtNombreCarrera;
    }

    public CommandButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void setBtnRegistrar(CommandButton btnRegistrar) {
        this.btnRegistrar = btnRegistrar;
    }

    public CommandButton getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(CommandButton btnModificar) {
        this.btnModificar = btnModificar;
    }

    public CommandButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(CommandButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public CommandButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(CommandButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    public List<Carrera> getListaCarreras() {
        if(listaCarreras==null){
            try {
                listaCarreras = carreraLogica.findAll();
            } catch (Exception ex) {
                Logger.getLogger(CarreraVista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaCarreras;
    }

    public void setListaCarreras(List<Carrera> listaCarreras) {
        this.listaCarreras = listaCarreras;
    }

    public Carrera getSelectedCarrera() {
        return selectedCarrera;
    }

    public void setSelectedCarrera(Carrera selectedCarrera) {
        this.selectedCarrera = selectedCarrera;
    }
    
    
    public void accion_registrar(){
        try {
            Carrera nuevaCarrera = new Carrera();
            nuevaCarrera.setNumerocarrera(Integer.parseInt(txtNumeroCarrera.getValue().toString()));
            nuevaCarrera.setNombrecarrera(txtNombreCarrera.getValue().toString());
            carreraLogica.create(nuevaCarrera);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "La carrera se registró correctamente."));
            listaCarreras = null;
            limpiar();
        }catch(NumberFormatException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El número de la carrera debe ser numerico"));
        }catch (Exception  ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage()));
        }
    }
    
    public void limpiar(){
        txtNumeroCarrera.setValue("");
        txtNombreCarrera.setValue("");
        btnRegistrar.setDisabled(false);
        btnModificar.setDisabled(true);
        btnEliminar.setDisabled(true);
    }
    
    
}
