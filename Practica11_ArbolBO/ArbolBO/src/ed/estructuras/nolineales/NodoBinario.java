/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo tal cual a estudiantes actuales o potenciales.
 */
package ed.estructuras.nolineales;

/**
 *
 * @author blackzafiro
 */
public interface NodoBinario<E> extends Nodo<E> {

    @Override
    NodoBinario<E> getPadre();

    /**
     * Devuelve al hijo izquierdo de este nodo.
     * @return devuelve al hijo izquierdo de este nodo.
     */
    NodoBinario<E> getHijoI();

	//Cuidado, yo agregué esté  método
	//NodoBinario<E> setHijoI(E hijo);

    /**
     * Devuelve al hijo derecho de este nodo.
     * @return devuelve al hijo derecho de este nodo.
     */
    NodoBinario<E> getHijoD();

	//Cuidado yo agregué este método
	//NodoBinario<E> setHijoD(E hijo);
}
