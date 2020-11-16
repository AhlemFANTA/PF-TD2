## Réponse -> Exercice 1 

##### Réponse à la question 1 (Comparaison entre Somme et Sommable) :
* L'interface Sommable prend un seul parametre et l'aditionne avec la source alors que l'interface Somme prend les 2 arguments à sommer.

##### Réponse à la question 3 (Function, Predicate, Consumer, Supplier) :

* Consumer (argument) Oui(T) (retour) Non,
Consume une entrée et ne retourne rien, Stockage Lambda expression : signature (T)  void

* Supplier (argument) Non (retour) Oui (T)
Générer une sortie, Stockage Lambda expression : signature () T

* Predicate (argument) Oui (T) (retour) boolean
Tests l’argument selon un critère et renvoie vrai ou faux.
Stockage Lambda expression : signature (T) boolean

* BiPredicate (argument) Oui (T,U)/ Arité: 2 (retour) boolean
Tests les 2 arguments de types potentiellement différents selon
un critère et renvoie vrai ou faux.  
 Stockage Lambda expression : signature (T,U)  boolean

* Function (argument) Oui (T) (retour) Oui (R)
Convertie (map) un type vers un autre. Lambda avec une variable
Stockage de lambda expression : signature (T)  R



## Réponse -> Exercice 3 (Etude de cas)
* Nb : pour visualiser le résulat veuillez exécuter le main qui se trouve dans la classe MainExo3
##### Réponse 1 
```java
public class Annee {
    ....

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
    a1.afficheSi("**TOUS LES ETUDIANTS (afficheSi)", etudiant -> etudiant.notes().containsKey(m3));
    a1.afficheSi2("**TOUS LES ETUDIANTS (afficheSi2) /forEach", etudiant -> etudiant.notes().containsKey(m3));
}
```
##### Réponse 2
```java
public class MainExo3 {
    ....
    //écrire un prédicat aDEF
    private static final Predicate<Etudiant> aDEF = etudiant -> (etudiant.estDefaillant());
    a1.afficheSi("Etudiant défaillant", aDEF);

}
```
##### Réponse 3
```java
public class MainExo3 {
    ....
    //écrire un prédicat aNoteEliminatoire
    private static final Predicate<Etudiant> aNoteEliminatoire = etudiant -> {
        Collection<Double> list = etudiant.notes().values();
        for (Double l : list)
            if (l < 6) {
                return true;
            }
        return false;
    };
    a1.afficheSi("**ETUDIANTS AVEC NOTE ELIMINATOIRE", aNoteEliminatoire);
}
```
##### Réponse 4
```java
public class MainExo3 {
    ....
    // question 4 - affichage moyenne 
    a1.afficherMoyenne(aDEF);
}
```
```java
public class Annee {
    ....
    public void afficherMoyenne(Predicate<Etudiant> aDef) {
        System.out.println("**MOYENNE DES ETUDIANTS");
        for (Etudiant etudiant : etudiants) {
            if (aDef.test(etudiant)) System.out.println(etudiant.nom() + " ->null");
            else System.out.println(etudiant.nom() + " -> moyenne -> " + etudiant.moyenne());
        }
    }
}
```
```java
public class Etudiant {
    ....
    public Double moyenne() {
        if (estDefaillant()) return null;
        Double n = matiereNotesCoef();
        return n / sommeCoef();
    }

    public Boolean estDefaillant() {
        for (UE ue : annee.ues()) {
            Set<Matiere> listeDeMatieres = ue.ects().keySet();
            for (Matiere matiere : listeDeMatieres) {
                if (!notes().containsKey(matiere)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Double matiereNotesCoef() {
        double somme = 0.0;

        for (Entry<Matiere, Double> couple : notes.entrySet()) {
            Integer coef = coefMatiere(couple.getKey());
            somme += couple.getValue() * coef;
        }
        return somme;
    }

    private Integer coefMatiere(Matiere mat) {
        for (UE ue : annee.ues()) {
            for (Entry<Matiere, Integer> couple : ue.ects().entrySet()) {
                if (couple.getKey().equals(mat)) {
                    return couple.getValue();
                }
            }
        }
        return null;
    }

    private double sommeCoef() {
        double sommeCoef = 0;
        for (UE ue : annee.ues()) {
            for (Integer i : ue.ects().values()) {
                sommeCoef += i;
            }
        }
        return sommeCoef;
    }
}
```
##### Réponse 5
```java
public class MainExo3 {
    ....
    //définir un prédicat naPasLaMoyennev1
    /* Quand on utilise ce predicat sur un etudiant défaillant il affiche un NullPointerException
     car un étudiant défaillant ne peut pas avoir de moyenne. */
    private static final Predicate<Etudiant> naPasLaMoyennev1 = etudiant -> etudiant.moyenne() < 10;
    a1.afficheSi("**ETUDIANTS SOUS LA MOYENNE", naPasLaMoyennev1);
}
```
##### Réponse 6
```java
public class MainExo3 {
    ....
    //définir un prédicat naPasLaMoyennev2
    private static final Predicate<Etudiant> naPasLaMoyennev2 = aDEF.or(naPasLaMoyennev1);
    a1.afficheSi("**ETUDIANTS SOUS LA MOYENNE", naPasLaMoyennev2);
}
```
##### Réponse 7
```java
public class MainExo3 {
    ....
    //définir un prédicat session2v1
    /*Si je ne mets pas le predicat aDEF (etudiant défaillant) en premier j'aurais tjr un NullPointerException
    Predicate <Etudiant> session2v1 = (naPasLaMoyennev1).or(aNoteEliminatoire).or(aDEF); // ordre indiqué dans l'énoncé
    Avec l'ordre ci-dessous j'ai la liste des etudiants en session 2 qui s'affiche correctement. */
    private static final Predicate<Etudiant> session2v1 = aDEF.or(naPasLaMoyennev1).or(aNoteEliminatoire);
    a1.afficheSi("**ETUDIANTS EN SESSION 2 (v2)",session2v1);
}
```
##### Réponse 8
```java
public class MainExo3 {
    ....
    //écrire une fonction afficheSiv2
     a1.afficheSiv2("**TOUS LES ETUDIANTS V2s /fonction de representation",etudiant -> etudiant.notes().containsKey(m3), Etudiant::toString);
     a1.afficheSiv2("**TOUS LES ETUDIANTS V2s /fonction ad-hoc anonyme", etudiant -> etudiant.notes().containsKey(m3), new Function<Etudiant, String>() {
         @Override
         public String apply(Etudiant etudiant) {
             if (etudiant.moyenne()==null) {
                 return etudiant.getPrenom() + " " + etudiant.getNom() + " : " + "défaillant";
             }else{
                 return etudiant.getPrenom()  + " " + etudiant.getNom() + " : " + etudiant.moyenne();
             }
         }
      });
}
```
```java
public class Annee {
    ....
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
}
```
##### Réponse 9
```java
public class MainExo3 {
    ....
    //écrire une fonction moyenneIndicative
    a1.afficheSiv2("**TOUS LES ETUDIANTS V2s",etudiant -> etudiant.notes().containsKey(m3), new Function<Etudiant, String>() {
        @Override
        public String apply(Etudiant etudiant) {
                return etudiant.getPrenom()  + " " + etudiant.getNom() + " : " + etudiant.moyenneIndicative();
            }
    });
}
```
```java
public class Etudiant {
    ....
    public Double moyenneIndicative() {
        Double n = matiereNotesCoef();
        return n / sommeCoef();
    }

    private Double matiereNotesCoef() {
        double somme = 0.0;

        for (Entry<Matiere, Double> couple : notes.entrySet()) {
            Integer coef = coefMatiere(couple.getKey());
            somme += couple.getValue() * coef;
        }
        return somme;
    }

    private Integer coefMatiere(Matiere mat) {
        for (UE ue : annee.ues()) {
            for (Entry<Matiere, Integer> couple : ue.ects().entrySet()) {
                if (couple.getKey().equals(mat)) {
                    return couple.getValue();
                }
            }
        }
        return null;
    }

    private double sommeCoef() {
        double sommeCoef = 0;
        for (UE ue : annee.ues()) {
            for (Integer i : ue.ects().values()) {
                sommeCoef += i;
            }
        }
        return sommeCoef;
    }
}
```
##### Réponse 10
```java
public class MainExo3 {
    ....
    //écrire une fonction naPasLaMoyenneGeneralise
    static Predicate<Etudiant> naPasLaMoyenneGeneralisee(Function<Etudiant, Double> calculMoyenne) {
        return etudiant -> calculMoyenne.apply(etudiant) < 10;
    }

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
}
```