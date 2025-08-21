

public abstract class RelationAbstraite implements RelationInterface {
	
	// Ex 3
	// renvoie true ssi this est inclus dans r
	public boolean inclusDans(RelationAbstraite r) {
		//TODO
		if (r == null || !this.depart().equals(r.depart()) || !this.arrivee().equals(r.arrivee()))
					return false;
		for (Couple  c : this )
			if (!r.contient(c))
				return false;
		return true;
	}

	// renvoie true ssi this est égale à o
	public boolean equals(Object o) {
		if (o== null) return false;
		if (o==this) return true;
		if (! (o instanceof RelationAbstraite)) return false;
		RelationAbstraite r = (RelationAbstraite) o;
		//TODO Vérifier que la relation courante est égale à r
		if (!this.depart().equals(r.depart()) || !this.arrivee().equals(r.arrivee()))
			return false;

		return this.inclusDans(r) && r.inclusDans(this);
	}
	/*for (Couple c1 : this) {
		if (!r.contient(c1)) return false;
	}
		for (Couple c2 : r) {
		if (!this.contient(c2)) return false;
	}
		return true;
*/

	//renvoie un hashCode associé à la relation
	
	public int hashCode(){
		int hash = this.depart().hashCode();
		hash = hash * 31 + this.arrivee().hashCode();
		for (int i = 1; i <= MAX; i++) {
			Elt d = new Elt(i);
			if (this.depart().contient(d)){
				for (int j = 1; j <=MAX; j++ ){
					Elt a = new Elt(j);
					Couple c = new Couple(d,a);
					if (this.contient(c))
						hash = hash *31 + c.hashCode();
				}
			}
		}
		return hash;
	}
}
