package entidades;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String tipoUsuario;  // SI ES MEDICO O PACIENTE





    //? CONSTRUCTORES

    public Usuario(){}
    public Usuario(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Usuario(String nombre, String apellido,String tipoUsuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoUsuario = tipoUsuario;
    }




    //? METODOS

    public List<Turno> ConsultarTurnos(Date fecha) {
        return null;
    }
    public boolean esMedico (){  // esto se va a utilizar en medico y en paciente
        return "Medico".equalsIgnoreCase(tipoUsuario);
    }

    public boolean esPaciente (){
        return "Paciente".equalsIgnoreCase(tipoUsuario);
    }





    //? GETTERS
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }





    //? SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    //? TOSTRING

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", tipoUsuario='" + tipoUsuario + '\'' +
                '}';
    }
}
