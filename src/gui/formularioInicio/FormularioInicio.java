package gui.formularioInicio;

import com.toedter.calendar.JCalendar;
import gui.PanelManager;
import gui.formularioInicio.Administracion.formularioAministracion;
import gui.formularioInicio.Administracion.formularioPaciente.FormularioPacienteInicio;
import gui.formularioInicio.Administracion.formulariosMedico.FormularioMedicoInicio;
import gui.formularioInicio.Medico.MedicoInicio;
import gui.formularioInicio.Paciente.PacienteInicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioInicio extends JPanel {

        PanelManager panel;
        JPanel panelInicio;
        JButton botonMedico;
        JButton botonPaciente;
        JButton botonAdministrador;

        public FormularioInicio(PanelManager panel) {
            this.panel = panel;
            setLayout(new GridBagLayout());
            armarFormulario();
        }

        public void armarFormulario() {
            panelInicio = new JPanel();
            panelInicio.setLayout(new GridLayout(3,1, 10, 10 ));
            botonMedico = new JButton("Medico");
            botonPaciente = new JButton("Paciente");
            botonAdministrador = new JButton("Administrador");

            panelInicio.add(this.botonMedico);
            panelInicio.add(this.botonPaciente);
            panelInicio.add(this.botonAdministrador);

            add(this.panelInicio);

            botonMedico.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.mostrar(new MedicoInicio(panel));
                    panel.setJFrameTitle("Medico");
                }
            });

            botonPaciente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.mostrar(new PacienteInicio(panel));
                    panel.setJFrameTitle("Paciente");
                }
            });

            botonAdministrador.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.mostrar(new formularioAministracion(panel));
                    panel.setJFrameTitle("Administracion");
                }
            });


        }

}
