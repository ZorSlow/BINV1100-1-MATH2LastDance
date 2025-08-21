import java.util.*;
import java.lang.reflect.Field;

public class TestMatrice {
    private static Scanner scanner = new Scanner(System.in);
    private static int temps = 300;
    private static Class classe = Matrice.class;
    private static Field data;
    private static Field nbL;
    private static Field nbC;
    private static String[] NOMS_METHODES = {"echelonner"};

    private static void preparerField() {
        Field[] champs = classe.getDeclaredFields();
        for (Field f : champs) {
            if (f.getName().equals("data")) {
                data = f;
                data.setAccessible(true);
            } else if (f.getName().equals("nbLignes")) {
                nbL = f;
                nbL.setAccessible(true);
            } else if (f.getName().equals("nbColonnes")) {
                nbC = f;
                nbC.setAccessible(true);
            }
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        System.out.println("*****************************************");
        System.out.println("* Programme Test pour la classe Matrice *");
        System.out.println("*****************************************");
        preparerField();
        int choix = 0;
        do {
            System.out.println("1 -> Tester la méthode echelonner()");
            System.out.println();
            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            System.out.println() ;
            boolean testOK = true;
            switch (choix) {
                case 1:
                    testOK = testEchelonner();
                    break;
                default:
                    return;
            }
            if (testOK)
                System.out.println("Les tests de la méthode " + NOMS_METHODES[choix - 1]
                        + " ont réussi.");
            else
                System.out.println("Les tests de la méthode " + NOMS_METHODES[choix - 1]
                        + " ont échoué.");
            System.out.println();
        } while (choix >= 1 && choix <= 4);

    }

    private static boolean testEchelonner() throws IllegalAccessException {
        boolean testOK = true ;
        System.out.println();
        double[][] dTest = {{1, 2, 3}, {0.5, -1, 0}, {1,0,1} } ;
        double[][] dTestI = {{1, 2, 3}, {0.5, -1, 0}, {1,0,1} } ;
        Matrice m = new Matrice(3,3);

        int[] lignes = {1,2,3} ;
        double[] coeff = {1.2 , 0.5, 0.25} ;

        System.out.print("Test 1 : ");
        try {
            data.set(m,dTest) ;
            m.echelonner(1,null,coeff);
            System.out.println("KO : il fallait une IllegalArgumentException car lignes==null");
            testOK = false ;
        } catch(IllegalArgumentException e) {
            double[][] dataR = (double[][]) data.get(m);
            if (!Arrays.deepEquals(dTestI,dataR)) {
                System.out.println("KO : vous avez modifié la matrice mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } else {
                System.out.println("OK") ;
            }
        } catch(Exception e) {
            System.out.println("KO : mauvais type d'exception : " + e.getClass() + " au lieu de IllegalArgumentException :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }
        System.out.println() ;

        System.out.print("Test 2 : ");
        try {
            data.set(m,dTest) ;
            m.echelonner(1,lignes,null);
            System.out.println("KO : il fallait une IllegalArgumentException car coeff==null");
            testOK = false ;
        } catch(IllegalArgumentException e) {
            double[][] dataR = (double[][]) data.get(m);
            if (!Arrays.deepEquals(dTestI,dataR)) {
                System.out.println("KO : vous avez modifié la matrice mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } else {
                System.out.println("OK") ;
            }
        } catch(Exception e) {
            System.out.println("KO : mauvais type d'exception : " + e.getClass() + " au lieu de IllegalArgumentException :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }
        System.out.println() ;

        System.out.print("Test 3 : ");
        try {
            data.set(m,dTest) ;
            m.echelonner(4,lignes,coeff);
            System.out.println("KO : il fallait une IllegalArgumentException car numLigne n'existe pas");
            testOK = false ;
        } catch(IllegalArgumentException e) {
            double[][] dataR = (double[][]) data.get(m);
            if (!Arrays.deepEquals(dTestI,dataR)) {
                System.out.println("KO : vous avez modifié la matrice mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } else {
                System.out.println("OK") ;
            }
        } catch(Exception e) {
            System.out.println("KO : mauvais type d'exception : " + e.getClass() + " au lieu de IllegalArgumentException :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }
        System.out.println() ;

        System.out.print("Test 4 : ");
        try {
            data.set(m,dTest) ;
            m.echelonner(0,lignes,coeff);
            System.out.println("KO : il fallait une IllegalArgumentException car numLigne n'existe pas");
            testOK = false ;
        } catch(IllegalArgumentException e) {
            double[][] dataR = (double[][]) data.get(m);
            if (!Arrays.deepEquals(dTestI,dataR)) {
                System.out.println("KO : vous avez modifié la matrice mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } else {
                System.out.println("OK") ;
            }
        } catch(Exception e) {
            System.out.println("KO : mauvais type d'exception : " + e.getClass() + " au lieu de IllegalArgumentException :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }
        System.out.println() ;

        System.out.print("Test 5 : ");
        lignes[0]=2 ;
        lignes[1]=1 ;
        lignes[2]=2 ;
        try {
            data.set(m,dTest) ;
            m.echelonner(2,lignes,coeff);
            System.out.println("KO : il fallait une IllegalArgumentException car il y avait 2 fois la ligne à modifier dans la combinaison");
            testOK = false ;
        } catch(IllegalArgumentException e) {
            double[][] dataR = (double[][]) data.get(m);
            if (!Arrays.deepEquals(dTestI,dataR)) {
                System.out.println("KO : vous avez modifié la matrice mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } else {
                System.out.println("OK") ;
            }
        } catch(Exception e) {
            System.out.println("KO : mauvais type d'exception : " + e.getClass() + " au lieu de IllegalArgumentException :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }
        System.out.println() ;

        System.out.print("Test 6 : ");
        lignes[0]=2 ;
        lignes[1]=1 ;
        lignes[2]=3 ;
        coeff[1]=0 ;
        try {
            data.set(m,dTest) ;
            m.echelonner(1,lignes,coeff);
            System.out.println("KO : il fallait une IllegalArgumentException car un des coefficient est égal à 0");
            testOK = false ;
        } catch(IllegalArgumentException e) {
            double[][] dataR = (double[][]) data.get(m);
            if (!Arrays.deepEquals(dTestI,dataR)) {
                System.out.println("KO : vous avez modifié la matrice mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } else {
                System.out.println("OK") ;
            }
        } catch(Exception e) {
            System.out.println("KO : mauvais type d'exception : " + e.getClass() + " au lieu de IllegalArgumentException :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }
        System.out.println() ;

        System.out.print("Test 7 : ");
        lignes = new int[2] ;
        lignes[0]=3 ;
        lignes[1]=1 ;
        coeff = new double[2] ;
        coeff[0]=1;
        coeff[1]=-1 ;
        try {
            data.set(m,dTest) ;
            m.echelonner(2,lignes,coeff);
            System.out.println("KO : il fallait une IllegalArgumentException car la ligne a modifier n'est pas dans la combinaison");
            testOK = false ;
        } catch(IllegalArgumentException e) {
            double[][] dataR = (double[][]) data.get(m);
            if (!Arrays.deepEquals(dTestI,dataR)) {
                System.out.println("KO : vous avez modifié la matrice mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } else {
                System.out.println("OK") ;
            }
        } catch(Exception e) {
            System.out.println("KO : mauvais type d'exception : " + e.getClass() + " au lieu de IllegalArgumentException :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }
        System.out.println() ;

        System.out.print("Test 8 : ");
        lignes = new int[2] ;
        lignes[0]=4 ;
        lignes[1]=1 ;
        coeff = new double[2] ;
        coeff[0]=1;
        coeff[1]=-1 ;
        try {
            data.set(m,dTest) ;
            m.echelonner(1,lignes,coeff);
            System.out.println("KO : il fallait une IllegalArgumentException car une des lignes de la combinaison n'existe pas");
            testOK = false ;
        } catch(IllegalArgumentException e) {
            double[][] dataR = (double[][]) data.get(m);
            if (!Arrays.deepEquals(dTestI,dataR)) {
                System.out.println("KO : vous avez modifié la matrice mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } else {
                System.out.println("OK") ;
            }
        } catch(Exception e) {
            System.out.println("KO : mauvais type d'exception : " + e.getClass() + " au lieu de IllegalArgumentException :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }
        System.out.println() ;

        System.out.print("Test 9 : ");
        lignes = new int[2] ;
        lignes[0]=1 ;
        lignes[1]=-1 ;
        coeff = new double[2] ;
        coeff[0]=1;
        coeff[1]=-1 ;
        try {
            data.set(m,dTest) ;
            m.echelonner(1,lignes,coeff);
            System.out.println("KO : il fallait une IllegalArgumentException car une des lignes de la combinaison n'existe pas");
            testOK = false ;
        } catch(IllegalArgumentException e) {
            double[][] dataR = (double[][]) data.get(m);
            if (!Arrays.deepEquals(dTestI,dataR)) {
                System.out.println("KO : vous avez modifié la matrice mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } else {
                System.out.println("OK") ;
            }
        } catch(Exception e) {
            System.out.println("KO : mauvais type d'exception : " + e.getClass() + " au lieu de IllegalArgumentException :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }
        System.out.println() ;

        System.out.print("Test 10 : ");
        lignes = new int[3] ;
        lignes[0]=2 ;
        lignes[1]=1 ;
        lignes[2]=3 ;
        coeff = new double[2] ;
        coeff[0]=1;
        coeff[1]=-1 ;
        try {
            data.set(m,dTest) ;
            m.echelonner(2,lignes,coeff);
            System.out.println("KO : il fallait une IllegalArgumentException car les tableau lignes et coeff n'ont pas la même taille");
            testOK = false ;
        } catch(IllegalArgumentException e) {
            double[][] dataR = (double[][]) data.get(m);
            if (!Arrays.deepEquals(dTestI,dataR)) {
                System.out.println("KO : vous avez modifié la matrice mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } else {
                System.out.println("OK") ;
            }
        } catch(Exception e) {
            System.out.println("KO : mauvais type d'exception : " + e.getClass() + " au lieu de IllegalArgumentException :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }
        System.out.println() ;

        System.out.print("Test 11 : ");
        lignes = new int[3] ;
        lignes[0]=3 ;
        lignes[1]=1 ;
        lignes[2]=2 ;
        coeff = new double[3] ;
        coeff[0]=1;
        coeff[1]=-1.5 ;
        coeff[2]=2 ;
        double[][] dataD = {{2,4,0,6},{0,-2,4,5},{3,10,-1,0},{-2,1,5,3}};
        double[][] dataA = {{2,4,0,6},{0,-2,4,5},{0,0,7,1},{-2,1,5,3}};
        try {
            m = new Matrice(4,4);
            data.set(m,dataD) ;
            m.echelonner(3,lignes,coeff);
            double[][] dataR = (double[][]) data.get(m);
            if (!Arrays.deepEquals(dataA,dataR)) {
                System.out.println("KO : ") ;
                Matrice ma = new Matrice(4,4) ;
                data.set(ma,dataA);
                System.out.println("Matrice attendue : ") ;
                System.out.println(ma) ;
                System.out.println() ;
                System.out.println("Matrice reçue : ") ;
                System.out.println(m) ;
                testOK = false ;
            } else if (nbL.getInt(m)!=4) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=4) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } else {
                System.out.println("OK") ;
            }

        } catch(Exception e) {
            System.out.println("KO : Il ne fallait pas d'Exception :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }
        System.out.println() ;

        System.out.print("Test 12 : ");
        lignes = new int[3] ;
        lignes[0]=4 ;
        lignes[1]=1 ;
        lignes[2]=2 ;
        coeff = new double[3] ;
        coeff[0]=1;
        coeff[1]=1 ;
        coeff[2]=2.5 ;
        for (int i=0 ; i<4 ; i++) {
            for (int j=0 ; j<4 ; j++) {
                dataD[i][j] = dataA[i][j];
            }
        }
        dataA[3][0] = 0 ;
        dataA[3][1] = 0 ;
        dataA[3][2] = 15 ;
        dataA[3][3] = 21.5 ;
        try {
            m = new Matrice(4,4);
            data.set(m,dataD) ;
            m.echelonner(4,lignes,coeff);
            double[][] dataR = (double[][]) data.get(m);
            if (!Arrays.deepEquals(dataA,dataR)) {
                System.out.println("KO : ") ;
                Matrice ma = new Matrice(4,4) ;
                data.set(ma,dataA);
                System.out.println("Matrice attendue : ") ;
                System.out.println(ma) ;
                System.out.println() ;
                System.out.println("Matrice reçue : ") ;
                System.out.println(m) ;
                testOK = false ;
            } else if (nbL.getInt(m)!=4) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=4) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } else {
                System.out.println("OK") ;
            }

        } catch(Exception e) {
            System.out.println("KO : Il ne fallait pas d'Exception :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }
        System.out.println() ;

        return testOK ;
    }
}
