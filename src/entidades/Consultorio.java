package entidades;

// 21 segundo parcial.


import java.util.Date;
import java.util.List;

public class Consultorio {

    private int id;
    private String Ubicacion;

    private List<Date> horariosNoDisponibles;



    public Consultorio() {
    }

    public void horariosNoDisponibles(Date horario) {
        //Elimina el horario de la lista de horarios disponibles cuando se programa un turno.
    }

    public int getId() {
        return id;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public List<Date> getHorariosNoDisponibles() {
        return horariosNoDisponibles;
    }
}
