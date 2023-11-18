package entidades;

import java.util.Date;

public class Turno {

    private Date fecha;
    private Date hora;
    private Medico medico;
    private Paciente paciente;
    private Consultorio consultorio;


    public Turno() {
    }


    public Turno(Medico medico, Paciente paciente, Consultorio consultorio, Date hora, Date fecha) {
        this.fecha = fecha;
        this.hora = hora;
        this.medico = medico;
        this.paciente = paciente;
        this.consultorio = consultorio;
    }

    public boolean ValidarDisponibilidad (Medico medico, Paciente paciente, Consultorio consultorio, Date hora, Date fecha){
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
        // esto verifica si tanto el medico, paciente y consultorio tiene
        // disponibilidad a esa hora y fecha.
        // esto evita que se pueda pedir un turno con el mismo medico,
        // paciente y consultorio en la misma hora y fecha.

        return true;
    }

    public Date getFecha() {
        return fecha;
    }

    public Date getHora() {
        return hora;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public int ExtraerId(){
        return 0;
    }
}
