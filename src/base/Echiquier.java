package base;

public class Echiquier {

    private Cellule[][] echiquier;
    private int taille;

    private char libre = '-';
    private char reine = 'R';
    private char menacee = '*';

    public Echiquier(int taille) {
        this.taille = taille;
        this.echiquier = new Cellule[taille][taille];
        this.initialiserEchequier();

    }

    public void initialiserEchequier() {
        for (int i = 0 ; i < this.taille ; i++) {
            for (int j = 0 ; j < this.taille ; j++) {
                this.echiquier[i][j] = new Cellule(i, j, this.libre);
            }
        }
    }

    public void modifierCellule(int i, int j, char valeur) {
        this.echiquier[i][j].setTypeOccupation(valeur);
    }

    public void placerReine(int i, int j) {
        
    }
}
