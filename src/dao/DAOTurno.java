package dao;

import Service.serviceExeption;
import Service.serviceMedico;
import Service.servicePaciente;
import entidades.Medico;
import entidades.Reporte;
import entidades.Turno;

import java.sql.*;
import java.util.ArrayList;

public class DAOTurno implements IDAO<Turno>{
    private String DB_JDBC_DRIVER="org.h2.Driver";
    private String DB_URL="jdbc:h2:file:~\\baseTurnera";
    private String DB_USER="sa";
    private String DB_PASSWORD="";

    @Override
    public void guardar(Turno elemento) throws DAOExeption {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement= connection.prepareStatement("INSERT INTO TURNO VALUES(?,?,?,?,?,?)");
            preparedStatement.setInt(1, elemento.getIdTurno());
            preparedStatement.setInt(2,elemento.getMedico().getId());
            preparedStatement.setInt(3, elemento.getPaciente().getId());
            preparedStatement.setDate(4, elemento.getFecha());
            preparedStatement.setTime(5, elemento.getHora());
            preparedStatement.setDouble(6, elemento.getCosto());
            int resultado=preparedStatement.executeUpdate();
            System.out.println("Se creo Turno " + resultado);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            String h= e.getMessage();
            throw new DAOExeption(h);
        }
    }


    @Override
    public void modificar(Turno elemento) throws DAOExeption {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement= connection.prepareStatement("UPDATE TURNO SET IDMEDICO=? ,IDPACIENTE=?, FECHA=? , HORA=? , COSTO=? WHERE IDTURNO=?");
            preparedStatement.setInt(1, elemento.getMedico().getId());
            preparedStatement.setInt(2, elemento.getPaciente().getId());
            preparedStatement.setDate(3, elemento.getFecha());
            preparedStatement.setTime(4, elemento.getHora());
            preparedStatement.setDouble(5, elemento.getCosto());
            preparedStatement.setInt(6, elemento.getIdTurno());
            int resultado=preparedStatement.executeUpdate();

            System.out.println("Se modifico" + resultado);

        }catch (ClassNotFoundException | SQLException e)
        {
            throw new DAOExeption(e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) throws DAOExeption{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement= connection.prepareStatement("DELETE FROM TURNO WHERE IDTURNO=?");
            preparedStatement.setInt(1,id);
            int resultado=preparedStatement.executeUpdate();
        }catch (ClassNotFoundException | SQLException e)
        {
            throw new DAOExeption(e.getMessage());
        }
    }

    @Override
    public Turno buscar(int turnoId) throws DAOExeption {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        Turno turno = null;
        servicePaciente paciente = new servicePaciente();
        serviceMedico medico = new serviceMedico();
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM TURNO WHERE IDTURNO = ?");
            preparedStatement.setInt(1,turnoId);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                turno = new Turno();
                turno.setFecha(rs.getDate("FECHA"));
                turno.setPaciente(paciente.buscar(rs.getInt("IDPACIENTE")));
                turno.setMedico(medico.buscar(rs.getInt("IDMEDICO")));
                turno.setHora(rs.getTime("HORA"));
                turno.setIdTurno(rs.getInt("IDTURNO"));
                turno.setCosto(rs.getDouble("COSTO"));
            }
        } catch(ClassNotFoundException | SQLException e){
        throw new DAOExeption("No se encuentra el alumno");
        } catch (serviceExeption e) {
            throw new RuntimeException(e);
        } finally {
            try{
            preparedStatement.close();
            }catch (SQLException s){
            throw new DAOExeption("no se pudo conectar");
             }
        }
        return turno;
    }

    @Override
    public ArrayList<Turno> buscarTodos() throws DAOExeption {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        Turno turno = null;

        servicePaciente paciente = new servicePaciente();
        serviceMedico medico = new serviceMedico();


        ArrayList<Turno> turnos = new ArrayList<>();
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM TURNO");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                turno = new Turno();
                turno.setMedico(medico.buscar(rs.getInt("IDMEDICO")));
                turno.setPaciente(paciente.buscar(rs.getInt("IDPACIENTE")));
                turno.setFecha(rs.getDate("FECHA"));
                turno.setHora(rs.getTime("HORA"));
                turno.setCosto(rs.getDouble("COSTO"));
                turno.setIdTurno(rs.getInt("IDTURNO"));
                turnos.add(turno);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOExeption("Ocurrio un error en la base de datos");
        } catch (serviceExeption e) {
            throw new RuntimeException(e);
        } finally {
            try{
                preparedStatement.close();
            }catch(SQLException s){
                throw new DAOExeption("No se pudo conectar");
            }
        }
        return turnos;
    }

    public ArrayList<Turno> calcularSumaCobrosRango(Date fechaDesde, Date fechaHasta, int IdMedico) throws DAOExeption {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        Turno turno = null;
        ArrayList<Turno> turnos = new ArrayList<>();
        serviceMedico medico = new serviceMedico();
        servicePaciente paciente = new servicePaciente();
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement= connection.prepareStatement("SELECT * FROM TURNO  WHERE FECHA BETWEEN ? AND ? AND IDMEDICO= ? GROUP BY IDTURNO ORDER BY FECHA, HORA");
            preparedStatement.setDate(1, fechaDesde);
            preparedStatement.setDate(2, fechaHasta);
            preparedStatement.setInt(3, IdMedico);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                turno = new Turno();
                turno.setMedico(medico.buscar(rs.getInt("IDMEDICO")));
                turno.setFecha(rs.getDate("FECHA"));
                turno.setHora(rs.getTime("HORA"));
                turno.setCosto(rs.getDouble("COSTO"));
                turno.setPaciente(paciente.buscar(rs.getInt("IDPACIENTE")));
                turno.setIdTurno(rs.getInt("IDTURNO"));
                turnos.add(turno);
            }

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            //throw new DAOExeption("Ocurrio un error en la base de datos");
        } catch (serviceExeption e) {
            throw new RuntimeException(e);
        } finally {
            try{
                preparedStatement.close();
            }catch(SQLException s){
                throw new DAOExeption("No se pudo conectar");
            }
        }
        return turnos;
    }


    public ArrayList<Turno> calcularSumaCobrosRangoTodos(Date fechaDesde, Date fechaHasta) throws DAOExeption {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        Turno turno = null;
        ArrayList<Turno> turnos = new ArrayList<>();
        serviceMedico medico = new serviceMedico();
        servicePaciente paciente = new servicePaciente();
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement= connection.prepareStatement("SELECT * FROM TURNO  WHERE FECHA BETWEEN ? AND ? GROUP BY IDTURNO ORDER BY FECHA, HORA");
            preparedStatement.setDate(1, fechaDesde);
            preparedStatement.setDate(2, fechaHasta);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                turno = new Turno();
                turno.setMedico(medico.buscar(rs.getInt("IDMEDICO")));
                turno.setFecha(rs.getDate("FECHA"));
                turno.setHora(rs.getTime("HORA"));
                turno.setCosto(rs.getDouble("COSTO"));
                turno.setPaciente(paciente.buscar(rs.getInt("IDPACIENTE")));
                turno.setIdTurno(rs.getInt("IDTURNO"));
                turnos.add(turno);
            }

        }catch (ClassNotFoundException | SQLException e){
            throw new DAOExeption("Ocurrio un error en la base de datos");
        } catch (serviceExeption e) {
            throw new RuntimeException(e);
        }finally {
            try{
                preparedStatement.close();
            }catch(SQLException s){
                throw new DAOExeption("No se pudo conectar");
            }
        }
        return turnos;
    }

    public ArrayList<Turno> calcularSumaCobrosRangoXMedico(Date fechaDesde, Date fechaHasta) throws DAOExeption {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        Turno turno = null;
        ArrayList<Turno> turnos = new ArrayList<>();
        serviceMedico medico = new serviceMedico();
        servicePaciente paciente = new servicePaciente();
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement= connection.prepareStatement("SELECT IDMEDICO, SUM(COSTO) AS SUMA FROM TURNO WHERE FECHA BETWEEN ? AND ? GROUP BY IDMEDICO ORDER BY IDMEDICO;");
            preparedStatement.setDate(1, fechaDesde);
            preparedStatement.setDate(2, fechaHasta);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                turno = new Turno();
                turno.setMedico(medico.buscar(rs.getInt("IDMEDICO")));
                turno.setFecha(null);
                turno.setHora(null);
                turno.setCosto(rs.getDouble("SUMA"));
                turno.setPaciente(null);
                turno.setIdTurno(0);
                turnos.add(turno);
            }

        }catch (ClassNotFoundException | SQLException e){
            //throw new DAOExeption("Ocurrio un error en la base de datos");
            System.out.println( e.getMessage());

        } catch (serviceExeption e) {
            throw new RuntimeException(e);
        }finally {
            try{
                preparedStatement.close();
            }catch(SQLException s){
                throw new DAOExeption("No se pudo conectar");
            }
        }
        return turnos;
    }


    public ArrayList<Time> buscarTurnosMedico(int idMedico, Date fecha) throws DAOExeption {
        Connection connection=null;
        PreparedStatement preparedStatement=null;

        Turno turno = null;
        servicePaciente paciente = new servicePaciente();
        serviceMedico medico = new serviceMedico();

        ArrayList<Time> turnos = new ArrayList<>();

        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM TURNO WHERE IDMEDICO = ? AND FECHA = ?");
            preparedStatement.setInt(1,idMedico);
            preparedStatement.setDate(2,fecha);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                turno = new Turno();
                turno.setFecha(rs.getDate("FECHA"));
                turno.setPaciente(paciente.buscar(rs.getInt("IDPACIENTE")));
                turno.setMedico(medico.buscar(rs.getInt("IDMEDICO")));
                turno.setHora(rs.getTime("HORA"));
                turno.setIdTurno(rs.getInt("IDTURNO"));
                turno.setCosto(rs.getDouble("COSTO"));
                turnos.add(turno.getHora());
            }
        }catch (ClassNotFoundException | SQLException e){
            throw new DAOExeption("Ocurrio un error en la base de datos");
        } catch (serviceExeption e) {
            throw new RuntimeException(e);
        }finally {
            try{
                preparedStatement.close();
            }catch(SQLException s){
                throw new DAOExeption("No se pudo conectar");
            }
        }
        return turnos;
    }

    public  ArrayList<Time> buscarTurnosPaciente(int idPaciente, Date fecha) throws DAOExeption{
        Connection connection=null;
        PreparedStatement preparedStatement=null;

        Turno turno = null;
        servicePaciente paciente = new servicePaciente();
        serviceMedico medico = new serviceMedico();

        ArrayList<Time> turnos = new ArrayList<>();

        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM TURNO WHERE IDPACIENTE = ? AND FECHA = ?");
            preparedStatement.setInt(1,idPaciente);
            preparedStatement.setDate(2,fecha);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                turno = new Turno();
                turno.setFecha(rs.getDate("FECHA"));
                turno.setPaciente(paciente.buscar(rs.getInt("IDPACIENTE")));
                turno.setMedico(medico.buscar(rs.getInt("IDMEDICO")));
                turno.setHora(rs.getTime("HORA"));
                turno.setIdTurno(rs.getInt("IDTURNO"));
                turno.setCosto(rs.getDouble("COSTO"));
                turnos.add(turno.getHora());
            }
        }catch (ClassNotFoundException | SQLException e){
            throw new DAOExeption("Ocurrio un error en la base de datos");
        } catch (serviceExeption e) {
            throw new RuntimeException(e);
        }finally {
            try{
                preparedStatement.close();
            }catch(SQLException s){
                throw new DAOExeption("No se pudo conectar");
            }
        }
        return turnos;
    }



    public ArrayList<Turno> fechaTurnosMedico (int idMedico, Date fecha)throws DAOExeption{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        Turno turno = null;
        servicePaciente paciente = new servicePaciente();
        serviceMedico medico = new serviceMedico();
        ArrayList<Turno> turnos = new ArrayList<>();
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT  * FROM TURNO WHERE IDMEDICO = ? AND FECHA = ? ORDER BY HORA");
            preparedStatement.setInt(1,idMedico);
            preparedStatement.setDate(2,fecha);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                turno = new Turno();
                turno.setFecha(rs.getDate("FECHA"));
                turno.setPaciente(paciente.buscar(rs.getInt("IDPACIENTE")));
                turno.setMedico(medico.buscar(rs.getInt("IDMEDICO")));
                turno.setHora(rs.getTime("HORA"));
                turno.setIdTurno(rs.getInt("IDTURNO"));
                turno.setCosto(rs.getDouble("COSTO"));
                turnos.add(turno);
            }
        }catch (ClassNotFoundException | SQLException e){
            throw new DAOExeption("Ocurrio un error en la base de datos");
        } catch (serviceExeption e) {
            throw new RuntimeException(e);
        }finally {
            try{
                preparedStatement.close();
            }catch(SQLException s){
                throw new DAOExeption("No se pudo conectar");
            }
        }

        return turnos;
    }

    public ArrayList<Turno> todosTurnosMedico (int idMedico) throws DAOExeption{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        Turno turno = null;
        servicePaciente paciente = new servicePaciente();
        serviceMedico medico = new serviceMedico();
        ArrayList<Turno> turnos = new ArrayList<>();
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT  * FROM TURNO WHERE IDMEDICO = ? ORDER BY FECHA, HORA");
            preparedStatement.setInt(1,idMedico);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                turno = new Turno();
                turno.setFecha(rs.getDate("FECHA"));
                turno.setPaciente(paciente.buscar(rs.getInt("IDPACIENTE")));
                turno.setMedico(medico.buscar(rs.getInt("IDMEDICO")));
                turno.setHora(rs.getTime("HORA"));
                turno.setIdTurno(rs.getInt("IDTURNO"));
                turno.setCosto(rs.getDouble("COSTO"));
                turnos.add(turno);
            }
        }catch (ClassNotFoundException | SQLException e){
            throw new DAOExeption("Ocurrio un error en la base de datos");
        } catch (serviceExeption e) {
            throw new RuntimeException(e);
        }finally {
            try{
                preparedStatement.close();
            }catch(SQLException s){
                throw new DAOExeption("No se pudo conectar");
            }
        }

        return  turnos;
    }


    public ArrayList<Turno> todosTurnosPaciente(int pacienteId) throws DAOExeption {
        ArrayList<Turno> turnos = new ArrayList<>();
        Connection connection=null;
        PreparedStatement preparedStatement=null;

        Turno turno = null;
        servicePaciente paciente = new servicePaciente();
        serviceMedico medico = new serviceMedico();

        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM TURNO WHERE IDPACIENTE  = ? ORDER BY FECHA, HORA");
            preparedStatement.setInt(1, pacienteId);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                turno = new Turno();
                turno.setFecha(rs.getDate("FECHA"));
                turno.setPaciente(paciente.buscar(rs.getInt("IDPACIENTE")));
                turno.setMedico(medico.buscar(rs.getInt("IDMEDICO")));
                turno.setHora(rs.getTime("HORA"));
                turno.setIdTurno(rs.getInt("IDTURNO"));
                turno.setCosto(rs.getDouble("COSTO"));
                turnos.add(turno);
            }
        }catch (ClassNotFoundException | SQLException e){
            throw new DAOExeption("Ocurrio un error en la base de datos");
        } catch (serviceExeption e) {
            throw new RuntimeException(e);
        }finally {
            try{
                preparedStatement.close();
            }catch(SQLException s){
                throw new DAOExeption("No se pudo conectar");
            }
        }
        return  turnos;
    }

    public ArrayList<Turno> fechaTurnosPaciente (int idPaciente, Date fecha)throws DAOExeption{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        Turno turno = null;
        servicePaciente paciente = new servicePaciente();
        serviceMedico medico = new serviceMedico();
        ArrayList<Turno> turnos = new ArrayList<>();
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT  * FROM TURNO WHERE IDPACIENTE = ? AND FECHA = ? ORDER BY HORA");
            preparedStatement.setInt(1,idPaciente);
            preparedStatement.setDate(2,fecha);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                turno = new Turno();
                turno.setFecha(rs.getDate("FECHA"));
                turno.setPaciente(paciente.buscar(rs.getInt("IDPACIENTE")));
                turno.setMedico(medico.buscar(rs.getInt("IDMEDICO")));
                turno.setHora(rs.getTime("HORA"));
                turno.setIdTurno(rs.getInt("IDTURNO"));
                turno.setCosto(rs.getDouble("COSTO"));
                turnos.add(turno);
            }
        }catch (ClassNotFoundException | SQLException e){
            throw new DAOExeption("Ocurrio un error en la base de datos");
        } catch (serviceExeption e) {
            throw new RuntimeException(e);
        }finally {
            try{
                preparedStatement.close();
            }catch(SQLException s){
                throw new DAOExeption("No se pudo conectar");
            }
        }

        return turnos;
    }


}
