package entidades;

import java.util.Date;
import java.util.List;

public class Paciente extends Usuario{
    
    private List<Turno> lisaTurnosAsignados;

    public Paciente(int id, String nombre, String apellido) {
        super (id, nombre, apellido);
    }

    public Paciente(String nombre,String apellido, String tipoUsuario) {
        super(nombre, apellido,tipoUsuario);
    }
    public void asignarTurno (Turno turno){
        lisaTurnosAsignados.add(turno);
    }


    @Override
    public List<Turno> ConsultarTurnos(Date fecha) {
        super.ConsultarTurnos(fecha);
        return null;
    }

    public List<Turno> getTurnosAsignados(){
        return lisaTurnosAsignados;
    }
}


