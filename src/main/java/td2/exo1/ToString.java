package td2.exo1;

/* quesion2:
écrire une interface fonctionnelle ToString générique sur un type T et permettant de
convertir un T en String. Créer deux lambdas implémentations de ToString, une pour les listes de
String (l2s) et une pour les map String → Integer (m2s) dont l’effet est respectivement de créér une
chaîne de la forme "e1, e2, …" et de la forme "k1: v1, k2: v2, …".
 */
@FunctionalInterface
public interface ToString<T> {
    String toString(T t);
}

/*
question3: {Function, Predicate, Consumer, Supplier}

Consumer (argument) Oui(T) (retour) Non,
Consume une entrée et ne retourne rien, Stockage Lambda expression : signature (T)  void

Supplier (argument) Non (retour) Oui (T)
Générer une sortie, Stockage Lambda expression : signature () T

Predicate (argument) Oui (T) (retour) boolean
Tests l’argument selon un critère et renvoie vrai ou faux.
Stockage Lambda expression : signature (T) boolean

BiPredicate (argument) Oui (T,U)/ Arité: 2 (retour) boolean
Tests les 2 arguments de types potentiellement différents selon
un critère et renvoie vrai ou faux.
Stockage Lambda expression : signature (T,U)  boolean

Function (argument) Oui (T) (retour) Oui (R)
Convertie (map) un type vers un autre. Lambda avec une variable
Stockage de lambda expression : signature (T)  R

 */