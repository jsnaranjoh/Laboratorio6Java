/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Carrera;
import persistencia.CarreraFacadeLocal;

/**
 *
 * @author DILOVE
 */
@Stateless
public class CarreraLogica implements CarreraLogicaLocal {
    @EJB
    private CarreraFacadeLocal carreraDAO;
    
    @Override
    public void create(Carrera carrera) throws Exception {
        Carrera objCarrera = carreraDAO.find(carrera.getNumerocarrera());
        if(carrera!=null){
            if(carrera.getNumerocarrera()==null){
                throw new Exception("Número de carrera Obligatorio");                
            }else if(carrera.getNombrecarrera()==null || carrera.getNombrecarrera().equals("")){
                throw new Exception("El nombre es obligatorio");
            }
        }else{
            throw new Exception("La carrera no tiene información");
        }
        
        if(objCarrera==null){
            carreraDAO.create(carrera);
        }else{
            throw new Exception("La carrera ya esta registrada");
        }
    }

    @Override
    public void edit(Carrera carrera) throws Exception {
        Carrera objCarrera = carreraDAO.find(carrera.getNumerocarrera());
        if(carrera!=null){
            if(carrera.getNumerocarrera()==null){
                throw new Exception("Número de carrera Obligatorio");                
            }else if(carrera.getNombrecarrera()==null || carrera.getNombrecarrera().equals("")){
                throw new Exception("El nombre es obligatorio");
            }
        }else{
            throw new Exception("La carrera no tiene información");
        }
        
        if(objCarrera==null){
            throw new Exception("carrera no existe. No se puede modificar");
        }else{
            carreraDAO.edit(carrera);
        }
    }

    @Override
    public void remove(Carrera carrera) throws Exception {
        Carrera objCarrera = carreraDAO.find(carrera.getNumerocarrera());
        if(carrera!=null){
            if(carrera.getNumerocarrera()==null){
                throw new Exception("Número de carrera Obligatorio");                
            }else if(carrera.getNombrecarrera()==null || carrera.getNombrecarrera().equals("")){
                throw new Exception("El nombre es obligatorio");
            }
        }else{
            throw new Exception("La carrera no tiene información");
        }
        
        if(objCarrera==null){
            throw new Exception("carrera no existe. No se puede modificar");
        }else{
            carreraDAO.remove(carrera);
        }
    }

    @Override
    public Carrera find(Integer codigoCarrera) throws Exception {
        if(codigoCarrera!=null){
            return carreraDAO.find(codigoCarrera);
        }else{
            throw new Exception("El codigo para consultar es obligatorio");
        }
    }

    @Override
    public List<Carrera> findAll() throws Exception {
        return carreraDAO.findAll();
    }
    
}
