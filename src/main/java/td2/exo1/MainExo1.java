package td2.exo1;

import java.util.List;
import java.util.Map;

public class MainExo1 {

// Réponse à la question 1 (Comparaison entre Somme et Sommable) :
// l'interface Sommable prend un seul parametre et l'aditionne avec la source alors que
// l'interface Somme prend les 2 arguments à sommer

    public static void main(String[] args) {

        Somme<Integer> sInt = (x, y) -> x + y;
        Somme<Double> sDouble = (x, y) -> x + y;
        Somme<Long> sLong = (x, y) -> x + y;
        Somme<String> sString = (x, y) -> x + y;

        System.out.println(sInt.sommer(10, 15));
        System.out.println(sDouble.sommer(11.50, 13.80));
        System.out.println(sString.sommer("exercice ", "question 1"));
        System.out.println(sLong.sommer(10l, 15l));


        ToString<List<String>> l2s = (list) ->
        {
            StringBuilder c = new StringBuilder();
            for(String s :list ){
                c.append(", ").append(s);
            }return c.toString();
        };



        System.out.println(l2s.toString(List.of("A", "B", "C", "toto")));
        // "A, B, C, toto"


        ToString<Map<String, Integer>> m2s = (map) -> {
            StringBuilder s = new StringBuilder();
            for(Map.Entry m : map.entrySet()){
                s.append(", ").append(m.getKey()).append(" : ").append(m.getValue());
            }
            return s.toString();
        };
        System.out.println(m2s.toString(Map.of("k1", 1, "k2", 2, "k3", 17)));
        // "k1: 1, k2: 2, k3: 17"



        /*
            Question 3 : pour chaque modèle dans {Function, Predicate, Consumer, Supplier}, donner une
            description de l’utilisation, dire si le modèle a des arguments et s’il retourne une valeur.
        */
    }
}
