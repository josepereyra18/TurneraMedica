package gui.formularioInicio.Paciente;

import Service.servicePaciente;
import entidades.Paciente;
import gui.PanelManager;
import gui.formularioInicio.FormularioInicio;
import gui.formularioInicio.Medico.MedicoVerTurnos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacienteInicio extends JPanel {
    PanelManager panel;

    JPanel panelInicio;
    JPanel panelAtras;

    JLabel Paciente;
    JTextField Pacientetext;

    JButton btnBuscar;
    JButton btnAtras;

    servicePaciente instance;


    public PacienteInicio(PanelManager panel) {
        this.panel = panel;
        instance = new servicePaciente();
        setLayout(new GridBagLayout());
        armarFormulario();
    }

    public void armarFormulario(){
        panelInicio = new JPanel();
        panelAtras = new JPanel();
        panelInicio.setLayout(new GridLayout(3,1, 10, 10));

        Paciente = new JLabel("Ingrese su DNI:");
        Pacientetext = new JTextField(7);
        btnBuscar = new JButton("Buscar");
        btnAtras = new JButton("<-");


        panelInicio.add(Paciente);
        panelInicio.add(Pacientetext);
        panelInicio.add(btnBuscar);
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

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paciente paciente = new Paciente();
                String dni = Pacientetext.getText();
                if (!dni.matches("[0-9]+")){
                    JOptionPane.showMessageDialog(null, "Ingrese un DNI valido");
                }else{
                    try {
                        paciente = instance.buscar(Integer.parseInt(Pacientetext.getText()));
                        if (paciente == null){
                            JOptionPane.showMessageDialog(null, "No se encontro paciente");
                        }else{
                            panel.mostrar(new PacienteVerTurnos(panel,paciente));
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al buscar paciente");
                    }
                }

            }
        });
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new FormularioInicio(panel));
            }
        });
    }
}
