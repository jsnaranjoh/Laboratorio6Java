/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import logica.DocenteLogicaLocal;
import logica.MateriaLogicaLocal;
import modelo.Docente;
import modelo.Materia;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Estudiante
 */
@ManagedBean
@RequestScoped
public class DocenteVista {
private InputText txtdocumentoDocente;
private InputText txtNombreDocente;
private InputText txtApellidoDocente;
private InputText txtCorreoDocente;
private InputText txtTlelefonodocente;
private InputText txtProfesionDocente;
private InputText txtClaveDocente;
private CommandButton btnRegistrar;
private CommandButton btnModificar;
private CommandButton btnLimpiar;
private CommandButton btnEliminar;
private CommandButton btnReporte;
private List<Docente> listaDocente;
private List<Materia> listaMaterias;
private List<Materia> listaMateriasSeleccionadas;
private Docente selectedDocente;

@EJB
private DocenteLogicaLocal docentelogica;

@EJB
private MateriaLogicaLocal materiaLogica;
    /**
     * Creates a new instance of DocenteVista
     */
    public DocenteVista() {
    }

    public InputText getTxtdocumentoDocente() {
        return txtdocumentoDocente;
    }

    public void setTxtdocumentoDocente(InputText txtdocumentoDocente) {
        this.txtdocumentoDocente = txtdocumentoDocente;
    }

    public InputText getTxtNombreDocente() {
        return txtNombreDocente;
    }

    public void setTxtNombreDocente(InputText txtNombreDocente) {
        this.txtNombreDocente = txtNombreDocente;
    }

    public InputText getTxtApellidoDocente() {
        return txtApellidoDocente;
    }

    public void setTxtApellidoDocente(InputText txtApellidoDocente) {
        this.txtApellidoDocente = txtApellidoDocente;
    }

    public InputText getTxtCorreoDocente() {
        return txtCorreoDocente;
    }

    public void setTxtCorreoDocente(InputText txtCorreoDocente) {
        this.txtCorreoDocente = txtCorreoDocente;
    }

    public InputText getTxtTlelefonodocente() {
        return txtTlelefonodocente;
    }

    public void setTxtTlelefonodocente(InputText txtTlelefonodocente) {
        this.txtTlelefonodocente = txtTlelefonodocente;
    }

    public InputText getTxtProfesionDocente() {
        return txtProfesionDocente;
    }

    public void setTxtProfesionDocente(InputText txtProfesionDocente) {
        this.txtProfesionDocente = txtProfesionDocente;
    }

    public InputText getTxtClaveDocente() {
        return txtClaveDocente;
    }

    public void setTxtClaveDocente(InputText txtClaveDocente) {
        this.txtClaveDocente = txtClaveDocente;
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

    public List<Docente> getListaDocente() {
        if(listaDocente== null){
            try {
                listaDocente=docentelogica.findAll();
            } catch (Exception ex) {
                Logger.getLogger(DocenteVista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaDocente;
    }

    public void setListaDocente(List<Docente> listaDocente) {
        this.listaDocente = listaDocente;
    }

    public Docente getSelectedDocente() {
        return selectedDocente;
    }

    public void setSelectedDocente(Docente selectedDocente) {
        this.selectedDocente = selectedDocente;
    }
     public void accion_registrar() {
        try {
            Docente nuevoDocente = new Docente();
            nuevoDocente.setDocumentodocente(Long.parseLong(txtdocumentoDocente.getValue().toString()));
            nuevoDocente.setNombredocente(txtNombreDocente.getValue().toString());
            nuevoDocente.setApellidodocente(txtApellidoDocente.getValue().toString());
            nuevoDocente.setCorreodocente(txtCorreoDocente.getValue().toString());
            nuevoDocente.setTelefonodocente(txtTlelefonodocente.getValue().toString());
            nuevoDocente.setProfesiondocente(txtProfesionDocente.getValue().toString());
            nuevoDocente.setClavedocente(txtClaveDocente.getValue().toString());
           docentelogica.create(nuevoDocente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "El docente se  registro correctamente"));
            listaDocente = null;
        } catch (NumberFormatException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El numero del documento  debe ser un numero y no letras"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
        }
    }

    public void limpiar() {
        txtdocumentoDocente.setValue("");
        txtNombreDocente.setValue("");
        txtApellidoDocente.setValue("");
        txtCorreoDocente.setValue("");
        txtTlelefonodocente.setValue("");
        txtProfesionDocente.setValue("");
        txtClaveDocente.setValue("");
        btnRegistrar.setDisabled(false);
        btnModificar.setDisabled(true);
        btnEliminar.setDisabled(true);
    }
    
    public void seleccionar(SelectEvent e){
        Docente d = selectedDocente;
        txtdocumentoDocente.setValue(d.getDocumentodocente()+"");
        txtNombreDocente.setValue(d.getNombredocente());
        txtApellidoDocente.setValue(d.getApellidodocente());
        txtCorreoDocente.setValue(d.getCorreodocente());
        txtTlelefonodocente.setValue(d.getTelefonodocente());
        txtProfesionDocente.setValue(d.getProfesiondocente());
        btnModificar.setDisabled(false);
        btnEliminar.setDisabled(false);
        btnRegistrar.setDisabled(true);
    }
    
    public void modificar(){
    try {
        Docente nuevoDocente = new Docente();
        nuevoDocente.setDocumentodocente(Long.parseLong(txtdocumentoDocente.getValue().toString()));
        nuevoDocente.setNombredocente(txtNombreDocente.getValue().toString());
        nuevoDocente.setApellidodocente(txtApellidoDocente.getValue().toString());
        nuevoDocente.setCorreodocente(txtCorreoDocente.getValue().toString());
        nuevoDocente.setTelefonodocente(txtTlelefonodocente.getValue().toString());
        nuevoDocente.setProfesiondocente(txtProfesionDocente.getValue().toString());
        nuevoDocente.setClavedocente(txtClaveDocente.getValue().toString());
        nuevoDocente.setMateriaList(listaMateriasSeleccionadas);
        docentelogica.edit(nuevoDocente);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "El docente se  modifico correctamente"));
        listaDocente = null;
    } catch (Exception ex) {
        Logger.getLogger(DocenteVista.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    public List<Materia> getListaMaterias() {
        if(listaMaterias==null){
            try {
                listaMaterias = materiaLogica.consultar();
            } catch (Exception ex) {
                Logger.getLogger(DocenteVista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaMaterias;
    }

    public void setListaMaterias(List<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public List<Materia> getListaMateriasSeleccionadas() {
        return listaMateriasSeleccionadas;
    }

    public void setListaMateriasSeleccionadas(List<Materia> listaMateriasSeleccionadas) {
        this.listaMateriasSeleccionadas = listaMateriasSeleccionadas;
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        // Indica a dónde se deben guardar los archivos de excel a partir de la carpeta build del proyecto.
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String rutaDestino = (String) servletContext.getRealPath("/excel"); // Sustituye "/" por el directorio ej: "/upload"

        System.out.println("Ruta Server: " + rutaDestino);
        try {
            copyFile(rutaDestino, event.getFile().getFileName(), event.getFile().getInputstream());
            String resultado = docentelogica.importarDatosInstructor(rutaDestino + "\\" + event.getFile().getFileName());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok: ", resultado));
           
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(DocenteVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void copyFile(String rutaDestino, String fileName, InputStream in) {
        try {
            OutputStream out = new FileOutputStream(new File(rutaDestino + "\\" + fileName));
            System.out.println("Ruta Archivo: " + rutaDestino + "\\" +fileName);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
            //System.out.println("New file created!");
        } catch (IOException e) {
            //System.out.println(e.getMessage());
        }
    }

    public CommandButton getBtnReporte() {
        return btnReporte;
    }

    public void setBtnReporte(CommandButton btnReporte) {
        this.btnReporte = btnReporte;
    }
    
    
    public void generarReporteDocente(){
        try {

                FacesContext fc = FacesContext.getCurrentInstance();
                ExternalContext ec = fc.getExternalContext();
                HttpServletRequest sr = ((HttpServletRequest) ec.getRequest());
                String scheme = sr.getScheme();
                String serverName = sr.getServerName();
                int port = sr.getServerPort();
                String contextPath = sr.getContextPath();
                String url = scheme + "://" + serverName + ":" + port + contextPath;
                docentelogica.generarReporteDocentes(url);
            } catch (Exception ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", ex.getMessage()));
            }

    }
}
