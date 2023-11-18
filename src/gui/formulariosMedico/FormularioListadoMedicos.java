package gui.formulariosMedico;

import entidades.Medico;
import gui.PanelManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormularioListadoMedicos extends JPanel{

    PanelManager panel;
    JPanel panelListado;
    JPanel panelAtras;
    JTable table1;
    DefaultTableModel modelo;
    JButton botonAtras;

    public FormularioListadoMedicos(PanelManager panel, ArrayList<Medico> medicos){
        this.panel = panel;
        setLayout(new GridBagLayout());
        armarFormulario(medicos);
    }

    public void armarFormulario  (ArrayList<Medico> medicos) {
        panelListado = new JPanel();
        panelAtras = new JPanel();
        botonAtras = new JButton("<-");
        panelAtras.add(botonAtras);
        modelo = new DefaultTableModel();

        String[] columnas = {"ID", "Nombre", "Apellido", "Precio Consulta"};
        //modelo.setColumnIdentifiers(columnas);
        modelo.setColumnIdentifiers(columnas);

        for (Medico medico : medicos) {
            modelo.addRow(new Object[]{medico.getId(), medico.getNombre(), medico.getApellido(), medico.getPrecioConsulta()});
        }

        table1 = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(table1);

        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new FormularioMedicoBuscar(panel));
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
