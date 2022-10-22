package base;

public class Echiquier {

    private final Cellule[][] echiquier; // tableau de cellules
    private final int taille; // taille de l'échiquier

    // Ensemble des possibles etats d'une case
    private final char libre = '-';
    private final char reine = 'R';
    private final char menacee = '*';

    // Constructeur
    public Echiquier(int taille) {
        this.taille = taille; // taille de l'échiquier
        this.echiquier = new Cellule[taille][taille]; // tableau de cellules
        this.initialiserEchequier(); // initialisation de l'échiquier

    }

    // Initialisation de l'échiquier
    public void initialiserEchequier() {
        // Parcours de l'échiquier
        for (int i = 0 ; i < this.taille ; i++) {
            for (int j = 0 ; j < this.taille ; j++) {
                this.echiquier[i][j] = new Cellule(i, j, this.libre); // initialisation de toute les cellules par une cellule libre
            }
        }
    }

    // Permet de modifier l'état d'une cellule
    public void modifierCellule(int x, int y, char valeur) {
        this.echiquier[x][y].setTypeOccupation(valeur);
    }

    // Permet de placer une reine sur l'échiquier
    public void placerReine(int x, int y) {
        if (this.echiquier[x][y].getTypeOccupation() == this.libre) { // si la cellule est libre

            for (int i = 0 ; i < this.taille ; i++) { // parcours de l'échiquier
                this.modifierCellule(i, y, this.menacee); // modification de la colonne en mettant des cellules menacées
                this.modifierCellule(x, i, this.menacee); // modification de la ligne en mettant des cellules menacées
            }

            // modification des diagonales en mettant des cellules menacées
            for (int i = 0 ; i < this.taille ; i++) {
                for (int j = 0 ; j < this.taille ; j++) {
                    if (i + j == x + y || i - j == x - y) {
                        this.modifierCellule(i, j, this.menacee);
                    }
                }
            }

            this.modifierCellule(x, y, this.reine); // modification de la cellule en mettant une reine
        }
    }

    // Permet d'afficher l'échiquier
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

    // Algorithme pour poser un maximum de reines sur l'échiquier
    public int[] meilleurPosition() {
        int[] res = new int[2]; // tableau de 2 cases pour stocker la position de la nouvelle reine
        int nombreMenaceeMax = this.taille * this.taille + 1; // nombre de menace maximum possible

        // Parcours de l'échiquier
        for (int i = 0 ; i < this.taille ; i++) {
            for (int j = 0 ; j < this.taille ; j++) {
                if (this.echiquier[i][j].getTypeOccupation() == this.libre) { // si la cellule est libre
                    int nombreMenacee = this.compterNbCaseMenacee(i, j); // on récupère le nombre de cellules menacées minimum par la nouvelle reine
                    if (nombreMenacee < nombreMenaceeMax) { // si le nombre de cellules menacées est inférieur au nombre de cellules menacées maximum
                        nombreMenaceeMax = nombreMenacee; // on met à jour le nombre de cellules menacées maximum
                        res[0] = i; // on met à jour la position de la nouvelle reine
                        res[1] = j; // on met à jour la position de la nouvelle reine
                    }
                }
            }
        }

        return res; // on retourne la position de la nouvelle reine
    }

    // Permet de compter le nombre de cellules menacées par une potentiel nouvelle reine
    public int compterNbCaseMenacee(int x, int y) {
        int res = 0;
        int resTemp = 0;
        Cellule[][] echiquierBis = new Cellule[this.taille][this.taille]; // on crée un échiquier temporaire

        // On copie l'échiquier dans l'échiquier temporaire
        for (int i = 0 ; i < this.taille ; i++) {
            for (int j = 0 ; j < this.taille ; j++) {
                echiquierBis[i][j] = new Cellule(i, j, this.echiquier[i][j].getTypeOccupation());
            }
        }

        // On compte le nombre de menace déja présente sur l'échiquier
        for (int i = 0 ; i < this.taille ; i++) {
            for (int j = 0 ; j < this.taille ; j++) {
                if (echiquierBis[i][j].getTypeOccupation() == this.menacee) resTemp++;
            }
        }

        // On place la nouvelle reine sur l'échiquier temporaire et on ajoute les nouvelles menaces
        for (int i = 0 ; i < this.taille ; i++) {
            echiquierBis[i][y].setTypeOccupation(this.menacee); // modification de la colonne en mettant des cellules menacées
            echiquierBis[x][i].setTypeOccupation(this.menacee); // modification de la ligne en mettant des cellules menacées
        }

        // modification des diagonales en mettant des cellules menacées
        for (int i = 0 ; i < this.taille ; i++) {
            for (int j = 0 ; j < this.taille ; j++) {
                if (i + j == x + y || i - j == x - y) {
                    echiquierBis[i][j].setTypeOccupation(this.menacee);
                }
            }
        }

        echiquierBis[x][y].setTypeOccupation(this.reine); // modification de la cellule en mettant une reine

        // On compte le nombre de menace sur l'échiquier temporaire
        for (int i = 0 ; i < this.taille ; i++) {
            for (int j = 0 ; j < this.taille ; j++) {
                if (echiquierBis[i][j].getTypeOccupation() == this.menacee) res++;
            }
        }

        res -= resTemp; // on soustrait le nombre de menace déja présente sur l'échiquier

        return res; // on retourne le nombre de menace
    }

    // Permet de compter le nombre de reine sur l'echiquier
    public int compterNbReine() {
        int res = 0;

        for (int i = 0 ; i < this.taille ; i++) {
            for (int j = 0 ; j < this.taille ; j++) {
                if (this.echiquier[i][j].getTypeOccupation() == this.reine) res++;
            }
        }

        return res;
    }

    // Permet de récupérer les coordonnées de toutes les reines
    public int[][] getCoordonneeReines() {
        int[][] res = new int[this.compterNbReine()][2];
        int compteur = 0;

        for (int i = 0 ; i < this.taille ; i++) {
            for (int j = 0 ; j < this.taille ; j++) {
                if (this.echiquier[i][j].getTypeOccupation() == this.reine) {
                    res[compteur][0] = i;
                    res[compteur][1] = j;
                    compteur++;
                }
            }
        }

        return res;
    }

    // Peremt de sqvoir si l'echiquier est rempli de menace ou non
    public boolean isFull() {
        boolean res = true;

        for (int i = 0 ; i < this.taille ; i++) {
            for (int j = 0 ; j < this.taille ; j++) {
                if (this.echiquier[i][j].getTypeOccupation() == this.libre) {
                    res = false;
                    break;
                }
            }
        }

        return res;
    }

}
