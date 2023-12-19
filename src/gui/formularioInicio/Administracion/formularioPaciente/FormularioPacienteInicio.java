package gui.formularioInicio.Administracion.formularioPaciente;

import gui.PanelManager;
import gui.formularioInicio.Administracion.formularioAministracion;
import gui.formularioInicio.FormularioInicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioPacienteInicio extends JPanel {

    JPanel panelAtras;
    JButton botonAtras;
    JPanel panelInicio;
    JButton botonAgregar;
    JButton botonBuscar;
    PanelManager panel;

    public FormularioPacienteInicio(PanelManager panel) {
        this.panel = panel;
        setLayout(new GridBagLayout());
        armarFormulario();
    }

    public void armarFormulario() {
        panelInicio = new JPanel();
        panelAtras = new JPanel();
        botonAgregar = new JButton("Agregar");
        botonBuscar = new JButton("Buscar");
        botonAtras= new JButton("<-");

        panelInicio.add(botonAgregar);
        panelInicio.add(botonBuscar);
        panelAtras.add(botonAtras);
        panelAtras.setLayout(new GridBagLayout());

        GridBagConstraints gbcBotones = new GridBagConstraints();
        gbcBotones.gridx = 0;
        gbcBotones.gridy = 2;
        gbcBotones.weightx = 1;
        gbcBotones.weighty = 1;
        gbcBotones.fill = GridBagConstraints.CENTER;
        add(panelInicio, gbcBotones);

        GridBagConstraints gbcAtras = new GridBagConstraints();
        gbcAtras.gridx = 0;
        gbcAtras.gridy = 0;
        gbcAtras.anchor = GridBagConstraints.NORTHWEST;
        add(panelAtras, gbcAtras);






        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new FormularioPacienteAgregar(panel));
            }
        });
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new FormularioPacienteBuscar(panel));
            }
        });

        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new formularioAministracion(panel));
                panel.setJFrameTitle("Administracion");
            }
        });

        add(this.panelInicio);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(panelInicio, gbc);
    }
}
