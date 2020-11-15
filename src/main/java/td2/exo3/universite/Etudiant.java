package td2.exo3.universite;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;

public class Etudiant {
    private String prenom;
    private String nom;
    private String numero;
    private Map<Matiere, Double> notes;
    private Annee annee;

    public Etudiant(String numero, String prenom, String nom, Annee a) {
        this.prenom = prenom;
        this.nom = nom;
        this.numero = numero;
        this.notes = new HashMap<>();
        this.annee = a;
        this.annee.inscrire(this);
    }

    /*
    Question 4 : écrire une fonction moyenne qui calcule la moyenne d’un étudiant selon la règle donnée
    On ne peut pas calculer de moyenne si l’étudiant est défaillant. Utiliser aDEF et retourner null dans
    ce cas.
     */

    public Double moyenne() {
        if (estDefaillant()) return null;
        Double n = matiereNotesCoef();
        return n / sommeCoef();
    }

    //question 9
    public Double moyenneIndicative() {
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

    private double sommeCoef() {
        double sommeCoef = 0;
        for (UE ue : annee.ues()) {
            for (Integer i : ue.ects().values()) {
                sommeCoef += i;
            }
        }
        return sommeCoef;
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


    public String prenom() {
        return prenom;
    }

    public String nom() {
        return nom;
    }

    public String numero() {
        return numero;
    }

    public Annee annee() {
        return annee;
    }

    public Map<Matiere, Double> notes() {
        return notes;
    }

    public void noter(Matiere m, Double note) {
        this.notes.put(m, note);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Etudiant))
            return false;
        return ((Etudiant) obj).numero().equals(numero);
    }

    @Override
    public int hashCode() {
        return numero.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder rtr = new StringBuilder();
        rtr.append(String.format("%s %s %s\n", numero, prenom, nom));
        for (UE ue : annee.ues()) {
            rtr.append(String.format("%s\n", ue.nom()));
            for (Entry<Matiere, Integer> ects : ue.ects().entrySet()) {
                Matiere matiere = ects.getKey();
                Integer credits = ects.getValue();
                String note = notes.containsKey(matiere) ? notes.get(matiere).toString() : "DEF";
                rtr.append(String.format("%s (%d) : %s\n", matiere, credits, note));
            }
        }
        return rtr.toString();
    }

    // getters
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
}
