public class Jeu {

    private Plateau p;
    private Ia ia;
    private int joueur1Type;
    private int joueur2Type;
    private int joueurCourant;

    public Jeu(Plateau p, int joueur1Type, int joueur2Type, int niveauIa) {
        this.p = p;
        this.joueur1Type = joueur1Type;
        this.joueur2Type = joueur2Type;
        this.joueurCourant = 1;
        this.ia = new Ia(niveauIa);
    }

    public Plateau getPlateau() {
        return p;
    }

    public int getTypeJoueurCourant() {
        switch (joueurCourant) {
            case 1:
                return joueur1Type;
            case 2:
                return joueur2Type;
            default:
                return 0;
        }
    }

    public void reset() {
        p.init_plateau();
        joueurCourant = 1;
    }

    public void jouerCoup(int i, int j) {
        p.clique(i, j);
        System.out.println("Joueur " + joueurCourant + " a joué: (" + i + ", " + j + ")");
        joueurCourant = (joueurCourant == 1) ? 2 : 1; // change de joueur
        System.out.println("A joueur" + joueurCourant + " de jouer");
        joueIa();
    }

    public void joueIa() {
        if (!p.estTermine() && getTypeJoueurCourant() == 1) { // Type 1 = IA
            Couple coup = ia.chercheCoup(p);
            if (coup == null || !p.cliquable(coup.x, coup.y)) {
                System.out.println("L'IA n'a pas trouvé de coup valide");
                return;
            }
            jouerCoup(coup.x, coup.y);
        }
    }
}
