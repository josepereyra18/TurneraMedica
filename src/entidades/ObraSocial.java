package entidades;

public class ObraSocial {
    private String ObrasSociales[]={"- Seleccione -","OSDE","Swiss Medical","Galeno","Medicus","Accord Salud","OMINT","OSDIPP","OSPATCA","OSPE","OSPIA","OSPOCE","OSSEG","OSUNLAR","OSUTHGRA","Otro"};


    public ObraSocial(String[] obrasSociales) {
        ObrasSociales = obrasSociales;
    }

    public ObraSocial() {
    }

    public String[] getObrasSociales() {
        return ObrasSociales;
    }

    public boolean verificarObraSocial(String obraSocialMedico, String obraSocialPaciente){
        if (obraSocialMedico == obraSocialPaciente){
            return true;
        }else{
            return false;
        }
    }

}
