/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.Local;
import modelo.Estudiante;

/**
 *
 * @author Estudiante
 */
@Local
public interface EstudianteLogicaLocal {
      void create (Estudiante estudiante)throws Exception;
    void edit (Estudiante estudiante)throws Exception;
    void remove (Estudiante estudiante)throws Exception;
    Estudiante find (Long documentoestudiante)throws Exception;
    List<Estudiante> findAll()throws Exception;
}
