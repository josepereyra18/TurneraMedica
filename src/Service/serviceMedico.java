package Service;

import dao.DAOExeption;
import dao.DAOMedico;
import entidades.Medico;

import java.util.ArrayList;

public class serviceMedico {

    private DAOMedico daoMedico;
    public serviceMedico(){
        this.daoMedico = new DAOMedico();
    }

    public void guardar(Medico medico) throws serviceExeption{
        try{
            daoMedico.guardar(medico);
        }catch(DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }

    }

    public void modificar(Medico medico) throws serviceExeption{
        try{
            daoMedico.modificar(medico);
        }catch (DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }
    }

    public void eliminar (int id) throws serviceExeption {
        try{
            daoMedico.eliminar(id);
        }catch (DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }
    }

    public Medico buscar (int id) throws serviceExeption{
        try{
            return daoMedico.buscar(id);
        }catch (DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }

    }

    public ArrayList<Medico> buscarTodos() throws serviceExeption {
        try{
            return daoMedico.buscarTodos();
        }catch (DAOExeption d){
            throw new serviceExeption(d.getMessage());
        }
    }
}
