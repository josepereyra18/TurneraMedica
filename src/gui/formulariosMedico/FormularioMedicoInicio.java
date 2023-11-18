package gui.formulariosMedico;

import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioMedicoInicio extends JPanel {

    JPanel panelInicio;
    JButton botonAgregar;
    JButton botonBuscar;
    PanelManager panel;

    public FormularioMedicoInicio(PanelManager panel) {
        this.panel = panel;
        setLayout(new GridBagLayout());
        armarFormulario();
    }

    public void armarFormulario() {
        panelInicio = new JPanel();
        botonAgregar = new JButton("Agregar");
        botonBuscar = new JButton("Buscar");
        panelInicio.add(this.botonAgregar);
        panelInicio.add(this.botonBuscar);



        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new FormularioMedicoAgregar(panel));
            }
        });

        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new FormularioMedicoBuscar(panel));
            }
        });


        add(this.panelInicio);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(panelInicio, gbc);
    }






}
