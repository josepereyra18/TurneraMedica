package gui.formularioInicio.Administracion.formulariosMedico;

import Service.serviceExeption;
import Service.serviceMedico;
import entidades.Medico;
import entidades.ObraSocial;
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

    ObraSocial obraSocial;

    JLabel ObraSocial;
    JComboBox combo;
    JButton botonModificar;
    JButton botonAtras;

    serviceMedico instance;

    public FormularioMedicoModificar(PanelManager panel, Medico medico) {
        this.panel = panel;
        instance = new serviceMedico();
        obraSocial = new ObraSocial();
        setLayout(new GridBagLayout());
        armarFormulario(medico);
    }

    public void armarFormulario(Medico medico){
        panelModificar = new JPanel();
        panelAtras = new JPanel();
        panelAtras.setLayout(new GridBagLayout());
        panelModificar.setLayout(new GridLayout(6,2, 5, 5));

        nombre = new JLabel("Nombre");
        nombreText = new JTextField(7);
        nombreText.setText(medico.getNombre());

        apellido = new JLabel("Apellido");
        apellidoText = new JTextField(7);
        apellidoText.setText(medico.getApellido());

        precioConsulta = new JLabel("Precio Consulta");
        precioConsultaText = new JTextField(7);
        precioConsultaText.setText(String.valueOf(medico.getPrecioConsulta()));


        ObraSocial = new JLabel("Obra Social");
        combo = new JComboBox(obraSocial.getObrasSociales());
        combo.setSelectedItem(medico.getObraSocial());

        botonModificar = new JButton("Modificar");

        botonAtras = new JButton("<-");
        panelAtras.add(botonAtras);

        panelModificar.add(nombre);
        panelModificar.add(nombreText);
        panelModificar.add(apellido);
        panelModificar.add(apellidoText);
        panelModificar.add(precioConsulta);
        panelModificar.add(ObraSocial);
        panelModificar.add(combo);
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
                String obraSocial = combo.getItemAt(combo.getSelectedIndex()).toString();

                if (nombre.isEmpty() || apellido.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos");
                } else if (!nombre.matches("[a-zA-Z]+")) {
                    JOptionPane.showMessageDialog(null, "El nombre solo puede contener letras");
                } else if (!apellido.matches("[a-zA-Z]+")) {
                    JOptionPane.showMessageDialog(null, "El apellido solo puede contener letras");
                } else if (!precioConsulta.matches("[0-9]*\\.?[0-9]+")){
                    JOptionPane.showMessageDialog(null, "El precio de la consulta debe ser un número. Recuerda que los numeros decimales van con punto");
                }else if(combo.getSelectedIndex()==0){
                    JOptionPane.showMessageDialog(null, "Debe seleccionarse una obra social");
                }else {
                    Medico medicoNuevo= new Medico();
                    medicoNuevo.setId(medico.getId());
                    medicoNuevo.setNombre(nombre);
                    medicoNuevo.setApellido(apellido);
                    medicoNuevo.setPrecioConsulta(Double.parseDouble(precioConsulta));
                    medicoNuevo.setObraSocial(obraSocial);

                    try{
                        instance.modificar(medicoNuevo);
                        panel.mostrar(new FormularioMedicoBuscar(panel));
                        String mensaje = "Se modificó el medico con exito. \n Nuevos datos: \n" +
                                "Nombre: " + nombre + "\n" +
                                "Apellido: " + apellido + "\n" +
                                "DNI: " + medico.getId()+ "\n" +
                                "Precio Consulta: " + precioConsulta+
                                "Obra Social: " + obraSocial + "\n";
                        JOptionPane.showMessageDialog(null, mensaje);
                    }catch (serviceExeption s){
                        JOptionPane.showMessageDialog(null, "No se pudo modificar el medico");
                    }
                }
            }
        });
    }
}