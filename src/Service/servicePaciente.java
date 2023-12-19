package Service;

import dao.DAOExeption;
import dao.DAOMedico;
import dao.DAOPaciente;
import entidades.Medico;
import entidades.Paciente;

import java.util.ArrayList;

public class servicePaciente {
    private DAOPaciente daoPaciente;

    public  servicePaciente(){this.daoPaciente = new DAOPaciente();}

    public void guardar (Paciente paciente) throws serviceExeption{
        try{
            daoPaciente.guardar(paciente);
        }catch (DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }
    }

    public void modificar (Paciente paciente) throws serviceExeption{
        try{
            daoPaciente.modificar(paciente);
        }catch (DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }
    }

    public void eliminar (int id) throws serviceExeption{
        try{
            daoPaciente.eliminar(id);
        }catch (DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }
    }

    public Paciente buscar (int id) throws serviceExeption{
        try{
            return daoPaciente.buscar(id);
        }catch (DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }
    }

    public ArrayList<Paciente> buscarTodos() throws serviceExeption{
        try{
            return daoPaciente.buscarTodos();
        }catch (DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }
    }

}
