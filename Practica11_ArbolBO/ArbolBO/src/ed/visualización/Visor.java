/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos, pero no está permitido
 * entregarlo tal cual a los estudiantes.
 */

package ed.visualización;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import org.apache.batik.swing.JSVGCanvas;
import ed.visualización.demos.*;

/**
 * Ventana para mostrar visualmente el estado de las estructuras de datos.
 * 
 * Documentación de batik:
 * http://xmlgraphics.apache.org/batik/javadoc/
 * @author Vero
 */
public class Visor implements ActionListener {
    private enum DEMO {
        ÁRBOLES_BINARIOS_ORDENADOS,
        //ÁRBOLES_AVL,
        //ÁRBOLES_ROJINEGROS
    }
    
    private JFrame marco;
    private JPanel panel;
    private JSVGCanvas svgCanvas = new JSVGCanvas();
    private HashMap<DEMO, Demo> demos = new HashMap<>();
    private Demo demo;
    
    public Visor() {
        // Demos
        demo = new DemoÁrbolesBinariosOrdenados();
        demos.put(DEMO.ÁRBOLES_BINARIOS_ORDENADOS, demo);
        //demo = new DemoÁrbolesAVL();
        //demos.put(DEMO.ÁRBOLES_AVL, demo);
        //demo = new DemoÁrbolesRojinegros();
        //demos.put(DEMO.ÁRBOLES_ROJINEGROS, demo);
        
        // Interfaz
        svgCanvas.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
        
        marco = new JFrame("Visor de estructuras");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        creaComponentes();
        creaMenús();
        marco.setSize(1200,600);
        marco.setVisible(true);
    }
    
    private void creaComponentes() {
        panel = new JPanel(new BorderLayout());
        panel.add("Center", svgCanvas);
        marco.getContentPane().add(panel);
    }
    
    private void creaMenús() {
        // Poner los botones al lado del marco.
        JPanel panelb = new JPanel();
        panelb.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        // Group the toggle buttons.
        ButtonGroup grupoTiposDemos = new ButtonGroup();
        JToggleButton menú;
        
        for(DEMO d : DEMO.values()) {
            menú = new JToggleButton(d.toString());
            grupoTiposDemos.add(menú);
            panelb.add(menú,c);
            
            // Panel para sus botones.
            JPanel panelBotones = new JPanel();
            panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
            
            // Group the radio buttons.
            ButtonGroup grupo = new ButtonGroup();

            // Botón
            JRadioButton botón;

            Method[] métodos = demos.get(d).getClass().getDeclaredMethods();
            for(Method método : métodos) {
                if(método.isAnnotationPresent(DemoMethod.class)) {
                    DemoMethod dm = método.getAnnotation(DemoMethod.class);
                    botón = new JRadioButton(dm.name());
                    botón.setActionCommand(método.getName());
                    botón.addActionListener(this);
                    grupo.add(botón);        
                    panelBotones.add(botón);
                }
            }
            panelb.add(panelBotones,c);
            panelBotones.setVisible(false);
            
            menú.addChangeListener(new ToggleDemosActionListener(this, panelBotones, panelb, menú, demos.get(d)));
        }
        // Fill space at bottom of side menu.
        c.weighty = 1;
        panelb.add(new JLabel(), c);
        // Add menu to panel.
        panel.add(panelb, BorderLayout.WEST);
    }
    
    public void setDemo(Demo demo) {
        this.demo = demo;
    }
    
    public static void main(String[] args) {
        Visor v = new Visor();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            svgCanvas.setURI((String) demo.getClass().getMethod(ae.getActionCommand()).invoke(demo));
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Visor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
