/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Docente;
import modelo.Materia;
import persistencia.DocenteFacadeLocal;
import persistencia.MateriaFacadeLocal;

/**
 *
 * @author DILOVE
 */
@Stateless
public class MateriaLogica implements MateriaLogicaLocal {
    
    @EJB
    private MateriaFacadeLocal materiaDAO;
    
    @EJB
    private DocenteFacadeLocal docenteDAO;

    @Override
    public void registrar(Materia materia) throws Exception {
        materiaDAO.create(materia);
    }

    @Override
    public void modificar(Materia materia) throws Exception {
        materiaDAO.edit(materia);
    }

    @Override
    public void eliminar(Materia materia) throws Exception {
        materiaDAO.remove(materia);
    }

    @Override
    public List<Materia> consultar() throws Exception {
        return materiaDAO.findAll();
    }

    @Override
    public Materia consultarPorCodigo(Integer codigoMateria) throws Exception {
        return materiaDAO.find(codigoMateria);
    }

    @Override
    public List<Materia> consultarMateriasDocente(String nombre) throws Exception {
        List<Docente> docentes = docenteDAO.consultarDocentexNombre(nombre);
        List<Materia> listaM = new ArrayList<>();
        for (int i = 0; i < docentes.size(); i++) {
              listaM.addAll(docentes.get(i).getMateriaList());
        }
        return listaM;
    }
}
