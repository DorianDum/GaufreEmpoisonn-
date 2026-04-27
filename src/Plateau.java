public class Plateau{
    short[] plateau;//tableau de cases
    int nLignes; //nombre de lignes
    int nColonnes; //nombre de colonnes
    int nCases; //nombre de cases

    public Plateau(int nLignes, int nColonnes){
        this.nLignes = nLignes;
        this.nColonnes = nColonnes;
        this.nCases = nLignes*nColonnes;
        this.plateau = new short[nCases];
        init_plateau();
    }

    public void init_plateau(){
        for(int i=0; i<nLignes; i++){
            plateau[i] = (short) nColonnes;
        }
        nCases = nLignes * nColonnes;
    }


    public boolean cliquable(int i, int j){
        if(i < 0 || i >= nLignes || j < 0 || j >= nColonnes){
            return false;
        }
        return j < plateau[i];
    }

    public void clique(int i, int j){
        for(int k = i; k < nLignes; k++){
            if (plateau[k] > j){
                nCases -= (plateau[k] - j);
                plateau[k] = (short) j;
            }  
        }
    }

    public boolean estTermine() {
        return nCases <= 0;
    }
}