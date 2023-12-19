package Service;

import dao.DAOExeption;
import dao.DAOTurno;
import entidades.Medico;
import entidades.Turno;

import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;

public class serviceTurno {
    private DAOTurno daoTurno;

    public serviceTurno(){this.daoTurno = new DAOTurno();}

    public void guardar(Turno turno) throws serviceExeption{
        try{
            daoTurno.guardar(turno);
        }catch(DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }

    }
    public void modificar (Turno turno) throws serviceExeption{
        try{
            daoTurno.modificar(turno);
        }catch(DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }
    }

    public void eliminar (int id) throws serviceExeption{
        try{
            daoTurno.eliminar(id);
        }catch(DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }
    }

    public Turno buscar(int id) throws serviceExeption{
        try{
            return daoTurno.buscar(id);
        }catch(DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }
    }

    public ArrayList<Turno> buscarTodos() throws serviceExeption{
        try{
            return daoTurno.buscarTodos();
        }catch(DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }
    }

    public ArrayList<Turno> calcularSumaCobrosRango(Date fechaDesde, Date fechaHasta, int IdMedico) throws serviceExeption{
        try{
            return daoTurno.calcularSumaCobrosRango(fechaDesde,fechaHasta, IdMedico);
        }catch(DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }
    }

    public ArrayList<Turno> calcularSumaCobrosRangoXMedico(Date fechaDesde, Date fechaHasta) throws serviceExeption{
        try{
            return daoTurno.calcularSumaCobrosRangoXMedico(fechaDesde,fechaHasta);
        }catch(DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }
    }

    public ArrayList<Time>buscarTurnosMedico(int IdMedico, Date fecha)throws serviceExeption{
        try{
            return daoTurno.buscarTurnosMedico(IdMedico, fecha);
        }catch (DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }
    }

    public ArrayList<Time>buscarTurnosPaciente(int idPaciente, Date fecha)throws serviceExeption{
        try{
            return daoTurno.buscarTurnosPaciente(idPaciente, fecha);
        }catch (DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }
    }

    public  ArrayList<Turno>todosTurnosMedico(int IdMedico)throws serviceExeption{
        try{
            return daoTurno.todosTurnosMedico(IdMedico);
        }catch (DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }
    }

    public ArrayList<Turno>fechaTurnosMedico(int IdMedico, Date fecha)throws serviceExeption{
        try{
            return daoTurno.fechaTurnosMedico(IdMedico, fecha);
        }catch (DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }
    }

    public ArrayList<Turno> todosTurnosPaciente (int pacienteId) throws serviceExeption{
        try{
            return daoTurno.todosTurnosPaciente(pacienteId);
        }catch(DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }
    }

    public ArrayList<Turno> fechaTurnosPaciente (int pacienteId, Date fecha) throws serviceExeption{
        try{
            return daoTurno.fechaTurnosPaciente(pacienteId,fecha);
        }catch(DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }
    }

}
