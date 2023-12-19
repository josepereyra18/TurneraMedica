package gui.formularioInicio.Administracion;

import Service.serviceExeption;
import gui.PanelManager;
import gui.formularioInicio.Administracion.Reportes.formularioReporteInicio;
import gui.formularioInicio.Administracion.Turno.formularioTurnoIinicio;
import gui.formularioInicio.Administracion.formularioPaciente.FormularioPacienteInicio;
import gui.formularioInicio.Administracion.formulariosMedico.FormularioMedicoInicio;
import gui.formularioInicio.FormularioInicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class formularioAministracion extends JPanel{
    PanelManager panel;
    JPanel panelInicio;
    JPanel panelAtras;
    JButton botonMedico;
    JButton botonPaciente;
    JButton botonTurno;
    JButton btnReportes;
    JButton botonAtras;


    public formularioAministracion(PanelManager panel) {
        this.panel = panel;
        setLayout(new GridBagLayout());
        armarFormulario();
    }

    public void armarFormulario() {
        panelInicio = new JPanel();
        panelAtras = new JPanel();
        panelAtras.setLayout(new GridBagLayout());
        panelInicio.setLayout(new GridLayout(2,2, 10, 10 ));
        botonMedico = new JButton("Medico");
        botonPaciente = new JButton("Paciente");
        botonTurno = new JButton("Turno");
        btnReportes = new JButton("Reportes");
        botonAtras = new JButton("<-");

        panelInicio.add(botonMedico);
        panelInicio.add(botonPaciente);
        panelInicio.add(botonTurno);
        panelInicio.add(btnReportes);
        panelAtras.add(botonAtras);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(panelAtras,gbc);
        add(panelInicio);

        GridBagConstraints gbcBotones = new GridBagConstraints();
        gbcBotones.gridx = 0;
        gbcBotones.gridy = 0;
        gbcBotones.weightx = 1;
        gbcBotones.weighty = 1;
        gbcBotones.fill = GridBagConstraints.NONE;
        add(panelInicio, gbcBotones);



        botonMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new FormularioMedicoInicio(panel));
                panel.setJFrameTitle("Adminstración Medico");
            }
        });

        botonPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new FormularioPacienteInicio(panel));
                panel.setJFrameTitle("Adminstración Paciente");
            }
        });

        botonTurno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new formularioTurnoIinicio(panel));
                panel.setJFrameTitle("Adminstración Turno");
            }
        });

        btnReportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    panel.mostrar(new formularioReporteInicio(panel));
                    panel.setBounds(600,600);
                } catch (serviceExeption ex) {
                    throw new RuntimeException(ex);
                }
                panel.setJFrameTitle("Adminstración Reportes");
            }
        });

        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new FormularioInicio(panel));
                panel.setJFrameTitle("Inicio");
            }
        });


    }
}
