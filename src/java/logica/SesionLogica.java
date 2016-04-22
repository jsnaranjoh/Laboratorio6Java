/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Docente;
import modelo.Estudiante;
import persistencia.DocenteFacadeLocal;
import persistencia.EstudianteFacadeLocal;

/**
 *
 * @author DILOVE
 */
@Stateless
public class SesionLogica implements SesionLogicaLocal {
    @EJB
    private EstudianteFacadeLocal estudianteDAO;
    
    @EJB
    private DocenteFacadeLocal docenteDAO;

    @Override
    public Estudiante iniciarSesionEstudiante(Long documento, String clave) throws Exception {
        if(documento==null || clave==null){
            throw new Exception("Los datos de ingreso son obligatorios");
        }
        if(clave.equals("")){
            throw new Exception("La clave es obligatoria");
        }
        Estudiante estu = estudianteDAO.find(documento);
        if(estu!=null){
            if(!estu.getClaveestudiante().equals(clave)){
                throw new Exception("La contraseña es incorrecta");
            }
        }
        return estu;
    }

    @Override
    public Docente iniciarSesionDocente(Long documento, String clave) throws Exception {
        if(documento==null || clave==null){
            throw new Exception("Los datos de ingreso son obligatorios");
        }
        if(clave.equals("")){
            throw new Exception("La clave es obligatoria");
        }
        Docente docente = docenteDAO.find(documento);
        if(docente!=null){
            if(!docente.getClavedocente().equals(clave)){
                throw new Exception("La contraseña es incorrecta");
            }
        }
        return docente;
    }

}
