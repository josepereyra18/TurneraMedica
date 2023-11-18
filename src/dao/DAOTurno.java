package dao;

import entidades.Turno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTurno implements IDAO<Turno>{
    private String DB_JDBC_DRIVER="org.h2.Driver";
    private String DB_URL="jdbc:h2:file:~\\baseTurnera";
    private String DB_USER="sa";
    private String DB_PASSWORD="";

    @Override
    public void guardar(Turno elemento) throws DAOExeption {
        Connection connection=null;
        System.out.println(1);
        PreparedStatement preparedStatement=null;
        System.out.println(2);
        try {
            Class.forName(DB_JDBC_DRIVER);
            System.out.println(3);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            System.out.println(4);
            preparedStatement= connection.prepareStatement("INSERT INTO TURNOS VALUES(?,?,?,?,?)");
            System.out.println(5);
            preparedStatement.setInt(1,elemento.getPaciente().getId()); // getPACIENTE, getMedico, getHora, getFecha, getConsultorio
            System.out.println(6);
            preparedStatement.setInt(2, elemento.getMedico().getId());  // INNER JOINNNNNNNNNN !!!!!!!
            System.out.println(7);
            int resultado=preparedStatement.executeUpdate();
            System.out.println("Se agrego " + resultado);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw new DAOExeption("Error al insertar");
        }
    }

    @Override
    public void modificar(Turno elemento) {

    }

    @Override
    public void eliminar(int calve) {

    }

    @Override
    public Turno buscar(int clave) throws DAOExeption {
        return null;
    }

    @Override
    public ArrayList<Turno> buscarTodos() throws DAOExeption {
        return null;
    }
}
