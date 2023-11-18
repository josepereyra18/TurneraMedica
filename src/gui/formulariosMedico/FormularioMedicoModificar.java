package gui.formulariosMedico;

import Service.serviceExeption;
import Service.serviceMedico;
import entidades.Medico;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioMedicoModificar  extends JPanel {

    JPanel panelModificar;
    JPanel panelAtras;

    PanelManager panel;
    JLabel nombre;
    JTextField nombreText;
    JLabel apellido;
    JTextField apellidoText;

    JLabel precioConsulta;
    JTextField precioConsultaText;

    JButton botonModificar;
    JButton botonAtras;

    serviceMedico instance;

    public FormularioMedicoModificar(PanelManager panel, Medico medico) {
        this.panel = panel;
        instance = new serviceMedico();
        setLayout(new GridBagLayout());
        armarFormulario(medico);
    }

    public void armarFormulario(Medico medico){
        panelModificar = new JPanel();
        panelAtras = new JPanel();
        panelAtras.setLayout(new GridBagLayout());
        panelModificar.setLayout(new GridLayout(4,2, 5, 5));

        nombre = new JLabel("Nombre");
        nombreText = new JTextField(7);
        nombreText.setText(medico.getNombre());

        apellido = new JLabel("Apellido");
        apellidoText = new JTextField(7);
        apellidoText.setText(medico.getApellido());

        precioConsulta = new JLabel("Precio Consulta");
        precioConsultaText = new JTextField(7);
        precioConsultaText.setText(String.valueOf(medico.getPrecioConsulta()));

        botonModificar = new JButton("Modificar");

        botonAtras = new JButton("<-");
        panelAtras.add(botonAtras);

        panelModificar.add(nombre);
        panelModificar.add(nombreText);
        panelModificar.add(apellido);
        panelModificar.add(apellidoText);
        panelModificar.add(precioConsulta);
        panelModificar.add(precioConsultaText);
        panelModificar.add(botonModificar);

        GridBagConstraints gbcModificar = new GridBagConstraints();
        gbcModificar.gridx = 0;
        gbcModificar.gridy = 1;
        gbcModificar.weightx = 1;
        gbcModificar.weighty = 1;
        gbcModificar.fill = GridBagConstraints.NONE;
        add(panelModificar, gbcModificar);
        setPreferredSize(new Dimension(300, 200));

        GridBagConstraints gbcAtras = new GridBagConstraints();
        gbcAtras.gridx = 0;
        gbcAtras.gridy = 0;
        gbcAtras.anchor = GridBagConstraints.NORTHWEST;
        add(panelAtras, gbcAtras);

        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(new FormularioMedicoEncontrado(panel, medico));
            }
        });

        botonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreText.getText();
                String apellido = apellidoText.getText();
                String precioConsulta = precioConsultaText.getText();

                if (nombre.isEmpty() || apellido.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos");
                } else if (!nombre.matches("[a-zA-Z]+")) {
                    JOptionPane.showMessageDialog(null, "El nombre solo puede contener letras");
                } else if (!apellido.matches("[a-zA-Z]+")) {
                    JOptionPane.showMessageDialog(null, "El apellido solo puede contener letras");
                } else if (!precioConsulta.matches("[0-9]*\\.?[0-9]+")){
                    JOptionPane.showMessageDialog(null, "El precio de la consulta debe ser un número. Recuerda que los numeros decimales van con punto");
                }else {
                    Medico medicoNuevo= new Medico();
                    medicoNuevo.setId(medico.getId());
                    medicoNuevo.setNombre(nombre);
                    medicoNuevo.setApellido(apellido);
                    medicoNuevo.setPrecioConsulta(Double.parseDouble(precioConsulta));

                    try{
                        instance.modificar(medicoNuevo);
                        panel.mostrar(new FormularioMedicoBuscar(panel));
                    }catch (serviceExeption s){
                        JOptionPane.showMessageDialog(null, "No se pudo modificar el medico");
                    }
                    String mensaje = "Se modificó el medico con exito. \n Nuevos datos: \n" +
                            "Nombre: " + nombre + "\n" +
                            "Apellido: " + apellido + "\n" +
                            "DNI: " + medico.getId()+ "\n" +
                            "Precio Consulta: " + precioConsulta;
                    JOptionPane.showMessageDialog(null, mensaje);
                }
            }
        });
    }
}