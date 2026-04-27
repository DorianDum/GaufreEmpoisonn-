
import java.util.Random;
public class Ia {

    private Random random;
    private int niveau;

    public Ia(int niveau) {
        this.random = new Random();
        this.niveau = niveau; // Niveau spécifié
    }

    public Couple chercheCoup(Plateau p) {
        switch (niveau) {
            case 0: // Facile
                return CoupRandom(p);
            case 1: // Moyen
                // Pour l'instant, on retourne un coup aléatoire pour le niveau moyen
                return CoupRandom(p);
            case 2: // Difficile
                // Pour l'instant, on retourne un coup aléatoire pour le niveau difficile
                return CoupRandom(p);
            default:
                return CoupRandom(p);
        }
    }

    private Couple CoupRandom(Plateau p){
        Couple c = null;
        if (p == null) {
            return new Couple(0, 0); // Retourne un coup par défaut si le plateau est null
        }
        
        // Sélectionner un coup aléatoire parmi les coups valides
        int choix = random.nextInt(p.nCases);
        int compteur = 0;
        for(int i = 0; i < p.nLignes; i++) {
            if(compteur + p.plateau[i] > choix) {
                int j = choix - compteur;
                return new Couple(i, j);
            }
            compteur += p.plateau[i];
        }

        return c;//sensé jamais arriver

    }

}