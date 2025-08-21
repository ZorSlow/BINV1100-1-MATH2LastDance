/** Classe Equivalence
	 Chaque instance de cette classe est une relation d'�quivalence sur un sous-ensemble de l'Univers
 */

import java.util.*;

public class Equivalence extends RelationAbstraite {

	private EnsembleAbstrait sousJac; // ensemble sous-jacent
	private Elt[] tabRep; // tableau des repr�sentants
	private int numVersion; // num�ro de version


	// Construit l�identit� sur e
	// Lance une IllegalArgumentException en cas de param�tre invalide
	public Equivalence(EnsembleAbstrait e) {
		//TODO
		if (e == null) throw new IllegalArgumentException();
		sousJac = e.clone();
		tabRep = new Elt[MAX + 1];
		numVersion = 0;
		for (Elt elt : sousJac) {
			tabRep[elt.val()] = elt;
		}

	}


	// ajoute (si n�cessaire) l�ar�te x-y au diagramme de classes de
	// l�Equivalence courante ; autrement dit, fusionne les classes de
	// c.getx() et de c.gety().
	// Lance une IllegalArgumentException en cas de param�tre invalide
	public void ajouter(Couple c) {
		//TODO
		// Vérification que le couple existe vraiment
		if (c == null)
			throw new IllegalArgumentException();

		// On récupère les deux éléments x et y du couple
		Elt x = c.getX();
		Elt y = c.getY();

		// Vérifier que x et y font partie de l’ensemble sous-jacent
		// (en math, ça veut dire : ils appartiennent au domaine de la relation)
		if (!sousJac.contient(x) || !sousJac.contient(y))
			throw new IllegalArgumentException();

		// On récupère les représentants actuels de x et y
		// (en math, c’est “quel est le chef/groupe auquel appartient cet élément ?”)
		Elt repX = tabRep[x.val()];
		Elt repY = tabRep[y.val()];

		// Si pour une raison étrange on n'a pas trouvé de représentant → erreur
		if (repX == null || repY == null)
			throw new IllegalStateException("Représentants non initialisés");

		// Si x et y ont déjà le même représentant, ça veut dire :
		// "Ils sont déjà dans la même classe", donc rien à changer
		if (repX.equals(repY))
			return;

		// On s'apprête à modifier la structure → on change la version
		numVersion++;

		// Fusionner les deux classes :
		// Pour tous les éléments qui avaient repY comme chef,
		// on leur donne maintenant repX comme chef.
		// En math, c’est l’opération : "Union des classes de x et y".
		for (Elt elt : sousJac) {
			if (tabRep[elt.val()].equals(repY)) {
				tabRep[elt.val()] = repX;
			}
		}
	}

	// Construit la cl�ture �quivalente de r, pour autant que celle-ci soit une relation sur un ensemble.
	// lance une IllegalArgumentException sinon
	public Equivalence(Relation r) {
		//TODO
		if (r == null)throw new IllegalArgumentException();

		EnsembleAbstrait d = r.depart();
		EnsembleAbstrait a = r.arrivee();
		if (!d.equals(a))throw new IllegalArgumentException();
		sousJac = d.clone();
		tabRep = new Elt[MAX +1];
		numVersion = 0;

		for (Elt elt : sousJac){
			tabRep[elt.val()] = elt;
		}
		for (Couple c : r)
			ajouter(c);
	}


	// renvoie true si c.getx() et c.gety() sont dans la m�me classe et false sinon
	// Lance une IllegalArgumentException en cas de param�tre invalide
	public boolean contient(Couple c) {
		//TODO
		if (c == null) throw new IllegalArgumentException();

		Elt x = c.getX();
		Elt y = c.getY();

		if (!sousJac.contient(x) || !sousJac.contient(y))
			throw new IllegalArgumentException();

		Elt repX = tabRep[x.val()];
		Elt repY = tabRep[y.val()];

		if (repX == null || repY == null)
			throw new IllegalArgumentException();

		return repX.equals(repY); // même classe si même représentant
	}

	// renvoie la classe d'�quivalence de x, ou g�n�re une IllegalArgumentException
	// si e est null ou si e n'appartient pas � l'ensemble sous-jacent
	public EnsembleAbstrait classe(Elt e) {
		//TODO
		if (e == null || !sousJac.contient(e)) throw new IllegalArgumentException();

		Elt repE = tabRep[e.val()];
		EnsembleAbstrait classeRep = new Ensemble();

		for (int i = 1; i < tabRep.length; i++) {
			if (tabRep[i] != null && tabRep[i].equals(repE))
				classeRep.ajouter(new Elt(i));
		}
		return classeRep;
	}

	// Si c.getx()et c.gety() sont distincts et si la classe commune
	// de c.getx() et c.gety() est {c.getx(),c.gety()}, alors cette classe
	// sera scind�e en deux classes.
	// g�n�re une IllegalArgumentException si le param�tre est invalide,
	// ou si c.getx(), c.gety() sont dans la m�me classe  mais qu'on n'est pas
	// dans le cas o� on peut scind�e cette classe.
	public void enlever(Couple c) {
		//TODO
		if (c == null) throw new IllegalArgumentException();

		Elt x = c.getX();
		Elt y = c.getY();
		if (x == null || y == null) throw new IllegalArgumentException();
		if (!sousJac.contient(x) || !sousJac.contient(y))
			throw new IllegalArgumentException();
		if (x.equals(y)) // on ne scinde pas avec (x,x)
			throw new IllegalArgumentException();

		Elt repX = tabRep[x.val()];
		Elt repY = tabRep[y.val()];
		if (repX == null || repY == null)
			throw new IllegalStateException("Représentants non initialisés.");

		// Doivent être dans la même classe pour pouvoir la scinder
		if (!repX.equals(repY))
			return;
		// Classe commune
		EnsembleAbstrait classe = classe(x);
		// La scission n'est autorisée que si la classe est exactement {x, y}
		if (classe.cardinal() != 2 || !classe.contient(x) || !classe.contient(y))
			throw new IllegalArgumentException("Scission impossible : la classe n'est pas exactement {x,y}.");

		// Scinder : chaque élément devient son propre représentant
		tabRep[x.val()] = x;
		tabRep[y.val()] = y;

		numVersion++;

	}

	// renvoie le nombre de classes de l'Equivalence courante
	public int nbreClasses() {
		//TODO
		HashSet<Elt> repsDistincts = new HashSet<>();
		for (Elt e : sousJac){
			Elt rep = tabRep[e.val()];
			repsDistincts.add(rep);
		}
		return repsDistincts.size();
	}

	// renvoie le quotient de l�ensemble sous-jacent par l'Equivalence
	// courante
	public EnsembleAbstrait[] quotient() {
		//TODO

		// Regrouper les éléments par représentant
		Map<Elt, EnsembleAbstrait> classes = new HashMap<>();
		for (Elt x : sousJac) {
			Elt rep = tabRep[x.val()];        // doit être non-null si bien initialisé
			EnsembleAbstrait cl = classes.get(rep);
			if (cl == null) {
				cl = new Ensemble();
				classes.put(rep, cl);
			}
			cl.ajouter(x);
		}

		// Convertir en tableau (ordre non garanti, et c'est ok ici)
		Collection<EnsembleAbstrait> valeurs = classes.values();
		return valeurs.toArray(new EnsembleAbstrait[0]);
	}


	public boolean estVide() {
		return sousJac.estVide();
	}

	@Override
	public EnsembleAbstrait depart() {
		return sousJac.clone();
	}

	@Override
	public EnsembleAbstrait arrivee() {
		return sousJac.clone();
	}

	/** renvoie un it�rateur sur l'Equivalence courante */
	public Iterator<Couple> iterator() {
		return new EquivalenceIterator();
	}

	private class EquivalenceIterator implements Iterator<Couple> {
		private Iterator<Couple> itC;
		private int version;

		public EquivalenceIterator() {
			version = numVersion;
			Relation r = new Relation(sousJac, sousJac);
			EnsembleInterface reste = sousJac.clone();
			while (!reste.estVide()) {
				Elt e = reste.unElement();
				EnsembleAbstrait classeE = classe(e);
				Iterator<Elt> itClasseE = classeE.iterator();
				while (itClasseE.hasNext()) {
					Elt next = itClasseE.next();
					r.ajouter(e, next);
					r.ajouter(next, e);
					r.ajouter(next, next);
				}
				reste.enlever(classeE);
			}
			r.cloTrans();
			itC = r.iterator();
		}

		@Override
		public boolean hasNext() {
			return itC.hasNext();
		}

		@Override
		public Couple next() {
			if (numVersion != this.version)
				throw new ConcurrentModificationException();
			if (!hasNext())
				throw new NoSuchElementException();
			return itC.next();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

} // Equivalence
