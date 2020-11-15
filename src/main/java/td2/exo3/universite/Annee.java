package td2.exo3.universite;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class Annee {
    private Set<UE> ues;
    private Set<Etudiant> etudiants;

    public Annee(Set<UE> ues) {
        this.ues = ues;
        this.etudiants = new HashSet<>();
    }

    /*
    Question 1 : écrire une fonction afficheSi qui prend en paramètre une chaîne de caractère en-tête,
    un prédicat portant sur un étudiant et une année et qui affiche l’en-tête suivi de tous les étudiants
    pour lesquels le prédicat est vrai. Utiliser dans un premier temps une boucle for puis dans un
    second temps un forEach avec un consommateur. Illustrez en utilisant cette fonction pour afficher
    tous les étudiants.
     */
    // Version 1 affichage avec une boucle for
    public void afficheSi(String titre, Predicate<Etudiant> monPredicate) {
        System.out.println(titre);
        for (Etudiant etudiant : etudiants) {
            if (monPredicate.test(etudiant)) {
                System.out.println(etudiant);
            }
        }
    }

    // Version 2 avec un consommateur
    public void afficheSi2(String titre, Predicate<Etudiant> monConso) {
        System.out.println(titre);
        etudiants.forEach(etudiant -> {
            if (monConso.test(etudiant)) System.out.println(etudiant);
        });
    }

    /* question 8 - amélioration de afficheSi */
    public void afficheSiv2(String titre, Predicate<Etudiant> monConso, Function<Etudiant, String> formateur) {
        System.out.println(titre);
        etudiants.forEach(etudiant -> {
            if (monConso.test(etudiant)) {
                String s = formateur.apply(etudiant);
                System.out.println(s);
            }
        });
    }


    /* question 4 - affichage moyenne*/
    public void afficherMoyenne(Predicate<Etudiant> aDef) {
        System.out.println("**MOYENNE DES ETUDIANTS");
        for (Etudiant etudiant : etudiants) {
            if (aDef.test(etudiant)) System.out.println(etudiant.nom() + " ->null");
            else System.out.println(etudiant.nom() + " -> moyenne -> " + etudiant.moyenne());
        }
    }


    public Set<UE> ues() {
        return ues;
    }

    public Set<Etudiant> etudiants() {
        return etudiants;
    }

    void inscrire(Etudiant e) {
        etudiants.add(e);
    }
}
