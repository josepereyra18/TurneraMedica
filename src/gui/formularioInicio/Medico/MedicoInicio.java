package gui.formularioInicio.Medico;

import Service.serviceMedico;
import entidades.Medico;
import gui.PanelManager;
import gui.formularioInicio.FormularioInicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedicoInicio extends JPanel {
    PanelManager panel;

    JPanel panelInicio;
    JPanel panelAtras;

    JLabel Medico;
    JTextField Medicotext;

    JButton btnBuscar;
    JButton btnAtras;

    serviceMedico instance;



    public MedicoInicio(PanelManager panel) {
        this.panel = panel;
        instance = new serviceMedico();
        setLayout(new GridBagLayout());
        armarFormulario();
    }


    public void armarFormulario(){
        panelInicio = new JPanel();
        panelAtras = new JPanel();
        panelInicio.setLayout(new GridLayout(3,1, 10, 10));

        Medico = new JLabel("Ingrese su ID:");
        Medicotext = new JTextField(7);
        btnBuscar = new JButton("Buscar");
        btnAtras = new JButton("<-");


        panelInicio.add(Medico);
        panelInicio.add(Medicotext);
        panelInicio.add(btnBuscar);
        panelAtras.add(btnAtras);

        GridBagConstraints gbcScroll = new GridBagConstraints();
        GridBagConstraints gbcModificar = new GridBagConstraints();
        gbcModificar.gridx = 0;
        gbcModificar.gridy = 1;
        gbcModificar.weightx = 1;
        gbcModificar.weighty = 1;
        gbcModificar.fill = GridBagConstraints.NONE;
        add(panelInicio, gbcModificar);
        setPreferredSize(new Dimension(300, 200));

        GridBagConstraints gbcAtras = new GridBagConstraints();
        gbcAtras.gridx = 0;
        gbcAtras.gridy = 0;
        gbcAtras.anchor = GridBagConstraints.NORTHWEST;
        add(panelAtras, gbcAtras);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Id = Medicotext.getText();
                Medico medico = new Medico();
                if (!Id.matches("[0-9]+")){
                    JOptionPane.showMessageDialog(null, "Ingrese un ID valido");
                }else{
                    try {
                        medico = instance.buscar(Integer.parseInt(Medicotext.getText()));
                        if (medico == null){
                            JOptionPane.showMessageDialog(null, "No se encontro medico");
                        }else{
                            panel.mostrar(new MedicoVerTurnos(panel,medico));
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al buscar medico");
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
