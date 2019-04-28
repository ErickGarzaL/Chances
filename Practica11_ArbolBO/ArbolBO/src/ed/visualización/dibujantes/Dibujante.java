/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ed.visualización.dibujantes;

import java.util.Collection;
import org.w3c.dom.Document;

/**
 *
 * @author Vero
 */
public interface Dibujante {
    /** Crea el dibujo y devuelve su URI. */
    public String drawSVG();
    public void setEstructura(Collection<?> c);
}
