package gui.formularioInicio.Administracion.formularioPaciente;

import Service.serviceExeption;
import Service.servicePaciente;
import entidades.ObraSocial;
import entidades.Paciente;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioPacienteAgregar extends JPanel{
    JPanel panelAgregar;
    JPanel panelSec;
    PanelManager panel;


    JButton botonAtras;


    JLabel nombre;
    JTextField nombreText;

    JLabel apellido;
    JTextField apellidoText;

    JLabel dni;
    JTextField dniText;

    JLabel ObraSocial;

    JButton botonAgregar;

    JComboBox combo;

    ObraSocial obraSocial;

    private String ObrasSociales[]={"- Seleccione -","OSDE","Swiss Medical","Galeno","Medicus","Accord Salud","OMINT","OSDIPP","OSPATCA","OSPE","OSPIA","OSPOCE","OSSEG","OSUNLAR","OSUTHGRA","Otro"};
    servicePaciente instance;

    public FormularioPacienteAgregar (PanelManager panel){
        this.panel = panel;
        instance = new servicePaciente();
        obraSocial = new ObraSocial();
        setLayout(new GridBagLayout());
        armarFormulario();
    }

    public void armarFormulario(){

            panelAgregar = new JPanel();
            panelSec = new JPanel();
            panelAgregar.setLayout(new GridLayout(5,2, 10, 10));

            botonAtras = new JButton("<-");
            botonAtras.setPreferredSize(new Dimension(50, 25));

            nombre = new JLabel("Nombre");
            nombreText = new JTextField(7);

            apellido = new JLabel("Apellido");
            apellidoText = new JTextField(7);

            dni = new JLabel("DNI");
            dniText = new JTextField(7);

            ObraSocial = new JLabel("Obra Social");
            combo = new  JComboBox(obraSocial.getObrasSociales());

            botonAgregar = new JButton("Agregar");

        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new FormularioPacienteInicio(panel));
            }
        });

            botonAgregar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nombre = nombreText.getText();
                    String apellido = apellidoText.getText();
                    String dni = dniText.getText();
                    String obraSocial = combo.getSelectedItem().toString();

                    if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos");
                    } else if (!nombre.matches("[a-zA-Z]+")) {
                        JOptionPane.showMessageDialog(null, "El nombre solo puede contener letras");
                    } else if (!apellido.matches("[a-zA-Z]+")) {
                        JOptionPane.showMessageDialog(null, "El apellido solo puede contener letras");
                    } else if (!dni.matches("[0-9]+")) {
                        JOptionPane.showMessageDialog(null, "El DNI solo puede contener números");
                    }else if(combo.getSelectedIndex()==0){
                        JOptionPane.showMessageDialog(null, "Debe seleccionarse una obra social");
                    } else {
                        Paciente paciente = new Paciente();
                        paciente.setNombre(nombre);
                        paciente.setApellido(apellido);
                        paciente.setId(Integer.parseInt(dni));
                        paciente.setObraSocial(obraSocial);
                        try {
                            instance.guardar(paciente);
                            String mensaje = "Se agregó el medico con exito. Datos: \n" +
                                    "Nombre: " + nombre + "\n" +
                                    "Apellido: " + apellido + "\n" +
                                    "DNI: " + dni+ "\n" +
                                    "Obra Social" + obraSocial;
                            JOptionPane.showMessageDialog(null, mensaje);
                        } catch (serviceExeption serviceExeption) {
                            JOptionPane.showMessageDialog(null, "No se pudo agregar el paciente");
                        }
                        nombreText.setText("");
                        apellidoText.setText("");
                        dniText.setText("");
                        combo.setSelectedIndex(0);
                    }
                }
            });

        panelAgregar.add(nombre);
        panelAgregar.add(nombreText);
        panelAgregar.add(apellido);
        panelAgregar.add(apellidoText);
        panelAgregar.add(dni);
        panelAgregar.add(dniText);
        panelAgregar.add(ObraSocial);
        panelAgregar.add(combo);
        panelAgregar.add(botonAgregar);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(panelAgregar, gbc);

        GridBagConstraints gbcSec = new GridBagConstraints();
        gbcSec.gridx = 0;
        gbcSec.gridy = 0;
        gbcSec.weightx = 0;
        gbcSec.weighty =0;
        gbcSec.fill = GridBagConstraints.NONE;
        add(panelSec, gbcSec);

        panelSec.setLayout(new BorderLayout());
        panelSec.setPreferredSize(new Dimension(50,25));
        panelSec.add(botonAtras, BorderLayout.WEST);
    }
}
