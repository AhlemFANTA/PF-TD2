package td2.exo3;

import td2.exo3.universite.Annee;
import td2.exo3.universite.Etudiant;
import td2.exo3.universite.Matiere;
import td2.exo3.universite.UE;

import java.util.Collection;
import java.util.Map;
import static java.util.Map.*;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

// voir reponses.md
public class MainExo3 {

    private static Matiere m1;
    private static Matiere m2;
    private static UE ue1;
    private static Matiere m3;
    private static UE ue2;
    private static Annee a1;

    private static Etudiant e1;
    private static Etudiant e2;
    private static Etudiant e3;


    // ---------------------------------------------------
    // ----------- LISTE DES PREDICATES ------------------
    private static final Predicate<Etudiant> aDEF = etudiant -> (estDefaillant(etudiant));

    private static final Predicate<Etudiant> aNoteEliminatoire = etudiant -> {
        Collection<Double> list = etudiant.notes().values();
        for (Double l : list)
            if (l < 6) {
                return true;
            }
        return false;
    };
    private static final Predicate<Etudiant> naPasLaMoyennev1 = etudiant -> moyenne(etudiant) < 10;

    private static final Predicate<Etudiant> naPasLaMoyennev2 = aDEF.or(naPasLaMoyennev1);

    private static final Predicate<Etudiant> session2v1 = aDEF.or(naPasLaMoyennev1).or(aNoteEliminatoire);

    static Predicate<Etudiant> naPasLaMoyenneGeneralisee(Function<Etudiant, Double> calculMoyenne) {
        return etudiant -> calculMoyenne.apply(etudiant) < 10;
    }

    // ---------------------------------------------------------------------
    // ---- REPONSES ------------------------------------------------------
    // --------------------------------------------------------------------

    // réponse 1 à la question 1
    private static void reponse1() {
        System.out.println("========== REPONSE Q1: ================");

        afficheSi(a1, "**TOUS LES ETUDIANTS (afficheSi)", etudiant -> etudiant.notes().containsKey(m3));
        afficheSi2(a1, "**TOUS LES ETUDIANTS (afficheSi2) /forEach", etudiant -> etudiant.notes().containsKey(m3));
        System.out.println("==== FIN Q1 ====\n");

    }

    // réponse 2 à la question 2
    private static void reponse2() {
        System.out.println("========== REPONSE Q2: ================");
        afficheSi(a1,"Etudiant défaillant", aDEF);
        System.out.println("==== FIN Q2 ====\n");
    }

    // réponse 3 à la question 3
    private static void reponse3() {
        System.out.println("========== REPONSE Q3: ================");
        // réponse 3 à la question 3

        afficheSi(a1,"**ETUDIANTS AVEC NOTE ELIMINATOIRE", aNoteEliminatoire);
        System.out.println("==== FIN Q3 ====\n");
    }

    // réponse 4 à la question 4
    private static void reponse4() {
        System.out.println("========== REPONSE Q4: ================");
        // réponse 4 à la question 4

        afficherMoyenne(a1, aDEF);
        System.out.println("==== FIN Q4 ====\n");
    }

    // réponse 5 à la question 5
    private static void reponse5() {
        System.out.println("========== REPONSE Q5: ================");
        // réponse 5 à la question 5

        /* Quand on utilise ce predicat sur un etudiant défaillant il affiche un NullPointerException
         car un étudiant défaillant ne peut pas avoir de moyenne. */
        // afficheSi(a1, "**ETUDIANTS SOUS LA MOYENNE", naPasLaMoyennev1);
        System.out.println("afficheSi(a1,\"**ETUDIANTS SOUS LA MOYENNE\", naPasLaMoyennev1) => NullPointerException");
        System.out.println("==== FIN Q5 ====\n");
    }

    // réponse 6 à la question 6
    private static void reponse6() {
        System.out.println("========== REPONSE Q6: ================");
        // réponse 6 à la question 6

        afficheSi(a1,"**ETUDIANTS SOUS LA MOYENNE", naPasLaMoyennev2);
        System.out.println("==== FIN 6 ====\n");
    }

    // réponse 7 à la question 7
    private static void reponse7() {
        System.out.println("========== REPONSE Q7: ================");
        // réponse 7 à la question 7

        /*Si je ne mets pas le predicat aDEF (etudiant défaillant) en premier j'aurais tjr un NullPointerException
         Predicate <Etudiant> session2v1 = (naPasLaMoyennev1).or(aNoteEliminatoire).or(aDEF); // ordre indiqué dans l'énoncé
         Avec l'ordre ci-dessous j'ai la liste des etudiants en session 2 qui s'affiche correctement. */
        afficheSi(a1,"**ETUDIANTS EN SESSION 2 (v2)", session2v1);
        System.out.println("==== FIN 7 ====\n");
    }

    // réponse 8 à la question 8
    private static void reponse8() {
        System.out.println("========== REPONSE Q8: ================");
        // réponse 8 à la question 8

        afficheSiv2(a1,"**TOUS LES ETUDIANTS V2s /fonction de representation", etudiant -> etudiant.notes().containsKey(m3), Etudiant::toString);
        afficheSiv2(a1,"**TOUS LES ETUDIANTS V2s /fonction ad-hoc anonyme", etudiant -> etudiant.notes().containsKey(m3), new Function<Etudiant, String>() {
            @Override
            public String apply(Etudiant etudiant) {
                if (moyenne(etudiant) == null) {
                    return etudiant.prenom() + " " + etudiant.nom() + " : " + "défaillant";
                } else {
                    return etudiant.prenom() + " " + etudiant.nom() + " : " + moyenne(etudiant);
                }
            }
        });
        System.out.println("==== FIN 8 ====\n");
    }

    // réponse 9 à la question 9
    private static void reponse9() {
        System.out.println("========== REPONSE Q9: ================");
        // réponse 9 à la question 9

        afficheSiv2(a1,"**TOUS LES ETUDIANTS V2s", etudiant -> etudiant.notes().containsKey(m3), new Function<Etudiant, String>() {
            @Override
            public String apply(Etudiant etudiant) {
                return etudiant.prenom() + " " + etudiant.nom() + " : " + moyenneIndicative(etudiant);
            }
        });
        System.out.println("==== FIN 9 ====\n");
    }

    // réponse 10 à la question 10
    private static void reponse10() {
        System.out.println("========== REPONSE Q10: ================");
        // réponse 10 à la question 10

        afficheSiv2(a1,"**TOUS LES ETUDIANTS SOUS LA MOYENNE INDICATIVE", naPasLaMoyenneGeneralisee(MainExo3::moyenneIndicative), new Function<Etudiant, String>() {
            @Override
            public String apply(Etudiant etudiant) {
                return etudiant.prenom() + " " + etudiant.nom() + " : " + moyenneIndicative(etudiant);
            }
        });
        e2.noter(m1, 20.0);
        e2.noter(m3, 20.0);
        afficheSiv2(a1,"**TOUS LES ETUDIANTS SOUS LA MOYENNE INDICATIVE", naPasLaMoyenneGeneralisee(MainExo3::moyenneIndicative), new Function<Etudiant, String>() {
            @Override
            public String apply(Etudiant etudiant) {
                return etudiant.prenom() + " " + etudiant.nom() + " : " + moyenneIndicative(etudiant);
            }
        });
        System.out.println("==== FIN 10 ====\n");
    }

    // --------------------------------------------------------------------
    // ---- FIN REPONSES --------------------------------------------------
    // --------------------------------------------------------------------


    // --------------------------------------------------------------------
    // ---- IMPLEMENTATION DES METHODES moyenne, affichage, etc -----------
    // --------------------------------------------------------------------

    /*
    Question 1 : écrire une fonction afficheSi qui prend en paramètre une chaîne de caractère en-tête,
    un prédicat portant sur un étudiant et une année et qui affiche l’en-tête suivi de tous les étudiants
    pour lesquels le prédicat est vrai. Utiliser dans un premier temps une boucle for puis dans un
    second temps un forEach avec un consommateur. Illustrez en utilisant cette fonction pour afficher
    tous les étudiants.
     */
    // Version 1 affichage avec une boucle for
    public static void afficheSi(Annee annee, String titre, Predicate<Etudiant> monPredicate) {
        System.out.println(titre);
        for (Etudiant etudiant : annee.etudiants()) {
            if (monPredicate.test(etudiant)) {
                System.out.println(etudiant);
            }
        }
    }

    // Version 2 avec un consommateur
    public static void afficheSi2(Annee annee,String titre, Predicate<Etudiant> monConso) {
        System.out.println(titre);
        annee.etudiants().forEach(etudiant -> {
            if (monConso.test(etudiant)) System.out.println(etudiant);
        });
    }

    /* question 8 - amélioration de afficheSi */
    public static void afficheSiv2(Annee annee, String titre, Predicate<Etudiant> monConso, Function<Etudiant, String> formateur) {
        System.out.println(titre);
        annee.etudiants().forEach(etudiant -> {
            if (monConso.test(etudiant)) {
                String s = formateur.apply(etudiant);
                System.out.println(s);
            }
        });
    }

    /* question 4 - affichage moyenne*/
    public static void afficherMoyenne(Annee annee, Predicate<Etudiant> aDef) {
        System.out.println("**MOYENNE DES ETUDIANTS");
        for (Etudiant etudiant : annee.etudiants()) {
            if (aDef.test(etudiant)) System.out.println(etudiant.nom() + " ->null");
            else System.out.println(etudiant.nom() + " -> moyenne -> " + moyenne(etudiant));
        }
    }

    /*
    Question 4 : écrire une fonction moyenne qui calcule la moyenne d’un étudiant selon la règle donnée
    On ne peut pas calculer de moyenne si l’étudiant est défaillant. Utiliser aDEF et retourner null dans
    ce cas.
     */

    public static Double moyenne(Etudiant etudiant) {
        if (estDefaillant(etudiant)) return null;
        Double n = matiereNotesCoef(etudiant);
        return n / sommeCoef(etudiant);
    }

    //question 9
    public static Double moyenneIndicative(Etudiant etudiant) {
        Double n = matiereNotesCoef(etudiant);
        return n / sommeCoef(etudiant);
    }

    public static Boolean estDefaillant(Etudiant etudiant) {
        for (UE ue : etudiant.annee().ues()) {
            Set<Matiere> listeDeMatieres = ue.ects().keySet();
            for (Matiere matiere : listeDeMatieres) {
                if (!etudiant.notes().containsKey(matiere)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static double sommeCoef(Etudiant etudiant) {
        double sommeCoef = 0;
        for (UE ue : etudiant.annee().ues()) {
            for (Integer i : ue.ects().values()) {
                sommeCoef += i;
            }
        }
        return sommeCoef;
    }

    private static Double matiereNotesCoef(Etudiant etudiant) {
        double somme = 0.0;

        for (Entry<Matiere, Double> couple : etudiant.notes().entrySet()) {
            Integer coef = coefMatiere(etudiant, couple.getKey());
            somme += couple.getValue() * coef;
        }
        return somme;
    }

    private static Integer coefMatiere(Etudiant etudiant, Matiere mat) {
        for (UE ue : etudiant.annee().ues()) {
            for (Entry<Matiere, Integer> couple : ue.ects().entrySet()) {
                if (couple.getKey().equals(mat)) {
                    return couple.getValue();
                }
            }
        }
        return null;
    }



    public static void main(String[] args) {
        // -----------------------------------------------
        // Initialisation des donnees:
        // -----------------------------------------------

        m1 = new Matiere("MAT1");
        m2 = new Matiere("MAT2");
        ue1 = new UE("UE1", Map.of(m1, 2, m2, 2));
        m3 = new Matiere("MAT3");
        ue2 = new UE("UE2", Map.of(m3, 1));
        a1 = new Annee(Set.of(ue1, ue2));

        e1 = new Etudiant("39001", "Alice", "Merveille", a1);
        e1.noter(m1, 12.0);
        e1.noter(m2, 14.0);
        e1.noter(m3, 10.0);
        // System.out.println(e1);
        e2 = new Etudiant("39002", "Bob", "Eponge", a1);
        e2.noter(m1, 14.0);
        e2.noter(m3, 14.0);
        e3 = new Etudiant("39003", "Charles", "Chaplin", a1);
        e3.noter(m1, 18.0);
        e3.noter(m2, 5.0);
        e3.noter(m3, 14.0);

        // ----------------------------------------------------------
        //  Pour les reponses, voir la fonction correspondante:
        // ---------------------------------------------------------

        // réponse 1 à la question 1
        reponse1();

        // réponse 2 à la question 2
        reponse2();


        // réponse 3 à la question 3
        reponse3();

        // réponse 4 à la question 4
        reponse4();


        // réponse 5 à la question 5
        reponse5();
        /* Quand on utilise ce predicat sur un etudiant défaillant il affiche un NullPointerException
         car un étudiant défaillant ne peut pas avoir de moyenne. */

        // réponse 6 à la question 6
        reponse6();

        // réponse 7 à la question 7
        reponse7();
        /* Si je ne mets pas le predicat aDEF (etudiant défaillant) en premier j'aurais tjr un NullPointerException
         Predicate <Etudiant> session2v1 = (naPasLaMoyennev1).or(aNoteEliminatoire).or(aDEF); // ordre indiqué dans l'énoncé
         Avec l'ordre ci-dessous j'ai la liste des etudiants en session 2 qui s'affiche correctement*/

        // réponse 8 à la question 8
        reponse8();

        // réponse 9 à la question 9
        reponse9();

        // réponse 10 à la question 10
        reponse10();
    }
}
