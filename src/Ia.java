
import java.util.Random;
public class Ia {

    private Random random;

    public Ia() {
        this.random = new Random();
    }

    public Couple chercheCoup(Plateau p) {
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
                c = new Couple(i, j);
                break;
            }
            compteur += p.plateau[i];
        }

        /*
        try {
            Thread.sleep(500); // petite attente pour éviter que l'IA joue instantanément
        } catch (InterruptedException e) {
            System.out.println("erreur dans le sleep" + e.getMessage());
        }
        */

        return c;
    }

}