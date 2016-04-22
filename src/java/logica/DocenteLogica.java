/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import jxl.Sheet;
import jxl.Workbook;
import modelo.Docente;
import org.primefaces.context.RequestContext;
import persistencia.DocenteFacadeLocal;

/**
 *
 * @author Estudiante
 */
@Stateless
public class DocenteLogica implements DocenteLogicaLocal {

    @EJB
    private DocenteFacadeLocal docenteDAO;

    private int docentesInsertados;
    private int docentesExistentes;

    @Override
    public void create(Docente docente) throws Exception {
        Docente objdocente = docenteDAO.find(docente.getDocumentodocente());
        if (docente != null) {
            if (docente.getDocumentodocente() == null) {
                throw new Exception("el documento es  obligatorio");
            } else if (docente.getNombredocente() == null || docente.getApellidodocente().equals("")) {
                throw new Exception("el nombre y el apellido es obligatorio");
            }
        } else {
            throw new Exception("el docente no tiene informacion");
        }
        if (objdocente == null) {
            docenteDAO.create(docente);
        } else {
            throw new Exception("el docente  ya esta registrado");
        }
    }

    @Override
    public void edit(Docente docente) throws Exception {
        Docente objdocente = docenteDAO.find(docente.getDocumentodocente());
        if (docente != null) {
            if (docente.getDocumentodocente() == null) {
                throw new Exception(" el documento es  obligatorio");
            } else if (docente.getNombredocente() == null || docente.getApellidodocente() == null || docente.getCorreodocente().equals("")) {
                throw new Exception("el nombre,apellido y el correo son obligatorio");
            }
        }
        if (objdocente == null) {
            throw new Exception("el docente  no existe no se puede modificar");
        } else {
            docenteDAO.edit(docente);
        }
    }

    @Override
    public void remove(Docente docente) throws Exception {
        Docente objdocente = docenteDAO.find(docente.getDocumentodocente());
        if (docente != null) {
            if (docente.getDocumentodocente() == null) {
                throw new Exception("el documento es  obligatorio");
            } else if (docente.getNombredocente() == null || docente.getNombredocente().equals("")) {
                throw new Exception("el nombre es obligatorio");
            }
        }
        if (objdocente == null) {
            throw new Exception("el docente  no existe no se puede modificar");
        } else {
            docenteDAO.remove(docente);
        }
    }

    @Override
    public Docente find(Long documentodocente) throws Exception {
        if (documentodocente != null) {
            return docenteDAO.find(documentodocente);
        } else {
            throw new Exception("el codigo para consultar es obligatorio");
        }
    }

    @Override
    public List<Docente> findAll() throws Exception {
        return docenteDAO.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public String importarDatosInstructor(String archivo) throws Exception {
        Workbook archivoExcel = Workbook.getWorkbook(new File(archivo));
        //Recorrer las filas de la primera hoja
        Sheet hoja = archivoExcel.getSheet(0);
        int numFilas = hoja.getRows();

        docentesInsertados = 0;
        docentesExistentes = 0;

        for (int fila = 1; fila < numFilas; fila++) { // Recorre cada 
            Docente nuevoDocente = new Docente();

            nuevoDocente.setDocumentodocente(Long.parseLong(hoja.getCell(0, fila).getContents()));
            nuevoDocente.setNombredocente(hoja.getCell(1, fila).getContents());
            nuevoDocente.setApellidodocente(hoja.getCell(2, fila).getContents());
            nuevoDocente.setCorreodocente(hoja.getCell(3, fila).getContents());
            nuevoDocente.setTelefonodocente(hoja.getCell(4, fila).getContents());
            nuevoDocente.setProfesiondocente(hoja.getCell(5, fila).getContents());
            nuevoDocente.setClavedocente(hoja.getCell(0, fila).getContents());

            Docente d = docenteDAO.find(nuevoDocente.getDocumentodocente());
            if (d == null) {
                docenteDAO.create(nuevoDocente);
                docentesInsertados++;
            } else {
                docentesExistentes++;
            }
        }
        return "Se registraron " + docentesInsertados + " docentes. Ya existían " + docentesExistentes;
    }
    
    @Override
    public void generarReporteDocentes(String url) throws Exception {
               String params =
                "'"
                + url + "/ReporteDocentes.do',"
                + "'reportWindow', "
                + "'"
                + "width=1024"
                + ",height=768"
                + ",status=no"
                + ",toolbar=no"
                + ",menubar=no"
                + ",location=no"
                + ",scrollbars=yes"
                + "'";
        RequestContext.getCurrentInstance().execute("location.href=" + params + ";");

    }
}
