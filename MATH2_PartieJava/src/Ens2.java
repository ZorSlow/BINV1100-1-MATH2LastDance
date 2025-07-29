public class Ens2 extends EnsembleAbstrait {

	private Elt[] elements; // contient les elements de l'ensemble. Il ne peut pas y avoir de doublon.
	private int cardinal;

	public Ens2() {
		//TODO
		elements = new Elt[MAX];
		cardinal = 0;
		
	}

	public boolean estVide() {
		//TODO
		return cardinal == 0 ;
	}
	
	public Elt unElement() {
		//TODO
		if (estVide())throw new MathException();
		return elements[0];
	}

	public boolean contient(Elt e) {
		//TODO
		if (e == null)throw new IllegalArgumentException();
		for (int i = 0; i < cardinal; i++) {
			if (elements[i].equals(e))
				return true;
		}
		return false;
	}

	public void ajouter(Elt e) {
		//TODO
		if (e == null) throw new IllegalArgumentException();
		if (cardinal >= elements.length)
			throw new IllegalArgumentException();
		if (!contient(e)) {
			elements[cardinal] = e;
			cardinal++;
		}
	}

	public void enlever(Elt e) {
		//TODO
		if (e == null)throw new IllegalArgumentException();
		for (int i = 0; i <cardinal ; i++) {
			if (elements[i].equals(e)) {
				elements[i] = elements[cardinal - 1];
				elements[cardinal -1] = null;
				cardinal--;
				return;
			}
		}
	}

	public int cardinal() {
		//TODO
		return cardinal;
	}

	public void complementer() {
		//TODO;
		Elt[] nouveauElements = new Elt[MAX];
		int cpt = 0;
		for (int i = 1; i <= MAX ; i++) {
			Elt e = new Elt(i);
			if (!contient(e)){
				nouveauElements[cpt] = e;
				cpt++;
			}
		}
		elements = nouveauElements;
		cardinal = cpt;
	}

	public String toString() {
		//TODO
		StringBuilder text = new StringBuilder("{");
		if (estVide())
			return text.append("}").toString();
		for (int i = 0; i < cardinal; i++) {
			if (i >0) {
				text.append(", ");
			}
			text.append(elements[i]);
		}
		text.append("}");
		return text.toString();
	}

}
