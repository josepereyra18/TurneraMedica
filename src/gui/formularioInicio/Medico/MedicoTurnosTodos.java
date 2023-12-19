package gui.formularioInicio.Medico;

import Service.serviceExeption;
import Service.serviceMedico;
import entidades.Medico;
import entidades.Turno;
import gui.PanelManager;
import gui.formularioInicio.Administracion.Turno.fromularioTurnoBuscar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MedicoTurnosTodos extends JPanel {
    PanelManager panel;

    JPanel panelInicio;
    JPanel panelAtras;

    JLabel labelFecha;
    JButton btnAtras;
    JTable tabla;
    DefaultTableModel modelo;

    serviceMedico instance;


    public MedicoTurnosTodos(PanelManager panel, ArrayList<Turno>turnos) {
        this.panel = panel;
        instance = new serviceMedico();
        setLayout(new GridBagLayout());
        armarFormulario(turnos);
    }

    public void armarFormulario(ArrayList<Turno> turnos){
        panelInicio = new JPanel();
        panelAtras = new JPanel();
        panelInicio.setLayout(new GridLayout(3,1));
        btnAtras = new JButton("<-");
        panelAtras.add(btnAtras);
        modelo = new DefaultTableModel();

        String [] columnas= { "FECHA", "HORA", "PACIENTE ID"};

        modelo.setColumnIdentifiers(columnas);

        for (Turno turno : turnos) {
            modelo.addRow(new Object[]{turno.getFecha(), turno.getHora(), turno.getPaciente().getId()});
        }

        tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Medico medico = new Medico();
                int idMedico= turnos.get(0).getMedico().getId();
                try{
                    medico = instance.buscar(idMedico);
                }catch (serviceExeption ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

                panel.mostrar(new MedicoVerTurnos(panel,medico));
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
