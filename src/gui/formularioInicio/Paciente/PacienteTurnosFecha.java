package gui.formularioInicio.Paciente;

import Service.serviceExeption;
import Service.serviceTurno;
import com.toedter.calendar.JDateChooser;
import entidades.Paciente;
import entidades.Turno;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class PacienteTurnosFecha  extends JPanel {

    PanelManager panel;
    JPanel panelInicio;
    JPanel panelAtras;

    JButton btnAtras;
    JLabel labelFecha;
    JDateChooser fecha;
    JButton btnBuscar;

    serviceTurno instance;

    public PacienteTurnosFecha(PanelManager panel, Paciente paciente) {
        this.panel = panel;
        instance = new serviceTurno();
        setLayout(new GridBagLayout());
        armarFormulario(paciente);
    }

    public void armarFormulario(Paciente paciente){
        panelInicio = new JPanel();
        panelAtras = new JPanel();
        panelInicio.setLayout(new GridLayout(3,1));

        LocalDate currentDate = LocalDate.now();
        java.sql.Date sqlMinDate = java.sql.Date.valueOf(currentDate);
        LocalDate maxSelectableDate = currentDate.plusYears(1);
        java.sql.Date sqlMaxSelectableDate = java.sql.Date.valueOf(maxSelectableDate);

        labelFecha = new JLabel("Seleccione fecha");
        fecha = new JDateChooser();
        fecha.setMaxSelectableDate(sqlMaxSelectableDate);
        fecha.setMinSelectableDate(sqlMinDate);
        btnBuscar = new JButton("Buscar");
        btnAtras = new JButton("<-");

        panelInicio.add(labelFecha);
        panelInicio.add(fecha);
        panelInicio.add(btnBuscar);
        panelAtras.add(btnAtras);

        GridBagConstraints gbcModificar = new GridBagConstraints();
        gbcModificar.gridx = 0;
        gbcModificar.gridy = 1;
        gbcModificar.weightx = 1;
        gbcModificar.weighty = 1;
        gbcModificar.fill = GridBagConstraints.NONE;
        add(panelInicio, gbcModificar);
        setPreferredSize(new Dimension(300, 200));

        GridBagConstraints gbcAtras = new GridBagConstraints();
        gbcAtras.gridx = 0;
        gbcAtras.gridy = 0;
        gbcAtras.anchor = GridBagConstraints.NORTHWEST;
        add(panelAtras, gbcAtras);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Turno> turnosFecha = new ArrayList<>();
                java.util.Date selectedDateUtil = fecha.getDate();
                java.sql.Date fechaSeleccionada = new java.sql.Date(selectedDateUtil.getTime());

                try{
                    turnosFecha = instance.fechaTurnosPaciente(paciente.getId(), fechaSeleccionada);
                    if (turnosFecha.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay turnos para la fecha seleccionada");
                    } else {
                        panel.mostrar(new PacienteTurnosTodos(panel, turnosFecha, paciente));
                    }
                }catch (
                    serviceExeption ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

            }
        });

        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new PacienteVerTurnos(panel, paciente));
            }
        });


    }


}
