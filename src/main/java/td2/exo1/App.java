package td2.exo1;

import java.util.List;
import java.util.stream.Collectors;

public class App {

// Réponse à la question 1 (Comparaison entre Somme et Sommable) :
// l'interface Sommable prend un seul parametre et l'aditionne avec la source alors que
// l'interface Somme prend les 2 arguments à sommer

    public static void main(String[] args) {

        Somme<Integer> sInt = (x,y)-> x+y;
        Somme<Double> sDouble = (x,y)-> x+y;
        Somme<Long> sLong = (x,y)-> x+y;
        Somme<String> sString = (x,y)-> x+y;

        System.out.println(sInt.sommer(10, 15));
        System.out.println(sDouble.sommer(11.50, 13.80));
        System.out.println(sString.sommer("exercice " ,"question1"));
        System.out.println(sLong.sommer(10l, 15l));
    }
}
