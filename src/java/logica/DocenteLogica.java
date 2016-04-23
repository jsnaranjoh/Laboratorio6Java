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
import persistencia.DocenteFacadeLocal;

/**
 *
 * @author jsnar
 */
@Stateless
public class DocenteLogica implements DocenteLogicaLocal {

    @EJB
    DocenteFacadeLocal docenteDAO;

    private int docentesInsertados;
    private int docentesExistentes;
    
    @Override
    public void registrarDocente(Docente docente) throws Exception {
        if(docente == null){
            throw new Exception("Campos vacíos.");
        }
        else{
            if(docente.getDocumentodocente() == 0 || docente.getDocumentodocente() == null){
                throw new Exception("Campo Documento Docente Obligatorio.");
            }
            if(docente.getNombredocente().equals("") || docente.getNombredocente() == null){
                throw new Exception("Campo Nombre Docente Obligatorio.");
            }
            if(docente.getApellidodocente().equals("") || docente.getApellidodocente() == null){
                throw new Exception("Campo Apellido Docente Obligatorio.");
            }
            if(docente.getCorreodocente().equals("") || docente.getCorreodocente() == null){
                throw new Exception("Campo E-mail Docente Obligatorio.");
            }
            if(!docente.getCorreodocente().contains("@") && (!docente.getCorreodocente().endsWith(".com") || !docente.getCorreodocente().endsWith(".es"))){
                throw new Exception("E-mail inválído. Ejemplos válidos: \"example@something.com\" o \"example@something.es\"");
            }
            if(docente.getTelefonodocente().equals("") || docente.getTelefonodocente() == null){
                throw new Exception("Campo Teléfono Obligatorio.");
            }
            if(docente.getProfesiondocente().equals("") || docente.getProfesiondocente() == null){
                throw new Exception("Campo Profesión Docente Obligatorio.");
            }
            if(docente.getClavedocente().equals("") || docente.getClavedocente() == null){
                throw new Exception("Campo Clave Docente Obligatorio");
            }
        }
        
        Docente objDocente = docenteDAO.find(docente.getDocumentodocente());
        if(objDocente != null){
            throw new Exception("Docente ya existe.");
        }
        else{
            docenteDAO.create(docente);
        }
    }

    @Override
    public void modificarDocente(Docente docente) throws Exception {
        if(docente == null){
            throw new Exception("Campos vacíos.");
        }
        else{
            if(docente.getDocumentodocente() == 0 || docente.getDocumentodocente() == null){
                throw new Exception("Campo Documento Docente Obligatorio.");
            }
            if(docente.getNombredocente().equals("") || docente.getNombredocente() == null){
                throw new Exception("Campo Nombre Docente Obligatorio.");
            }
            if(docente.getApellidodocente().equals("") || docente.getApellidodocente() == null){
                throw new Exception("Campo Apellido Docente Obligatorio.");
            }
            if(docente.getCorreodocente().equals("") || docente.getCorreodocente() == null){
                throw new Exception("Campo E-mail Docente Obligatorio.");
            }
            if(!docente.getCorreodocente().contains("@") && (!docente.getCorreodocente().endsWith(".com") || !docente.getCorreodocente().endsWith(".es"))){
                throw new Exception("E-mail inválído. Ejemplos válidos: \"example@something.com\" o \"example@something.es\"");
            }
            
            // Dejaremos el teléfono como campo opcional, pues no todos los profesores tienen teléfono
            
            if(docente.getProfesiondocente().equals("") || docente.getProfesiondocente() == null){
                throw new Exception("Campo Profesión Docente Obligatorio.");
            }
            if(docente.getClavedocente().equals("") || docente.getClavedocente() == null){
                throw new Exception("Campo Clave Docente Obligatorio");
            }
        }
        
        Docente objDocente = docenteDAO.find(docente.getDocumentodocente());
        if(objDocente == null){
            throw new Exception("El docente a modificar no existe.");
        }
        else{
            objDocente.setNombredocente(docente.getNombredocente());
            objDocente.setApellidodocente(docente.getApellidodocente());
            objDocente.setCorreodocente(docente.getCorreodocente());
            objDocente.setTelefonodocente(docente.getTelefonodocente());
            objDocente.setProfesiondocente(docente.getProfesiondocente());
            objDocente.setClavedocente(docente.getClavedocente());
            docenteDAO.edit(objDocente);
        }
    }

    @Override
    public void eliminarDocente(Docente docente) throws Exception {
        if(docente == null){
            throw new Exception("Campos vacíos.");
        }
        else{
            if(docente.getDocumentodocente() == 0 || docente.getDocumentodocente() == null){
                throw new Exception("El documento de Docente es Obligatorio.");
            }
        }
        
        Docente objDocente = docenteDAO.find(docente.getDocumentodocente());
        if(objDocente == null){
            throw new Exception("El Docente a eliminar no existe.");
        }
        else{
            if(objDocente.getMateriaList().size() > 0){
                throw new Exception("El docente tiene materias asociadas.");
            }
            docenteDAO.remove(docente);
        }
    }

    @Override
    public Docente consultarxcodigo(Integer codigo) throws Exception {
        if(codigo == null || codigo == 0){
            throw new Exception("El Código es Obligatorio");
        }
        else{
            return docenteDAO.find(codigo);
        }
    }

    @Override
    public List<Docente> consultarTodos() throws Exception {
        return docenteDAO.findAll();
    }

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
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
