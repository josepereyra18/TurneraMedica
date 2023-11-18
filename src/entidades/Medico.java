package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Medico extends Usuario{
    private String Especialidad;
    private double PrecioConsulta;
    private List<Turno> turnosAsignados;










//? CONSTRUCTORES

    public Medico(){
    }
    public Medico(int id, String nombre, String apellido ,String especialidad, int precioConsulta) {
        super(id, nombre,apellido);
        this.Especialidad = especialidad;
        this.PrecioConsulta = precioConsulta;
        this.turnosAsignados = new ArrayList<>();
    }
    public Medico(int id, String nombre, String apellido) {
        super(id, nombre, apellido);
    }

    public Medico(int id, String nombre, String apellido, int precioConsulta) {
            super(id, nombre, apellido);
            this.PrecioConsulta= precioConsulta;
    }




//? METODOS
    @Override
    public List<Turno> ConsultarTurnos(Date fecha) {
        List<Turno> turnosEnFecha = new ArrayList<>();
        for (Turno turno : this.turnosAsignados) {
            if (turno.getFecha().equals(fecha)) {
                turnosEnFecha.add(turno);
            }
        }
        return turnosEnFecha;
    }

    public void asignarTurno (Turno turno){
        turnosAsignados.add(turno);
    }

    public List<Turno> ListarRecaudacion (Date fechaInicio, Date fechaFin) {
        List<Turno> turnosEnRango = new ArrayList<>();
        for (Turno turno : this.turnosAsignados) {
            if (!turno.getFecha().before(fechaInicio) && !turno.getFecha().after(fechaFin)) {
                turnosEnRango.add(turno);
            }
        }
        return turnosEnRango;
    }







//? GETTERS
    public List<Turno> getTurnosAsignados(){
        return turnosAsignados;
    }

    //public void AsignarConsultorio(Consultorio consultorio, Date fecha){
    // aca se asigna un consultorio para una fecha
    //}

    public String getEspecialidad() {
        return Especialidad;
    }

    public double getPrecioConsulta() {
        return PrecioConsulta;
    }






    //? SETTERS
public void setEspecialidad(String especialidad) {
    Especialidad = especialidad;
}

    public void setPrecioConsulta(double precioConsulta) {
        PrecioConsulta = precioConsulta;
    }

    public void setTurnosAsignados(List<Turno> turnosAsignados) {
        this.turnosAsignados = turnosAsignados;
    }




//? TOSTRING
    @Override
    public String toString() {
        return "Medico{" +
                super.toString()+
                ", PrecioConsulta=" + PrecioConsulta +
                ", turnosAsignados=" + turnosAsignados +
                '}';
    }
}
