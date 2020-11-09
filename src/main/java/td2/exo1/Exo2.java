package td2.exo1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Exo2 {

    public static <T> List<T> filtragePredicatif(List<Predicate<T>> predicates, List<T> elements) {
        List<T> l = new ArrayList<>();
        for (T elem : elements) {
            boolean  elemVerifie = true;
            for (Predicate<T> p : predicates) {
                elemVerifie = p.test(elem);

                if (!elemVerifie) break;
            }
            if (elemVerifie) l.add(elem);
        }
        return l;
    }

    public static void main(String[] args) {
        Predicate<Paire<Integer, Double>> taille_trop_petite = (p) -> p.fst < 100;
        Predicate<Paire<Integer, Double>> taille_trop_grande = (p) -> p.fst > 200;
        Predicate<Paire<Integer, Double>> poids_trop_lourd = (p) -> p.snd > 150;

        Predicate<Paire<Integer, Double>> taille_incorrecte = taille_trop_petite.or(taille_trop_grande);
        Predicate<Paire<Integer, Double>> taille_correcte = taille_incorrecte.negate();
        Predicate<Paire<Integer, Double>> poids_correct = poids_trop_lourd.negate();
        Predicate<Paire<Integer, Double>> acces_autorise = taille_correcte.and(poids_correct);

        Paire<Integer, Double> p1 = new Paire<>(150, 70d);
        Paire<Integer, Double> p2 = new Paire<>(90, 70d);
        Paire<Integer, Double> p3 = new Paire<>(201, 70d);
        Paire<Integer, Double> p5 = new Paire<>(150, 155d);
        Paire<Integer, Double> p6 = new Paire<>(90, 155d);
        Paire<Integer, Double> p7 = new Paire<>(201, 155d);
        Paire<Integer, Double> p8 = new Paire<>(150, 155d);
        System.out.println(p1.toString() +": accept=" + acces_autorise.test(p1));
        System.out.println(p2.toString() +": accept=" + acces_autorise.test(p2));
        System.out.println(p3.toString() +": accept=" + acces_autorise.test(p3));
        System.out.println(p5.toString() +": accept=" + acces_autorise.test(p5));
        System.out.println(p6.toString() +": accept=" + acces_autorise.test(p6));
        System.out.println(p7.toString() +": accept=" + acces_autorise.test(p7));
        System.out.println(p8.toString() +": accept=" + acces_autorise.test(p8));
        List<Paire<Integer, Double>> l = filtragePredicatif(List.of(taille_correcte,poids_correct),List.of(p1, p2, p3,p5,p6, p7, p8 ));
        System.out.println("elements autorisés"+ l);
    }


}
