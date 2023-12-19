package entidades;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String ObraSocial;





    //? CONSTRUCTORES

    public Usuario(){}
    public Usuario(int id, String nombre, String apellido, String obraSocial) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ObraSocial = obraSocial;
    }

    public Usuario(String nombre, String apellido,String tipoUsuario) {
        this.nombre = nombre;
        this.apellido = apellido;
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

    public String getObraSocial() {
        return ObraSocial;
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

    public void setObraSocial(String obraSocial) {
        ObraSocial = obraSocial;
    }

    //? TOSTRING

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }

}
