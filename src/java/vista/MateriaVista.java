/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import logica.CarreraLogicaLocal;
import logica.MateriaLogicaLocal;
import modelo.Carrera;
import modelo.Materia;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author DILOVE
 */
@ManagedBean
@RequestScoped
public class MateriaVista {
    
    private InputText txtNumero;
    private InputText txtNombre;
    private InputText txtcreditos;
    private SelectOneMenu cmbCarrera;
    private ArrayList<SelectItem> opcionesCarrera;
    private List<Materia> listaMaterias;
    private Materia selectMateria;
    private CommandButton btnRegistrar;
    private CommandButton btnModificar;
    private CommandButton btnEliminar;
    private CommandButton btnLimpiar;
    
    @EJB
    private CarreraLogicaLocal carreraLogica;
    
    @EJB
    private MateriaLogicaLocal materiaLogica;
    

    /**
     * Creates a new instance of MateriaVista
     */
    public MateriaVista() {
    }

    public InputText getTxtNumero() {
        return txtNumero;
    }

    public void setTxtNumero(InputText txtNumero) {
        this.txtNumero = txtNumero;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtcreditos() {
        return txtcreditos;
    }

    public void setTxtcreditos(InputText txtcreditos) {
        this.txtcreditos = txtcreditos;
    }

    public SelectOneMenu getCmbCarrera() {
        return cmbCarrera;
    }

    public void setCmbCarrera(SelectOneMenu cmbCarrera) {
        this.cmbCarrera = cmbCarrera;
    }

    public ArrayList<SelectItem> getOpcionesCarrera() {
        if(opcionesCarrera==null){
            try {
                opcionesCarrera = new ArrayList<>();
                List<Carrera> listacarreras = carreraLogica.findAll();
                for (int i = 0; i < listacarreras.size(); i++) {
                    opcionesCarrera.add(new SelectItem(listacarreras.get(i).getNumerocarrera(), listacarreras.get(i).getNombrecarrera()));
                }
            } catch (Exception ex) {
                Logger.getLogger(MateriaVista.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return opcionesCarrera;
    }

    public void setOpcionesCarrera(ArrayList<SelectItem> opcionesCarrera) {
        this.opcionesCarrera = opcionesCarrera;
    }

    public List<Materia> getListaMaterias() {
        if(listaMaterias==null){
            try {
                listaMaterias = materiaLogica.consultar();
            } catch (Exception ex) {
                Logger.getLogger(MateriaVista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaMaterias;
    }

    public void setListaMaterias(List<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public Materia getSelectMateria() {
        return selectMateria;
    }

    public void setSelectMateria(Materia selectMateria) {
        this.selectMateria = selectMateria;
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
    
    public void registrar(){
        try {
            Materia nuevaMateria = new Materia();
            Carrera nuevaCarrera = new Carrera();
            nuevaMateria.setNumeromateria(Integer.parseInt(txtNumero.getValue().toString()));
            nuevaMateria.setNombremateria(txtNombre.getValue().toString());
            nuevaMateria.setCreditosmateria(Integer.parseInt(txtcreditos.getValue().toString()));
            nuevaCarrera.setNumerocarrera(Integer.parseInt(cmbCarrera.getValue().toString()));
            nuevaMateria.setNumerocarrera(nuevaCarrera);
            materiaLogica.registrar(nuevaMateria);
            limpiar();
            listaMaterias =null;
        } catch (Exception ex) {
            Logger.getLogger(MateriaVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void limpiar(){
        txtNombre.setValue("");
        txtNumero.setValue("");
        cmbCarrera.setValue("seleccione");
        txtcreditos.setValue("");
        btnRegistrar.setDisabled(false);
        btnModificar.setDisabled(true);
        btnEliminar.setDisabled(true);
        txtNumero.setDisabled(false);
    
    }
    
    public void onRowSelect(SelectEvent evt){
        Materia m = selectMateria;
        txtNumero.setValue(m.getNumeromateria());
        txtNombre.setValue(m.getNombremateria());
        txtcreditos.setValue(m.getCreditosmateria());
        cmbCarrera.setValue(m.getNumerocarrera().getNumerocarrera());
        btnRegistrar.setDisabled(true);
        btnModificar.setDisabled(false);
        btnEliminar.setDisabled(false);
        txtNumero.setDisabled(true);
    }
    
    public void modificar(){
        try {
            Materia m = new Materia();
            m.setNumeromateria(Integer.parseInt(txtNumero.getValue().toString()));
            m.setNombremateria(txtNombre.getValue().toString());
            m.setCreditosmateria(Integer.parseInt(txtcreditos.getValue().toString()));
            Carrera c = new Carrera();
            c.setNumerocarrera(Integer.parseInt(cmbCarrera.getValue().toString()));
            m.setNumerocarrera(c);
            materiaLogica.modificar(m);
             limpiar();
            listaMaterias =null;
            FacesContext.getCurrentInstance().addMessage("Mensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "La materia se modificó con Éxito"));
        } catch (Exception ex) {
            Logger.getLogger(MateriaVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void eliminar(){
        try {
            Materia m = new Materia();
            m.setNumeromateria(Integer.parseInt(txtNumero.getValue().toString()));            
            materiaLogica.eliminar(m);
             limpiar();
            listaMaterias =null;
            FacesContext.getCurrentInstance().addMessage("Mensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "La materia se eliminó con Éxito"));
        } catch (Exception ex) {
            Logger.getLogger(MateriaVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
