package gui.formulariosMedico;

import Service.serviceExeption;
import Service.serviceMedico;
import entidades.Medico;
import gui.PanelManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormularioMedicoBuscar extends JPanel {

    JPanel panelBuscar;
    JPanel panelAtras;

    PanelManager panel;
    serviceMedico instance;

    JLabel dni;
    JTextField dniText;
    JButton botonBuscar;
    JButton botonAtras;

    JButton BuscarTodos;


    public FormularioMedicoBuscar(PanelManager panel){
        this.panel = panel;
        instance = new serviceMedico();
        setLayout(new GridBagLayout());
        armarFormulario();
    }

    public void armarFormulario(){
        panelBuscar = new JPanel();
        panelAtras = new JPanel();
        panelAtras.setLayout(new GridBagLayout());
        panelBuscar.setLayout(new GridBagLayout());



        dni = new JLabel("Ingrese DNI");
        dniText = new JTextField(7);
        botonBuscar = new JButton("Buscar");
        BuscarTodos = new JButton("Buscar Todos");
        botonAtras = new JButton("<-");

        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 0;
        gbcLabel.weightx = 1;
        gbcLabel.weighty = 1;
        gbcLabel.fill = GridBagConstraints.NONE;
        panelBuscar.add(dni, gbcLabel);

        GridBagConstraints gbcTxt= new GridBagConstraints();
        gbcTxt.gridx = 1;
        gbcTxt.gridy = 0;
        gbcTxt.weightx = 1;
        gbcTxt.weighty = 1;
        gbcTxt.fill = GridBagConstraints.NONE;
        panelBuscar.add(dniText);

        GridBagConstraints gbcBotonBuscar = new GridBagConstraints();
        gbcBotonBuscar.gridx = 1;
        gbcBotonBuscar.gridy = 1;
        gbcBotonBuscar.weightx = 2;
        gbcBotonBuscar.weighty = 2;
        gbcBotonBuscar.fill = GridBagConstraints.NONE;
        panelBuscar.add(botonBuscar,gbcBotonBuscar);

        GridBagConstraints gbcBotonBuscarTodos = new GridBagConstraints();
        gbcBotonBuscarTodos.gridx = 1;
        gbcBotonBuscarTodos.gridy = 2;
        gbcBotonBuscarTodos.weightx = 2;
        gbcBotonBuscarTodos.weighty = 2;
        gbcBotonBuscarTodos.fill = GridBagConstraints.NONE;
        panelBuscar.add(BuscarTodos,gbcBotonBuscarTodos);

        GridBagConstraints gbcBuscar = new GridBagConstraints();
        gbcBuscar.gridx = 0;
        gbcBuscar.gridy = 1;
        gbcBuscar.weightx = 1;
        gbcBuscar.weighty = 1;
        gbcBuscar.fill = GridBagConstraints.NONE;
        add(panelBuscar, gbcBuscar);

        GridBagConstraints gbcAtras = new GridBagConstraints();
        gbcAtras.gridx = 0;
        gbcAtras.gridy = 0;
        gbcAtras.anchor = GridBagConstraints.NORTHWEST;
        add(botonAtras, gbcAtras);


        botonBuscar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        Medico medico = new Medico();
        String dni = dniText.getText();
        if (dni.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos");
        } else if (!dni.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null, "El dni solo puede contener numeros");
        } else {
            try {
                medico = instance.buscar(Integer.parseInt(dniText.getText()));
                if (medico == null) {
                    JOptionPane.showMessageDialog(null, "No se encontro el medico solicitado");
                }else{
                    panel.mostrar(new FormularioMedicoEncontrado(panel, medico));
                }
            } catch (serviceExeption s) {
                JOptionPane.showMessageDialog(null, s.getMessage());
            }

        }
    }
});
        BuscarTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Medico> medicos = new ArrayList<>();
                    try {
                        medicos = instance.buscarTodos();
                        panel.mostrar(new FormularioListadoMedicos(panel, medicos));
                    } catch (serviceExeption ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
        });

        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new FormularioMedicoInicio(panel));
            }
        });

    }
}
