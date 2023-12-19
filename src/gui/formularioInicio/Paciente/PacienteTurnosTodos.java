package gui.formularioInicio.Paciente;

import Service.serviceExeption;
import Service.servicePaciente;
import entidades.Medico;
import entidades.Paciente;
import entidades.Turno;
import gui.PanelManager;
import gui.formularioInicio.Medico.MedicoVerTurnos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PacienteTurnosTodos extends JPanel {

    PanelManager panel;

    JPanel panelInicio;
    JPanel panelAtras;

    JLabel fechasNombre;
    JLabel labelFecha;
    JButton btnAtras;
    JTable tabla;
    DefaultTableModel modelo;

    servicePaciente instance;

    public PacienteTurnosTodos(PanelManager panel, ArrayList<Turno>turnos, Paciente paciente) {
        this.panel = panel;
        instance = new servicePaciente();
        setLayout(new GridBagLayout());
        armarFormulario(turnos, paciente);
    }

    public void armarFormulario(ArrayList<Turno>turnos, Paciente paciente){
        panelInicio = new JPanel();
        panelAtras = new JPanel();
        panelInicio.setLayout(new GridLayout(3,1));
        btnAtras = new JButton("<-");
        panelAtras.add(btnAtras);
        modelo = new DefaultTableModel();

        String [] columnas= { "FECHA", "HORA", "MEDICO ID", "IDPACIENTE"};

        modelo.setColumnIdentifiers(columnas);

        for (Turno turno : turnos) {
            modelo.addRow(new Object[]{turno.getFecha(), turno.getHora(), turno.getMedico().getId(), turno.getPaciente().getId()});
        }

        tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);


        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new PacienteVerTurnos(panel, paciente));
            }

        });

        GridBagConstraints gbcScroll = new GridBagConstraints();
        gbcScroll.gridx = 0;
        gbcScroll.gridy = 1;
        gbcScroll.weightx = 1;
        gbcScroll.weighty = 1;
        gbcScroll.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbcScroll);


        GridBagConstraints gbcTabla = new GridBagConstraints();
        gbcTabla.gridx = 0;
        gbcTabla.gridy = 1;
        gbcTabla.weightx = 1;
        gbcTabla.weighty = 1;
        gbcTabla.fill = GridBagConstraints.BOTH;
        add(panelInicio, gbcTabla);


        GridBagConstraints gbcAtras = new GridBagConstraints();
        gbcAtras.gridx = 0;
        gbcAtras.gridy = 0;
        gbcAtras.anchor = GridBagConstraints.NORTHWEST;
        add(panelAtras, gbcAtras);
    }

}
