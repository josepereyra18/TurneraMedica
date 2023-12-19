package entidades;

import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Reporte {
    private Medico medico;
    private Date Fecha;
    private Time Hora;
    private Double suma;


    public double SumaRecaudo (Reporte[] reportes){
        double suma = 0;
        for (Reporte reporte : reportes) {
            suma += reporte.getSuma();
        }
        return suma;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public Time getHora() {
        return Hora;
    }

    public void setHora(Time hora) {
        Hora = hora;
    }

    public Double getSuma() {
        return suma;
    }

    public void setSuma(Double suma) {
        this.suma = suma;
    }
}
