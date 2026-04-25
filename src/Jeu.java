import javax.swing.JComponent;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;

public class Jeu extends JComponent{

    private Plateau p;
    private Ia ia;
    private int joueur1Type;
    private int joueur2Type;
    private int joueurCourant;

    public Jeu(Plateau p, int joueur1Type, int joueur2Type) {
        this.p = p;
        this.joueur1Type = joueur1Type;
        this.joueur2Type = joueur2Type;
        this.joueurCourant = 1;
        this.ia = new Ia();

        setFocusable(true);
        requestFocusInWindow();

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (getJoueurCourant() != 0) { // Type 0 = Humain
                    return;
                }

                int w = getWidth() / p.nColonnes;
                int h = getHeight() / p.nLignes;

                int i = e.getY() / h;
                int j = e.getX() / w;

                if (p.cliquable(i, j)) {
                    jouerCoup(i, j);
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_R) {
                    System.out.println("Mise à zéro du plateau");
                    p.init_plateau();
                    joueurCourant = 1;
                    repaint();
                    joueIa();
                }
            }
        });
    }

    private int getJoueurCourant() {
        switch (joueurCourant) {
            case 1:
                return joueur1Type;
            case 2:
                return joueur2Type;
            default:
                return 0;
        }
    }

    private void jouerCoup(int i, int j) {
        p.clique(i, j);
        repaint();
        System.out.println("Joueur " + joueurCourant + " a joué: (" + i + ", " + j + ")");
        joueurCourant = (joueurCourant == 1) ? 2 : 1;//change de joueur
        System.out.println("A joueur" + joueurCourant + " de jouer");
        joueIa();
    }

    private void joueIa() {
        //repaint();
        while (!p.estTermine() && getJoueurCourant() == 1) {//Type 1 = IA
            Couple coup = ia.chercheCoup(p);
            if (coup == null || !p.cliquable(coup.x, coup.y)) {
                System.out.println("L'IA n'a pas trouvé de coup valide");
                return;
            }
            jouerCoup(coup.x, coup.y);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth() / p.nColonnes;//largeur d'une case
        int h = getHeight() / p.nLignes;//hauteur d'une case

        for(int i=0; i<p.nLignes; i++){
            for(int j=0; j<p.nColonnes; j++){
                if(j < p.plateau[i]){
                    g.setColor(Color.YELLOW);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(j*w, i*h, w, h);
            }
        }
    }
}