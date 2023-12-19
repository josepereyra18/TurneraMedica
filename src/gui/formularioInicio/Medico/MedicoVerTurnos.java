package gui.formularioInicio.Medico;

import Service.serviceExeption;
import Service.serviceTurno;
import entidades.Medico;
import entidades.Turno;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MedicoVerTurnos extends JPanel {
    JPanel panelInicio;
    JPanel panelAtras;

    JLabel nombreMedico;
    JButton btnBuscarTodo;
    JButton btnBuscarXFecha;
    JButton btnAtras;

    serviceTurno instance;

    PanelManager panel;


    public MedicoVerTurnos(PanelManager panel, Medico medico){
        this.panel = panel;
        instance = new serviceTurno();
        setLayout(new GridBagLayout());
        armarFormulario(medico);
    }

    public void armarFormulario(Medico medico){
        panelInicio = new JPanel();
        panelAtras = new JPanel();
        panelInicio.setLayout(new GridLayout(3,1,2,2));
        btnBuscarXFecha = new JButton("Buscar turnos por fecha");
        btnBuscarTodo = new JButton("Buscar todos los turnos");
        btnAtras = new JButton("<-");
        nombreMedico = new JLabel ("Bienvenid@: "+ medico.getNombre() + " " + medico.getApellido()+"!");

        panelInicio.add(nombreMedico);
        panelInicio.add(btnBuscarXFecha);
        panelInicio.add(btnBuscarTodo);
        panelAtras.add(btnAtras);

        //! CREAR UN PANEL NUEVO PARA EL SALUDO

        GridBagConstraints gbcModificar = new GridBagConstraints();
        gbcModificar.gridx = 0;
        gbcModificar.gridy = 1;
        gbcModificar.weightx = 1;
        gbcModificar.weighty = 1;
        gbcModificar.fill = GridBagConstraints.NONE;
        add(panelInicio, gbcModificar);

        GridBagConstraints gbcAtras = new GridBagConstraints();
        gbcAtras.gridx = 0;
        gbcAtras.gridy = 0;
        gbcAtras.anchor = GridBagConstraints.NORTHWEST;
        add(panelAtras, gbcAtras);


        btnBuscarTodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Turno> turnos = new ArrayList<>();
                try{
                    turnos = instance.todosTurnosMedico(medico.getId());
                    if (turnos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No se encontraron turnos");
                    }else{
                        panel.mostrar(new MedicoTurnosTodos(panel, turnos));
                    }
                } catch (serviceExeption ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        btnBuscarXFecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new MedicoTurnosFecha(panel, medico));
            }
        });

        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new MedicoInicio(panel));
            }
        });



    }
}
