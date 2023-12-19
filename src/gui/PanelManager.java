package gui;

import gui.formularioInicio.FormularioInicio;

import javax.swing.*;
import java.awt.*;

public class PanelManager {

    private FormularioInicio formularioInicio;

    JFrame jFrame;

    public PanelManager(){
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("Inicio");

        formularioInicio = new FormularioInicio(this);
        mostrar(formularioInicio);

    }
    public void setJFrameTitle(String title) {
        jFrame.setTitle(title);
    }

        public void mostrar(JPanel panel){
        jFrame.getContentPane().removeAll();
        jFrame.getContentPane().add(BorderLayout.CENTER,panel);
        jFrame.getContentPane().validate();
        jFrame.getContentPane().repaint();
        jFrame.setVisible(true);
        jFrame.pack();
        setBounds(400,400);
        //jFrame.setLocation(600,200);
    }

    public void setBounds (int width, int height) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (screenSize.getWidth() / 2 - width / 2);
        int y = (int) (screenSize.getHeight() / 2 -  height / 2);
        jFrame.setBounds(x, y, width, height);
    }


}
