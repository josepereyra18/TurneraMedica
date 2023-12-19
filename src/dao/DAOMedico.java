package dao;

import entidades.Medico;

import java.sql.*;
import java.util.ArrayList;

public class DAOMedico implements IDAO<Medico>{

    private String DB_JDBC_DRIVER="org.h2.Driver";
    private String DB_URL="jdbc:h2:file:~\\baseTurnera";
    private String DB_USER="sa";
    private String DB_PASSWORD="";

    @Override
    public void guardar(Medico elemento) throws DAOExeption {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement= connection.prepareStatement("INSERT INTO MEDICO VALUES(?,?,?,?,?)");
            preparedStatement.setInt(1,elemento.getId());
            preparedStatement.setString(2, elemento.getNombre());
            preparedStatement.setString(3, elemento.getApellido());
            preparedStatement.setDouble(4,elemento.getPrecioConsulta());
            preparedStatement.setString(5,elemento.getObraSocial());
            int resultado=preparedStatement.executeUpdate();
            System.out.println("Se agrego " + resultado);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw new DAOExeption("Error al insertar");
        }
    }

    @Override
    public void modificar(Medico elemento) throws DAOExeption{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement= connection.prepareStatement("UPDATE MEDICO SET NOMBRE=? ,APELLIDO=?, PRECIOCONSULTA=? , OBRASOCIAL=? WHERE IDMEDICO=?");
            preparedStatement.setString(1, elemento.getNombre());
            preparedStatement.setString(2, elemento.getApellido());
            preparedStatement.setDouble(3,elemento.getPrecioConsulta());
            preparedStatement.setString(4,elemento.getObraSocial());
            preparedStatement.setInt(5,elemento.getId());

            int resultado=preparedStatement.executeUpdate();

            System.out.println("Se modifico" + resultado);

        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw new DAOExeption(e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) throws DAOExeption{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement= connection.prepareStatement("DELETE FROM MEDICO WHERE IDMEDICO=?");
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
public Medico buscar(int id) throws DAOExeption {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        Medico medico = null;
    try {
        Class.forName(DB_JDBC_DRIVER);
        connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
        preparedStatement = connection.prepareStatement("SELECT * FROM MEDICO WHERE IDMEDICO = ?");
        preparedStatement.setInt(1,id);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            medico = new Medico();
            medico.setId(rs.getInt("IDMEDICO"));
            medico.setNombre(rs.getString("NOMBRE"));
            medico.setApellido(rs.getString("APELLIDO"));
            medico.setPrecioConsulta(rs.getDouble("PRECIOCONSULTA"));
            medico.setObraSocial(rs.getString("OBRASOCIAL"));
        }
        System.out.println(medico);
    }catch(ClassNotFoundException | SQLException e){
        throw new DAOExeption("No se encuentra el alumno");
    }finally {
        try{
            preparedStatement.close();
        }catch (SQLException s){
            throw new DAOExeption("no se pudo conectar");
        }
    }
    return medico;
}

    @Override
    public ArrayList<Medico> buscarTodos() throws DAOExeption {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        Medico medico = null;

        ArrayList<Medico> medicos = new ArrayList<>();
        System.out.println("1");
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM MEDICO");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                medico = new Medico();
                medico.setId(rs.getInt("IDMEDICO"));
                medico.setNombre(rs.getString("NOMBRE"));
                medico.setApellido(rs.getString("APELLIDO"));
                medico.setPrecioConsulta(rs.getDouble("PRECIOCONSULTA"));
                medico.setObraSocial(rs.getString("OBRASOCIAL"));
                medicos.add(medico);
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
        return medicos;
    }
}
