/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import java.util.List;
import javax.ejb.Local;
import modelo.Materia;

/**
 *
 * @author DILOVE
 */
@Local
public interface MateriaLogicaLocal {
    public void registrar(Materia materia) throws Exception;
    public void modificar(Materia materia) throws Exception;
    public void eliminar(Materia materia) throws Exception;
    public List<Materia> consultar() throws Exception;
    public Materia consultarPorCodigo(Integer codigoMateria) throws Exception;
    public List<Materia> consultarMateriasDocente (String nombre) throws Exception;
}
