/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.Local;
import modelo.Docente;

/**
 *
 * @author Estudiante
 */
@Local
public interface DocenteLogicaLocal {
     void create (Docente docente)throws Exception;
    void edit (Docente docente)throws Exception;
    void remove (Docente docente)throws Exception;
    Docente find (Long documentodocente)throws Exception;
    List<Docente> findAll()throws Exception;
    String importarDatosInstructor(String archivo) throws Exception;
    public void generarReporteDocentes(String url) throws Exception;
}
