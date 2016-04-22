/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import java.util.List;
import javax.ejb.Local;
import modelo.Carrera;
import modelo.Materia;

/**
 *
 * @author DILOVE
 */
@Local
public interface CarreraLogicaLocal {
        void create(Carrera carrera) throws Exception;
        void edit (Carrera carrera) throws Exception;
        void remove (Carrera carrera) throws Exception;
        Carrera find(Integer codigoCarrera) throws Exception;
        List<Carrera> findAll() throws Exception;       
}
