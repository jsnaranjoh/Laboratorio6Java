/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;


import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Estudiante;
import persistencia.EstudianteFacadeLocal;

/**
 *
 * @author Estudiante
 */
@Stateless
public class EstudianteLogica implements EstudianteLogicaLocal {
 @EJB
        private EstudianteFacadeLocal estudianteDAO;
    @Override
    public void create(Estudiante estudiante) throws Exception {
          Estudiante objestudiante= estudianteDAO.find(estudiante.getDocumentoestudiante());
       if(estudiante!=null){
           if(estudiante.getDocumentoestudiante()==null){
               throw  new Exception("el documento es  obligatorio");
           }else if (estudiante.getNombreestudiante()==null || estudiante.getApellidoestudiante().equals("")){
               throw new Exception("el nombre y el apellido  es obligatorio");    
           }
       }else{
           throw new Exception("el estudiante no tiene informacion");
       }
       if(objestudiante==null){
           estudianteDAO.create(estudiante);
       }else{
           throw  new Exception("el estudiante ya esta registrado");
       }
    }

    @Override
    public void edit(Estudiante estudiante) throws Exception {
           Estudiante objestudiante= estudianteDAO.find(estudiante.getDocumentoestudiante());
          if(estudiante!=null){
           if(estudiante.getDocumentoestudiante()==null){
               throw  new Exception(" el documento es  obligatorio");
           }else if (estudiante.getNombreestudiante()==null || estudiante.getApellidoestudiante().equals("")){
               throw new Exception("el nombre y el apellido son obligatorios");    
           }
    }
          if(objestudiante==null){
              throw new Exception("el estudiante  no existe no se puede modificar");
          }else{
              estudianteDAO.edit(estudiante);
          }
    }

    @Override
    public void remove(Estudiante estudiante) throws Exception {
           Estudiante objestudiante= estudianteDAO.find(estudiante.getDocumentoestudiante());
          if(estudiante!=null){
           if(estudiante.getDocumentoestudiante()==null){
               throw  new Exception(" el documento es  obligatorio");
           }else if (estudiante.getNombreestudiante()==null || estudiante.getApellidoestudiante()==null||estudiante.getSemestreestudiante().equals("")){
               throw new Exception("el nombre, el apellido y el semestre  son obligatorios");    
           }
    }
          if(objestudiante==null){
              throw new Exception("el estudiante  no existe no se puede modificar");
          }else{
              estudianteDAO.remove(estudiante);
          }
    }

    @Override
    public Estudiante find(Long documentoestudiante) throws Exception {
          if(documentoestudiante!=null){
            return estudianteDAO.find(documentoestudiante);
        }else{
            throw  new Exception("el codigo para consultar es obligatorio");
        }
    }

    @Override
    public List<Estudiante> findAll() throws Exception {
      return estudianteDAO.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
