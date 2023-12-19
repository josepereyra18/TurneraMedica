package gui.formularioInicio.Administracion.Turno;

import Service.serviceExeption;
import Service.serviceTurno;
import entidades.Turno;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class formularioTurnoEncontrado extends JPanel {

    JPanel panelInicio;
    JPanel panelAtras;
    JPanel panelEncontrado;

    JLabel turnoCodigo;
    JLabel Medico;
    JLabel Paciente;
    JLabel Fecha;
    JLabel Hora;
    JLabel Precio;

    JButton botonAtras;
    JButton botonEliminar;
    JButton botonModificar;

    serviceTurno instance;

    PanelManager panel;

    public formularioTurnoEncontrado(PanelManager panel, Turno turno){
        this.panel = panel;
        instance = new serviceTurno();
        setLayout(new GridBagLayout());
        armarFormulario(turno);
    }

    public void  armarFormulario (Turno turno){
        panelEncontrado = new JPanel();
        panelInicio = new JPanel();
        panelAtras = new JPanel();

        panelEncontrado.setLayout(new GridLayout(6,1));
        turnoCodigo = new JLabel("Codigo: " + turno.getIdTurno());
        Medico = new JLabel("Medico: " + turno.getMedico().getNombre() + " " + turno.getMedico().getApellido());
        Paciente = new JLabel("Paciente: " + turno.getPaciente().getNombre() + " " + turno.getPaciente().getApellido());
        Fecha = new JLabel("Fecha: " + turno.getFecha());
        Hora = new JLabel("Hora: " + turno.getHora());
        Precio = new JLabel("Precio: " + turno.getCosto());

        panelEncontrado.add(turnoCodigo);
        panelEncontrado.add(Medico);
        panelEncontrado.add(Paciente);
        panelEncontrado.add(Fecha);
        panelEncontrado.add(Hora);
        panelEncontrado.add(Precio);

        panelInicio.setLayout(new GridLayout(1,2));
        botonModificar = new JButton("Modificar");
        botonEliminar = new JButton("Eliminar");
        panelInicio.add(botonModificar);
        panelInicio.add(botonEliminar);

        botonAtras = new JButton("<-");
        panelAtras.add(botonAtras);


        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new fromularioTurnoBuscar(panel));
            }
        });

        botonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    panel.mostrar(new formularioTurnoModificar(panel, turno));
                } catch (serviceExeption ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resultado= JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar el turno?", "Eliminar Turno", JOptionPane.YES_NO_OPTION);
                if (resultado == JOptionPane.YES_OPTION){
                    int turnoId = turno.getIdTurno();
                    try {
                        instance.eliminar(turnoId);
                        JOptionPane.showMessageDialog(null, "Se elimino correctamente");
                        panel.mostrar(new fromularioTurnoBuscar(panel));
                    } catch (serviceExeption ex) {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar");
                    }
                }
            }
        });

        GridBagConstraints gbcInfo = new GridBagConstraints();
        gbcInfo.gridx = 1;
        gbcInfo.gridy = 1;
        gbcInfo.weightx = 1;
        gbcInfo.weighty = 1;
        gbcInfo.fill = GridBagConstraints.NONE;
        add(panelEncontrado, gbcInfo);

        GridBagConstraints gbcBotones = new GridBagConstraints();
        gbcBotones.gridx = 1;
        gbcBotones.gridy = 2;
        gbcBotones.weightx = 1;
        gbcBotones.weighty = 1;
        gbcBotones.fill = GridBagConstraints.NONE;
        add(panelInicio, gbcBotones);

        GridBagConstraints gbcAtras = new GridBagConstraints();
        gbcAtras.gridx = 1;
        gbcAtras.gridy = 0;
        gbcAtras.anchor = GridBagConstraints.NORTHWEST;
        add(panelAtras, gbcAtras);


    }







}
