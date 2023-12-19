package entidades;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Medico extends Usuario{
    private String Especialidad;
    private double PrecioConsulta;
    private ArrayList<Turno> turnosAsignados;

//? CONSTRUCTORES

    public Medico(){
        this.turnosAsignados = new ArrayList<>();
    }
    public Medico(int id, String nombre, String apellido ,String especialidad, int precioConsulta, String obraSocial) {
        super(id, nombre,apellido, obraSocial);
        this.Especialidad = especialidad;
        this.PrecioConsulta = precioConsulta;
        this.turnosAsignados = new ArrayList<>();
    }
    public Medico(int id, String nombre, String apellido,String obraSocial) {
        super(id, nombre, apellido, obraSocial);
    }

    public Medico(int id, String nombre, String apellido, int precioConsulta,String obraSocial) {
            super(id, nombre, apellido, obraSocial);
            this.PrecioConsulta= precioConsulta;
            this.turnosAsignados = new ArrayList<>();
    }




//? METODOS
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

    public ArrayList<Time> getHorasTurnos(Date fechaSeleccionada) {
        ArrayList<Time> horasTurnos = new ArrayList<>();
        for (Turno turno : this.turnosAsignados) {
            if (turno.getFecha().equals(fechaSeleccionada)) {
                horasTurnos.add(turno.getHora());
            }
        }
        return horasTurnos;
    }
//? GETTERS
    public ArrayList<Turno> getTurnosAsignados(){
        return turnosAsignados;
    }

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

    public void setTurnosAsignados(ArrayList<Turno> turnosAsignados) {
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
