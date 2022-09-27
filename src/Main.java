import base.Echiquier;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        algoEchiquierUn();
    }

    public static void algoEchiquierUn() {
        Echiquier e = new Echiquier(8);
        System.out.println(e);
        e.placerReine(4, 2);
        System.out.println(e);
    }
}