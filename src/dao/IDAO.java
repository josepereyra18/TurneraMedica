package dao;

import entidades.Medico;

import java.util.ArrayList;

public interface IDAO <T>{
    public void guardar(T elemento) throws DAOExeption;  // alta baja modificar (registros)---> tpdas las tablas tienen que tener esto
    public void modificar (T elemento)throws DAOExeption;
    public void eliminar (int id) throws DAOExeption;
    public T buscar (int clave) throws DAOExeption;
    public ArrayList<T> buscarTodos() throws DAOExeption;
}
