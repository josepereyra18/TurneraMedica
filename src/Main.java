import dao.DAOMedico;
import entidades.Medico;
import gui.PanelManager;

public class Main {
    public static void main(String[] args) {
        DAOMedico tablaMedico= new DAOMedico();
        Medico medico=new Medico(1,"lalalala","Perez",123);
        Medico medico1= new Medico(2,"LUCY", "PEREYRA", 1234);
        try {
            //tablaMedico.modificar(medico);
            //tablaMedico.buscar(2);
            //tablaMedico.guardar(medico);
            tablaMedico.eliminar(1);
        }
        catch(Exception d) {
            System.out.println(d.getMessage());
        }

        PanelManager panelManager = new PanelManager();
    }
}