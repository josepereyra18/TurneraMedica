package gui.formularioInicio.Administracion.Turno;

import Service.serviceExeption;
import Service.serviceMedico;
import Service.servicePaciente;
import Service.serviceTurno;
import com.toedter.calendar.JDateChooser;
import entidades.Medico;
import entidades.Turno;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class formularioTurnoModificar extends JPanel {

    PanelManager panel;

    JPanel panelInicio;
    JPanel panelAtras;

    JLabel turnoCodigo;
    JLabel Medico;
    JComboBox comboMedico;
    JLabel fecha;
    JDateChooser fechaText;
    JLabel hora;
    JComboBox comboHora;
    JButton botonModificar;
    JButton botonAtras;

    serviceTurno instanceTurno;
    serviceMedico instanceMedico;
    servicePaciente instancePaciente;

    public formularioTurnoModificar(PanelManager panel, Turno turno) throws serviceExeption {
        this.panel = panel;
        instanceTurno = new serviceTurno();
        instanceMedico = new serviceMedico();
        instancePaciente = new servicePaciente();
        setLayout(new GridBagLayout());
        armarFormulario(turno);
    }

    public void armarFormulario(Turno turno) throws serviceExeption {
        panelInicio = new JPanel();
        panelAtras = new JPanel();
        panelAtras.setLayout(new GridBagLayout());
        panelInicio.setLayout(new GridLayout(7,1, 5, 5));

        LocalDate currentDate = LocalDate.now();
        java.sql.Date sqlMinDate = java.sql.Date.valueOf(currentDate);
        LocalDate maxSelectableDate = currentDate.plusYears(1);
        java.sql.Date sqlMaxSelectableDate = java.sql.Date.valueOf(maxSelectableDate);

        ArrayList<entidades.Medico> medicos = instanceMedico.buscarTodos();
        Map<String,Medico> nombreMedicos = new LinkedHashMap<>();
        nombreMedicos.put("Seleccione un medico",null);
        for (Medico medicosnombre : medicos){
            nombreMedicos.put(medicosnombre.getNombre()+" "+medicosnombre.getApellido(),medicosnombre);
        }

        Medico = new JLabel("Medico");
        comboMedico = new JComboBox(nombreMedicos.keySet().toArray(new String[0]));
        comboMedico.setSelectedItem(turno.getMedico().getNombre() + " " + turno.getMedico().getApellido());

        fecha = new JLabel("Fecha");
        fechaText = new JDateChooser();
        fechaText.setMaxSelectableDate(sqlMaxSelectableDate);
        fechaText.setMinSelectableDate(sqlMinDate);
        fechaText.setDate(turno.getFecha());
        hora = new JLabel("Hora");
        comboHora = new JComboBox();
        ActualizacionHorarios(turno.getMedico());
        comboHora.addItem(turno.getHora());
        comboHora.setSelectedItem(turno.getHora());


        botonModificar = new JButton("Modificar");
        botonAtras = new JButton("<-");

        panelInicio.add(Medico);
        panelInicio.add(comboMedico);
        panelInicio.add(fecha);
        panelInicio.add(fechaText);
        panelInicio.add(hora);
        panelInicio.add(comboHora);
        panelInicio.add(botonModificar);
        panelAtras.add(botonAtras);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(panelAtras, gbc);

        GridBagConstraints gbcBotones = new GridBagConstraints();
        gbcBotones.gridx = 0;
        gbcBotones.gridy = 0;
        gbcBotones.weightx = 1;
        gbcBotones.weighty = 1;
        gbcBotones.fill = GridBagConstraints.NONE;
        add(panelInicio, gbcBotones);


        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new formularioTurnoEncontrado(panel, turno));
            }
        });

        fechaText.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("date")) {
                    ActualizacionHorarios(nombreMedicos.get(comboMedico.getSelectedItem()));
                }
            }
        });

        comboMedico.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    ActualizacionHorarios(nombreMedicos.get(comboMedico.getSelectedItem()));
                }
            }
        });

        botonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.sql.Date SelectedDate = new java.sql.Date(fechaText.getDate().getTime());
                int desc= 50;
                Turno turnoModificado = new Turno();
                turnoModificado.setIdTurno(turno.getIdTurno());
                turnoModificado.setMedico(nombreMedicos.get(comboMedico.getSelectedItem()));
                turnoModificado.setPaciente(turno.getPaciente());
                turnoModificado.setHora((Time) comboHora.getSelectedItem());
                turnoModificado.setFecha(SelectedDate);
                turnoModificado.setCosto(turno.getMedico().getPrecioConsulta());
                turnoModificado.setIdTurno(turno.getIdTurno());
                if (turnoModificado.getMedico().getObraSocial().equals(turnoModificado.getPaciente().getObraSocial())){
                    double f= turnoModificado.getMedico().getPrecioConsulta();
                    f -= (f * desc / 100);
                    turnoModificado.setCosto(f);
                }
                try {
                    instanceTurno.modificar(turnoModificado);
                    String mensaje = "Turno Modificado: \n" +
                            "Medico: " + turnoModificado.getMedico().getApellido() + "\n" +
                            "Dni paciente: " + turnoModificado.getPaciente().getId() + "\n" +
                            "Fecha " + SelectedDate+" , "+comboHora.getSelectedItem()+ "\n" +
                            "Costo:" + turnoModificado.getCosto();
                    JOptionPane.showMessageDialog(null, mensaje);
                    panel.mostrar(new fromularioTurnoBuscar(panel));
                } catch (serviceExeption serviceExeption) {
                    JOptionPane.showMessageDialog(null, "No se pudo Modificar el turno.");
                }
            }
        });


    }

    public void ActualizacionHorarios(Medico nombreMedicos) {
        ArrayList<Time> horarios = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        for (int i = 8; i < 20; i++) {
            for (int j = 0; j < 60; j += 30) {
                horarios.add(Time.valueOf(LocalTime.of(i, j)));
            }
        }

        Medico medico = new Medico();
        medico = nombreMedicos;
        ArrayList<Time> horas = new ArrayList<>();

        if (fechaText.getDate() != null) {
            LocalDate selectedLocalDate;
            try {
                java.util.Date selectedDateUtil = fechaText.getDate();
                selectedLocalDate = selectedDateUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                horas = instanceTurno.buscarTurnosMedico(medico.getId(), java.sql.Date.valueOf(selectedLocalDate));
            } catch (serviceExeption ex) {
                throw new RuntimeException(ex);
            }

            System.out.println(currentDate);
            System.out.println(selectedLocalDate);

            if (currentDate.equals(selectedLocalDate)) {
                Iterator<Time> iterator = horarios.iterator();
                while (iterator.hasNext()) {
                    Time horaItem = iterator.next();
                    if (horaItem.before(Time.valueOf(LocalTime.now()))) {
                        iterator.remove();
                    }
                }
            }
        }
        horarios.removeAll(horas);
        comboHora.removeAllItems();
        comboHora.addItem("Seleccione una hora");
        for (Time horaItem : horarios) {
            comboHora.addItem(horaItem);
        }
    }
}

