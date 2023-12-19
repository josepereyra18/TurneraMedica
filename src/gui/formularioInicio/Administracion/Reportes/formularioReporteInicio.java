package gui.formularioInicio.Administracion.Reportes;

import Service.serviceExeption;
import Service.serviceMedico;
import Service.serviceTurno;
import com.toedter.calendar.JDateChooser;
import entidades.Medico;
import entidades.Turno;
import gui.PanelManager;
import gui.formularioInicio.Administracion.formularioAministracion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class formularioReporteInicio extends JPanel {
    PanelManager panel;

    JPanel panelInicio;
    JPanel filtroPanel;
    JPanel tablaPanel;
    JPanel totalPanel;
    JPanel panelAtras;


    JButton botonAtras;

    JTable table1;

    JComboBox medicosCombo;
    JDateChooser dateChooser1;
    JDateChooser dateChooser2;

    JButton btnBuscar;
    JLabel TotalLabel;
    JLabel Total;

    serviceMedico instanceMedico;
    serviceTurno instanceTurno;




    public formularioReporteInicio (PanelManager panel) throws serviceExeption {
        this.panel = panel;
        instanceMedico = new serviceMedico();
        instanceTurno = new serviceTurno();
        setLayout(new GridBagLayout());
        armarFormulario();
    }

    public void armarFormulario() throws serviceExeption {

        LocalDate currentDate = LocalDate.now();
        java.sql.Date sqlMinDate = java.sql.Date.valueOf(currentDate);

        LocalDate maxSelectableDate = currentDate.plusYears(1);
        java.sql.Date sqlMaxSelectableDate = java.sql.Date.valueOf(maxSelectableDate);

        panelInicio = new JPanel();
        filtroPanel = new JPanel();
        tablaPanel = new JPanel();
        totalPanel = new JPanel();
        panelAtras = new JPanel();

        panelInicio.setLayout(new GridBagLayout());
        filtroPanel.setLayout(new GridLayout(1,4, 10,10));
        totalPanel.setLayout(new GridLayout(1,2, 10,10));

        ArrayList<Medico>medicos = new ArrayList<>();
        Map<String,Medico> nombreMedicos = new LinkedHashMap<>();
        nombreMedicos.put("Seleccione un medico",null);
        medicos = instanceMedico.buscarTodos();

        for (Medico medicosnombre : medicos){
            nombreMedicos.put(medicosnombre.getNombre()+" "+medicosnombre.getApellido(),medicosnombre);
        }
        btnBuscar = new JButton("Buscar");
        medicosCombo = new JComboBox(nombreMedicos.keySet().toArray(new String[0]));
        dateChooser1 = new JDateChooser();
        dateChooser2 = new JDateChooser();

        table1 = new JTable();
        TotalLabel = new JLabel("Total: ");
        Total = new JLabel("$"+0);

        dateChooser1.setMinSelectableDate(sqlMinDate);
        dateChooser1.setDate(sqlMinDate);
        dateChooser1.setMaxSelectableDate(sqlMaxSelectableDate);

        dateChooser2.setMinSelectableDate(sqlMinDate);
        dateChooser2.setDate(sqlMinDate);
        dateChooser2.setMaxSelectableDate(sqlMaxSelectableDate);

        botonAtras = new JButton("<-");

        filtroPanel.add(medicosCombo);
        filtroPanel.add(dateChooser1);
        filtroPanel.add(dateChooser2);
        filtroPanel.add(btnBuscar);

        tablaPanel.add(new JScrollPane(table1));

        totalPanel.add(TotalLabel);
        totalPanel.add(Total);

        panelAtras.add(botonAtras);

        GridBagConstraints gbcAtras = new GridBagConstraints();
        gbcAtras.gridx = 0;
        gbcAtras.gridy = 0;
        gbcAtras.anchor = GridBagConstraints.NORTHWEST;
        add(panelAtras, gbcAtras);

        GridBagConstraints gbcInicio = new GridBagConstraints();
        gbcInicio.gridx = 0;
        gbcInicio.gridy = 1;
        gbcInicio.weightx = 1;
        gbcInicio.weighty = 1;
        gbcInicio.fill = GridBagConstraints.BOTH;
        add(panelInicio, gbcInicio);

        GridBagConstraints gbcFiltro = new GridBagConstraints();
        gbcFiltro.gridx = 0;
        gbcFiltro.gridy = 0;
        gbcFiltro.weightx = 1;
        gbcFiltro.weighty = 0;
        gbcFiltro.fill = GridBagConstraints.BOTH;
        panelInicio.add(filtroPanel, gbcFiltro);

        GridBagConstraints gbcTabla = new GridBagConstraints();
        gbcTabla.gridx = 0;
        gbcTabla.gridy = 1;
        gbcTabla.weightx = 1;
        gbcTabla.weighty = 1;
        gbcTabla.fill = GridBagConstraints.BOTH;
        panelInicio.add(tablaPanel, gbcTabla);

        GridBagConstraints gbcTotal = new GridBagConstraints();
        gbcTotal.gridx = 0;
        gbcTotal.gridy = 2;
        gbcTotal.weightx = 1;
        gbcTotal.weighty = 1;
        gbcTotal.fill = GridBagConstraints.BOTH;
        gbcTotal.anchor = GridBagConstraints.CENTER;
        panelInicio.add(totalPanel, gbcTotal);


        dateChooser1.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if ("date".equals(e.getPropertyName())) {
                    dateChooser2.setMinSelectableDate(dateChooser1.getDate());

                }
            }
        });

        btnBuscar. addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date localFechadesde= dateChooser1.getDate();
                Date localFechaHasta= dateChooser2.getDate();
                java.sql.Date sqlFechadesde = new java.sql.Date(localFechadesde.getTime());
                java.sql.Date sqlFechaHasta = new java.sql.Date(localFechaHasta.getTime());

                if(medicosCombo.getSelectedIndex() == 0){
                    try {
                        ArrayList<Turno> turnos = new ArrayList<>();

                        turnos = instanceTurno.calcularSumaCobrosRangoXMedico(sqlFechadesde, sqlFechaHasta);

                        actualizarTablaxMedico(turnos);
                        actualizarTotal(turnos);

                    } catch (serviceExeption ex) {
                        throw new RuntimeException(ex);
                    }
                }else  {
                    try {
                        int idMedico = nombreMedicos.get(medicosCombo.getSelectedItem()).getId();
                        ArrayList<Turno> turnos = new ArrayList<>();

                        turnos = instanceTurno.calcularSumaCobrosRango(sqlFechadesde, sqlFechaHasta,idMedico);

                        actualizarTabla(turnos);
                        actualizarTotal(turnos);

                    } catch (serviceExeption ex) {
                        JOptionPane.showMessageDialog(null, "No se pudo acceder a los datos");
                    }
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

    public void actualizarTabla (ArrayList<Turno> turnos){
        DefaultTableModel modelo = new DefaultTableModel();
        String[] columnas = {"MEDICO", "FECHA", "HORA", "PACIENTE", "COSTO"};
        modelo.setColumnIdentifiers(columnas);

        for (Turno turno : turnos) {
            modelo.addRow(new Object[]{turno.getMedico().getId(), turno.getFecha(), turno.getHora(), turno.getPaciente().getId(), turno.getCosto()});
        }
        table1.setModel(modelo);
    }

    public void actualizarTotal (ArrayList<Turno> turnos){
        double total = 0;
        for (Turno turno : turnos) {
            total += turno.getCosto();
        }
        Total.setText("$"+total);

    }

    public void actualizarTablaxMedico (ArrayList<Turno> turnos){
        DefaultTableModel modelo = new DefaultTableModel();
        String[] columnas = {"MEDICO", "RECAUDACION"};
        modelo.setColumnIdentifiers(columnas);

        for (Turno turno : turnos) {
            modelo.addRow(new Object[]{turno.getMedico().getId(), turno.getCosto()});
        }

        table1.setModel(modelo);
    }
}


/*
if (medicosCombo.getSelectedIndex() == 0){
                    try {
                        ArrayList<Turno> turnos = new ArrayList<>();

                        turnos = instanceTurno.calcularSumaCobrosRangoTodos(sqlFechadesde, sqlFechaHasta);

                        actualizarTabla(turnos);
                        actualizarTotal(turnos);

                    } catch (serviceExeption ex) {
                        JOptionPane.showMessageDialog(null, "No se pudo acceder a los datos");
                    }
                } else if(medicosCombo.getSelectedIndex() == 1){
                    try {
                        ArrayList<Turno> turnos = new ArrayList<>();

                        turnos = instanceTurno.calcularSumaCobrosRangoXMedico(sqlFechadesde, sqlFechaHasta);

                        actualizarTablaxMedico(turnos);
                        actualizarTotal(turnos);

                    } catch (serviceExeption ex) {
                        throw new RuntimeException(ex);
                    }
                }else  {
                    try {
                        int idMedico = nombreMedicos.get(medicosCombo.getSelectedItem()).getId();
                        ArrayList<Turno> turnos = new ArrayList<>();

                        turnos = instanceTurno.calcularSumaCobrosRango(sqlFechadesde, sqlFechaHasta,idMedico);

                        actualizarTabla(turnos);
                        actualizarTotal(turnos);

                    } catch (serviceExeption ex) {
                        JOptionPane.showMessageDialog(null, "No se pudo acceder a los datos");
                    }
                }
*/