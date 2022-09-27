package base;

public class Echiquier {

    private final Cellule[][] echiquier;
    private final int taille;

    private final char libre = '-';
    private final char reine = 'R';
    private final char menacee = '*';

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
        StringBuilder res = new StringBuilder();

        for (int i = 0 ; i < this.taille ; i++) {
            for (int j = 0; j < this.taille; j++) {
                res.append(this.echiquier[i][j].getTypeOccupation()).append(" ");
            }
            res.append("\n");
        }
        return res.toString();
    }

    public int[] meilleurPosition() {
        int[] res = new int[2];
        int nombreMenaceeMax = this.taille * this.taille + 1;

        for (int i = 0 ; i < this.taille ; i++) {
            for (int j = 0 ; j < this.taille ; j++) {
                if (this.echiquier[i][j].getTypeOccupation() == this.libre) {
                    int nombreMenacee = this.compterNbCaseMenacee(i, j);
                    if (nombreMenacee < nombreMenaceeMax) {
                        nombreMenaceeMax = nombreMenacee;
                        res[0] = i;
                        res[1] = j;
                    }
                }
            }
        }

        return res;
    }

    public int compterNbCaseMenacee(int x, int y) {
        int res = 0;
        int resTemp = 0;
        Cellule[][] echiquierBis = new Cellule[this.taille][this.taille];

        for (int i = 0 ; i < this.taille ; i++) {
            for (int j = 0 ; j < this.taille ; j++) {
                echiquierBis[i][j] = new Cellule(i, j, this.echiquier[i][j].getTypeOccupation());
            }
        }

        for (int i = 0 ; i < this.taille ; i++) {
            for (int j = 0 ; j < this.taille ; j++) {
                if (echiquierBis[i][j].getTypeOccupation() == this.menacee) resTemp++;
            }
        }

        for (int i = 0 ; i < this.taille ; i++) {
            echiquierBis[i][y].setTypeOccupation(this.menacee);
            echiquierBis[x][i].setTypeOccupation(this.menacee);
        }

        for (int i = 0 ; i < this.taille ; i++) {
            for (int j = 0 ; j < this.taille ; j++) {
                if (i + j == x + y || i - j == x - y) {
                    echiquierBis[i][j].setTypeOccupation(this.menacee);
                }
            }
        }

        echiquierBis[x][y].setTypeOccupation(this.reine);

        for (int i = 0 ; i < this.taille ; i++) {
            for (int j = 0 ; j < this.taille ; j++) {
                if (echiquierBis[i][j].getTypeOccupation() == this.menacee) res++;
            }
        }

        res -= resTemp;

        return res;
    }

}
