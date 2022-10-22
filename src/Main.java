import base.Echiquier;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        algoEchiquierUn();
        algoEchiquierDeux();
    }

    // Exercice 1 - Créer l'échiquier et placer une reine
    public static void algoEchiquierUn() {
        Echiquier e = new Echiquier(8);
        System.out.println(e);
        e.placerReine(4, 2);
        System.out.println(e);


    }

    // Exercice 2 - Placer toutes les reiness
    public static void algoEchiquierDeux() {
        Echiquier e = new Echiquier(8);
        while (!e.isFull()) {
            e.placerReine(e.meilleurPosition()[0], e.meilleurPosition()[1]);
        }
        System.out.println(e);

        System.out.println("Il y a " + e.compterNbReine() + " Reine(s)");

        System.out.println("Coordonees des Reines : ");

        for (int i = 0 ; i < e.compterNbReine() ; i++) {
            System.out.println("(" + e.getCoordonneeReines()[i][0] + " , " + e.getCoordonneeReines()[i][1] + ")");
        }
    }
}