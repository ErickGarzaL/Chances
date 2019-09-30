/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.visualización;

import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import ed.visualización.demos.Demo;

/**
 *
 * @author veronica
 */
public class ToggleDemosActionListener implements ChangeListener {
    private Visor visor;
    private JPanel panelBotones;
    //private JPanel panelb;
    private JToggleButton menú;
    private Demo demo;
    
    public ToggleDemosActionListener(Visor visor,
            JPanel panelBotones,
            JPanel panelb,
            JToggleButton menú,
            Demo demo) {
        this.visor = visor;
        this.panelBotones = panelBotones;
        //this.panelb = panelb;
        this.menú = menú;
        this.demo = demo;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(menú.isSelected()){
            visor.setDemo(demo);
            panelBotones.setVisible(true);
        } else {
            panelBotones.setVisible(false);
        }
    }
}
