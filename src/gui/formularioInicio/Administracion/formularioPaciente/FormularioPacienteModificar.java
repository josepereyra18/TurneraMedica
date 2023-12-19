package gui.formularioInicio.Administracion.formularioPaciente;

import Service.serviceExeption;
import Service.servicePaciente;
import entidades.Paciente;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioPacienteModificar extends JPanel {
    JPanel panelModificar;
    JPanel panelAtras;
    PanelManager panel;
    JLabel nombre;
    JTextField nombreText;
    JLabel apellido;
    JTextField apellidoText;
    JLabel ObraSocial;
    JComboBox combo;
    private String ObrasSociales[]={"- Seleccione -","OSDE","Swiss Medical","Galeno","Medicus","Accord Salud","OMINT","OSDIPP","OSPATCA","OSPE","OSPIA","OSPOCE","OSSEG","OSUNLAR","OSUTHGRA","Otro"};
    JButton botonModificar;
    JButton botonAtras;
    servicePaciente instance;

    public FormularioPacienteModificar(PanelManager panel, Paciente paciente) {
        this.panel = panel;
        instance = new servicePaciente();
        setLayout(new GridBagLayout());
        armarFormulario(paciente);
    }

    public void armarFormulario(Paciente paciente){
        panelModificar = new JPanel();
        panelAtras = new JPanel();
        panelAtras.setLayout(new GridBagLayout());
        panelModificar.setLayout(new GridLayout(4,2, 5, 5));

        nombre = new JLabel("Nombre");
        nombreText = new JTextField(7);
        nombreText.setText(paciente.getNombre());

        apellido = new JLabel("Apellido");
        apellidoText = new JTextField(7);
        apellidoText.setText(paciente.getApellido());

        ObraSocial = new JLabel("Obra Social");
        combo = new JComboBox(ObrasSociales);
        combo.setSelectedItem(paciente.getObraSocial());

        botonModificar = new JButton("Modificar");

        botonAtras = new JButton("<-");
        panelAtras.add(botonAtras);

        panelModificar.add(nombre);
        panelModificar.add(nombreText);
        panelModificar.add(apellido);
        panelModificar.add(apellidoText);
        panelModificar.add(ObraSocial);
        panelModificar.add(combo);
        panelModificar.add(botonModificar);

        GridBagConstraints gbcModificar = new GridBagConstraints();
        gbcModificar.gridx = 0;
        gbcModificar.gridy = 1;
        gbcModificar.weightx = 1;
        gbcModificar.weighty = 1;
        gbcModificar.fill = GridBagConstraints.NONE;
        add(panelModificar, gbcModificar);
        setPreferredSize(new Dimension(300, 200));

        GridBagConstraints gbcAtras = new GridBagConstraints();
        gbcAtras.gridx = 0;
        gbcAtras.gridy = 0;
        gbcAtras.anchor = GridBagConstraints.NORTHWEST;
        add(panelAtras, gbcAtras);

        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new FormularioPacienteEncontrado(panel, paciente));
            }
        });

        botonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreText.getText();
                String apellido = apellidoText.getText();
                String obraSocial = combo.getItemAt(combo.getSelectedIndex()).toString();

                if (nombre.isEmpty() || apellido.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos");
                } else if (!nombre.matches("[a-zA-Z]+")) {
                    JOptionPane.showMessageDialog(null, "El nombre solo puede contener letras");
                } else if (!apellido.matches("[a-zA-Z]+")) {
                    JOptionPane.showMessageDialog(null, "El apellido solo puede contener letras");
                } else if(combo.getSelectedIndex()==0){
                    JOptionPane.showMessageDialog(null, "Debe seleccionarse una obra social");
                }else {
                    Paciente pacienteNuevo= new Paciente();
                    pacienteNuevo.setId(paciente.getId());
                    pacienteNuevo.setNombre(nombre);
                    pacienteNuevo.setApellido(apellido);
                    pacienteNuevo.setObraSocial(obraSocial);

                    try{
                        instance.modificar(pacienteNuevo);
                        panel.mostrar(new FormularioPacienteBuscar(panel));
                    }catch (serviceExeption s){
                        JOptionPane.showMessageDialog(null, "No se pudo modificar el medico");
                    }
                    String mensaje = "Se modificó el medico con exito. \n Nuevos datos: \n" +
                            "Nombre: " + nombre + "\n" +
                            "Apellido: " + apellido + "\n" +
                            "DNI: " + paciente.getId()+ "\n" +
                            "Obra Social: " + obraSocial + "\n";
                    JOptionPane.showMessageDialog(null, mensaje);
                }
            }
        });
    }

}
