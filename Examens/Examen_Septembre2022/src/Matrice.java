import java.util.Arrays ;
public class Matrice {
    private final int nbLignes;              // nombre de lignes
    private final int nbColonnes;            // nombre de colonnes
    private final double[][] data;           // matrice (nbLignes,nbColonnes)

    // ce constructeur cree la matrice nulle de genre (a,b)
    public Matrice(int a, int b) throws IllegalArgumentException {
        if (a<=0 || b<=0)
            throw new IllegalArgumentException("a ou b négatif") ;
        data = new double[a][b] ;
        nbLignes = a ;
        nbColonnes = b ;
    }
	
	//Remplace la ligne numLigne de la matrice courante par la somme des lignes 
	//dont les numéros sont la table lignes pondérées par les coefficients se trouvant dans la table coeff
	//Lance une IllegalArgumentException si
	//     numLigne est un numéro de ligne invalide
    //     lignes ou coeff est null
    //     lignes et coeff n'ont pas la même taille
    //     lignes contient un ou plusieurs numéros de lignes invalide
    //     lignes ne contient pas une et une seule fois numLigne
    //     un coefficient contenu dans coeff est égal à 0
	// Exemple : si on veut faire l_3 <-- l_3-0.5 l_1+1.5 l_2 
    //           alors numLigne=3  lignes={3,1,2} et coeff = {1,-0.5,1.5}	

    public void echelonner(int numLigne, int[] lignes, double[] coeff) {
        //TODO

		
    }
    
    // affiche la matrice en format standard //NE PAS MODIFIER CETTE METHODE !!!
    public String toString(){
        String st = "" ;
        int tmax = 0 ;
        for (int i=0 ; i<nbLignes ; i++) {
            for (int j=0 ; j<nbColonnes ;j++) {
                String s = "" + data[i][j] ;
                if (data[i][j]>=0)
                    s = " "+s ;
                if (s.length()>tmax)
                    tmax = s.length() ;
            }
        }
        for (int i=0 ; i<nbLignes ; i++) {
            for (int j=0 ; j<nbColonnes ;j++) {
                String s = "" + data[i][j] ;
                if (data[i][j]>=0)
                    s = " "+s ;
                st = st + s ;
                int nbBlanc = tmax-s.length()+2;
                for (int k=0 ; k<nbBlanc ; k++)
                    st = st + " " ;
            }
            if (i<nbLignes-1)
                st = st+'\n' ;
        }
        return st ;
    }

}
