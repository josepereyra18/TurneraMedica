package entidades;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

public class Turno {

    private Date fecha;
    private Time hora;
    private Medico medico;
    private Paciente paciente;
    private int IdTurno;
    private Double costo;
    private static final Random random = new Random();


    public Turno() {
        this.IdTurno = generateRandomId();
    }

    public static int generateRandomId() {
        return random.nextInt(Integer.MAX_VALUE);
    }

    public Turno(Medico medico, Paciente paciente, Time hora, Date fecha) {
        this.fecha = fecha;
        this.hora = hora;
        this.medico = medico;
        this.paciente = paciente;
        this.IdTurno = generateRandomId();
    }

    public Turno(Medico medico, Paciente paciente, Time hora, Date fecha, Double costo) {
        this.fecha = fecha;
        this.hora = hora;
        this.medico = medico;
        this.paciente = paciente;
        this.costo = costo;
        this.IdTurno = generateRandomId();
    }

    public boolean ValidarDisponibilidad (Medico medico, Paciente paciente, Time hora, Date fecha){
        for (Turno turno : medico.getTurnosAsignados()) {
            if (turno.getFecha().equals(fecha) && turno.getHora().equals(hora)) {
                return false;
            }
        }
        for (Turno turno : paciente.getTurnosAsignados()) {
            if (turno.getFecha().equals(fecha) && turno.getHora().equals(hora)) {
                return false;
            }
        }
        return true;
    }

    public Double sumaRecaudacion (ArrayList<Turno>turnos){
        double total = 0;
        for (Turno turno : turnos) {
            costo += turno.getCosto();
        }
        return total;
    }



    public Date getFecha() {
        return fecha;
    }

    public Time getHora() {
        return hora;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public int getIdTurno() {
        return IdTurno;
    }

    public double getCosto() {
        return costo;
    }



    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setIdTurno(int idTurno) {
        IdTurno = idTurno;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public int ExtraerId(){
        return 0;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + IdTurno +
                ", fecha=" + fecha +
                ", hora=" + hora +
                '}';
    }
}
