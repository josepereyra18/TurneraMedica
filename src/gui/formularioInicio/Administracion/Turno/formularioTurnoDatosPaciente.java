package gui.formularioInicio.Administracion.Turno;

import Service.serviceExeption;
import Service.servicePaciente;
import entidades.Paciente;
import gui.PanelManager;
import gui.formularioInicio.Administracion.formularioPaciente.FormularioPacienteAgregar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class formularioTurnoDatosPaciente extends JPanel {

    PanelManager panel;
    JPanel panelInicio;
    JPanel panelAtras;
    JLabel DniPaciente;
    JTextField DniPacienteText;
    JButton btnBuscar;
    JButton btnAtras;
    servicePaciente instance;

    public formularioTurnoDatosPaciente(PanelManager panel) {
        this.panel = panel;
        instance = new servicePaciente();
        setLayout(new GridBagLayout());
        armarFormulario();
    }

    public void armarFormulario() {
        JPanel panelInicio = new JPanel();
        JPanel panelAtras = new JPanel();
        panelInicio.setLayout(new GridLayout(1,2));
        DniPaciente = new JLabel("DNI Paciente");
        DniPacienteText = new JTextField(10);
        btnBuscar = new JButton("Buscar");
        btnAtras = new JButton("<-");

        panelInicio.add(DniPaciente);
        panelInicio.add(DniPacienteText);
        panelInicio.add(btnBuscar);
        panelAtras.add(btnAtras);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(panelAtras,gbc);

        GridBagConstraints gbcBotones = new GridBagConstraints();
        gbcBotones.gridx = 0;
        gbcBotones.gridy = 0;
        gbcBotones.weightx = 1;
        gbcBotones.weighty = 1;
        gbcBotones.fill = GridBagConstraints.NONE;
        add(panelInicio, gbcBotones);

        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new formularioTurnoIinicio(panel));
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paciente paciente = new Paciente();
                String dni = DniPacienteText.getText();
                if (dni.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Ingrese un DNI");
                } else if (!dni.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "El DNI debe ser numerico");
                }else{
                    try{
                        paciente = instance.buscar(Integer.parseInt(DniPacienteText.getText()));
                        if (paciente == null) {
                            int resultado= JOptionPane.showConfirmDialog(null, "No se encontro el paciente.\n¿Quiere Agregarlo?");
                            if (resultado == JOptionPane.YES_OPTION){
                                panel.mostrar(new FormularioPacienteAgregar(panel));
                            }
                        }else{
                            panel.mostrar(new formularioTurnoAgendarxMedico(panel, paciente));
                        }
                    } catch (serviceExeption s) {
                            JOptionPane.showMessageDialog(null, "Ocurrio un error con la base de datos");
                    }
                }
            }
        });


    }

}
