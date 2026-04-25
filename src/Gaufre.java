import java.util.*;
import javax.swing.JFrame;

public class Gaufre{
    public static void main(String[] args){
        System.out.println("Gaufre lancée");
        
        Plateau p = new Plateau(5, 5);
        //dans jeu : 0 = humain, 1 = IA
        Jeu j = new Jeu(p, 0, 1);
        
        JFrame frame = new JFrame("Gaufre");
        frame.add(j);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}