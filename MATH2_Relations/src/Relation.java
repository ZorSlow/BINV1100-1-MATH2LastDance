/** Classe Relation héritant de RelationDeBase
	 Fournit des outils de manipulation des relations entre sous-ensembles de l'Univers
 */

import java.util.*;

public class Relation extends RelationDeBase {

	/** Valeur numérique de MAXELT */
	private static final int MAX = Elt.MAXELT.val();

	/** Construit la Relation vide sur l'ensemble vide */
	public Relation() {
		super();
	}

	/** Construit la Relation vide de d vers a */
	public Relation(EnsembleAbstrait d, EnsembleAbstrait a) {
		super(d, a);
	}

	/** Clone */
	public Relation clone() {
		return (Relation) super.clone();
	}
	
	//Ex1
	//renvoie le domaine de la relation courante
	public EnsembleAbstrait domaine() {
		//TODO
		Ensemble domaine = new Ensemble();
		for (Couple c : this)
			domaine.ajouter(c.getX());
		return domaine;
	}
	
	//renvoie l'image de la relation courante
	public EnsembleAbstrait image() {
		//TODO
		Ensemble domaine = new Ensemble();
		for (Couple c : this)
			domaine.ajouter(c.getY());
		return domaine;
	}
	
	// EX 2
	// renvoie la complémentaire de la relation courante
	public Relation complementaire() {
		//TODO
		// arefairesolo

		Relation complementaire = new Relation(this.depart(),this.arrivee());
		Ensemble depart = (Ensemble) depart();
		Ensemble arrive = (Ensemble) arrivee();
		for (Elt x : depart) {
			for (Elt y : arrive) {
				Couple c = new Couple(x, y);
				if (!this.contient(c)) {
					complementaire.ajouter(c);
				}
			}
		}
		return complementaire;
	}

	// renvoie la réciproque de la relation courante
	public Relation reciproque() {
		//TODO
		// arefaire solo

		Relation reciproque = new Relation(this.arrivee(),this.depart());
		for (Couple c : this){
			reciproque.ajouter(c.getY(),c.getX());
		}
		return reciproque;
	}

	// si possible, remplace la relation courante par son union avec r
	//sinon, lance une IllegalArgumentException
	public void ajouter(RelationInterface r) {
		//TODO
		if (r == null || !r.depart().equals(this.depart())|| !r.arrivee().equals(arrivee()))throw new IllegalArgumentException();
		for (Couple c : r)
			this.ajouter(c);
	}

	// si possible, remplace this par sa différence avec r
	//sinon, lance une IllegalArgumentException
	public void enlever(RelationInterface r) {
		//TODO
		if (r == null || !r.depart().equals(this.depart())|| !r.arrivee().equals(arrivee()))throw new IllegalArgumentException();
		if (r == this) r = this.clone();
		for (Couple c : r)
			this.enlever(c);
	}

	// si possible, remplace this par son intersection avec r
	//sinon, lance une IllegalArgumentException
	public void intersecter(RelationInterface r) {
		//TODO
		if (r == null || !r.depart().equals(this.depart()) || !r.arrivee().equals(this.arrivee()))throw new IllegalArgumentException();
		Relation rel = this.clone();

		for (Couple c : rel)
			if (!r.contient(c))
				this.enlever(c);

	}

	// si possible, renvoie la composée : this après r
	//sinon, lance une IllegalArgumentException
	public Relation apres(RelationInterface r) {
		//TODO

		// Retourne la composition this ∘ r (A→C) : pour chaque (x,y) de r et (y,z) de this,
		// si r.arrivee == this.depart alors on ajoute (x,z) dans une nouvelle relation.
		if (r == null || !r.arrivee().equals(this.depart()))throw new IllegalArgumentException();
		Relation rel = new Relation(r.depart(),this.arrivee());
		for (Couple cr : r) {
			for (Couple ct : this) {
				if (cr.getY().equals(ct.getX())) {
					rel.ajouter(cr.getX(), ct.getY());
				}
			}
		}

		return rel;
	}


	
	/*Les exercices 4 et 5 ne concernent que les relations sur un ensemble.
	 * Les méthodes demandées génèreront donc une MathException lorsque l'ensemble de départ
	 * ne coïncide pas avec l'ensemble d'arrivée.
	 */
	
	/* Ex 4 */
		
	// Clôture la Relation courante pour la réflexivité
	public void cloReflex() {
		//TODO
		if (!this.depart().equals(this.arrivee()))throw new MathException();
		for (Elt e : arrivee())
			this.ajouter(e,e);

	}

	// Clôture la Relation courante pour la symétrie
	public void cloSym() {
		//TODO
		if (!this.depart().equals(this.arrivee()))throw new MathException();
		Relation clone = this.clone();
		for (Couple c : clone){
			this.ajouter(c.getY(),c.getX());
		}
	}

	// Clôture la Relation courante pour la transitivité (Warshall)
	public void cloTrans() {
		//TODO
		if (!this.depart().equals(this.arrivee()))throw new MathException();
		for (Elt milieu : arrivee())
			for (Elt debut : depart())
				for (Elt fin : arrivee())
					if (contient(debut,milieu) && contient(milieu,fin))
						this.ajouter(debut,fin);
	}

	
	//Ex 5
	/*Les questions qui suivent ne concernent que les relations sur un ensemble.
	 * Les méthodes demandées génèreront donc une MathException lorsque l'ensemble de départ
	 * ne coïncide pas avec l'ensemble d'arrivée.
	 */
	// renvoie true ssi this est réflexive
	public boolean reflexive(){
		//TODO
		if (!this.depart().equals(arrivee()))throw new MathException();
		for (Elt e : arrivee())
			if (!this.contient(e,e))
				return false;
		return true;
	}

	// renvoie true ssi this est antiréflexive
	public boolean antireflexive(){
		//TODO
		if (!this.depart().equals(arrivee()))throw new MathException();
		for (Elt e : arrivee())
			if (this.contient(e,e))
				return false;
		return true;
	}

	// renvoie true ssi this est symétrique
	public boolean symetrique(){
		//TODO
		if (!this.depart().equals(arrivee()))throw new MathException();
		for (Couple c : this)
			if (!this.contient(c.getY(),c.getX())){
				return false;
		}
		return true;
	}

	// renvoie true ssi this est antisymétrique
	public boolean antisymetrique(){
		//TODO
		if (!this.depart().equals(arrivee()))throw new MathException();
		for (Couple c : this){
			Elt x = c.getX();
			Elt y = c.getY();
			if (this.contient(y,x) && !x.equals(y))
				return false;
		}
		return true;
	}

	// renvoie true ssi  this est transitive
	public boolean transitive(){
		//TODO
		if (!this.depart().equals(arrivee()))throw new MathException();
		for (Couple c1 : this) {
			Elt x = c1.getX();
			Elt y = c1.getY();
			for (Couple c2 : this) {
				if (c2.getX().equals(y)){
					Elt y2 = c2.getX();
					Elt z = c2.getY();
					if (y.equals(y2) && !this.contient(x, z))
						return false;
				}
			}
		}
		return true;
	}

	// Ex 6
	//Construit une copie de la relation en paramètre
	//lance une IllegalArgumentException en cas de paramètre invalide
	public Relation(RelationInterface r) {
		//TODO
		if (r == null)throw new IllegalArgumentException();
		for (Elt e : r.depart()){
			this.ajouterDepart(e);
		}
		for (Elt arr : r.arrivee())
			this.ajouterArrivee(arr);
		for (Couple c : r)
			this.ajouter(c);
	}

	//renvoie l'identité sur e
	//lance une IllegalArgumentException en cas de paramètre invalide
	public static Relation identite(EnsembleAbstrait e) {
		//TODO
		if (e == null)throw new IllegalArgumentException();
		Relation r  = new Relation(e,e);
		for (Elt x : e){
			r.ajouter(x,x);
		}
		return r;
	}

	//renvoie le produit cartésien de a et b
	//lance une IllegalArgumentException en cas de paramètre invalide
	public static Relation produitCartesien(EnsembleAbstrait a, EnsembleAbstrait b) {
			//TODO
		if(a == null || b == null )throw new IllegalArgumentException();
		Relation r = new Relation(a,b);
		for (Elt ea : a){
			for (Elt eb: b)
				r.ajouter(ea,eb);
		}
		return r;
	}

} // class Relation
