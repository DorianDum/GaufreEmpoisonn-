
import javax.swing.JFrame;

public class Gaufre{
    public static void main(String[] args){

        //Config :
        int nbLignes = 5;
        int nbColonnes = 5;
        int joueur1Type = 1; // 0 = humain
        int joueur2Type = 1; // 1 = IA
        int niveauIa = 0; // 0 = facile, 1 = moyen, 2 = difficile

        //Fin config

        System.out.println("Gaufre lancée");
        
        Plateau p = new Plateau(nbLignes, nbColonnes);
        //dans jeu : 0 = humain, 1 = IA
        Jeu j = new Jeu(p, joueur1Type, joueur2Type, niveauIa);
        Affichage affichage = new Affichage(j);
        
        JFrame frame = new JFrame("Gaufre");
        frame.add(affichage);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}