import base.Echiquier;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        algoEchiquierUn();
    }

    public static void algoEchiquierUn() {
        Echiquier e = new Echiquier(8);
        System.out.println(e);
        System.out.println(e.meilleurPosition()[0]);
        System.out.println(e.meilleurPosition()[1]);
        System.out.println(e);

        for (int i = 0 ; i < 50 ; i++) {
            e.placerReine(e.meilleurPosition()[0], e.meilleurPosition()[1]);
        }
        System.out.println(e);

    }
}