package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reporte {

    public List<Turno> GenerarReporteMedico(Medico medico, Date fechaInicio, Date fechaFin){
        // Aca se calcula la recaudacion de un medico entre dos fechas.
        // va a recorrer lson turnos del medico en el rango de las fehcas
        List<Turno> turnosEnRango = new ArrayList<>();
        for (Turno turno : medico.getTurnosAsignados()) {
            if (turno.getFecha().after(fechaInicio) && turno.getFecha().before(fechaFin)) {
                turnosEnRango.add(turno);
            }
        }
        return turnosEnRango;
    }

    public List<Medico> GenerarReporteMedicosRecaudacion(Date fechaInicio, Date fechaFin) {
        // aca se va a listar los medicos y sus recaudaciones entre dos fechas.
        List<Medico> medicos = new ArrayList<>();
        // agrega cada medico a la lista con su recaudacion.
        return medicos;
    }
}
