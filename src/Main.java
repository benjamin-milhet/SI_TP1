import base.Echiquier;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        algoEchiquierUn();
        algoEchiquierDeux();
    }

    public static void algoEchiquierUn() {
        Echiquier e = new Echiquier(8);
        System.out.println(e);
        e.placerReine(4, 2);
        System.out.println(e);


    }

    public static void algoEchiquierDeux() {
        Echiquier e = new Echiquier(8);
        for (int i = 0 ; i < 100 ; i++) {
            e.placerReine(e.meilleurPosition()[0], e.meilleurPosition()[1]);
        }
        System.out.println(e);


    }
}