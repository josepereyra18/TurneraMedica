package dao;

import entidades.Paciente;

import java.sql.*;
import java.util.ArrayList;

public class DAOPaciente implements IDAO<Paciente>{

    private String DB_JDBC_DRIVER="org.h2.Driver";
    private String DB_URL="jdbc:h2:file:~\\baseTurnera";
    private String DB_USER="sa";
    private String DB_PASSWORD="";

    @Override
    public void guardar(Paciente elemento) throws DAOExeption {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement= connection.prepareStatement("INSERT INTO PACIENTE VALUES(?,?,?,?)");
            preparedStatement.setInt(1,elemento.getId());
            preparedStatement.setString(2, elemento.getNombre());
            preparedStatement.setString(3,elemento.getApellido());
            preparedStatement.setString(4,elemento.getObraSocial());
            int resultado=preparedStatement.executeUpdate();
            System.out.println("Se agrego " + resultado);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw new DAOExeption("Error al insertar");
        }
    }

    @Override
    public void modificar(Paciente elemento) throws DAOExeption{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement= connection.prepareStatement("UPDATE PACIENTE SET NOMBRE=? ,APELLIDO=?, OBRASOCIAL=? WHERE DNI=?");
            preparedStatement.setString(1, elemento.getNombre());
            preparedStatement.setString(2, elemento.getApellido());
            preparedStatement.setString(3,elemento.getObraSocial());
            preparedStatement.setInt(4,elemento.getId());
            int resultado=preparedStatement.executeUpdate();
            System.out.println("Se modifico" + resultado);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw new DAOExeption(e.getMessage());
        }
    }

    @Override
    public void eliminar(int id)  throws DAOExeption{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement= connection.prepareStatement("DELETE FROM PACIENTE WHERE DNI=?");
            preparedStatement.setInt(1,id);
            int resultado=preparedStatement.executeUpdate();
            System.out.println("Se elimino" + resultado);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw new DAOExeption(e.getMessage());
        }
    }

    @Override
    public Paciente buscar(int id) throws DAOExeption {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        Paciente paciente = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM PACIENTE WHERE DNI = ?");
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                paciente = new Paciente();

                paciente.setId(rs.getInt("DNI"));
                paciente.setNombre(rs.getString("NOMBRE"));
                paciente.setApellido(rs.getString("APELLIDO"));
                paciente.setObraSocial(rs.getString("OBRASOCIAL"));
            }
            System.out.println(paciente);
        }catch(ClassNotFoundException | SQLException e){
            throw new DAOExeption("No se encuentra el alumno");
        }finally {
            try{
                preparedStatement.close();
            }catch (SQLException s){
                throw new DAOExeption("no se pudo conectar");
            }
        }
        return paciente;
    }

    @Override
    public ArrayList<Paciente> buscarTodos() throws DAOExeption {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        Paciente paciente = null;

        ArrayList<Paciente> pacientes = new ArrayList<>();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM PACIENTE");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                paciente = new Paciente();
                paciente.setId(rs.getInt("DNI"));
                paciente.setNombre(rs.getString("NOMBRE"));
                paciente.setApellido(rs.getString("APELLIDO"));
                paciente.setObraSocial(rs.getString("OBRASOCIAL"));
                pacientes.add(paciente);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOExeption("Ocurrio un error en la base de datos");
        } finally {
            try{
                preparedStatement.close();
            }catch(SQLException s){
                throw new DAOExeption("No se pudo conectar");
            }
        }
        return pacientes;
    }
}
