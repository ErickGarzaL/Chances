package ed.estructuras.nolineales;
import java.util.List;
import java.util.Iterator;

public class Prueba {
	public static void main(String[] args) {

	NodoBOLigado<String> hijo = new NodoBOLigado<String>("h", null,null, null);
	//Ya actualiza las  alturas en el constructor


	NodoBOLigado<String> otroHijo = new NodoBOLigado<String>("q", null,null, null);

	NodoBOLigado<String> nuevo = new NodoBOLigado<String>("p", null,hijo, otroHijo);

	//Ya funciona Actualiza alturas
	//System.out.println("nuevo" + nuevo.getAltura());
	//System.out.println("hijo" + hijo.getAltura());
	//System.out.println("otroHijo" + otroHijo.getAltura());
	//System.out.println("ya");

	//Ya funciona intercambiaDato
	//nuevo.intercambiaDato(hijo);
	//System.out.println(nuevo.getElemento());
	//System.out.println(hijo.getElemento());
	//Es hoja
	//System.out.println(otroHijo.esHoja());

	//gethijo. Ya funciona con 1 y 0
	//System.out.println(nuevo.getHijo(1));

	//getHermanoSiguiente
	//NodoBOLigado pru = (NodoBOLigado)nuevo.getHermanoSiguiente(otroHijo);
	//System.out.println(pru.getElemento().toString());
	//System.out.println(nuevo.getGrado());

	//ya funciona getListaHijos
	/*
	List<Nodo<String>> lista = nuevo.getListaHijos();
	for(Nodo<String> e: lista) System.out.println(e);

	*/

	//Ya funciona remueveHijo
	//nuevo.remueveHijo(hijo);
	//System.out.println(nuevo.getGrado());

	//List<Nodo<String>> lista = nuevo.getListaHijos();
	//for(Nodo<String> e: lista) System.out.println(e);

	///////////////////////////ÁRBOL BINARIO ORDENADO LIGADO

	ÁrbolBOLigado<Integer> arbol = new ÁrbolBOLigado<Integer>();
	//Ya funciona add para agregar un elemento
	arbol.add(10);
	//Parece que ya funciona agregar
	arbol.add(1);
	arbol.add(15);
	arbol.add(3);
	arbol.add(0);
	arbol.add(-1);

	//Ya sirve grandote
	//System.out.println(arbol.raiz.grandote());
	//System.out.println(arbol.raiz.getHijoI().getHijoD().getElemento());
	//Ya funciona contains por ende funciona busca
	//System.out.println(arbol.contains(3));

	//Ya funciona pequenito
	//System.out.println(arbol.raiz.pequenito());

	//Iterator<Integer> it = arbol.getIteradorInorden();
	//while(it.hasNext()) {
	//System.out.println(it.next());
	//}
	System.out.println("pequenito, es hoja");
	System.out.println(arbol.raiz.pequenito());
	System.out.println("Vamos a eliminar a -1");
	System.out.println(arbol.raiz.pequenito().remueveHoja());
		Iterator<Integer> it = arbol.getIteradorInorden();
		while(it.hasNext()) {
			System.out.println(it.next());
		}

	}

}
