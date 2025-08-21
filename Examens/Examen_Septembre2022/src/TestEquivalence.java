import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Scanner;

public class TestEquivalence {
	private final static Scanner scanner = new Scanner(System.in);
	private static final Class cl = Equivalence.class;
	private static Field fieldSsJac;
	private static Field fieldRep;


	public static void main(String[] args) throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		fieldSsJac = cl.getDeclaredField("sousJac");
		fieldSsJac.setAccessible(true);
		fieldRep = cl.getDeclaredField("tabRep");
		fieldRep.setAccessible(true);

		System.out.println("*****************************************");
		System.out.println("Programme Test pour la classe Equivalence");
		System.out.println("*****************************************");
		System.out.println();
		testSupprimerIsoles();

	}

	private static void testSupprimerIsoles() throws IllegalArgumentException, IllegalAccessException{
		boolean testOK = true;
		Equivalence eq;
		System.out.println("Test1 : equivalence de l'enonce");
		Elt[] tab1 = new Elt[Elt.MAXELT.val()+1];
		tab1[1]=new Elt(7);
		tab1[3]=new Elt(3);
		tab1[4]=new Elt(4);
		tab1[5]=new Elt(9);
		tab1[7]=new Elt(7);
		tab1[8]=new Elt(8);
		tab1[9]=new Elt(9);
		tab1[10]=new Elt(9);
		tab1[11]=new Elt(9);
		tab1[12]=new Elt(3);
		eq = new Equivalence(tab1 );
		try{
			eq.supprimerLesIsoles();
			Ensemble sousJac = (Ensemble) fieldSsJac.get(eq);
			Ensemble sousJacAttendu = new Ensemble("1,3,5,7,9,10,11,12");
			if (sousJac == null) {
				System.out.println("Test1 ko");
				testOK = false;
				System.out.println("L'ensemble sous-jacent est null");

			} else if (!sousJac.equals(sousJacAttendu)) {
				System.out.println("Test1 ko");
				testOK = false;
				System.out.println("L'ensemble sous-jacent ne contient pas les bons elements");
				System.out.println("Voici le contenu de votre ensemble : ");
				System.out.println(sousJac);
			}else {
				Elt[] tabRep = (Elt[]) fieldRep.get(eq);
				if (tabRep == null) {
					System.out.println("Test1 ko");
					testOK = false;
					System.out.println("la table des representants est null");
				} else if (tabRep.length != Elt.MAXELT.val() + 1) {
					System.out.println("Test1 ko");
					testOK = false;
					System.out.println("La table des representants n'a pas la bonne dimension");
				} else if (tabRep[2] != null || tabRep[6] != null || tabRep[13] != null || tabRep[4] != null || tabRep[8] != null) {
					System.out.println("Test1 ko");
					testOK = false;
					System.out.println("La table des representants n'a pas ete bien mise a jour");
					System.out.println("Voici le contenu de votre table :");
					System.out.println(Arrays.toString(tabRep));
				} else if (tabRep[1] == null || tabRep[3] == null || tabRep[5] == null || tabRep[7] == null || tabRep[9] == null || tabRep[10] == null || tabRep[11] == null || tabRep[12] == null) {
					System.out.println("Test1 ko");
					testOK = false;
					System.out.println("La table des representants n'a pas ete bien mise a jour");
					System.out.println("Voici le contenu de votre table :");
					System.out.println(Arrays.toString(tabRep));
				} else if (tabRep[1].val() != 7 || tabRep[3].val() != 3 || tabRep[5].val() != 9 || tabRep[7].val() != 7 || tabRep[9].val() != 9 || tabRep[10].val() != 9 || tabRep[11].val() != 9 || tabRep[12].val() != 3) {
					System.out.println("Test1 ko");
					testOK = false;
					System.out.println("La table des representants n'a pas ete bien mise a jour");
					System.out.println("Voici le contenu de votre table :");
					System.out.println(Arrays.toString(tabRep));
				} else {
					System.out.println("Test1 ok");
				}
			}

		}catch (Exception e) {
			testOK = false;
			System.out.println("Test1 ko");
			System.out.println("Il y a eu une exception inattendue");
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("Test2 : equivalence de l'enonce sans isole (pas 4 pas 8");
		Elt[] tab2 = new Elt[Elt.MAXELT.val()+1];
		tab2[1]=new Elt(7);
		tab2[3]=new Elt(3);
		tab2[5]=new Elt(9);
		tab2[7]=new Elt(7);
		tab2[9]=new Elt(9);
		tab2[10]=new Elt(9);
		tab2[11]=new Elt(9);
		tab2[12]=new Elt(3);
		eq = new Equivalence(tab2 );
		try{
			eq.supprimerLesIsoles();
			Ensemble sousJac = (Ensemble) fieldSsJac.get(eq);
			Ensemble sousJacAttendu = new Ensemble("1,3,5,7,9,10,11,12");
			if (sousJac == null) {
				System.out.println("Test2 ko");
				testOK = false;
				System.out.println("L'ensemble sous-jacent est null");

			} else if (!sousJac.equals(sousJacAttendu)) {
				System.out.println("Test2 ko");
				testOK = false;
				System.out.println("L'ensemble sous-jacent a ete modifie");
				System.out.println("Voici le contenu de votre ensemble : ");
				System.out.println(sousJac);
			}else {
				Elt[] tabRep = (Elt[]) fieldRep.get(eq);
				if (tabRep == null) {
					System.out.println("Test2 ko");
					testOK = false;
					System.out.println("la table des representants est null");
				} else if (tabRep.length != Elt.MAXELT.val() + 1) {
					System.out.println("Test2 ko");
					testOK = false;
					System.out.println("La table des representants n'a pas la bonne dimension");
				} else if (tabRep[2] != null || tabRep[6] != null || tabRep[13] != null || tabRep[4] != null || tabRep[8] != null) {
					System.out.println("Test2 ko");
					testOK = false;
					System.out.println("La table des representants a ete modifiee");
					System.out.println("Voici le contenu de votre table :");
					System.out.println(Arrays.toString(tabRep));
				} else if (tabRep[1] == null || tabRep[3] == null || tabRep[5] == null || tabRep[7] == null || tabRep[9] == null || tabRep[10] == null || tabRep[11] == null || tabRep[12] == null) {
					System.out.println("Test2 ko");
					testOK = false;
					System.out.println("La table des representants a ete modifiee");
					System.out.println("Voici le contenu de votre table :");
					System.out.println(Arrays.toString(tabRep));
				} else if (tabRep[1].val() != 7 || tabRep[3].val() != 3 || tabRep[5].val() != 9 || tabRep[7].val() != 7 || tabRep[9].val() != 9 || tabRep[10].val() != 9 || tabRep[11].val() != 9 || tabRep[12].val() != 3) {
					System.out.println("Test2 ko");
					testOK = false;
					System.out.println("La table des representants a ete modifiee");
					System.out.println("Voici le contenu de votre table :");
					System.out.println(Arrays.toString(tabRep));
				} else {
					System.out.println("Test2 ok");
				}
			}
		}catch (Exception e) {
			testOK = false;
			System.out.println("Test2 ko");
			System.out.println("Il y a eu une exception inattendue");
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("Test3 : equivalence avec uniquement 3 isoles : 1, 25 et 50");
		Elt[] tab3 = new Elt[Elt.MAXELT.val()+1];
		tab3[1]=new Elt(1);
		tab3[25]=new Elt(25);
		tab3[50]=new Elt(50);
		eq = new Equivalence(tab3);
		try{
			eq.supprimerLesIsoles();
			Ensemble sousJac = (Ensemble) fieldSsJac.get(eq);
			Ensemble sousJacAttendu = new Ensemble();
			if (sousJac == null) {
				System.out.println("Test3 ko");
				testOK = false;
				System.out.println("L'ensemble sous-jacent est null");

			} else if (!sousJac.equals(sousJacAttendu)) {
				System.out.println("Test3 ko");
				testOK = false;
				System.out.println("L'ensemble sous-jacent devrait etre vide");
				System.out.println("Voici le contenu de votre ensemble : ");
				System.out.println(sousJac);
			}else {
				Elt[] tabRep = (Elt[]) fieldRep.get(eq);
				if (tabRep == null) {
					System.out.println("Test3 ko");
					testOK = false;
					System.out.println("la table des representants est null");
				} else if (tabRep.length != Elt.MAXELT.val() + 1) {
					System.out.println("Test3 ko");
					testOK = false;
					System.out.println("La table des representants n'a pas la bonne dimension");
				} else if (tabRep[1] != null || tabRep[25] != null || tabRep[50] != null) {
					System.out.println("Test3 ko");
					testOK = false;
					System.out.println("La table des representants n'a pas ete bien mise a jour");
					System.out.println("Voici le contenu de votre table :");
					System.out.println(Arrays.toString(tabRep));
				} else {
					System.out.println("Test3 ok");
				}
			}
		}catch (Exception e) {
			testOK = false;
			System.out.println("Test3 ko");
			System.out.println("Il y a eu une exception inattendue");
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("Test4 : equivalence vide");
		Elt[] tab4 = new Elt[Elt.MAXELT.val()+1];
		eq = new Equivalence(tab4);
		try{
			eq.supprimerLesIsoles();
			Ensemble sousJac = (Ensemble) fieldSsJac.get(eq);
			Ensemble sousJacAttendu = new Ensemble();
			if (sousJac == null) {
				System.out.println("Test4 ko");
				testOK = false;
				System.out.println("L'ensemble sous-jacent est null");

			} else if (!sousJac.equals(sousJacAttendu)) {
				System.out.println("Test4 ko");
				testOK = false;
				System.out.println("L'ensemble sous-jacent devrait etre vide");
				System.out.println("Voici le contenu de votre ensemble : ");
				System.out.println(sousJac);
			}else {
				Elt[] tabRep = (Elt[]) fieldRep.get(eq);
				if (tabRep == null) {
					System.out.println("Test4 ko");
					testOK = false;
					System.out.println("la table des representants est null");
				} else if (tabRep.length != Elt.MAXELT.val() + 1) {
					System.out.println("Test4 ko");
					testOK = false;
					System.out.println("La table des representants n'a pas la bonne dimension");
				} else if (tabRep[1] != null || tabRep[25] != null || tabRep[50] != null) {
					System.out.println("Test4 ko");
					testOK = false;
					System.out.println("La table des representants ne devrait contenir que des null");
					System.out.println("Voici le contenu de votre table :");
					System.out.println(Arrays.toString(tabRep));
				} else {
					System.out.println("Test4 ok");
				}
			}
		}catch (Exception e) {
			testOK = false;
			System.out.println("Test4 ko");
			System.out.println("Il y a eu une exception inattendue");
			e.printStackTrace();
		}


		System.out.println();
		if(testOK)
			System.out.println("tous les tests ont reussi !");
		else
			System.out.println("Attention, tous les tests n'ont pas reussi !");

	}
}
