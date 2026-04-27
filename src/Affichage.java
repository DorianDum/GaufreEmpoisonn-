import javax.swing.JComponent;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;

public class Affichage extends JComponent {

    private Jeu jeu;

    public Affichage(Jeu jeu) {
        this.jeu = jeu;

        setFocusable(true);
        requestFocusInWindow();

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (jeu.getTypeJoueurCourant() != 0) { // Type 0 = Humain
                    return;
                }

                Plateau p = jeu.getPlateau();
                int w = getWidth() / p.nColonnes;
                int h = getHeight() / p.nLignes;

                int i = e.getY() / h;
                int j = e.getX() / w;

                if (p.cliquable(i, j)) {
                    jeu.jouerCoup(i, j);
                    repaint();
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    System.out.println("Mise à zéro du plateau");
                    jeu.reset();
                    repaint();
                    jeu.joueIa();
                }
            }
        });

        jeu.joueIa(); // Lancer l'IA si le joueur 1 est une IA au démarrage
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Plateau p = jeu.getPlateau();
        int w = getWidth() / p.nColonnes; // largeur d'une case
        int h = getHeight() / p.nLignes; // hauteur d'une case

        for (int i = 0; i < p.nLignes; i++) {
            for (int j = 0; j < p.nColonnes; j++) {
                if (j < p.plateau[i]) {
                    g.setColor(Color.YELLOW);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(j * w, i * h, w, h);
            }
        }
    }
}
