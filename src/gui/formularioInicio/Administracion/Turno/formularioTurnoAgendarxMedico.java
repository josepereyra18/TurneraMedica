package gui.formularioInicio.Administracion.Turno;

import Service.serviceExeption;
import Service.serviceMedico;
import Service.servicePaciente;
import Service.serviceTurno;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import entidades.Medico;
import entidades.Paciente;
import entidades.Turno;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

public class formularioTurnoAgendarxMedico extends JPanel {

    PanelManager panel;
    JPanel panelInicio;
    JPanel panelAtras;
    JComboBox comboMedico;
    JLabel labelMedico;
    JDateChooser fecha;
    JLabel labelFecha;
    JComboBox hora1;
    JLabel labelHora;
    JButton btnAgendar;
    JButton btnAtras;
    serviceMedico instanceMedico;
    servicePaciente instancePaciente;
    serviceTurno instanceTurno;

    public formularioTurnoAgendarxMedico(PanelManager panel, Paciente paciente) throws serviceExeption {
        this.panel = panel;
        instanceMedico = new serviceMedico();
        instancePaciente = new servicePaciente();
        instanceTurno = new serviceTurno();
        setLayout(new GridBagLayout());
        armarFormulario(paciente);
    }

    public void armarFormulario(Paciente paciente) throws serviceExeption {
        JPanel panelInicio = new JPanel();
        JPanel panelAtras = new JPanel();
        panelInicio.setLayout(new GridLayout(5, 2));

        LocalDate currentDate = LocalDate.now();
        java.sql.Date sqlMinDate = java.sql.Date.valueOf(currentDate);
        LocalDate maxSelectableDate = currentDate.plusYears(1);
        java.sql.Date sqlMaxSelectableDate = java.sql.Date.valueOf(maxSelectableDate);


        ArrayList<Medico> medicos = instanceMedico.buscarTodos();
        Map<String,Medico> nombreMedicos = new LinkedHashMap<>();
        nombreMedicos.put("Seleccione un medico",null);
        for (Medico medicosnombre : medicos){
            nombreMedicos.put(medicosnombre.getNombre()+" "+medicosnombre.getApellido(),medicosnombre);
        }

        panelAtras = new JPanel();
        comboMedico = new JComboBox(nombreMedicos.keySet().toArray(new String[0]));
        labelMedico = new JLabel("Medico");
        fecha = new JDateChooser();
        labelFecha = new JLabel("Fecha");
        fecha.setMaxSelectableDate(sqlMaxSelectableDate);
        fecha.setMinSelectableDate(sqlMinDate);
        hora1 = new JComboBox();
        labelHora = new JLabel("Hora");

        btnAgendar = new JButton("Agendar");
       btnAtras = new JButton("<-");


        panelInicio.add(labelMedico);
        panelInicio.add(comboMedico);
        panelInicio.add(labelFecha);
        panelInicio.add(fecha);
        panelInicio.add(labelHora);
        panelInicio.add(hora1);
        panelInicio.add(btnAgendar);
        panelAtras.add(btnAtras);


        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new formularioTurnoDatosPaciente(panel));
            }
        });

        fecha.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if ("date".equals(e.getPropertyName())) {
                    Medico nombreMedico = nombreMedicos.get(comboMedico.getSelectedItem());
                    ActualizacionHorarios(nombreMedico, paciente);

                }
            }
        });

        comboMedico.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Medico nombreMedico = nombreMedicos.get(comboMedico.getSelectedItem());
                    ActualizacionHorarios(nombreMedico, paciente);
                }
            }
        });


        btnAgendar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.sql.Date SelectedDate = new java.sql.Date(fecha.getDate().getTime());
                int desc= 50;
                Turno turno = new Turno();
                turno.setMedico(nombreMedicos.get(comboMedico.getSelectedItem()));
                turno.setPaciente(paciente);
                turno.setHora((Time) hora1.getSelectedItem());
                turno.setFecha(SelectedDate);
                turno.setCosto(turno.getMedico().getPrecioConsulta());
                if (turno.getMedico().getObraSocial().equals(turno.getPaciente().getObraSocial())){
                    double f= turno.getMedico().getPrecioConsulta();
                    f -= (f * desc / 100);
                    turno.setCosto(f);
                }

                try{
                    instanceTurno.guardar(turno);
                    String mensaje = "Turno Agendado: \n" +
                            "Medico: " + turno.getMedico().getApellido() + "\n" +
                            "Dni paciente: " + paciente.getId() + "\n" +
                            "Fecha " + SelectedDate+" , "+hora1.getSelectedItem()+ "\n" +
                            "Costo:" + turno.getCosto();
                    JOptionPane.showMessageDialog(null, mensaje);
                    panel.mostrar(new formularioTurnoIinicio(panel));
                }catch (serviceExeption ex) {
                    JOptionPane.showMessageDialog(null, "No se pudo agendar el turno");
                }
            }
        });

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

    }


    public void ActualizacionHorarios(Medico nombreMedicos, Paciente paciente) {
        ArrayList<Time> horarios = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        for (int i = 8; i < 20; i++) {
            for (int j = 0; j < 60; j += 30) {
                horarios.add(Time.valueOf(LocalTime.of(i, j)));
            }
        }

        Medico medico = new Medico();
        medico = nombreMedicos;
        ArrayList<Time> horasMedico = new ArrayList<>();
        ArrayList<Time> horasPaciente = new ArrayList<>();

        // Asegúrate de que la fecha no sea null
        if (fecha.getDate() != null) {
            LocalDate selectedLocalDate;
            try {
                java.util.Date selectedDateUtil = fecha.getDate();
                selectedLocalDate = selectedDateUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                horasMedico = instanceTurno.buscarTurnosMedico(medico.getId(), java.sql.Date.valueOf(selectedLocalDate));
                horasPaciente = instanceTurno.buscarTurnosPaciente(paciente.getId(), java.sql.Date.valueOf(selectedLocalDate));
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

        horarios.removeAll(horasMedico);
        horarios.removeAll(horasPaciente);
        hora1.removeAllItems();
        hora1.addItem("Seleccione una hora");
        for (Time horaItem : horarios) {
            hora1.addItem(horaItem);
        }
    }
}
