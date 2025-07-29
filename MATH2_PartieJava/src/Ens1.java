import java.lang.classfile.attribute.CodeAttribute;

public class Ens1 extends EnsembleAbstrait {

	private boolean[] tabB; // e appartient � l'ensemble courant ssi tabE[e.val()] est � true.
	private int cardinal;

	public Ens1() {
		//TODO
		tabB = new boolean[Elt.MAXELT.val()+1];
		cardinal =0;
	}
	
	public boolean estVide() {
		//TODO
		return cardinal == 0 ;
	}
	
	public Elt unElement() {
		//TODO
		if (estVide()) throw new MathException();
		int i = 1;
		while (!tabB[i])
			i++;
		return new Elt(i);
	}

	public boolean contient(Elt e) {
		//TODO
		if ( e == null) throw new IllegalArgumentException();
		return tabB[e.val()];
	}

	public void ajouter(Elt e) {
		//TODO
		if (e == null) throw new IllegalArgumentException();
		if (contient(e))
			return;
		tabB[e.val()] = true;
		cardinal++;

	}

	public void enlever(Elt e) {
		//TODO

		if (e == null) throw new IllegalArgumentException();
		if (!contient(e))
			return;
		tabB[e.val()] = false;
		cardinal--;
	}

	public int cardinal() {
		//TODO
		return  cardinal;
	}

	public void complementer() {
		int c = 0;
		for (int i = 1; i <= MAX; i++) {
			tabB[i] = !tabB[i];
			if (tabB[i]) c++;
		}
		cardinal = c;
	}

	public String toString() {
		// TODO

		return null;
	}
	
}
