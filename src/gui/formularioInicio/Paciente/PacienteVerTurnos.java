package gui.formularioInicio.Paciente;

import Service.serviceExeption;
import Service.serviceTurno;
import entidades.Paciente;
import entidades.Turno;
import gui.PanelManager;
import gui.formularioInicio.Medico.MedicoTurnosTodos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PacienteVerTurnos extends JPanel {
    PanelManager panel;

    JPanel panelInicio;
    JPanel panelAtras;

    JLabel nombrePaciente;
    JButton btnBuscarTodo;
    JButton btnBuscarXFecha;
    JButton btnAtras;

    serviceTurno instance;

    public PacienteVerTurnos(PanelManager panel, Paciente paciente) {
        this.panel = panel;
        setLayout(new GridBagLayout());
        instance = new serviceTurno();
        armarFormulario(paciente);
    }

    public void armarFormulario(Paciente paciente) {
        panelInicio = new JPanel();
        panelAtras = new JPanel();
        panelInicio.setLayout(new GridLayout(3,1,2,2));
        btnBuscarXFecha = new JButton("Buscar turnos por fecha");
        btnBuscarTodo = new JButton("Buscar todos los turnos");
        btnAtras = new JButton("<-");
        nombrePaciente = new JLabel ("Bienvenid@: "+ paciente.getNombre() + " " + paciente.getApellido()+"!");

        panelInicio.add(nombrePaciente);
        panelInicio.add(btnBuscarXFecha);
        panelInicio.add(btnBuscarTodo);
        panelAtras.add(btnAtras);

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
                ArrayList<Turno> turno123 = new ArrayList<>();
                int xd = paciente.getId();
                try{
                    turno123 = instance.todosTurnosPaciente(xd);
                    if (turno123.isEmpty()){
                        JOptionPane.showMessageDialog(null, "No hay turnos para mostrar");
                    }else{
                        panel.mostrar(new PacienteTurnosTodos(panel, turno123, paciente));
                    }
                } catch (serviceExeption ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        btnBuscarXFecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new PacienteTurnosFecha(panel, paciente));
            }
        });

        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new PacienteInicio(panel));
            }
        });



    }

}
