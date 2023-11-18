package gui;

import gui.formulariosMedico.FormularioMedicoInicio;

import javax.swing.*;
import java.awt.*;

public class PanelManager {
    private FormularioMedicoInicio formularioMedicoInicio;

    JFrame jFrame;

    public PanelManager(){
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.setTitle("Sistema de gestión de médicos");

        formularioMedicoInicio = new FormularioMedicoInicio(this);
        mostrar(formularioMedicoInicio);

    }

        public void mostrar(JPanel panel){
        jFrame.getContentPane().removeAll();
        jFrame.getContentPane().add(BorderLayout.CENTER,panel);
        jFrame.getContentPane().validate();
        jFrame.getContentPane().repaint();
        jFrame.setVisible(true);
        jFrame.pack();
        jFrame.setBounds(600,200,400,400);
        //jFrame.setLocation(600,200);
    }

}
