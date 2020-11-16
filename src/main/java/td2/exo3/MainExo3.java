package td2.exo3;

import td2.exo3.universite.Annee;
import td2.exo3.universite.Etudiant;
import td2.exo3.universite.Matiere;
import td2.exo3.universite.UE;

import java.util.Collection;
import java.util.Map;
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
    private static final Predicate<Etudiant> aDEF = etudiant -> (etudiant.estDefaillant());

    private static final Predicate<Etudiant> aNoteEliminatoire = etudiant -> {
        Collection<Double> list = etudiant.notes().values();
        for (Double l : list)
            if (l < 6) {
                return true;
            }
        return false;
    };
    private static final Predicate<Etudiant> naPasLaMoyennev1 = etudiant -> etudiant.moyenne() < 10;

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

        a1.afficheSi("**TOUS LES ETUDIANTS (afficheSi)", etudiant -> etudiant.notes().containsKey(m3));
        a1.afficheSi2("**TOUS LES ETUDIANTS (afficheSi2) /forEach", etudiant -> etudiant.notes().containsKey(m3));
        System.out.println("==== FIN Q1 ====\n");

    }

    // réponse 2 à la question 2
    private static void reponse2() {
        System.out.println("========== REPONSE Q2: ================");
        a1.afficheSi("Etudiant défaillant", aDEF);
        System.out.println("==== FIN Q2 ====\n");
    }

    // réponse 3 à la question 3
    private static void reponse3() {
        System.out.println("========== REPONSE Q3: ================");
        // réponse 3 à la question 3

        a1.afficheSi("**ETUDIANTS AVEC NOTE ELIMINATOIRE", aNoteEliminatoire);
        System.out.println("==== FIN Q3 ====\n");
    }

    // réponse 4 à la question 4
    private static void reponse4() {
        System.out.println("========== REPONSE Q4: ================");
        // réponse 4 à la question 4

        a1.afficherMoyenne(aDEF);
        System.out.println("==== FIN Q4 ====\n");
    }

    // réponse 5 à la question 5
    private static void reponse5() {
        System.out.println("========== REPONSE Q5: ================");
        // réponse 5 à la question 5

        /* Quand on utilise ce predicat sur un etudiant défaillant il affiche un NullPointerException
         car un étudiant défaillant ne peut pas avoir de moyenne. */
        // a1.afficheSi("**ETUDIANTS SOUS LA MOYENNE", naPasLaMoyennev1);
        System.out.println("a1.afficheSi(\"**ETUDIANTS SOUS LA MOYENNE\", naPasLaMoyennev1) => NullPointerException");
        System.out.println("==== FIN Q5 ====\n");
    }

    // réponse 6 à la question 6
    private static void reponse6() {
        System.out.println("========== REPONSE Q6: ================");
        // réponse 6 à la question 6

        a1.afficheSi("**ETUDIANTS SOUS LA MOYENNE", naPasLaMoyennev2);
        System.out.println("==== FIN 6 ====\n");
    }

    // réponse 7 à la question 7
    private static void reponse7() {
        System.out.println("========== REPONSE Q7: ================");
        // réponse 7 à la question 7

        /*Si je ne mets pas le predicat aDEF (etudiant défaillant) en premier j'aurais tjr un NullPointerException
         Predicate <Etudiant> session2v1 = (naPasLaMoyennev1).or(aNoteEliminatoire).or(aDEF); // ordre indiqué dans l'énoncé
         Avec l'ordre ci-dessous j'ai la liste des etudiants en session 2 qui s'affiche correctement. */
        a1.afficheSi("**ETUDIANTS EN SESSION 2 (v2)", session2v1);
        System.out.println("==== FIN 7 ====\n");
    }

    // réponse 8 à la question 8
    private static void reponse8() {
        System.out.println("========== REPONSE Q8: ================");
        // réponse 8 à la question 8

        a1.afficheSiv2("**TOUS LES ETUDIANTS V2s /fonction de representation", etudiant -> etudiant.notes().containsKey(m3), Etudiant::toString);
        a1.afficheSiv2("**TOUS LES ETUDIANTS V2s /fonction ad-hoc anonyme", etudiant -> etudiant.notes().containsKey(m3), new Function<Etudiant, String>() {
            @Override
            public String apply(Etudiant etudiant) {
                if (etudiant.moyenne() == null) {
                    return etudiant.getPrenom() + " " + etudiant.getNom() + " : " + "défaillant";
                } else {
                    return etudiant.getPrenom() + " " + etudiant.getNom() + " : " + etudiant.moyenne();
                }
            }
        });
        System.out.println("==== FIN 8 ====\n");
    }

    // réponse 9 à la question 9
    private static void reponse9() {
        System.out.println("========== REPONSE Q9: ================");
        // réponse 9 à la question 9

        a1.afficheSiv2("**TOUS LES ETUDIANTS V2s", etudiant -> etudiant.notes().containsKey(m3), new Function<Etudiant, String>() {
            @Override
            public String apply(Etudiant etudiant) {
                return etudiant.getPrenom() + " " + etudiant.getNom() + " : " + etudiant.moyenneIndicative();
            }
        });
        System.out.println("==== FIN 9 ====\n");
    }

    // réponse 10 à la question 10
    private static void reponse10() {
        System.out.println("========== REPONSE Q10: ================");
        // réponse 10 à la question 10

        a1.afficheSiv2("**TOUS LES ETUDIANTS SOUS LA MOYENNE INDICATIVE", naPasLaMoyenneGeneralisee(Etudiant::moyenneIndicative), new Function<Etudiant, String>() {
            @Override
            public String apply(Etudiant etudiant) {
                return etudiant.getPrenom() + " " + etudiant.getNom() + " : " + etudiant.moyenneIndicative();
            }
        });
        e2.noter(m1, 20.0);
        e2.noter(m3, 20.0);
        a1.afficheSiv2("**TOUS LES ETUDIANTS SOUS LA MOYENNE INDICATIVE", naPasLaMoyenneGeneralisee(Etudiant::moyenneIndicative), new Function<Etudiant, String>() {
            @Override
            public String apply(Etudiant etudiant) {
                return etudiant.getPrenom() + " " + etudiant.getNom() + " : " + etudiant.moyenneIndicative();
            }
        });
        System.out.println("==== FIN 10 ====\n");
    }

    // ---------------------------------------------------------------------
    // ---- FIN REPONSES ---------------------------------------------------

    // --------------------------------------------------------------------


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
        //a1.afficheSi("**TOUS LES ETUDIANTS",etudiant -> etudiant.notes().containsKey(m3));
        //a1.afficheSi2("**TOUS LES ETUDIANTS",etudiant -> etudiant.notes().containsKey(m3));

        // réponse 2 à la question 2
        reponse2();
        // Predicate<Etudiant> aDEF = etudiant -> (etudiant.estDefaillant());
        //a1.afficheSi("Etudiant défaillant",aDEF);

        // réponse 3 à la question 3
        reponse3();
        /*Predicate<Etudiant> aNoteEliminatoire = etudiant -> {
            Collection<Double> list = etudiant.notes().values();
            for (Double l : list)
                if (l < 6) {
                    return true;
                }
            return false;
        };
         System.out.println("*".repeat(30) + "\n");
         a1.afficheSi("**ETUDIANTS AVEC NOTE ELIMINATOIRE",aNoteEliminatoire);*/

        // réponse 4 à la question 4
        reponse4();
        //a1.afficherMoyenne(aDEF);

        // réponse 5 à la question 5
        reponse5();
        /* Quand on utilise ce predicat sur un etudiant défaillant il affiche un NullPointerException
         car un étudiant défaillant ne peut pas avoir de moyenne. */
        //a1.afficheSi("**ETUDIANTS SOUS LA MOYENNE",naPasLaMoyennev1);

        // réponse 6 à la question 6
        reponse6();
        //a1.afficheSi("**ETUDIANTS SOUS LA MOYENNE",naPasLaMoyennev2);

        // réponse 7 à la question 7
        reponse7();
        /*Si je ne mets pas le predicat aDEF (etudiant défaillant) en premier j'aurais tjr un NullPointerException
         Predicate <Etudiant> session2v1 = (naPasLaMoyennev1).or(aNoteEliminatoire).or(aDEF); // ordre indiqué dans l'énoncé
         Avec l'ordre ci-dessous j'ai la liste des etudiants en session 2 qui s'affiche correctement*/
        //a1.afficheSi("**ETUDIANTS EN SESSION 2 (v2)",session2v1);


        // réponse 8 à la question 8
        reponse8();
        // a1.afficheSiv2("**TOUS LES ETUDIANTS V2s",etudiant -> etudiant.notes().containsKey(m3), Etudiant::toString);
        /* a1.afficheSiv2("**TOUS LES ETUDIANTS V2s", etudiant -> etudiant.notes().containsKey(m3), new Function<Etudiant, String>() {
            @Override
            public String apply(Etudiant etudiant) {
                if (etudiant.moyenne()==null) {
                    return etudiant.getPrenom() + " " + etudiant.getNom() + " : " + "défaillant";
                }else{
                    return etudiant.getPrenom()  + " " + etudiant.getNom() + " : " + etudiant.moyenne();
                }
            }
         });*/

        // réponse 9 à la question 9
        reponse9();
        /* a1.afficheSiv2("**TOUS LES ETUDIANTS V2s",etudiant -> etudiant.notes().containsKey(m3), new Function<Etudiant, String>() {
            @Override
            public String apply(Etudiant etudiant) {
                    return etudiant.getPrenom()  + " " + etudiant.getNom() + " : " + etudiant.moyenneIndicative();
                }
        });*/

        // réponse 10 à la question 10
        reponse10();
        /* a1.afficheSiv2("**TOUS LES ETUDIANTS V2s", naPasLaMoyenneGeneralisee(Etudiant::moyenneIndicative), new Function<Etudiant, String>() {
            @Override
            public String apply(Etudiant etudiant) {
                return etudiant.getPrenom() + " " + etudiant.getNom() + " : " + etudiant.moyenneIndicative();
            }
        });
        e2.noter(m1, 20.0);
        e2.noter(m3, 20.0);
        a1.afficheSiv2("**TOUS LES ETUDIANTS V2s", naPasLaMoyenneGeneralisee(Etudiant::moyenneIndicative), new Function<Etudiant, String>() {
            @Override
            public String apply(Etudiant etudiant) {
                return etudiant.getPrenom() + " " + etudiant.getNom() + " : " + etudiant.moyenneIndicative();
            }
        });*/
    }
}
