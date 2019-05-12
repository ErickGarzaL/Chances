package ed.estructuras.nolineales;

import ed.estructuras.lineales.ListaDoblementeLigada;
import java.util.List;

/**
 * Clase para representar Nodos Binarios Ordenados ligados.  Este tipo
 * de nodos poseen una altura y pueden o no contar con un hijo
 * izquierdo y un hijo derecho.
 */
/*
 * Notas sobre la implementación: Para la nueva versión se cambió al
 * tipo del padre, hijo izquierdo e hijo derecho
 */
public class NodoBOLigado<E extends Comparable<E>> implements NodoBinario<E> {

    E elemento;
    protected NodoBinario<E> padre;
    protected NodoBinario<E> izq;
    protected NodoBinario<E> der;
    protected int altura;

    /**
     * Construye un Nodo Binario Ordenado Ligado.
     * @param elemento el elemento que va a almacenar este nodo.
     * @param padre el padre de el nodo que se está creando.
     * @param izq el hijo izquierdo de este nodo.
     * @param der el hijo derecho de  este nodo.
     */
    /* Sólo podemos tener este único constructor porque es el que
     * manda a llamar el método creaNodo de ÁrbolBOLigado. */
    public NodoBOLigado(E elemento, NodoBinario<E> padre, NodoBinario<E> izq, NodoBinario<E> der) {
        this.elemento = elemento;
        this.padre = padre;
        this.izq = izq;
        this.der = der;

        if(der != null) der.setPadre(this);

        if(izq != null) izq.setPadre(this);

        if(padre != null) {
	        NodoBOLigado<E> aux = this.getPadre();
            //Si padre(elem) < this(elemento) soy su hijo derecho
            if(aux.getElemento().compareTo(elemento) <= 0) {
                aux.setHD(this);
            } else {

	            //Si soy su hijo izquierdo
	            aux.setHI(this);
            }
            this.actualizaAlturas();
        }
    }

    /** Actualiza la altura de un nodo. */
    /* Partimos de la suposición de que las hojas tienen altura
     * cero. */
    public void actualizaAltura() {
	    if(this.esHoja()) {
            this.altura = 0;
            return;
        }

        if(this.der == null) {
            this.altura = 1 + this.izq.getAltura();
            return;
        }

        if(this.izq == null) {
            this.altura = 1 + this.der.getAltura();
            return;
        }

        //Como ninguno de sus  hijos es null
        this.altura = 1 + Math.max(this.izq.getAltura(), this.der.getAltura());
    }

    /**
     * Actualiza la altura de todos los nodos del árbol que pertenecen
     * a la trayectoria que va de este nodo hasta el nodo raíz.
     */
    public void actualizaAlturas() {
        NodoBOLigado<E> transeunte = this;
        while(transeunte != null) {
            transeunte.actualizaAltura();
            transeunte = transeunte.getPadre();
        }
    }

    @Override
    public boolean esHoja() {
        return (izq == null && der == null);
    }

    /**
     * Devuelve al nodo que almacena al elemento más grande del
     * subárbol que cuelga de este nodo.
     * @return nodo que almacena al elemento más grande del subárbol
     * que cuelta de este nodo.
     */
    /* Duda; este método quedó chistoso por la condición del ciclo
     * while. */
    public NodoBinario<E> grandote() {
        NodoBOLigado<E> transeunte = this;
        while(transeunte != null) {

            //Si ya no tiene hijo derecho
            if(transeunte.der == null) return transeunte;

            //Si sí tiene hijo derecho
            transeunte = transeunte.getHijoD();
        }

        return null;
    }

    @Override
    public E getElemento() {
        return this.elemento;
    }

    @Override
    public int getAltura() {
        return this.altura;
    }

    @Override
    public Nodo<E> getHermanoSiguiente(Nodo<E> hijo) throws IllegalArgumentException {

        if(this.der != hijo && this.izq != hijo) {
            throw new IllegalArgumentException();
        }

        //si estamos en el izquierdo
        if(this.izq == hijo) {
            return this.der;
        }

        //debemos estar en el derecho
        return this.izq;
    }

    //Suponiendo que izq = 0 y der = 1
    @Override
    public Nodo<E> getHijo(int indice) throws IndexOutOfBoundsException {
        if(indice != 0 && indice != 1) throw new IndexOutOfBoundsException();
        if(indice == 0) {
            return this.izq;
        } else {
            return this.der;
        }
    }

    @Override
    public int getGrado() {
        int hijos = 0;
        if(this.izq != null) hijos++;

        if(this.der != null) hijos++;

        return hijos;
    }

    public void intercambiaDato(NodoBinario<E> nodo) {
        E aux = this.getElemento();
        this.setElemento(nodo.getElemento());
        nodo.setElemento(aux);
    }

    /* No se agregan a sus hijos sin son == null */
    @Override
    public List<Nodo<E>> getListaHijos() {
        List<Nodo<E>> hijos = new ListaDoblementeLigada<Nodo<E>>();

        if(this.izq != null) hijos.add(this.izq);

        if(this.der != null) hijos.add(this.der);

        return hijos;
    }

    /**
     * Devuelve al nodo que contiene al elemento más pequeño del
     * subárbol que cuelga de este nodo.
     * @return al nodo que contiene al elemento más pequeño del
     * subárbol que cuelga de este nodo.
     */
    public NodoBinario<E> pequenito() {
        NodoBOLigado<E> transeunte = this;
        while(transeunte != null) {

            //Si ya no tiene hijo izquierdo
            if(transeunte.izq == null) return transeunte;

            //Si sí tiene hijo izquierdo
            transeunte = transeunte.getHijoI();

        }

        return null;
    }

    /**
     * Elimina este nodo de subárbol que cuelga de él.
     * @return nodo que fue eliminado.
     */
    /* Si queremos eliminar a la raíz de un árbol y ésta es una hoja,
     * no se debe de mandar a llamar a este método, sino modificar la
     * referencia de la raíz del árbol a null. */
    public NodoBinario<E> remove() {
        if(this.esHoja()) {
            return this.remueveHoja();
        }

        //Si sí tengo hijo derecho
        if(this.der != null) {
	        NodoBOLigado<E> aux = (NodoBOLigado<E>)this.getHijoD().pequenito();
            this.intercambiaDato(aux);
            return aux.remove();
        }

        //Si no tengo hijo derecho
        NodoBOLigado<E> aux = (NodoBOLigado<E>)this.getHijoI().grandote();
        aux.intercambiaDato(this);
        return aux.remove();
    }

    /**
     * Este método sólo debe ser utilizado si se está seguro de que el
     * nodo que manda a llamar a este método es una hoja.
     * @return devuelve al nodo que acaba de ser eliminado.
     */
    public NodoBOLigado<E> remueveHoja() {
        if(!this.esHoja() || this.getPadre() == null) {
            throw new IllegalStateException("El nodo que manda a llamar al método no es una hoja o no tiene padre.");
        }

        //Ya sabemos que tiene padre
        NodoBOLigado<E> aux = (NodoBOLigado)this.getPadre();
        NodoBOLigado<E> aux2 = this;

        //Si es su hijo izquierdo
        if(aux.getHijo(0) == this) {
            aux.setHI(null);
            return aux2;
        }

        //Si es su hijo derecho
        if(aux.getHijo(1) == this) {
            aux.setHD(null);
            return aux2;
        }

        return null;
    }

    @Override
    public void setElemento(E dato) {
        this.elemento = dato;
    }

    /**
     * Define al hijo izquierdo de este nodo.
     * @param izq el nuevo hijo de este nodo.
     */
    public void setHI(NodoBinario<E> izq) {
	    this.izq = (NodoBOLigado<E>)izq;
        //if(izq != null) izq.padre = this;
        this.actualizaAlturas();
    }

    /**
     * Define al hijo derecho de este nodo.
     * @param der el nuevo hijo derecho de este nodo.
     */
    public void setHD(NodoBinario<E> der) {
        this.der = (NodoBOLigado<E>)der;
        //if(der != null) der.padre = this;
        this.actualizaAlturas();
    }

    //Duda ¿el tipo del padre queda definido por el método en la
    //interfaz, verdad?  el padre puede ser null DUDA: ¿ES NECESARIO
    //PONER ALGO MÁS QUE SÓLO EL SET PADRE?  ¿es necesario ajustar al
    //hijo del padre?  ¿es necesario ajustar la altura del padre ¿no?
    /* Por ahora dentro de este método sólo se asignará al padre de
     * este nodo, pero recordar que cada que se manda a llamar a este
     * método, se le debe avisar al padre que tiene un nuevo hijo, es
     * decir, se debe ajustar a alguno de sus hijos de tal forma que
     * el nodo que acaba de mandar a llamar a este método quede como
     * su hijo. */
    @Override
    public void setPadre(Nodo<E> padre) {
        this.padre = (NodoBOLigado<E>)padre;
    }

    @Override
    public boolean remueveHijo(Nodo<E> hijo) {

        if(this.izq == hijo) {
            this.izq = null;
            this.actualizaAlturas();
            return true;
        }

        if(this.der == hijo) {
            this.der = null;
            this.actualizaAlturas();
            return true;
        }

        //Si no tenía hijos o hijo no era su hijo
        return false;
    }

    @Override
    public NodoBOLigado<E> getPadre() {
	    return (NodoBOLigado<E>)this.padre;
    }

    @Override
    public NodoBOLigado<E> getHijoI() {
        return (NodoBOLigado<E>)this.izq;
    }

    @Override
    public NodoBOLigado<E> getHijoD() {
        return (NodoBOLigado<E>)this.der;
    }

    @Override
    public String toString() {
        return this.getElemento().toString();
    }
}
