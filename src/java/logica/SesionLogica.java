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
    public void buscarCamposInvalidosOVacios(Long documento, String clave) throws Exception {
        if(documento==null) {
            throw new Exception("Ingrese un usuario válido.");
        }
        if(clave.equals("")) {
            throw new Exception("La contraseña es obligatoria.");
        }
    }
    
    @Override
    public Estudiante iniciarSesionEstudiante(Long documento, String clave) throws Exception {
        Estudiante e = estudianteDAO.find(documento);
        if(e!=null) {
            if(!e.getClaveestudiante().equals(clave)) {
                throw new Exception("La contraseña es incorrecta.");
            }
        }
        return e;
    }

    @Override
    public Docente iniciarSesionDocente(Long documento, String clave) throws Exception {
        Docente d = docenteDAO.find(documento);
        if(d!=null) {
            if(!d.getClavedocente().equals(clave)) {
                throw new Exception("La contraseña es incorrecta.");
            }
        } else {
            throw new Exception("El usuario no existe.");
        }
        return d;
    }
}