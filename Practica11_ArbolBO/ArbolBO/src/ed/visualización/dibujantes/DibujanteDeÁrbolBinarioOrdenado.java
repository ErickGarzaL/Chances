/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo tal cual a estudiantes actuales o potenciales.
 */
package ed.visualización.dibujantes;

import ed.estructuras.nolineales.NodoBinario;
import ed.estructuras.nolineales.ÁrbolBinarioOrdenado;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

/**
 *
 * @author Vero
 */
public class DibujanteDeÁrbolBinarioOrdenado implements Dibujante{
    
    protected ÁrbolBinarioOrdenado<?> árbol;
    protected final int TAM_NODO = 60;
    
    public DibujanteDeÁrbolBinarioOrdenado() {}

    @Override
    public String drawSVG() {
        File archivo = new File("data/ArbolBinarioOrdenado.svg");
        String línea = "<?xml version=\"1.0\" standalone=\"no\"?>";
        try {
            BufferedWriter escriba = new BufferedWriter(new FileWriter(archivo));
            escriba.write(línea); escriba.newLine();
            línea = "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.0//EN\" \"http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd\">";
            escriba.write(línea); escriba.newLine();
            escribeÁrbol(escriba);
            escriba.close();
        } catch(IOException io) {
            throw new RuntimeException("Error al escribir el archivo svg:" + io);
        }
        return archivo.toURI().toString();
    }
    
    private void escribeÁrbol(BufferedWriter escriba) throws IOException {
        int altura;
        String línea;
        if(árbol == null) {
            línea = "<svg width=\"500\" height=\"500\">"
                    + "<text x=\"100\" y=\"15\" fill=\"red\">No hay árbol</text>"
                    + "</svg>";
            escriba.write(línea); escriba.newLine();
            return;
        } else if((altura = árbol.getAltura()) < 0) {
            línea = "<svg width=\"500\" height=\"500\">"
                    + "<text x=\"100\" y=\"15\" fill=\"blue\">Árbol vacío</text>"
                    + "</svg>";
            escriba.write(línea); escriba.newLine();
            return;
        }
        int ancho = TAM_NODO * (2 << altura);
        int alto = TAM_NODO * (altura + 1);
        línea = "<svg width=\"" + ancho + "\" height=\"" + alto + "\">";
        escriba.write(línea); escriba.newLine();
        
        línea = "<defs>\n" +
            "    <linearGradient id=\"grad1\" x1=\"0%\" y1=\"0%\" x2=\"0%\" y2=\"100%\">\n" +
            "      <stop offset=\"0%\" style=\"stop-color:rgb(255,255,255);stop-opacity:1\" />\n" +
            "      <stop offset=\"100%\" style=\"stop-color:rgb(0,100,255);stop-opacity:1\" />\n" +
            "    </linearGradient>\n" +
            "    <linearGradient id=\"grad-texto\" x1=\"0%\" y1=\"0%\" x2=\"0%\" y2=\"100%\">\n" +
            "      <stop offset=\"0%\" style=\"stop-color:black;stop-opacity:1\" />\n" +
            "      <stop offset=\"100%\" style=\"stop-color:rgb(100,100,100);stop-opacity:1\" />\n" +
            "    </linearGradient>\n" +
            "  </defs>";
        escriba.write(línea); escriba.newLine();
        
        escribeNodo(árbol.getRaíz(), 0, ancho, TAM_NODO, escriba);
        escriba.write("</svg>"); escriba.newLine();
    }
    
    
    protected void escribeNodo(NodoBinario<?> nb, int x0, int x1, int y, BufferedWriter escriba) throws IOException{
        int x = (x1 + x0) / 2;
        String línea;
        
        // Aristas
        int hx;
        if(nb.getHijoI() != null) {
            hx = ((x0 + (x1 - x0)/2) + x0) / 2;
            línea = "<line x1=\"" + x + "\" y1=\"" + y + "\" x2=\"" +
                    hx + "\" y2=\"" + (y + TAM_NODO) + "\" style=\"stroke:black;stroke-width:2\" />";
            escriba.write(línea); escriba.newLine();
        }
        if(nb.getHijoD() != null) {
            hx = (x1 + (x0 + (x1 - x0)/2) + 1) / 2;
            línea = "<line x1=\"" + x + "\" y1=\"" + y + "\" x2=\"" +
                    hx + "\" y2=\"" + (y + TAM_NODO) + "\" style=\"stroke:black;stroke-width:2\" />";
            escriba.write(línea); escriba.newLine();
        }
        
        // Nodo
        línea = "<g transform=\"translate(" + x + "," + y + ")\"><circle cx=\"0\" cy=\"0\" r=\"20\" stroke=\"black\" stroke-width=\"3\" style=\"fill:url(#grad1)\">";
        escriba.write(línea); escriba.newLine();
        
        escriba.write("</circle>"); escriba.newLine();
        línea = "<text x=\"-7\" y=\"7\" style=\"fill:url(#grad-texto)\" font-size=\"20\">" + nb.getElemento() + "</text>";
        escriba.write(línea); escriba.newLine();
        línea = "<text x=\"-14\" y=\"35\" style=\"fill:black\" font-size=\"15\">h=" + nb.getAltura() + "</text></g>";
        escriba.write(línea); escriba.newLine();
        
        // Hijos
        if(nb.getHijoI() != null) {
            escribeNodo(nb.getHijoI(), x0, x0 + (x1 - x0)/2, y + TAM_NODO, escriba);
        }
        if(nb.getHijoD() != null) {
            escribeNodo(nb.getHijoD(), x0 + (x1 - x0)/2 + 1, x1, y + TAM_NODO, escriba);
        }
    }

    /**
     * Asigna el <code>ÁrbolBinarioOrdenado</code> a dibujar.
     * @param c 
     */
    @Override
    public void setEstructura(Collection<?> c) {
        this.árbol = (ÁrbolBinarioOrdenado<?>)c;
    }
}
