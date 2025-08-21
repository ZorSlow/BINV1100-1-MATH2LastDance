

public class Equivalence{
	
	/** Valeur numerique de MAXELT */
	private static final int MAX = Elt.MAXELT.val();

	private EnsembleAbstrait sousJac; // ensemble sous-jacent
	private Elt[] tabRep; // tableau des representants
	// Ne pas ajouter d'attribut

	// si possible, construit une relation d'equivalence
	// sinon, lance une IllegalArgumentException
	public Equivalence(EnsembleAbstrait[] classesEquivalence) {
		/*
		classesEquivalence = [{a, b, c},
                               {d, e},
                                {f}]
           */
		//TODO
		if (classesEquivalence == null)throw new IllegalArgumentException();
		sousJac = new Ensemble();
		tabRep = new Elt[MAX+1];

		for (int i = 0; i < classesEquivalence.length; i++) {
			if (classesEquivalence[i] == null || classesEquivalence[i].cardinal() == 0)throw new IllegalArgumentException("class null ou vide");

			Elt rep = classesEquivalence[i].unElement();
			for (Elt e : classesEquivalence[i]) {
				if (sousJac.contient(e))throw new IllegalArgumentException("un element est dans deux classes differens ");
				sousJac.ajouter(e);
				tabRep[e.val()] = rep;
			}
		}

	}

}
