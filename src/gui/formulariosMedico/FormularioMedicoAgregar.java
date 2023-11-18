package gui.formulariosMedico;

import Service.serviceExeption;
import Service.serviceMedico;
import entidades.Medico;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioMedicoAgregar extends JPanel {

    JPanel panelAgregar;
    JPanel panelSec;
    PanelManager panel;


    JButton botonAtras;


    JLabel nombre;
    JTextField nombreText;

    JLabel apellido;
    JTextField apellidoText;

    JLabel dni;
    JTextField dniText;

    JLabel precioConsulta;
    JTextField precioConsultaText;

    JButton botonAgregar;

    serviceMedico instance;


    public FormularioMedicoAgregar (PanelManager panel){
        this.panel = panel;
        instance = new serviceMedico();
        setLayout(new GridBagLayout()); // Establecer GridBagLayout para el panel principal
        armarFormulario();
    }

    public void armarFormulario(){

        panelAgregar = new JPanel();
        panelSec = new JPanel();
        panelAgregar.setLayout(new GridLayout(5,2, 5, 5));

        botonAtras = new JButton("<-");
        botonAtras.setPreferredSize(new Dimension(50, 25));

        nombre = new JLabel("Nombre");
        nombreText = new JTextField(7);

        apellido = new JLabel("Apellido");
        apellidoText = new JTextField(7);

        dni = new JLabel("DNI");
        dniText = new JTextField(7);

        precioConsulta = new JLabel("Precio Consulta");
        precioConsultaText = new JTextField(7);

        botonAgregar = new JButton("Agregar");


        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String nombre = nombreText.getText();
                    String apellido = apellidoText.getText();
                    String dni = dniText.getText();
                    String precioConsulta = precioConsultaText.getText();

                    if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos");
                    } else if (!nombre.matches("[a-zA-Z]+")) {
                        JOptionPane.showMessageDialog(null, "El nombre solo puede contener letras");
                    } else if (!apellido.matches("[a-zA-Z]+")) {
                        JOptionPane.showMessageDialog(null, "El apellido solo puede contener letras");
                    } else if (!dni.matches("[0-9]+")) {
                        JOptionPane.showMessageDialog(null, "El DNI solo puede contener números");
                    } else if (!precioConsulta.matches("[0-9]*\\.?[0-9]+")){
                        JOptionPane.showMessageDialog(null, "El precio de la consulta debe ser un número. Recuerda que los numeros decimales van con punto");
                    }else {
                        Medico medico = new Medico();
                        medico.setNombre(nombre);
                        medico.setApellido(apellido);
                        medico.setId(Integer.parseInt(dni));
                        medico.setPrecioConsulta(Double.parseDouble(precioConsulta));
                        try{
                            instance.guardar(medico);
                        }catch (serviceExeption s){
                            JOptionPane.showMessageDialog(null, "No se pudo agregar el medico");
                        }
                        String mensaje = "Se agregó el medico con exito. Datos: \n" +
                                "Nombre: " + nombre + "\n" +
                                "Apellido: " + apellido + "\n" +
                                "DNI: " + dni+ "\n" +
                                "Precio Consulta: " + precioConsulta;
                        JOptionPane.showMessageDialog(null, mensaje);
                        nombreText.setText("");
                        apellidoText.setText("");
                        dniText.setText("");
                        precioConsultaText.setText("");
                    }
            }
        });

        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new FormularioMedicoInicio(panel));
            }
        });


        //botonAgregar.addActionListener(e -> {
            //try {
            //    panel.getServiceMedico().agregarMedico(nombreText.getText(), apellidoText.getText(), dniText.getText());
            //    JOptionPane.showMessageDialog(null, "Medico agregado con exito");
           // } catch (Exception exception) {
            //    JOptionPane.showMessageDialog(null, exception.getMessage());
            //}
        //});
        panelAgregar.add(nombre);
        panelAgregar.add(nombreText);
        panelAgregar.add(apellido);
        panelAgregar.add(apellidoText);
        panelAgregar.add(dni);
        panelAgregar.add(dniText);
        panelAgregar.add(precioConsulta);
        panelAgregar.add(precioConsultaText);
        panelAgregar.add(botonAgregar);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(panelAgregar, gbc);

        GridBagConstraints gbcSec = new GridBagConstraints();
        gbcSec.gridx = 0;
        gbcSec.gridy = 0;
        gbcSec.weightx = 0;
        gbcSec.weighty =0;
        gbcSec.fill = GridBagConstraints.NONE;
        add(panelSec, gbcSec);

        panelSec.setLayout(new BorderLayout());
        panelSec.setPreferredSize(new Dimension(50,25));
        panelSec.add(botonAtras, BorderLayout.WEST);


    }

}
