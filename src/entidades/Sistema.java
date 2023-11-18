package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sistema{

    private List<Medico> medicos;
    private List<Paciente> pacientes;
    private List<Consultorio> consultorios;
    private List<Turno> turnos;
    private Reporte reporte;
    private Turno turno;


    public Sistema(List<Medico> medicos, List<Paciente> pacientes, List<Consultorio> consultorios, List<Turno> turnos, Reporte reporte) {
        medicos = new ArrayList<>();
        pacientes = new ArrayList<>();
        consultorios = new ArrayList<>();
        turnos = new ArrayList<>();
        reporte = new Reporte();
    }

    public void registrarUsuario(String nombre, String tipoUsuario, int id, String apellido) {
        Usuario nuevoUsuario = new Usuario(nombre, apellido,tipoUsuario);
        if (nuevoUsuario.esMedico()) {
            medicos.add( new Medico(id, nombre,apellido) );
        } else if (nuevoUsuario.esPaciente()) {
            pacientes.add( new Paciente(id, nombre, apellido));
        }
        // Otro procesamiento
    }

    public boolean ProgramarTurno(Paciente paciente, Date fecha, Date hora, Medico medico, Consultorio consultorio){
        if (turno.ValidarDisponibilidad(medico, paciente, consultorio,hora, fecha)){
            Turno turno = new Turno(medico, paciente, consultorio, hora, fecha);
            turnos.add(turno);
            medico.asignarTurno(turno);
            paciente.asignarTurno(turno);
            return true;
        }else{
            return false;
        }
    }

    public void AsignarConsultorio (Medico medico, Consultorio consultorio, int fecha){
        // aca se asigna el cosnultorio para un meidco en una fecha espcifica

    }

    public List<Turno> GenerarReporteRecaudacionMedica (Medico medico, Date fechaInicio, Date fechaFin){
        return reporte.GenerarReporteMedico(medico, fechaInicio, fechaFin);
    }

    public List<Medico> generarReporteMedicoRecaudacion (Date fechaInicio, Date fechaFin){
        return reporte.GenerarReporteMedicosRecaudacion(fechaInicio, fechaFin);
    }
}
