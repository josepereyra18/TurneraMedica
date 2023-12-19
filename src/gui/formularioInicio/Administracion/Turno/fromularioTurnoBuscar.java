package gui.formularioInicio.Administracion.Turno;

import Service.serviceExeption;
import Service.serviceTurno;
import entidades.Turno;
import gui.PanelManager;
import gui.formularioInicio.Administracion.formularioAministracion;
import gui.formularioInicio.Administracion.formularioPaciente.FormularioListadoPaciente;
import gui.formularioInicio.Administracion.formularioPaciente.FormularioPacienteEncontrado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class fromularioTurnoBuscar extends JPanel {
    JPanel panelInicio;
    JPanel panelAtras;

    JLabel turnoCodigo;
    JTextField turnoCodigoText;
    JButton btnBusacr;
    JButton btnBuscarTodos;

    JButton botonAtras;

    serviceTurno instance;
    PanelManager panel;


    public fromularioTurnoBuscar(PanelManager panel){
        this.panel = panel;
        instance = new serviceTurno();
        setLayout(new GridBagLayout());
        armarFormulario();
    }

    public void armarFormulario(){
        panelInicio = new JPanel();
        panelAtras = new JPanel();
        panelInicio.setLayout(new GridLayout(2,2, 10, 10));
        turnoCodigo = new JLabel("Ingrese Codigo");
        turnoCodigoText = new JTextField(7);
        btnBusacr = new JButton("Buscar");
        btnBuscarTodos = new JButton("Buscar Todos");
        botonAtras = new JButton("<-");

        panelInicio.add(turnoCodigo);
        panelInicio.add(turnoCodigoText);
        panelInicio.add(btnBusacr);
        panelInicio.add(btnBuscarTodos);
        panelAtras.add(botonAtras);

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

        btnBusacr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Turno turno = new Turno();
                String turnoId = turnoCodigoText.getText();
                if (turnoId.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estan completados");
                } else if (!turnoId.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "El Id del turno solo puede contener numeros");
                }else {
                    try{
                        turno= instance.buscar(Integer.parseInt( turnoCodigoText.getText()));
                        panel.mostrar(new formularioTurnoEncontrado(panel,turno));
                        if (turno == null) {
                            JOptionPane.showMessageDialog(null, "No se encontro el turno buscado.");
                        }else{
                            //panel.mostrar(new formularioTurnoEncontrado(panel, paciente));
                        }
                    } catch (serviceExeption s) {
                    JOptionPane.showMessageDialog(null, s.getMessage());
                }
                }
            }
        });

        btnBuscarTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Turno> turnos = new ArrayList<>();
                try{
                    turnos = instance.buscarTodos();
                    panel.mostrar(new formularioTurnoListadoTodos(panel, turnos));
                } catch (serviceExeption ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new formularioAministracion(panel));
            }
        });
    }
}
