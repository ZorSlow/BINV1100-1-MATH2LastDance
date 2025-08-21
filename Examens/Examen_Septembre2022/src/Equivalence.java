

public class Equivalence{
	
	/** Valeur numerique de MAXELT */
	private static final int MAX = Elt.MAXELT.val();

	private EnsembleAbstrait sousJac; // ensemble sous-jacent
	private Elt[] tabRep; // tableau des representants
	// Ne pas ajouter d'attribut

	//modifie l'equivalence courante en supprimant tous les elements isoles
	public void supprimerLesIsoles(){
		//TODO
		EnsembleAbstrait sj = sousJac.clone();
		for (Elt e : sj) {
			if (tabRep[e.val()].equals(e)){
				boolean isole = true;
			for (Elt f : sousJac) {
				if (tabRep[f.val()] == e && !f.equals(e)){
					isole = false;
					break;
				}
			}
			if (isole){
				sousJac.enlever(e);
				tabRep[e.val()] = null;
			}
			}
		}


	}

	//NE PAS MODIFIER : va servir pour les tests
	public Equivalence(Elt[] tabRepARecopier) {
		sousJac = new Ensemble();
		tabRep = new Elt[MAX+1];
		for (int i = 1; i < tabRepARecopier.length; i++) {
			if(tabRepARecopier[i]!=null){
				sousJac.ajouter(new Elt(i));
				tabRep[i]=tabRepARecopier[i];
			}
		}
	}



	
} 
