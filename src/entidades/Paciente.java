package entidades;

import java.util.Date;
import java.util.List;

public class Paciente extends Usuario{
    
    private List<Turno> listaTurnosAsignados;

    public Paciente(int id, String nombre, String apellido, String obraSocial){
        super (id, nombre, apellido, obraSocial);
    }

    public Paciente(){}
    public Paciente(String nombre,String apellido, String tipoUsuario) {
        super(nombre, apellido,tipoUsuario);
    }
    public void asignarTurno (Turno turno){
        listaTurnosAsignados.add(turno);
    }


    public List<Turno> getTurnosAsignados(){
        return listaTurnosAsignados;
    }
}


