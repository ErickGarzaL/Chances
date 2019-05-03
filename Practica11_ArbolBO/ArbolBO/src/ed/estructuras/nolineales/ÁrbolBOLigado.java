package ed.estructuras.nolineales;

import ed.estructuras.ColeccionAbstracta;
import java.util.Iterator;
import java.util.List;

//Duda ¿es necesario poner que c extends comparable.  Hay varias
//dudas: ¿es necesario que extends Colecion Abstracta? porque eso ya lo hace árbol
/**
 * Clase que implementa a un Árbol Binario Ordenado.
 */
public class ÁrbolBOLigado<C extends Comparable <C>>
    extends ColeccionAbstracta<C> implements ÁrbolBinarioOrdenado<C> {

    /* La raíz del árbol. */
    NodoBOLigado<C> raiz;

    /* La altura de este árbol está definido por la altura del nodo
     * raíz. */

    /**
     * Crea un árbol vacío.
     */
    public ÁrbolBOLigado() {
        this.raiz = null;
    }

    public boolean add(C e) {
        this.addNode(e);
        return true;
    }

    /*
     * Agrega un nodo en este árbol binario ordenado en su posición
     * correspondiente con el parámetro como su dato.
     */
    protected NodoBOLigado<C> addNode(C e) {
        //Si tenemos un árbol vacío
        if(this.raiz == null) {
            this.raiz = (NodoBOLigado)this.creaNodo(e, null, null, null);
            this.tam++;
            return this.raiz;
        }

        NodoBOLigado<C> transeunte = this.raiz;
        NodoBOLigado<C> ultimoVisitado = transeunte;
        while(transeunte != null) {
            ultimoVisitado = transeunte;
            // e < transeunte.getElemento()
            if(transeunte.getElemento().compareTo(e) > 0) {
                transeunte = transeunte.izq;
                continue;
            }

            //Por tricotomia ya sabemos que el dato e es mayor o igual
            //al nodo donde estamos parados
            transeunte = transeunte.der;
        }

        //Conectamos al nuevo nodo con el último visitado y al
        //ultimosivitado con el Nodo.
        NodoBOLigado<C> nuevo = (NodoBOLigado)this.creaNodo(e,ultimoVisitado,null,null);
        this.tam++;
        return nuevo;
    }

    /*
     * Método que busca y devuelve al primer nodo que contiene al dato
     * pasado como parámetro si no existe dicho nodo, devuelve null
     */
    private NodoBOLigado<C> busca(C dato) {
        if(this.isEmpty()) return null;

        NodoBOLigado<C> transeunte = this.raiz;
        while(transeunte != null) {

            //si ese nodo contiene al dato buscado
            if(transeunte.getElemento().compareTo(dato) == 0) {
                return transeunte;
            }

            //Si el dato es menor que el elemento en el nodo
            if(transeunte.getElemento().compareTo(dato) > 0) {
                transeunte = transeunte.izq;
                continue;
            }

            //Por tricotomia es mayor o igual que el elemento en
            //transeunte.
            transeunte = transeunte.der;
        }

        //Si no lo encontró
        return null;
    }

    @Override
    public boolean contains(C o) {
        if(this.busca(o) != null) return true;

        return false;
    }

    protected NodoBinario<C> creaNodo(C dato, NodoBinario<C> padre,NodoBinario<C> hijoI, NodoBinario<C> hijoD) {
        if(dato == null) throw new IllegalArgumentException();

        //no se verifica que padre, hijoI e hijoD sean instancias de
        //NodoBOLigado dado que si entra null, daría false y no
        //crearía el nodo. Debemos permitir la entrada de null en
        //padre, hijoI e hijoD pues sí existe un nodo que puede tener
        //tales características, éste es el nodo raíz sin hijos.

        //Duda ¿si intento hacer cast a null no pasa nada?
        //Se hace el cast particular que se necesita para este árbol.
        NodoBOLigado<C> nvoPadre = (NodoBOLigado<C>)padre;
        NodoBOLigado<C> nvoHijoI = (NodoBOLigado<C>)hijoI;
        NodoBOLigado<C> nvoHijoD = (NodoBOLigado<C>)hijoD;

        //En el constructor del nodo se conectan a los nodos sólo si son
        //distintos de null
        NodoBOLigado<C> nuevo = new NodoBOLigado(dato, nvoPadre, nvoHijoI, nvoHijoD);

        return nuevo;
    }

    @Override
    public int getAltura() {
        if(this.isEmpty()) return -1;
        return this.raiz.getAltura();
    }

    @Override
    public NodoBOLigado<C> getRaíz() {
        return this.raiz;
    }

    @Override
    public Iterator<C> getIteradorInorden() {
        return new IteratorInOrden();
    }

    @Override
    public Iterator<C> getIteradorPreorden() {
        return new IteratorPreorden();
    }

    @Override
    public Iterator<C> getIteradorPostorden() {
        return null;
    }

    //Devolver inOrden
    @Override
    public Iterator<C> iterator() {
        return new IteratorInOrden();
    }

    @Override
    public List<C> recorridoPostorden() {
        return null;
    }

    @Override
    public boolean remove(C o) {

        if(o == null) throw new NullPointerException();

        NodoBOLigado<C> encontrado = this.busca(o);

        //si este árbol no contenía al elemento _o_
        if(encontrado == null) return false;

        //Si es hoja y es la raíz del árbol (ie. árbol con un sólo
        //elemento)
        if(encontrado.esHoja() && this.raiz == encontrado) {
            this.raiz = null;
            this.tam--;
            return true;
        }

        //Si no es la raíz o no es hoja
        encontrado.remove();
        this.tam--;
        return true;
    }

    protected class IteratorInOrden implements Iterator<C> {
        /* Nodo que nos indicará dónde estamos parados. */
        private NodoBOLigado<C> actual;

        public IteratorInOrden() {
            actual = ÁrbolBOLigado.this.raiz.pequenito();
        }

        @Override
        public boolean hasNext() {
            return actual != null;
        }

        public C next() {
            if(!this.hasNext()) {
                throw new IllegalStateException("No hay elemento siguiente");
            }

            C dato = actual.getElemento();

            //Si todavía no terminamos de recorrer su subárbol
            //derecho.
            /*Nota: getHijoD lo implementa de NodoBinario por eso es
             * necesario hacer el cast.*/
            if(actual.getHijoD() != null) {
                NodoBOLigado<C> aux = (NodoBOLigado)actual.getHijoD();
                actual = aux.pequenito();
            } else {
                NodoBOLigado<C> temp = actual;
                actual = (NodoBOLigado)actual.getPadre();

                while(actual != null && actual.getHijoD() == temp) {
                    temp = actual;
                    NodoBOLigado<C> aux2 = (NodoBOLigado)actual.getPadre();
                    actual = aux2;
                }
            }
            return dato;
        }
    }

    protected class IteratorPreorden implements Iterator<C> {
        /* Nodo que nos indicará dónde estamos parados. */
        private NodoBOLigado<C> actual;

        public IteratorPreorden() {
            actual = ÁrbolBOLigado.this.raiz.pequenito();
        }

        @Override
        public boolean hasNext() {
            return actual != null;
        }

        public C next() {
            if(!this.hasNext()) {
                throw new IllegalStateException("No hay elemento siguiente");
            }

            C dato = actual.getElemento();

            //Si todavía no terminamos de recorrer su subárbol
            //derecho.
            /*Nota: getHijoD lo implementa de NodoBinario por eso es
             * necesario hacer el cast.*/
            if(actual.getHijoD() != null) {
                NodoBOLigado<C> aux = (NodoBOLigado)actual.getHijoD();
                actual = aux.pequenito();
            } else {
                NodoBOLigado<C> temp = actual;
                actual = (NodoBOLigado)actual.getPadre();

                while(actual != null && actual.getHijoD() == temp) {
                    temp = actual;
                    NodoBOLigado<C> aux2 = (NodoBOLigado)actual.getPadre();
                    actual = aux2;
                }
            }
            return dato;
        }
    }
}
