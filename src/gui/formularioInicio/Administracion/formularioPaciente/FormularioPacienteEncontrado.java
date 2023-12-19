package gui.formularioInicio.Administracion.formularioPaciente;

import Service.serviceExeption;
import Service.servicePaciente;
import entidades.Paciente;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioPacienteEncontrado extends JPanel{

    JPanel panelBotones;
    JPanel panelAtras;
    JLabel nombre;
    JLabel apellido;
    JLabel dni;
    JLabel ObraSocial;
    JPanel panelPacienteEncontrado;
    PanelManager panel;
    servicePaciente instance;
    JButton botonEliminar;
    JButton botonModificar;
    JButton botonAtras;

    public FormularioPacienteEncontrado(PanelManager panel, Paciente paciente){
        this.panel = panel;
        instance = new servicePaciente();
        setLayout(new GridBagLayout());
        armarFormulario(paciente);
    }

    public  void armarFormulario (Paciente paciente){
        panelPacienteEncontrado = new JPanel();
        panelBotones = new JPanel();
        panelAtras = new JPanel();

        panelPacienteEncontrado.setLayout(new GridLayout(4,1));
        nombre= new JLabel("Nombre: " + paciente.getNombre());
        apellido= new JLabel("Apellido: " + paciente.getApellido());
        dni= new JLabel("DNI: " + paciente.getId());
        ObraSocial= new JLabel("Obra Social: " + paciente.getObraSocial());


        panelBotones.setLayout(new GridLayout(1,2));
        botonModificar = new JButton("Modificar");
        botonEliminar = new JButton("Eliminar");
        panelBotones.add(botonModificar);
        panelBotones.add(botonEliminar);


        botonAtras = new JButton("<-");
        panelAtras.add(botonAtras);



        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new FormularioPacienteBuscar(panel));
            }
        });

        botonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new FormularioPacienteModificar(panel, paciente));
            }
        });

        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resultado= JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar el paciente?", "Eliminar paciente", JOptionPane.YES_NO_OPTION);
                if (resultado == JOptionPane.YES_OPTION){
                    int dni = paciente.getId();
                    try {
                        instance.eliminar(dni);
                        JOptionPane.showMessageDialog(null, "Se elimino correctamente");
                        panel.mostrar(new FormularioPacienteInicio(panel));
                    } catch (serviceExeption ex) {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar");
                    }
                }
            }
        });


        panelPacienteEncontrado.add(nombre);
        panelPacienteEncontrado.add(apellido);
        panelPacienteEncontrado.add(dni);
        panelPacienteEncontrado.add(ObraSocial);

        GridBagConstraints gbcInfo = new GridBagConstraints();
        gbcInfo.gridx = 1;
        gbcInfo.gridy = 1;
        gbcInfo.weightx = 1;
        gbcInfo.weighty = 1;
        gbcInfo.fill = GridBagConstraints.NONE;
        add(panelPacienteEncontrado, gbcInfo);

        GridBagConstraints gbcBotones = new GridBagConstraints();
        gbcBotones.gridx = 1;
        gbcBotones.gridy = 2;
        gbcBotones.weightx = 1;
        gbcBotones.weighty = 1;
        gbcBotones.fill = GridBagConstraints.NONE;
        add(panelBotones, gbcBotones);

        GridBagConstraints gbcAtras = new GridBagConstraints();
        gbcAtras.gridx = 1;
        gbcAtras.gridy = 0;
        gbcAtras.anchor = GridBagConstraints.NORTHWEST;
        add(panelAtras, gbcAtras);

    }

}
