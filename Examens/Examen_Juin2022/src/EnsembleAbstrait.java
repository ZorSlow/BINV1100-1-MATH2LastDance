public abstract class EnsembleAbstrait implements EnsembleInterface {

	/**
	* Renvoie une Relation représentant this + a
	* 
	* @parameter a : l'ensemble a "sommer" avec this
	* @return une Relation représentant la somme disjointe de this et a
	* @throws IllegalArgumentException en cas de paramètre invalide
	**/
	public Relation sommeDisjointe(EnsembleAbstrait a) {
		//TODO
		if (a == null)throw new IllegalArgumentException();
		EnsembleAbstrait depart = new Ensemble();
		EnsembleAbstrait arrive= new Ensemble();
		depart.ajouter(new Elt(1));
		depart.ajouter(new Elt(2));
		for (Elt e : this)
			arrive.ajouter(e);
		for (Elt e :a)
			arrive.ajouter(e);
		Relation sommeD = new Relation(depart,arrive);
		for (Elt e : this )
			sommeD.ajouter(new Elt(1),e);
		for (Elt e : a)
			sommeD.ajouter(new Elt(2),e);
		return sommeD;
	}

	public boolean inclusDans (EnsembleAbstrait e) {
		if (e==null)
			return false ;
		for (Elt el:this) {
			if (!e.contient(el)) {
				return false ;
			}
		}
		return true ;
	}

	// renvoie true ssi this est égal à  o
	public final boolean equals(Object o) {
		if (o == null)
			return false;
		if (o == this)
			return true;
		if (!(o instanceof EnsembleAbstrait))
			return false ;
	
		EnsembleAbstrait e = (EnsembleAbstrait) o ;
		return this.inclusDans(e) && e.inclusDans(this) ;
	}

	@Override
	public final int hashCode() {
		int result = 1;
		int prime = 31;
		for (int i = 1; i <= MAX; i++) {
			Elt ei = new Elt(i);
			if (this.contient(ei))
				result = result * prime + ei.hashCode();
		}
		return result;
	}

	@Override
	public EnsembleAbstrait clone()  {
		try {
			return (EnsembleAbstrait) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}
	
}
