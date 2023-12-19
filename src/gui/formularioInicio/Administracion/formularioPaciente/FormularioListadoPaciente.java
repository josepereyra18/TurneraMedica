package gui.formularioInicio.Administracion.formularioPaciente;

import entidades.Paciente;
import gui.PanelManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormularioListadoPaciente extends JPanel {
    PanelManager panel;
    JPanel panelListado;
    JPanel panelAtras;
    JTable table1;
    DefaultTableModel modelo;
    JButton botonAtras;

    public FormularioListadoPaciente(PanelManager panel, ArrayList<Paciente> pacientes){
        this.panel = panel;
        setLayout(new GridBagLayout());
        armarFormulario(pacientes);
    }

    public void armarFormulario  (ArrayList<Paciente> pacientes) {
        panelListado = new JPanel();
        panelAtras = new JPanel();
        botonAtras = new JButton("<-");
        panelAtras.add(botonAtras);
        modelo = new DefaultTableModel();

        String[] columnas = {"ID", "Nombre", "Apellido", "Obra Social"};

        modelo.setColumnIdentifiers(columnas);

        for (Paciente paciente : pacientes) {
            modelo.addRow(new Object[]{paciente.getId(), paciente.getNombre(), paciente.getApellido(), paciente.getObraSocial()});
        }

        table1 = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(table1);

        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panel.mostrar(new FormularioPacienteBuscar(panel));
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
        add(panelListado, gbcTabla);


        GridBagConstraints gbcAtras = new GridBagConstraints();
        gbcAtras.gridx = 0;
        gbcAtras.gridy = 0;
        gbcAtras.anchor = GridBagConstraints.NORTHWEST;
        add(panelAtras, gbcAtras);



    }

}
