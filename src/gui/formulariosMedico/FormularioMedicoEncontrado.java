package gui.formulariosMedico;

import Service.serviceExeption;
import Service.serviceMedico;
import entidades.Medico;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioMedicoEncontrado extends JPanel {

    JPanel panelBotones;
    JPanel panelAtras;
    JLabel nombre;
    JLabel apellido;
    JLabel dni;
    JLabel precioConsulta;

    JPanel panelMedicoEncontrado;

    PanelManager panel;

    serviceMedico instance;

    JButton botonEliminar;
    JButton botonModificar;

    JButton botonAtras;





    public FormularioMedicoEncontrado(PanelManager panel, Medico medico){
        this.panel = panel;
        instance = new serviceMedico();
        setLayout(new GridBagLayout());
        armarFormulario(medico);
    }

    public  void armarFormulario (Medico medico){
        panelMedicoEncontrado = new JPanel();
        panelBotones = new JPanel();
        panelAtras = new JPanel();

        panelMedicoEncontrado.setLayout(new GridLayout(4,1));
        nombre= new JLabel("Nombre: " + medico.getNombre());
        apellido= new JLabel("Apellido: " + medico.getApellido());
        dni= new JLabel("DNI: " + medico.getId());
        precioConsulta= new JLabel("Precio Consulta: " + medico.getPrecioConsulta());


        panelBotones.setLayout(new GridLayout(1,2));
        botonModificar = new JButton("Modificar");
        botonEliminar = new JButton("Eliminar");
        panelBotones.add(botonModificar);
        panelBotones.add(botonEliminar);


        botonAtras = new JButton("<-");
        panelAtras.add(botonAtras);



        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new FormularioMedicoBuscar(panel));
            }
        });

        botonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new FormularioMedicoModificar(panel, medico));
            }
        });

        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resultado= JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar el medico?", "Eliminar Medico", JOptionPane.YES_NO_OPTION);
               if (resultado == JOptionPane.YES_OPTION){
                   int dni = medico.getId();
                   try {
                       instance.eliminar(dni);
                       JOptionPane.showMessageDialog(null, "Se elimino correctamente");
                       panel.mostrar(new FormularioMedicoInicio(panel));
                   } catch (serviceExeption ex) {
                       JOptionPane.showMessageDialog(null, "No se pudo eliminar");
                   }
               }
            }
        });





        panelMedicoEncontrado.add(nombre);
        panelMedicoEncontrado.add(apellido);
        panelMedicoEncontrado.add(dni);
        panelMedicoEncontrado.add(precioConsulta);

        GridBagConstraints gbcInfo = new GridBagConstraints();
        gbcInfo.gridx = 1;
        gbcInfo.gridy = 1;
        gbcInfo.weightx = 1;
        gbcInfo.weighty = 1;
        gbcInfo.fill = GridBagConstraints.NONE;
        add(panelMedicoEncontrado, gbcInfo);

        GridBagConstraints gbcBotones = new GridBagConstraints();
        gbcBotones.gridx = 1;
        gbcBotones.gridy = 2;
        gbcBotones.weightx = 1;
        gbcBotones.weighty = 1;
        gbcBotones.fill = GridBagConstraints.NONE;
        add(panelBotones, gbcBotones);

        GridBagConstraints gbcAtras = new GridBagConstraints();
        gbcAtras.gridx = 1;
        gbcAtras.gridy = 0;
        gbcAtras.anchor = GridBagConstraints.NORTHWEST;
        add(panelAtras, gbcAtras);

    }

}
