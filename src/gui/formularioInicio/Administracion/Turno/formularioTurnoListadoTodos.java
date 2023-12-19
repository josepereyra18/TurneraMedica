package gui.formularioInicio.Administracion.Turno;

import entidades.Medico;
import entidades.Turno;
import gui.PanelManager;
import gui.formularioInicio.Administracion.formulariosMedico.FormularioMedicoBuscar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class formularioTurnoListadoTodos extends JPanel {

    JPanel panelInicio;
    JPanel panelAtras;

    JTable tablaTurnos;
    DefaultTableModel modelo;
    JButton botonAtras;

    PanelManager panel;

    public formularioTurnoListadoTodos(PanelManager panel, ArrayList<Turno> turnos){
        this.panel = panel;
        setLayout(new GridBagLayout());
        armarFormulario(turnos);
    }

    public void armarFormulario(ArrayList<Turno> turnos){
        panelInicio = new JPanel();
        panelAtras = new JPanel();
        botonAtras = new JButton("<-");
        panelAtras.add(botonAtras);
        modelo = new DefaultTableModel();

        String [] columnas= {"ID", "MEDICO ID", "PACIENTE ID", "FECHA", "HORA", "COSTO"};

        modelo.setColumnIdentifiers(columnas);

        for (Turno turno : turnos) {
            modelo.addRow(new Object[]{turno.getIdTurno(), turno.getMedico().getId(), turno.getPaciente().getId(), turno.getFecha(), turno.getHora(), turno.getCosto()});
        }

        tablaTurnos = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tablaTurnos);

        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new fromularioTurnoBuscar(panel));
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
