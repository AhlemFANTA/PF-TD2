package td2.exo1;

public class App {
    private static int somme(int a, int b) {
        return a + b;
    }
    private static double somme(double a, double b) {
        return a + b;
    }
    private static long somme(long a, long b) {
        return a + b;
    }
    private static String somme(String a, String b) {
        return a + b;
    }

// Réponse de la Comparaison Somme et Sommable :
// l'interface Sommable prend un seul parametre et l'aditionne avec la source alors que
// l'interface Somme prend les 2 arguments à sommer'

    public static void main(String[] args) {

        Somme<Integer> sInt = App::somme;
        Somme<Double> sDouble = App::somme;
        Somme<Long> sLong = App::somme;
        Somme<String> sString = App::somme;

        System.out.println(sInt.sommer(10, 15));
        System.out.println(sDouble.sommer(11.50, 13.80));
        System.out.println(sString.sommer("exercice " ,"question1"));
        System.out.println(sLong.sommer(10l, 15l));

    }
}
