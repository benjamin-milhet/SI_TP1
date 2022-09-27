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

    public void modifierCellule(int x, int y, char valeur) {
        this.echiquier[x][y].setTypeOccupation(valeur);
    }

    public void placerReine(int x, int y) {
        if (this.echiquier[x][y].getTypeOccupation() == this.libre) {

            for (int i = 0 ; i < this.taille ; i++) {
                this.echiquier[i][y].setTypeOccupation(this.menacee);
                this.echiquier[x][i].setTypeOccupation(this.menacee);
            }

            for (int i = 0 ; i < this.taille ; i++) {
                for (int j = 0 ; j < this.taille ; j++) {
                    if (i + j == x + y || i - j == x - y) {
                        this.echiquier[i][j].setTypeOccupation(this.menacee);
                    }
                }
            }

            this.echiquier[x][y].setTypeOccupation(this.reine);

        }
    }
    public String toString() {
        String res = "";

        for (int i = 0 ; i < this.taille ; i++) {
            for (int j = 0; j < this.taille; j++) {
                res += this.echiquier[i][j].getTypeOccupation() + " ";
            }
            res += "\n";
        }
        return res;
    }
}
