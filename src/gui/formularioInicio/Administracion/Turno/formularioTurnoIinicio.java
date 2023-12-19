package gui.formularioInicio.Administracion.Turno;

import gui.PanelManager;
import gui.formularioInicio.Administracion.formularioAministracion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class formularioTurnoIinicio extends JPanel {
    PanelManager panel;
    JPanel panelInicio;
    JPanel panelAtras;
    JButton btnAgendarTurno;
    JButton btnVerTurnos;
    JButton btnAtras;


    public formularioTurnoIinicio(PanelManager panel) {
        this.panel = panel;
        setLayout(new GridBagLayout());
        armarFormulario();
    }

    public void armarFormulario(){
        JPanel panelInicio = new JPanel();
        panelInicio.setLayout(new GridBagLayout());
        panelAtras = new JPanel();
        btnAgendarTurno = new JButton("Agendar Turno");
        btnVerTurnos = new JButton("Ver Turnos");
        btnAtras = new JButton("<-");

        panelInicio.add(btnAgendarTurno);
        panelInicio.add(btnVerTurnos);
        panelAtras.add(this.btnAtras);


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


        btnAgendarTurno.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new formularioTurnoDatosPaciente(panel));
            }
        }));

        btnVerTurnos.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new fromularioTurnoBuscar(panel));
            }
        }));

        btnAtras.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new formularioAministracion(panel));
            }
        }));
    }
}

