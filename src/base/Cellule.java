package base;

public class Cellule {

    private int x;
    private int y;

    private char typeOccupation; // libre, reine, menacee par une reine

    public Cellule(int x, int y, char typeOccupation) {
        this.x = x;
        this.y = y;
        this.typeOccupation = typeOccupation;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getTypeOccupation() {
        return typeOccupation;
    }

    public void setTypeOccupation(char typeOccupation) {
        this.typeOccupation = typeOccupation;
    }
}
