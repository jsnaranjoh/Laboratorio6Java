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
import logica.EstudianteLogicaLocal;
import modelo.Estudiante;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

/**
 *
 * @author Estudiante
 */
@ManagedBean
@RequestScoped
public class EstudianteVista {
   private InputText txtDocumentoEstudiante;
   private InputText txtNombreEstudiante;
   private InputText txtApellidoEstudiante;
   private InputText txtCorreoEstudiante;
   private InputText txtSemestreEstudiante;
   private InputText txtClaveEstudiante;
   private CommandButton btnRegistrar;
   private CommandButton btnModificar;
   private CommandButton btnLimpiar;
   private CommandButton btnEliminar;
   private List<Estudiante> listaEstudiante;
   private Estudiante selectedEstudiante;
   
   @EJB
    private EstudianteLogicaLocal estudiantelogica;
    /**
     * Creates a new instance of EstudianteVista
     */
    public EstudianteVista() {
    }

    public InputText getTxtDocumentoEstudiante() {
        return txtDocumentoEstudiante;
    }

    public void setTxtDocumentoEstudiante(InputText txtDocumentoEstudiante) {
        this.txtDocumentoEstudiante = txtDocumentoEstudiante;
    }

    public InputText getTxtNombreEstudiante() {
        return txtNombreEstudiante;
    }

    public void setTxtNombreEstudiante(InputText txtNombreEstudiante) {
        this.txtNombreEstudiante = txtNombreEstudiante;
    }

    public InputText getTxtApellidoEstudiante() {
        return txtApellidoEstudiante;
    }

    public void setTxtApellidoEstudiante(InputText txtApellidoEstudiante) {
        this.txtApellidoEstudiante = txtApellidoEstudiante;
    }

    public InputText getTxtCorreoEstudiante() {
        return txtCorreoEstudiante;
    }

    public void setTxtCorreoEstudiante(InputText txtCorreoEstudiante) {
        this.txtCorreoEstudiante = txtCorreoEstudiante;
    }

    public InputText getTxtSemestreEstudiante() {
        return txtSemestreEstudiante;
    }

    public void setTxtSemestreEstudiante(InputText txtSemestreEstudiante) {
        this.txtSemestreEstudiante = txtSemestreEstudiante;
    }

    public InputText getTxtClaveEstudiante() {
        return txtClaveEstudiante;
    }

    public void setTxtClaveEstudiante(InputText txtClaveEstudiante) {
        this.txtClaveEstudiante = txtClaveEstudiante;
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

    public CommandButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(CommandButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    public CommandButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(CommandButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }
    
    public List<Estudiante> getListaEstudiante() {
        if(listaEstudiante==null){
            try {
                listaEstudiante = estudiantelogica.findAll();
            } catch (Exception ex) {
                Logger.getLogger(EstudianteVista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaEstudiante;
    }

    public void setListaEstudiante(List<Estudiante> listaEstudiante) {
        this.listaEstudiante = listaEstudiante;
    }

    public Estudiante getSelectedEstudiante() {
        return selectedEstudiante;
    }

    public void setSelectedEstudiante(Estudiante selectedEstudiante) {
        this.selectedEstudiante = selectedEstudiante;
    }

    public EstudianteLogicaLocal getEstudiantelogica() {
        return estudiantelogica;
    }

    public void setEstudiantelogica(EstudianteLogicaLocal estudiantelogica) {
        this.estudiantelogica = estudiantelogica;
    }
    public void accion_registrar() {
        try {
            Estudiante nuevaEstudiante = new Estudiante();
            nuevaEstudiante.setDocumentoestudiante(Long.parseLong(txtDocumentoEstudiante.getValue().toString()));
            nuevaEstudiante.setNombreestudiante(txtNombreEstudiante.getValue().toString());
            nuevaEstudiante.setApellidoestudiante(txtApellidoEstudiante.getValue().toString());
            nuevaEstudiante.setCorreoestudiante(txtCorreoEstudiante.getValue().toString());
            nuevaEstudiante.setSemestreestudiante(Integer.parseInt(txtSemestreEstudiante.getValue().toString()));
            nuevaEstudiante.setClaveestudiante(txtClaveEstudiante.getValue().toString());
           estudiantelogica.create(nuevaEstudiante);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "El estudiante se  registro correta mente"));
            listaEstudiante = null;
        } catch (NumberFormatException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El numero del documento  debe ser un numero y no letras"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
        }
    }

    public void limpiar() {
        txtDocumentoEstudiante.setValue("");
        txtNombreEstudiante.setValue("");
        txtApellidoEstudiante.setValue("");
        txtCorreoEstudiante.setValue("");
        txtSemestreEstudiante.setValue("");
        txtClaveEstudiante.setValue("");
        btnRegistrar.setDisabled(false);
        btnModificar.setDisabled(true);
        btnEliminar.setDisabled(true);
    }
}
