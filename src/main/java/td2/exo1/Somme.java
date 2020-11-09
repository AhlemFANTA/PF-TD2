package td2.exo1;

/* quesion1:
écrire une interface fonctionnelle Somme et utiliser cette dernière pour calculer la somme de deux entiers,
de deux doubles, de deux longs et de deux chaînes de caractères
(vous définirez 4 lambdas qui implantent Somme).
Comparer Somme et Sommable vu au TD1. Discuter.
 */
@FunctionalInterface
public interface Somme<T> {
    T sommer(T t1, T t2);
}
