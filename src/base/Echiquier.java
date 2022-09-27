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

    public int[] meilleurPosition() {
        int[] res = new int[2];
        int max = 0;
        int nbMenacees = 0;
        for (int i = 0 ; i < this.taille ; i++) {
            for (int j = 0 ; j < this.taille ; j++) {
                if (this.echiquier[i][j].getTypeOccupation() == this.libre) {
                    nbMenacees = this.nombreMenacees(i, j);
                    if (nbMenacees > max) {
                        max = nbMenacees;
                        res[0] = i;
                        res[1] = j;
                    }
                }
            }
        }
        return res;
    }

    public int nombreMenacees(int x, int y) {
        int res = 0;
        for (int i = 0 ; i < this.taille ; i++) {
            if (this.echiquier[i][y].getTypeOccupation() == this.menacee) {
                res++;
            }
            if (this.echiquier[x][i].getTypeOccupation() == this.menacee) {
                res++;
            }
        }
        for (int i = 0 ; i < this.taille ; i++) {
            for (int j = 0 ; j < this.taille ; j++) {
                if (i + j == x + y || i - j == x - y) {
                    if (this.echiquier[i][j].getTypeOccupation() == this.menacee) {
                        res++;
                    }
                }
            }
        }
        return res;
    }
}
