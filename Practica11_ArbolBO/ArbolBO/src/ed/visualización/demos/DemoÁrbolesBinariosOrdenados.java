/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal.
 */

package ed.visualización.demos;

import ed.estructuras.nolineales.ÁrbolBinarioOrdenado;
import ed.visualización.dibujantes.DibujanteDeÁrbolBinarioOrdenado;

/**
 *
 * @author Vero
 */
public class DemoÁrbolesBinariosOrdenados extends Demo {
    private DibujanteDeÁrbolBinarioOrdenado dibujante;
	
    private ÁrbolBinarioOrdenado<String> creaÁrbol() {
        return new ÁrbolAVL<>();
    }
    
    @DemoMethod(name = "Árbol vacío")
    public String dibujaÁrbol0() {
        ÁrbolBinarioOrdenado<String> árbol = creaÁrbol();
        dibujante = new DibujanteDeÁrbolBinarioOrdenado();
        dibujante.setEstructura(árbol);
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Un elemento")
    public String dibujaÁrbol1() {
        ÁrbolBinarioOrdenado<String> árbol = creaÁrbol();
        dibujante = new DibujanteDeÁrbolBinarioOrdenado();
        dibujante.setEstructura(árbol);
        árbol.add("A");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Árbol 1")
    public String dibujaÁrbol2() {
        ÁrbolBinarioOrdenado<String> árbol = creaÁrbol();
        dibujante = new DibujanteDeÁrbolBinarioOrdenado();
        dibujante.setEstructura(árbol);
        árbol.add("B");
        árbol.add("A");
        árbol.add("D");
        árbol.add("C");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Árbol 2")
    public String dibujaÁrbol3() {
        ÁrbolBinarioOrdenado<String> árbol = creaÁrbol();
        dibujante = new DibujanteDeÁrbolBinarioOrdenado();
        dibujante.setEstructura(árbol);
        árbol.add("E");
        árbol.add("A");
        árbol.add("D");
        árbol.add("C");
        árbol.add("H");
        árbol.add("B");
        árbol.add("F");
        árbol.add("G");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Remueve A de Árbol 2")
    public String dibujaÁrbol4() {
        ÁrbolBinarioOrdenado<String> árbol = creaÁrbol();
        dibujante = new DibujanteDeÁrbolBinarioOrdenado();
        dibujante.setEstructura(árbol);
        árbol.add("E");
        árbol.add("A");
        árbol.add("D");
        árbol.add("C");
        árbol.add("H");
        árbol.add("B");
        árbol.add("F");
        árbol.add("G");
        árbol.remove("A");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Remueve A y E de Árbol 2")
    public String dibujaÁrbol5() {
        ÁrbolBinarioOrdenado<String> árbol = creaÁrbol();
        dibujante = new DibujanteDeÁrbolBinarioOrdenado();
        dibujante.setEstructura(árbol);
        árbol.add("E");
        árbol.add("A");
        árbol.add("D");
        árbol.add("C");
        árbol.add("H");
        árbol.add("B");
        árbol.add("F");
        árbol.add("G");
        árbol.remove("A");
        árbol.remove("E");
        return dibujante.drawSVG();
    }
    
    @DemoMethod(name = "Remueve G de Árbol 2")
    public String dibujaÁrbol6() {
        ÁrbolBinarioOrdenado<String> árbol = creaÁrbol();
        dibujante = new DibujanteDeÁrbolBinarioOrdenado();
        dibujante.setEstructura(árbol);
        árbol.add("E");
        árbol.add("A");
        árbol.add("D");
        árbol.add("C");
        árbol.add("H");
        árbol.add("B");
        árbol.add("F");
        árbol.add("G");
        árbol.remove("G");
        return dibujante.drawSVG();
    }
}
